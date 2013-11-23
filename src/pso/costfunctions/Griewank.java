package pso.costfunctions;

import pso.swarm.Particle;

public class Griewank implements CostFunction {

	@Override
	public double compute(Particle particle) {
		double result = 1;
		double sum = 0;
		double prod = 1;
		for (int i = 0; i < particle.getDimensions(); i++) {
			sum += particle.getCurrentPos().get(i) * particle.getCurrentPos().get(i);
			prod *= Math.cos(particle.getCurrentPos().get(i) / Math.sqrt(i + 1));
		}

		result += sum / 4000 - prod;
		return result;
	}

}
