package pso.swarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Particle {

	private List<Double> pBest;
	private List<Double> currentPos;
	private List<Double> speed;
	private double pBestValue;
	
	public Particle(int size) {
		Random r = new Random();
		pBest = new ArrayList<Double>(size);
		currentPos = new ArrayList<Double>(size);
		speed = new ArrayList<Double>(size);
		pBestValue = Double.MAX_VALUE;
		
		for (int i = 0; i < size; i++) {
			double coeff = r.nextDouble();
			pBest.add(coeff);
			currentPos.add(coeff);
			speed.add(0.0);
		}
	}
	
	public Particle(Particle parent) {
		super();
		this.pBest = new ArrayList<Double>(parent.pBest);
		this.currentPos = new ArrayList<Double>(parent.currentPos);
		this.speed = new ArrayList<Double>(parent.speed);
		this.pBestValue = parent.pBestValue;
	}

	public List<Double> getpBest() {
		return pBest;
	}
	
	public void setPBest(List<Double> pBest) {
		this.pBest = pBest;
	}
	
	public List<Double> getCurrentPos() {
		return currentPos;
	}
	
	public List<Double> getSpeed() {
		return speed;
	}
	
	public void setSpeed(List<Double> speed) {
		this.speed = speed;
	}
	
	public double getPBestValue() {
		return pBestValue;
	}
	
	public int getDimensions() {
		return currentPos.size();
	}
	
	public void setNewBest(List<Double> bestPos, double bestVal) {
		pBest = bestPos;
		pBestValue = bestVal;
	}
	
	@Override
	public String toString() {
		return "Particle [value=" + pBestValue + ", currentPos=" + currentPos + ", speed=" + speed + "]";
	}
	
}
