package pso;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pso.costfunctions.CostFunction;
import pso.costfunctions.Griewank;
import pso.movements.Movement;
import pso.movements.SimpleMovement;
import pso.psoalgorithms.Pso;
import pso.psoalgorithms.SequentialPso;
import pso.swarm.Particle;
import pso.swarm.PsoResult;
import pso.swarm.SwarmGenerator;

public class Main {

	private static final double INERTIA = 0.4;
	private static final double PERSONAL_WEIGHT = 2.0;
	private static final double GLOBAL_WEIGHT = 2.0;
	private static final int REPEAT_COMPUTATION = 5;
	
	private static final List<Integer> DIMENSIONS = Arrays.asList(1, 2, 3, 5, 8, 13, 21, 34, 50);
	private static final List<Integer> PARTICLE_COUNT = Arrays.asList(2, 3, 5, 8, 13, 21, 34, 50);

	public static void main(String[] args) throws IOException {
		CostFunction costFunction = new Griewank();
		
		for (int dim : DIMENSIONS) {
			Movement movement = new SimpleMovement(dim, INERTIA,
					PERSONAL_WEIGHT, GLOBAL_WEIGHT);
			List<String> outputRows = new ArrayList<String>();
			for (int particleCount : PARTICLE_COUNT) {
				List<PsoResult> results = new ArrayList<PsoResult>();
				for (int i = 0; i < REPEAT_COMPUTATION; i++) {
					System.out.println("\n\nIteration " + i + " dim: " + dim + " part: " + particleCount);
					List<Particle> particles = SwarmGenerator.generateSwarm(costFunction, particleCount, dim);
					Pso pso = new SequentialPso();
					PsoResult result = pso.run(costFunction, movement, particles, (int)1e5, 1e-6);
					results.add(result);
					System.out.println(result);
				}
				outputRows.add(logAsTexTableRow(results, particleCount));
			}
			logAsTexTable(outputRows, dim);
		}
	}
	
	private static String logAsTexTableRow(List<PsoResult> results, int particleCount) {
		int iterMin = Integer.MAX_VALUE;
		int iterMax = -Integer.MAX_VALUE;
		
		double funMin = Double.MAX_VALUE;
		double funMax = -Double.MAX_VALUE;
		double funAvg = 0.0;
		
		for (PsoResult result : results) {
			int count = result.getCount();
			iterMin = Math.min(iterMin, count);
			iterMax = Math.max(iterMax, count);
			
			double bestValue = result.getParticle().getPBestValue();
			funMin = Math.min(funMin, bestValue);
			funMax = Math.max(funMax, bestValue);
			
			funAvg += bestValue;
		}
		funAvg /= results.size();
		
		return particleCount + " & " + iterMin + " \\newline " + iterMax + " & " + funMin + " \\newline " + funAvg + " \\newline " + funMax + " \\\\ \\hline";
	}
	
	private static void logAsTexTable(List<String> outputRows, int dim) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/tmp/results_" + dim)));
		for (String s : outputRows) {
			bw.write(s + "\n");
		}
		bw.flush();
		bw.close();
	}


}
