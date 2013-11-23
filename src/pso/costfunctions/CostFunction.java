package pso.costfunctions;

import pso.swarm.Particle;

public interface CostFunction {

	double compute(Particle particle);
	
}
