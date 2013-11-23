package pso.psoalgorithms;

import java.util.List;

import pso.costfunctions.CostFunction;
import pso.movements.Movement;
import pso.swarm.Particle;

public interface Pso {

	public Particle run(CostFunction costFunction, Movement movement,
			List<Particle> particles, int iterations, double precision);

}