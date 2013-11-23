package pso.movements;

import java.util.Random;

import pso.swarm.Particle;

public class SimpleMovement implements Movement {

	private int dimension;
	private double inertia;
	private double pWeight;
	private double gWeight;
	private Random random = new Random();

	public SimpleMovement(int dimension, double inertia, double pWeight,
			double gWeight) {
		super();
		this.dimension = dimension;
		this.inertia = inertia;
		this.pWeight = pWeight;
		this.gWeight = gWeight;
	}

	@Override
	public Particle move(Particle particle, Particle gBest) {
		double r1 = random.nextDouble();
		double r2 = random.nextDouble();
		for (int i = 0; i < dimension; i++) {
			double oldSpeed = particle.getSpeed().get(i);
			double diffToGlobal = gBest.getCurrentPos().get(i)
					- particle.getCurrentPos().get(i);
			double diffToPersonal = particle.getpBest().get(i)
					- particle.getCurrentPos().get(i);
			double newSpeed = inertia * oldSpeed + pWeight * r1
					* diffToPersonal + gWeight * r2 * diffToGlobal;

			particle.getSpeed().set(i, newSpeed);
			particle.getCurrentPos().set(i,
					particle.getCurrentPos().get(i) + newSpeed);
		}
		return particle;
	}

}
