package pso;

import java.util.List;

import pso.costfunctions.CostFunction;
import pso.costfunctions.Griewank;
import pso.movements.Movement;
import pso.movements.SimpleMovement;
import pso.psoalgorithms.Pso;
import pso.psoalgorithms.SequentialPso;
import pso.swarm.Particle;
import pso.swarm.SwarmGenerator;

public class Main {

	private static final int PARTICLES = 10;
	private static final int DIMENSIONS = 5;
	private static final double INERTIA = 0.4;
	private static final double PERSONAL_WEIGHT = 2.0;
	private static final double GLOBAL_WEIGHT = 2.0;

	public static void main(String[] args) {
		CostFunction costFunction = new Griewank();
		Movement movement = new SimpleMovement(DIMENSIONS, INERTIA,
				PERSONAL_WEIGHT, GLOBAL_WEIGHT);

		List<Particle> particles = SwarmGenerator.generateSwarm(costFunction, PARTICLES, DIMENSIONS);
		
		Pso pso = new SequentialPso();
		System.out.println("START");
		System.out.println(pso.run(costFunction, movement, particles, (int)1e6, 1e-2));
	}

}
