"use strict";

const fs = require("fs");
const path = require("path");
const { measureAll } = require("./lib/measure");

function arg(name, fallback) {
  const index = process.argv.indexOf(name);
  if (index === -1 || index + 1 >= process.argv.length) return fallback;
  return process.argv[index + 1];
}

async function main() {
  if (process.argv.includes("--help")) {
    console.log("Usage: node scripts/measure-tables.js [--out <path>]");
    return;
  }
  const output = path.resolve(process.cwd(), arg("--out", "scripts/results/measurements.json"));
  const measurements = await measureAll((step) => {
    process.stderr.write(`[measure] ${step}\n`);
  });
  fs.mkdirSync(path.dirname(output), { recursive: true });
  fs.writeFileSync(output, JSON.stringify(measurements, null, 2) + "\n");

  process.stdout.write(`Wrote ${output}\n`);
  process.stdout.write(
    JSON.stringify(
      {
        generatedAt: measurements.generatedAt,
        environment: measurements.environment,
        tables: measurements.tables,
      },
      null,
      2
    ) + "\n"
  );
}

main()
  .then(() => process.exit(0))
  .catch((error) => {
    console.error(error);
    process.exit(1);
  });
