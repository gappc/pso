package pso.swarm;

public class PsoResult {

	private Particle particle;
	private int count;
	
	public PsoResult(Particle particle, int count) {
		super();
		this.particle = particle;
		this.count = count;
	}
	
	public Particle getParticle() {
		return particle;
	}
	public void setParticle(Particle particle) {
		this.particle = particle;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "PsoResult [particle=" + particle + ", count=" + count + "]";
	}
	
}
