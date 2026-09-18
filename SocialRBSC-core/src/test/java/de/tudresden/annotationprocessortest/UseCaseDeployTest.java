package de.tudresden.annotationprocessortest;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.tools.JavaFileObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;

import de.tudresden.multiagentsystem.annotationprocessor.DeployProcessor;
import de.tudresden.multiagentsystem.util.Util;

/**
 * Verifies that annotating a class representing the supply‑chain use case with
 * {@code @Deploy} produces a web3j deployer containing the expected stubs.
 */
public class UseCaseDeployTest {

    @Test
    public void generateSupplyChainDeployer() throws IOException {
        String source = """
            package de.tudresden.usecasesupplychain;

            import de.tudresden.socialrbscdeterministic.mas.annotation.Deploy;

            @Deploy
            public class SupplyChainUseCase {
                public void createRetailer() {}
                public void createWholesaler() {}
                public void assignCoordinator() {}
            }
            """;

        JavaFileObject src = JavaFileObjects.forSourceString("de.tudresden.usecasesupplychain.SupplyChainUseCase", source);
        Compilation compilation = Compiler.javac().withProcessors(new DeployProcessor()).compile(src);
        assertEquals("SUCCESS", compilation.status().toString());

        Path runner = Paths.get("generatedsmartcontract", "runner", "SupplyChainUseCaseDeployer.java");
        String content = Util.readACompiledFile(runner.toString());
        assertNotNull(content);
        assertTrue(content.contains("Web3j"));
        assertTrue(content.contains("SupplyChainUseCaseDeployer"));
        // ensure stub lines for declared methods
        assertTrue(content.contains("createRetailer"));
        assertTrue(content.contains("createWholesaler"));
        assertTrue(content.contains("assignCoordinator"));

        JavaFileObject runtime = compilation.generatedSourceFile(
                "de.tudresden.usecasesupplychain.runtime.SupplyChainUseCaseSolidityRuntime")
                .orElse(null);
        assertNotNull(runtime);
        assertTrue(runtime.getCharContent(true).toString().contains("class SupplyChainUseCaseSolidityRuntime"));
    }
}
