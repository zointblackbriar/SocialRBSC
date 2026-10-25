"use strict";

const fs = require("fs");
const path = require("path");
const { measureAll } = require("./lib/measure");
const { TABLE6, TABLE7, TABLE8, TABLE9, TABLE10, TABLE11 } = require("./lib/table-data");

const checks = [];

function arg(name, fallback) {
  const index = process.argv.indexOf(name);
  if (index === -1 || index + 1 >= process.argv.length) return fallback;
  return process.argv[index + 1];
}

function hasFlag(name) {
  return process.argv.includes(name);
}

function sum(values) {
  return values.reduce((total, value) => total + value, 0);
}

function record(check) {
  const entry = {
    table: check.table,
    name: check.name,
    expected: check.expected === undefined ? null : check.expected,
    measured: check.measured === undefined ? null : check.measured,
    delta: check.delta === undefined ? null : check.delta,
    tolerance: check.tolerance === undefined ? null : check.tolerance,
    unit: check.unit || "",
    status: check.status || "INFO",
    note: check.note || "",
  };
  checks.push(entry);
  return entry;
}

function compare(table, name, expected, measured, options = {}) {
  const tolerance = options.tolerance === undefined ? 0 : options.tolerance;
  const unit = options.unit || "gas";
  const note = options.note || "";
  if (measured === null || measured === undefined || !Number.isFinite(Number(measured))) {
    return record({ table, name, expected, measured, tolerance, unit, status: "SKIP", note });
  }
  const delta = Number(measured) - Number(expected);
  const pass = Math.abs(delta) <= tolerance + 1e-9;
  return record({
    table,
    name,
    expected,
    measured,
    delta,
    tolerance,
    unit,
    status: pass ? "PASS" : "FAIL",
    note,
  });
}

function linearR2(xs, ys) {
  const n = xs.length;
  const meanX = sum(xs) / n;
  const meanY = sum(ys) / n;
  let sxx = 0;
  let sxy = 0;
  let syy = 0;
  for (let i = 0; i < n; i += 1) {
    const dx = xs[i] - meanX;
    const dy = ys[i] - meanY;
    sxx += dx * dx;
    sxy += dx * dy;
    syy += dy * dy;
  }
  const slope = sxy / sxx;
  const intercept = meanY - slope * meanX;
  let residual = 0;
  for (let i = 0; i < n; i += 1) {
    const predicted = intercept + slope * xs[i];
    residual += (ys[i] - predicted) ** 2;
  }
  return 1 - residual / syy;
}

function parseGasReport(file) {
  const map = new Map();
  if (!fs.existsSync(file)) return map;
  const lines = fs.readFileSync(file, "utf8").split(/\r?\n/);
  for (const line of lines) {
    const trimmed = line.trim();
    if (!trimmed || trimmed.startsWith("#")) continue;
    const parts = trimmed.split("|").map((part) => part.trim());
    if (parts.length < 5) continue;
    const [name, minRaw, maxRaw, avgRaw, limitRaw] = parts;
    const parse = (value) => {
      if (!value || value === "-") return null;
      const numeric = Number(value.replace(/[^0-9.]/g, ""));
      return Number.isFinite(numeric) ? numeric : null;
    };
    const entry = {
      min: parse(minRaw),
      max: parse(maxRaw),
      avg: parse(avgRaw),
      limitPct: parse(limitRaw),
    };
    if (entry.min === null && entry.max === null && entry.avg === null) continue;
    map.set(name, entry);
  }
  return map;
}

function normalizeModuleReport(report) {
  const modules = new Map();
  if (!report || typeof report !== "object") return modules;
  const source = Array.isArray(report.modules)
    ? report.modules
    : report.modules && typeof report.modules === "object"
      ? Object.entries(report.modules).map(([name, value]) => ({ module: name, ...value }))
      : Object.entries(report).map(([name, value]) => ({ module: name, ...value }));
  for (const entry of source) {
    if (!entry || typeof entry !== "object") continue;
    const name = entry.module || entry.name;
    if (!name) continue;
    modules.set(name, entry);
  }
  return modules;
}

function validateTable6(fixture, moduleReport) {
  const grandTotal = TABLE6.reportedTotal.total;
  const byName = Object.fromEntries(TABLE6.rows.map((row) => [row.module, row]));

  for (const row of TABLE6.rows) {
    compare(6, `${row.module}: deployment + execution = total`, row.total, row.deployment + row.execution);
    compare(6, `${row.module}: total / grand total share`, row.sharePct, (row.total / grandTotal) * 100, {
      tolerance: 0.005,
      unit: "%",
    });
  }

  compare(6, "deployment column sum", TABLE6.reportedTotal.deployment, sum(TABLE6.rows.map((row) => row.deployment)));
  compare(6, "execution column sum", TABLE6.reportedTotal.execution, sum(TABLE6.rows.map((row) => row.execution)));
  compare(6, "total column sum", TABLE6.reportedTotal.total, sum(TABLE6.rows.map((row) => row.total)));
  compare(6, "reported shares sum", 100, sum(TABLE6.rows.map((row) => row.sharePct)), {
    tolerance: 0.011,
    unit: "%",
  });

  const reasoningTotal = byName.GoalPlanTree.total + byName.SocialAgentDeliberationCycle.total;
  compare(6, "reasoning modules share (text: 80.1%)", TABLE6.derivedClaims.reasoningSharePct, (reasoningTotal / grandTotal) * 100, {
    tolerance: 0.05,
    unit: "%",
  });
  compare(6, "DeepRolePlayer share (text: 6.9%)", TABLE6.derivedClaims.deepRolePlayerSharePct, (byName.DeepRolePlayer.total / grandTotal) * 100, {
    tolerance: 0.05,
    unit: "%",
  });

  if (fixture.size === 0) {
    record({
      table: 6,
      name: "upstream deployment gas report loaded",
      status: "SKIP",
      note: "fixture missing or empty",
    });
  } else {
    const missingModules = TABLE6.rows.filter((row) => !fixture.has(row.module)).map((row) => row.module);
    record({
      table: 6,
      name: "gas report covers all five modules",
      expected: TABLE6.rows.length,
      measured: TABLE6.rows.length - missingModules.length,
      status: missingModules.length === 0 ? "PASS" : "FAIL",
      note: missingModules.length === 0 ? "" : `missing: ${missingModules.join(", ")}`,
    });
    for (const row of TABLE6.rows) {
      const reported = fixture.get(row.module);
      if (!reported) {
        record({
          table: 6,
          name: `${row.module}: deployment attested by gas report`,
          status: "SKIP",
          note: "module absent from fixture",
        });
        continue;
      }
      const candidates = [reported.min, reported.max, reported.avg].filter((value) => value !== null);
      if (reported.min !== null && reported.max !== null && reported.avg !== null) {
        record({
          table: 6,
          name: `${row.module}: gas report statistic ordering`,
          expected: "min <= avg <= max",
          measured: `${reported.min} <= ${reported.avg} <= ${reported.max}`,
          status:
            reported.min <= reported.avg && reported.avg <= reported.max ? "PASS" : "FAIL",
        });
      }
      const matched = candidates.includes(row.deployment);
      const statistic =
        row.deployment === reported.max
          ? "max"
          : row.deployment === reported.min
            ? "min"
            : row.deployment === reported.avg
              ? "avg"
              : null;
      record({
        table: 6,
        name: `${row.module}: deployment attested by gas report`,
        expected: candidates.join(" / "),
        measured: row.deployment,
        status: matched ? "PASS" : "FAIL",
        note: matched ? `matches report ${statistic}` : `not one of min/max/avg (${candidates.join(", ")})`,
      });
    }
  }

  if (moduleReport) {
    const modules = normalizeModuleReport(moduleReport);
    for (const row of TABLE6.rows) {
      const measured = modules.get(row.module);
      if (!measured) {
        record({
          table: 6,
          name: `${row.module}: independently re-measured`,
          status: "SKIP",
          note: "module absent from module report",
        });
        continue;
      }
      const deployment = measured.deployment === undefined ? null : measured.deployment;
      const execution = measured.execution === undefined ? null : measured.execution;
      if (deployment !== null) {
        compare(6, `${row.module}: deployment re-measured`, row.deployment, deployment);
      }
      if (execution !== null) {
        compare(6, `${row.module}: execution re-measured`, row.execution, execution);
      }
      if (deployment !== null && execution !== null) {
        compare(6, `${row.module}: total re-measured`, row.total, deployment + execution);
      }
    }
  } else {
    record({
      table: 6,
      name: "execution column independently re-measured",
      status: "SKIP",
      note:
        "the five module contracts are not in this extraction; run the full framework benchmark and pass --module-report <json>",
    });
  }
}

function validateTable7(measured) {
  const binding = TABLE7.rows[0];
  const lifecycle = TABLE7.rows[1];

  compare(7, "Role binding: hand-written", binding.handWritten, measured.roleBinding.baseline);
  compare(7, "Role binding: SocialRbSC", binding.socialrbsc, measured.roleBinding.socialrbsc);
  compare(7, "Role binding: overhead", binding.overheadPct, measured.roleBinding.overheadPct, {
    tolerance: 0.1,
    unit: "%",
  });

  compare(7, "Role lifecycle: hand-written", lifecycle.handWritten, measured.lifecycle.baseline);
  compare(7, "Role lifecycle: SocialRbSC", lifecycle.socialrbsc, measured.lifecycle.socialrbsc);
  compare(7, "Role lifecycle: overhead", lifecycle.overheadPct, measured.lifecycle.overheadPct, {
    tolerance: 0.1,
    unit: "%",
  });

  compare(7, "Role-registry deployment", TABLE7.rows[2].handWritten, measured.deployments.roleRegistry);
  compare(7, "Compartment deployment", TABLE7.rows[3].socialrbsc, measured.deployments.compartment);
  compare(7, "Role-behaviour deployment", TABLE7.rows[4].socialrbsc, measured.deployments.roleBehaviour);

  const roundedLifecycle = Math.round(measured.lifecycle.overheadPct * 10) / 10;
  if (Math.abs(roundedLifecycle - lifecycle.overheadPct) > 1e-9) {
    record({
      table: 7,
      name: "Role lifecycle: reported overhead is correctly rounded",
      expected: lifecycle.overheadPct,
      measured: roundedLifecycle,
      delta: roundedLifecycle - lifecycle.overheadPct,
      unit: "%",
      status: "WARN",
      note: `raw overhead ${measured.lifecycle.overheadPct.toFixed(3)}%; nearest tenth is ${roundedLifecycle.toFixed(1)}%`,
    });
  }
}

function validateTable8(measured) {
  const expected = TABLE8.phases;
  compare(8, "Deployment (compartment + mediator + role)", expected.deployment, measured.deployment);
  compare(8, "Bind DeliveryProvider role", expected.bindProvider, measured.bindProvider);
  compare(8, "Bind Validator role", expected.bindValidator, measured.bindValidator);
  compare(8, "Mediator agent registration", expected.mediatorRegistration, measured.mediatorRegistration);
  compare(8, "Status transition to IN_TRANSIT", expected.inTransit, measured.inTransit);
  compare(8, "Status transition to DELIVERED", expected.delivered, measured.delivered);
  compare(8, "Validator confirmation", expected.confirmation, measured.confirmation);
  compare(8, "Operations subtotal", expected.operationsSubtotal, measured.operationsSubtotal);
  compare(8, "End-to-end total", expected.total, measured.total);

  const expectedOperations = sum([
    expected.bindProvider,
    expected.bindValidator,
    expected.mediatorRegistration,
    expected.inTransit,
    expected.delivered,
    expected.confirmation,
  ]);
  const measuredOperations = sum([
    measured.bindProvider,
    measured.bindValidator,
    measured.mediatorRegistration,
    measured.inTransit,
    measured.delivered,
    measured.confirmation,
  ]);
  compare(8, "Paper operations subtotal internally consistent", expected.operationsSubtotal, expectedOperations);
  compare(8, "Paper end-to-end total internally consistent", expected.total, expected.deployment + expected.operationsSubtotal);
  compare(8, "Measured operations subtotal internally consistent", measured.operationsSubtotal, measuredOperations);
  compare(8, "Measured end-to-end total internally consistent", measured.total, measured.deployment + measured.operationsSubtotal);
}

function validateTable9(measured) {
  const measuredByN = new Map(measured.map((row) => [row.N, row]));

  for (const row of TABLE9.rows) {
    const actual = measuredByN.get(row.N);
    if (!actual) {
      record({ table: 9, name: `N=${row.N}: measurement present`, status: "SKIP" });
      continue;
    }
    compare(9, `N=${row.N}: total binding gas`, row.bindingTotal, actual.bindingTotal, { tolerance: 100 });
    compare(9, `N=${row.N}: gas per binding`, row.gasPerBinding, actual.gasPerBinding, { tolerance: 5 });
    compare(9, `N=${row.N}: paper average recomputed`, row.gasPerBinding, row.bindingTotal / row.N, { tolerance: 0.5, unit: "gas" });
    compare(9, `N=${row.N}: measured average recomputed`, actual.gasPerBinding, actual.bindingTotal / row.N, { tolerance: 0.5, unit: "gas" });
  }

  for (let i = 1; i < TABLE9.rows.length; i += 1) {
    const previous = TABLE9.rows[i - 1];
    const current = TABLE9.rows[i];
    record({
      table: 9,
      name: `Paper total binding cost grows from N=${previous.N} to N=${current.N}`,
      expected: "strictly increasing",
      measured: `${previous.bindingTotal} -> ${current.bindingTotal}`,
      status: current.bindingTotal > previous.bindingTotal ? "PASS" : "FAIL",
    });
  }

  const r2 = linearR2(
    TABLE9.rows.map((row) => row.N),
    TABLE9.rows.map((row) => row.bindingTotal)
  );
  record({
    table: 9,
    name: "Paper aggregate binding cost is linear in N (R²)",
    expected: 1,
    measured: r2,
    delta: r2 - 1,
    tolerance: 0.01,
    unit: "",
    status: r2 >= 0.99 ? "PASS" : "FAIL",
  });

  const perBinding = TABLE9.rows.map((row) => row.gasPerBinding);
  const tail = TABLE9.rows.filter((row) => row.N >= 5).map((row) => row.gasPerBinding);
  record({
    table: 9,
    name: "Paper per-binding cost stabilises for N >= 5",
    expected: "max - min < 5000",
    measured: Math.max(...tail) - Math.min(...tail),
    status: Math.max(...tail) - Math.min(...tail) < 5000 ? "PASS" : "FAIL",
  });
  record({
    table: 9,
    name: "Paper per-binding cost stays in the O(1) band",
    expected: "140000 <= gas <= 170000",
    measured: `${Math.min(...perBinding)} / ${Math.max(...perBinding)}`,
    status: Math.min(...perBinding) >= 140000 && Math.max(...perBinding) <= 170000 ? "PASS" : "FAIL",
  });
}

function validateTable10(measured) {
  const measuredByA = new Map(measured.map((row) => [row.A, row]));
  const deployment = TABLE7.rows[3].socialrbsc;

  for (const row of TABLE10.rows) {
    const actual = measuredByA.get(row.A);
    if (!actual) {
      record({ table: 10, name: `A=${row.A}: measurement present`, status: "SKIP" });
      continue;
    }
    compare(10, `A=${row.A}: agents`, row.agents, actual.agents);
    compare(10, `A=${row.A}: binding gas`, row.bindings, actual.bindings, { tolerance: 100 });
    compare(10, `A=${row.A}: cycle gas`, row.cycles, actual.cycles, { tolerance: 100 });
    compare(10, `A=${row.A}: total gas`, row.total, actual.total, { tolerance: 200 });
    compare(10, `A=${row.A}: per-provider cost`, row.perAgent, actual.perAgent, { tolerance: 10 });
    compare(10, `A=${row.A}: paper total recomputed`, row.total, deployment + row.bindings + row.cycles);
    compare(10, `A=${row.A}: paper per-provider recomputed`, row.perAgent, (row.bindings + row.cycles) / row.A, {
      tolerance: 0.6,
    });
    compare(10, `A=${row.A}: measured total recomputed`, actual.total, deployment + actual.bindings + actual.cycles);
    compare(10, `A=${row.A}: measured per-provider recomputed`, actual.perAgent, (actual.bindings + actual.cycles) / row.A, {
      tolerance: 0.6,
    });
  }

  for (let i = 1; i < TABLE10.rows.length; i += 1) {
    const previous = TABLE10.rows[i - 1];
    const current = TABLE10.rows[i];
    record({
      table: 10,
      name: `Paper per-provider cost decreases from A=${previous.A} to A=${current.A}`,
      expected: "strictly decreasing",
      measured: `${previous.perAgent} -> ${current.perAgent}`,
      status: current.perAgent < previous.perAgent ? "PASS" : "FAIL",
    });
  }
}

function validateTable11(measured) {
  const measuredByT = new Map(measured.map((row) => [row.T, row]));

  for (const row of TABLE11.rows) {
    const actual = measuredByT.get(row.T);
    if (!actual) {
      record({ table: 11, name: `T=${row.T}: measurement present`, status: "SKIP" });
      continue;
    }
    compare(11, `T=${row.T}: insert total`, row.insertTotal, actual.insertTotal);
    compare(11, `T=${row.T}: insert per node`, row.insertPerNode, actual.insertPerNode);
    compare(11, `T=${row.T}: traverse`, row.traverse, actual.traverse);
    compare(11, `T=${row.T}: traverse per node`, row.traversePerNode, actual.traversePerNode);
    compare(11, `T=${row.T}: paper insert per node recomputed`, row.insertPerNode, row.insertTotal / row.T, {
      tolerance: 0.5,
    });
    compare(11, `T=${row.T}: paper traverse per node recomputed`, row.traversePerNode, row.traverse / row.T, {
      tolerance: 0.5,
    });
    compare(11, `T=${row.T}: measured insert per node recomputed`, actual.insertPerNode, actual.insertTotal / row.T, {
      tolerance: 0.5,
    });
    compare(11, `T=${row.T}: measured traverse per node recomputed`, actual.traversePerNode, actual.traverse / row.T, {
      tolerance: 0.5,
    });
  }

  const insertPerNode = TABLE11.rows.map((row) => row.insertPerNode);
  record({
    table: 11,
    name: "Paper insertion cost per node is constant",
    expected: "max - min < 2000",
    measured: Math.max(...insertPerNode) - Math.min(...insertPerNode),
    status: Math.max(...insertPerNode) - Math.min(...insertPerNode) < 2000 ? "PASS" : "FAIL",
  });

  const traversePerNode = TABLE11.rows.map((row) => row.traversePerNode);
  record({
    table: 11,
    name: "Paper traversal cost per node decreases with tree size",
    expected: "strictly decreasing",
    measured: traversePerNode.join(" -> "),
    status: traversePerNode.every((value, index) => index === 0 || value < traversePerNode[index - 1]) ? "PASS" : "FAIL",
  });

  const insertR2 = linearR2(
    TABLE11.rows.map((row) => row.T),
    TABLE11.rows.map((row) => row.insertTotal)
  );
  record({
    table: 11,
    name: "Paper insertion total is linear in T (R²)",
    expected: 1,
    measured: insertR2,
    delta: insertR2 - 1,
    tolerance: 0.0001,
    unit: "",
    status: insertR2 >= 0.9999 ? "PASS" : "FAIL",
  });

  const traverseR2 = linearR2(
    TABLE11.rows.map((row) => row.T),
    TABLE11.rows.map((row) => row.traverse)
  );
  record({
    table: 11,
    name: "Paper traversal total is linear in T (R²)",
    expected: 1,
    measured: traverseR2,
    delta: traverseR2 - 1,
    tolerance: 0.001,
    unit: "",
    status: traverseR2 >= 0.999 ? "PASS" : "FAIL",
  });
}

function formatNumber(value, unit) {
  if (value === null || value === undefined) return "-";
  if (unit === "%") return `${Number(value).toFixed(3)}%`;
  if (typeof value === "number") {
    if (Number.isInteger(value)) return value.toLocaleString("en-US");
    return value.toLocaleString("en-US", { maximumFractionDigits: 4 });
  }
  return String(value);
}

function printReport() {
  const titles = {
    6: TABLE6.title,
    7: TABLE7.title,
    8: TABLE8.title,
    9: TABLE9.title,
    10: TABLE10.title,
    11: TABLE11.title,
  };
  for (const table of [6, 7, 8, 9, 10, 11]) {
    const entries = checks.filter((check) => check.table === table);
    if (entries.length === 0) continue;
    const tableChecks = entries.filter((entry) => ["PASS", "FAIL"].includes(entry.status));
    const failures = entries.filter((entry) => entry.status === "FAIL").length;
    const warnings = entries.filter((entry) => entry.status === "WARN").length;
    const skips = entries.filter((entry) => entry.status === "SKIP").length;
    const status = failures > 0 ? "FAIL" : warnings > 0 || skips > 0 ? "PARTIAL" : "PASS";
    console.log(`\nTable ${table} - ${titles[table]}`);
    console.log(`Status: ${status} (${tableChecks.length - failures}/${tableChecks.length} comparable checks passed, ${warnings} warnings, ${skips} skipped)`);
    for (const entry of entries) {
      const expected = formatNumber(entry.expected, entry.unit);
      const measured = formatNumber(entry.measured, entry.unit);
      const delta =
        entry.delta === null || entry.delta === undefined
          ? ""
          : ` (delta ${formatNumber(entry.delta, entry.unit)})`;
      const note = entry.note ? ` - ${entry.note}` : "";
      console.log(`  [${entry.status}] ${entry.name}: expected ${expected}, measured ${measured}${delta}${note}`);
    }
  }

  const summary = {
    pass: checks.filter((check) => check.status === "PASS").length,
    fail: checks.filter((check) => check.status === "FAIL").length,
    warn: checks.filter((check) => check.status === "WARN").length,
    skip: checks.filter((check) => check.status === "SKIP").length,
  };
  console.log(
    `\nOverall: ${summary.pass} passed, ${summary.fail} failed, ${summary.warn} warnings, ${summary.skip} skipped`
  );
  return summary;
}

async function loadMeasurements(options) {
  const file = path.resolve(process.cwd(), options.measurementsPath);
  if (!hasFlag("--run") && fs.existsSync(file)) {
    return { measurements: JSON.parse(fs.readFileSync(file, "utf8")), file, fresh: false };
  }
  process.stderr.write("[validate] running benchmark measurements...\n");
  const measurements = await measureAll((step) => {
    process.stderr.write(`[validate] ${step}\n`);
  });
  fs.mkdirSync(path.dirname(file), { recursive: true });
  fs.writeFileSync(file, JSON.stringify(measurements, null, 2) + "\n");
  return { measurements, file, fresh: true };
}

async function main() {
  if (hasFlag("--help")) {
    console.log(`Usage: node scripts/validate-tables.js [options]`);
    console.log(`  --run                     re-run all measurements instead of using the cached JSON`);
    console.log(`  --measurements <path>     measurement JSON (default scripts/results/measurements.json)`);
    console.log(`  --module-report <path>    JSON with { modules: { name: { deployment, execution } } } for Table 6`);
    console.log(`  --table6-fixture <path>   deployment gas-report excerpt (default scripts/fixtures/gas-report-table6.txt)`);
    console.log(`  --report <path>           validation report JSON (default scripts/results/validation-report.json)`);
    console.log(`  --strict                  treat warnings and skipped checks as failures`);
    return 0;
  }

  const measurementsPath = arg("--measurements", "scripts/results/measurements.json");
  const fixturePath = path.resolve(process.cwd(), arg("--table6-fixture", "scripts/fixtures/gas-report-table6.txt"));
  const reportPath = path.resolve(process.cwd(), arg("--report", "scripts/results/validation-report.json"));
  const moduleReportPath = arg("--module-report", null);

  const { measurements, fresh } = await loadMeasurements({ measurementsPath });
  const moduleReport = moduleReportPath
    ? JSON.parse(fs.readFileSync(path.resolve(process.cwd(), moduleReportPath), "utf8"))
    : null;
  const fixture = parseGasReport(fixturePath);

  console.log(`Measurements: ${fresh ? "freshly measured" : measurementsPath}`);
  console.log(`Environment: solc ${measurements.environment.solc}, optimizer ${JSON.stringify(measurements.environment.optimizer)}, network ${measurements.environment.network}`);

  validateTable6(fixture, moduleReport);
  validateTable7(measurements.tables[7]);
  validateTable8(measurements.tables[8]);
  validateTable9(measurements.tables[9]);
  validateTable10(measurements.tables[10]);
  validateTable11(measurements.tables[11]);

  const summary = printReport();
  const report = {
    generatedAt: new Date().toISOString(),
    measurementsGeneratedAt: measurements.generatedAt,
    environment: measurements.environment,
    strict: hasFlag("--strict"),
    checks,
    summary,
  };
  fs.mkdirSync(path.dirname(reportPath), { recursive: true });
  fs.writeFileSync(reportPath, JSON.stringify(report, null, 2) + "\n");
  console.log(`\nWrote ${reportPath}`);

  const failed = summary.fail > 0 || (hasFlag("--strict") && (summary.warn > 0 || summary.skip > 0));
  return failed ? 1 : 0;
}

main()
  .then((code) => process.exit(code))
  .catch((error) => {
    console.error(error);
    process.exit(1);
  });
