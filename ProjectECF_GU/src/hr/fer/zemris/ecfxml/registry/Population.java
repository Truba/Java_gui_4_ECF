package hr.fer.zemris.ecfxml.registry;

import hr.fer.zemris.ecfxml.IRegistry;
import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class Population implements IRegistry {
	
	private Double randomizerSeed; //<!-- 0 uses time(NULL) (default: 0) -->
	private Double populationSize; //<!-- number of individuals (default: 100) -->
	private Double populationDemes; //<!-- number of demes (default: 1) -->
	private Double migrationFreq; //<!-- individuals are exchanged each 'freq' generations (default: none) -->
	private Double migrationNumber; //<!-- number of individuals to be sent to another deme (default: 1) -->

	public Population() {
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if (randomizerSeed != null)
			sb.append(Xmlhalper.entryTag("randomizer.seed", randomizerSeed)+"\n");
		if (populationSize != null)
			sb.append(Xmlhalper.entryTag("population.size", populationSize)+"\n");
		if (populationDemes != null)
			sb.append(Xmlhalper.entryTag("population.demes", populationDemes)+"\n");
		if (migrationFreq != null)
			sb.append(Xmlhalper.entryTag("migration.freq", migrationFreq)+"\n");
		if (migrationNumber != null)
			sb.append(Xmlhalper.entryTag("migration.number", migrationNumber)+"\n");
		
		return sb.toString();
	}

	public void setRandomizerSeed(double randomizerSeed) {
		this.randomizerSeed = randomizerSeed;
	}

	public void setPopulationSize(double populationSize) {
		this.populationSize = populationSize;
	}

	public void setPopulationDemes(double populationDemes) {
		this.populationDemes = populationDemes;
	}

	public void setMigrationFreq(double migrationFreq) {
		this.migrationFreq = migrationFreq;
	}

	public void setMigrationNumber(double migrationNumber) {
		this.migrationNumber = migrationNumber;
	}
	
	

}
