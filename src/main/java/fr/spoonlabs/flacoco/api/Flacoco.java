package fr.spoonlabs.flacoco.api;

import fr.spoonlabs.flacoco.core.config.FlacocoConfig;
import fr.spoonlabs.flacoco.localization.FaultLocalizationRunner;
import fr.spoonlabs.flacoco.localization.spectrum.SpectrumRunner;
import fr.spoonlabs.flacoco.utils.spoon.SpoonConverter;
import org.apache.log4j.Logger;
import spoon.reflect.code.CtStatement;

import java.util.Map;

/**
 * This class serves as the main entry point to Flacoco.
 *
 * @author andre15silva
 */
public class Flacoco implements FlacocoAPI {

	private Logger logger = Logger.getLogger(Flacoco.class);
	private FlacocoConfig config = FlacocoConfig.getInstance();

	public Flacoco() {
	}

	/**
	 * Default run method for Flacoco.
	 * @return Mapping between source code lines and suspiciousness scores
	 */
	@Override
	public Map<String, Suspiciousness> runDefault() {
		this.logger.info("Running Flacoco...");
		return getRunner().run();
	}

	/**
	 * Spoon mode for Flacoco
	 * @return Mapping between CtStatements representing a code lines and suspiciousness scores
	 */
	@Override
	public Map<CtStatement, Suspiciousness> runSpoon() {
		Map<String, Suspiciousness> defaultResults = runDefault();
		return SpoonConverter.convert(defaultResults);
	}

	/**
	 * Helper method to get the correct runner according to the configuration
	 * @return A FaultLocalizationRunner according to the fault localization family
	 */
	private FaultLocalizationRunner getRunner() {
		switch (this.config.getFamily()) {
			case SPECTRUM_BASED:
				return new SpectrumRunner();
		}
		return null;
	}

}
