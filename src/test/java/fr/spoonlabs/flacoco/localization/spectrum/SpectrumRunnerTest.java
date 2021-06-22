package fr.spoonlabs.flacoco.localization.spectrum;

import fr.spoonlabs.flacoco.core.config.FlacocoConfig;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SpectrumRunnerTest {

	@Before
	public void setUp() {
		LogManager.getRootLogger().setLevel(Level.DEBUG);

		FlacocoConfig config = FlacocoConfig.getInstance();
		String dep1 = new File("./examples/libs/junit-4.12.jar").getAbsolutePath();
		String dep2 = new File("./examples/libs/hamcrest-core-1.3.jar").getAbsolutePath();
		String dep3 = new File("./examples/libs/junit-jupiter-api-5.7.2.jar").getAbsolutePath();
		config.setClasspath(dep1 + File.pathSeparatorChar + dep2 + File.pathSeparatorChar + dep3);
	}

	@After
	public void tearDown() {
		FlacocoConfig.deleteInstance();
	}

	@Test
	public void testExampleFL1Ochiai() {
		// Setup config
		FlacocoConfig config = FlacocoConfig.getInstance();
		config.setProjectPath(new File("./examples/exampleFL1/FLtest1").getAbsolutePath());
		config.setSpectrumFormula(SpectrumFormula.OCHIAI);

		SpectrumRunner runner = new SpectrumRunner();

		Map<String, Double> susp = runner.run();

		for (String line : susp.keySet()) {
			System.out.println("susp " + line + " " + susp.get(line));
		}

		assertEquals(6, susp.size());

		// Line executed only by the failing
		assertEquals(1.0, susp.get("fr/spoonlabs/FLtest1/Calculator@-@15"), 0);

		// Line executed by a mix of failing and passing
		assertEquals(0.70, susp.get("fr/spoonlabs/FLtest1/Calculator@-@14"), 0.01);
		assertEquals(0.57, susp.get("fr/spoonlabs/FLtest1/Calculator@-@12"), 0.01);

		// Lines executed by all test
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@10"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@5"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@6"), 0);
	}

	@Test
	public void testExampleFL2Ochiai() {
		// Setup config
		FlacocoConfig config = FlacocoConfig.getInstance();
		config.setProjectPath(new File("./examples/exampleFL2/FLtest1").getAbsolutePath());
		config.setSpectrumFormula(SpectrumFormula.OCHIAI);

		SpectrumRunner runner = new SpectrumRunner();

		Map<String, Double> susp = runner.run();

		for (String line : susp.keySet()) {
			System.out.println("susp " + line + " " + susp.get(line));
		}

		assertEquals(7, susp.size());

		// Line executed only by the failing
		assertEquals(1.0, susp.get("fr/spoonlabs/FLtest1/Calculator@-@21"), 0);

		// Line executed by a mix of failing and passing
		assertEquals(0.70, susp.get("fr/spoonlabs/FLtest1/Calculator@-@18"), 0.01);
		assertEquals(0.57, susp.get("fr/spoonlabs/FLtest1/Calculator@-@16"), 0.01);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@14"), 0);

		// Lines executed by all test
		assertEquals(0.44, susp.get("fr/spoonlabs/FLtest1/Calculator@-@12"), 0.01);
		assertEquals(0.44, susp.get("fr/spoonlabs/FLtest1/Calculator@-@5"), 0.01);
		assertEquals(0.44, susp.get("fr/spoonlabs/FLtest1/Calculator@-@6"), 0.01);
	}

	@Test
	public void testExampleFL3Ochiai() {
		// Setup config
		FlacocoConfig config = FlacocoConfig.getInstance();
		config.setProjectPath(new File("./examples/exampleFL3/FLtest1").getAbsolutePath());
		config.setSpectrumFormula(SpectrumFormula.OCHIAI);

		SpectrumRunner runner = new SpectrumRunner();

		Map<String, Double> susp = runner.run();

		for (String line : susp.keySet()) {
			System.out.println("susp " + line + " " + susp.get(line));
		}

		assertEquals(7, susp.size());

		// Line executed only by the failing
		assertEquals(1.0, susp.get("fr/spoonlabs/FLtest1/Calculator@-@21"), 0);

		// Line executed by a mix of failing and passing
		assertEquals(0.70, susp.get("fr/spoonlabs/FLtest1/Calculator@-@18"), 0.01);
		assertEquals(0.57, susp.get("fr/spoonlabs/FLtest1/Calculator@-@16"), 0.01);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@14"), 0);

		// Lines executed by all test
		assertEquals(0.44, susp.get("fr/spoonlabs/FLtest1/Calculator@-@12"), 0.01);
		assertEquals(0.44, susp.get("fr/spoonlabs/FLtest1/Calculator@-@5"), 0.01);
		assertEquals(0.44, susp.get("fr/spoonlabs/FLtest1/Calculator@-@6"), 0.01);
	}

	@Test
	public void testExampleFL1OchiaiCoverTests() {
		// Setup config
		FlacocoConfig config = FlacocoConfig.getInstance();
		config.setProjectPath(new File("./examples/exampleFL1/FLtest1").getAbsolutePath());
		config.setSpectrumFormula(SpectrumFormula.OCHIAI);
		config.setCoverTests(true);

		SpectrumRunner runner = new SpectrumRunner();

		Map<String, Double> susp = runner.run();

		for (String line : susp.keySet()) {
			System.out.println("susp " + line + " " + susp.get(line));
		}

		assertEquals(8, susp.size());

		// Line executed only by the failing
		assertEquals(1.0, susp.get("fr/spoonlabs/FLtest1/Calculator@-@15"), 0);

		// Line executed by a mix of failing and passing
		assertEquals(0.70, susp.get("fr/spoonlabs/FLtest1/Calculator@-@14"), 0.01);
		assertEquals(0.57, susp.get("fr/spoonlabs/FLtest1/Calculator@-@12"), 0.01);

		// Lines executed by all test
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/CalculatorTest@-@9"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/CalculatorTest@-@7"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@10"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@5"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@6"), 0);
	}

	@Test
	public void testExampleFL4JUnit5Ochiai() {
		// Setup config
		FlacocoConfig config = FlacocoConfig.getInstance();
		config.setProjectPath(new File("./examples/exampleFL4/FLtest1").getAbsolutePath());
		config.setSpectrumFormula(SpectrumFormula.OCHIAI);
		config.setTestFramework(FlacocoConfig.TestFramework.JUNIT5);

		SpectrumRunner runner = new SpectrumRunner();

		Map<String, Double> susp = runner.run();

		for (String line : susp.keySet()) {
			System.out.println("susp " + line + " " + susp.get(line));
		}

		assertEquals(6, susp.size());

		// Line executed only by the failing
		assertEquals(1.0, susp.get("fr/spoonlabs/FLtest1/Calculator@-@15"), 0);

		// Line executed by a mix of failing and passing
		assertEquals(0.70, susp.get("fr/spoonlabs/FLtest1/Calculator@-@14"), 0.01);
		assertEquals(0.57, susp.get("fr/spoonlabs/FLtest1/Calculator@-@12"), 0.01);

		// Lines executed by all test
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@10"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@5"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@6"), 0);
	}

	@Test
	public void testExampleFL4JUnit5OchiaiCoverTests() {
		// Setup config
		FlacocoConfig config = FlacocoConfig.getInstance();
		config.setProjectPath(new File("./examples/exampleFL4/FLtest1").getAbsolutePath());
		config.setSpectrumFormula(SpectrumFormula.OCHIAI);
		config.setTestFramework(FlacocoConfig.TestFramework.JUNIT5);
		config.setCoverTests(true);

		SpectrumRunner runner = new SpectrumRunner();

		Map<String, Double> susp = runner.run();

		for (String line : susp.keySet()) {
			System.out.println("susp " + line + " " + susp.get(line));
		}

		assertEquals(8, susp.size());

		// Line executed only by the failing
		assertEquals(1.0, susp.get("fr/spoonlabs/FLtest1/Calculator@-@15"), 0);

		// Line executed by a mix of failing and passing
		assertEquals(0.70, susp.get("fr/spoonlabs/FLtest1/Calculator@-@14"), 0.01);
		assertEquals(0.57, susp.get("fr/spoonlabs/FLtest1/Calculator@-@12"), 0.01);

		// Lines executed by all test
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/CalculatorTest@-@9"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/CalculatorTest@-@7"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@10"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@5"), 0);
		assertEquals(0.5, susp.get("fr/spoonlabs/FLtest1/Calculator@-@6"), 0);
	}

}
