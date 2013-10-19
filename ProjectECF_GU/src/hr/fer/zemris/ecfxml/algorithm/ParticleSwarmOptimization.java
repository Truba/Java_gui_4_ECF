package hr.fer.zemris.ecfxml.algorithm;

import hr.fer.zemris.ecfxml.IAlgorithm;
import hr.fer.zemris.ecfxml.xmlhalpers.Xmlhalper;

public class ParticleSwarmOptimization implements IAlgorithm {
	
	private double weightTypeUpdate; // <!-- weight type update: 0 - constant, 1 - time dependant (based on max generations) -->
	private double initialInertiaWeight; //<!-- initial inertia weight (either constant or time dependant) -->
	private double maxParticleVelocity; //<!-- max particle velocity -->
	
	
	public ParticleSwarmOptimization(double weightTypeUpdate,
			double initialInertiaWeight, double maxParticleVelocity) {
		this.weightTypeUpdate = weightTypeUpdate;
		this.initialInertiaWeight = initialInertiaWeight;
		this.maxParticleVelocity = maxParticleVelocity;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ParticleSwarmOptimization>\n").
		append(Xmlhalper.entryTag("weightType", weightTypeUpdate)+"\n"). 
		append(Xmlhalper.entryTag("weight", initialInertiaWeight)+"\n"). 
		append(Xmlhalper.entryTag("maxVelocity", maxParticleVelocity)+"\n").
		append("</DifferentialEvolution>");
		return sb.toString();
	}

}
