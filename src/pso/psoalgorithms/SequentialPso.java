package pso.psoalgorithms;

import java.util.ArrayList;
import java.util.List;

import pso.costfunctions.CostFunction;
import pso.movements.Movement;
import pso.swarm.Particle;

public class SequentialPso implements Pso {

	public Particle run(CostFunction costFunction, Movement movement,
			List<Particle> particles, int iterations, double precision) {
		// store the gBest of the swarm
		Particle gBest = getGlobalBest(particles, particles.get(0));

		// while (!termination_criterion)
		int count = 0;
		boolean end = false;
		while (!end) {
			for (Particle particle : particles) {
				// update the speed of the particles
				// move the particles to the new position
				movement.move(particle, gBest);

				// update the pBest of each particle
				double particleVal = costFunction.compute(particle);
				if (particleVal < particle.getPBestValue()) {
					particle.setNewBest(
							new ArrayList<Double>(particle.getCurrentPos()),
							particleVal);
				}
			}

			count++;
			
			// update the gBest
			gBest = getGlobalBest(particles, gBest);

			if (gBest.getPBestValue() < precision) {
				end = true;
			}

			if (count % 1e4 == 0 || count < 10) {
				System.out.println(count + " " + gBest.getPBestValue() + " "
						+ gBest);
			}
			
			
			
			if (count == iterations) {
				end = true;
			}
		}

		// return gBest
		return gBest;
	}

	private Particle getGlobalBest(List<Particle> particles, Particle gBest) {
		for (Particle particle : particles) {
			if (particle.getPBestValue() < gBest.getPBestValue()) {
				gBest = particle;
			}
		}

		return new Particle(gBest);
	}
		
}
