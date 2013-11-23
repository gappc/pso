package pso.swarm;

import java.util.ArrayList;
import java.util.List;

import pso.costfunctions.CostFunction;

public class SwarmGenerator {
	
	public static List<Particle> generateSwarm(CostFunction costFunction,
			int particleCount, int dimensions) {
		// N particles generated at random
		// store the pBest of each particle
		// set the speed of each particle to 0
		
		List<Particle> result = new ArrayList<Particle>();

		for (int i = 0; i < particleCount; i++) {
			Particle particle = new Particle(dimensions);
			double particleVal = costFunction.compute(particle);

			particle.setNewBest(particle.getCurrentPos(), particleVal);
			result.add(particle);
		}
		return result;
	}
}
