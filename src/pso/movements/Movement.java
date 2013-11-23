package pso.movements;

import pso.swarm.Particle;


public interface Movement {

	public Particle move(Particle particle, Particle gBest);

}
