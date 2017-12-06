package it.unisa.minimization.study;

import it.unisa.prioritization.algorithm.AdditionalGreedyPrioritization;
import it.unisa.prioritization.criterion.GeneralizedAveragePercentage;
import it.unisa.prioritization.problems.GeneralizedPrioritizationProblem;
import it.unisa.prioritization.problems.MultiObjectiveGeneralizedPrioritizationProblem;
import it.unisa.prioritization.problems.SingleObjectiveGeneralizedPrioritizationProblem;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.singleobjective.geneticalgorithm.GeneticAlgorithmBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.PMXCrossover;
import org.uma.jmetal.operator.impl.mutation.PermutationSwapMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.runner.AbstractAlgorithmRunner;
import org.uma.jmetal.solution.PermutationSolution;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.evaluator.impl.MultithreadedSolutionListEvaluator;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExperimentRunner extends AbstractAlgorithmRunner {
    public static void main(String[] args){
        int numberOfCores = 4;
        int independentRuns = 10;
        String inputFolder = "/home/dardin88/Documenti/Unisa - phd/3 - Papers work in progress/"
                + "Hypervolume-based Search for Test Case - Estensione/systems/flex/";
        String outputFolder = "/home/dardin88/Documenti/Unisa - phd/3 - Papers work in progress/"
                + "Hypervolume-based Search for Test Case - Estensione/results_2/flex/";
        String objectives = "coverage_s";
        String algorithmName = "AdditionalGreedy";

        try {
            ExperimentRunner.run(inputFolder, outputFolder, objectives, algorithmName, numberOfCores, independentRuns);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void run(String inputPath, String outputFolder, String objectives, String algorithmName,
                            int numberOfCores, int independentRuns) throws IOException, InterruptedException {

        outputFolder += algorithmName + "/";

        List<String> coverageFilenames = new ArrayList<>();
        if (objectives.contains("coverage_s")) {
            coverageFilenames.add(inputPath + "comp_coverage_matrix_s");
        }
        if (objectives.contains("coverage_b")) {
            coverageFilenames.add(inputPath + "comp_coverage_matrix_b");
        }
        if (objectives.contains("coverage_f")) {
            coverageFilenames.add(inputPath + "comp_coverage_matrix_f");
        }
        if (objectives.contains("past_fault")) {
            coverageFilenames.add(inputPath + "past_fault_matrix");
        }
        if (objectives.contains("input_diversity")) {
            coverageFilenames.add(inputPath + "input_diversity");
        }

        Algorithm algorithm = null;

        int populationSize;
        int maxEvaluations;
        double mutationProbability;
        double crossoverProbability;

        CrossoverOperator<PermutationSolution<Integer>> crossover;
        MutationOperator<PermutationSolution<Integer>> mutation;
        SelectionOperator<List<PermutationSolution<Integer>>, PermutationSolution<Integer>> selection;

        GeneralizedPrioritizationProblem problem = null;

        switch (algorithmName) {
            case "NSGAII":

                problem = new MultiObjectiveGeneralizedPrioritizationProblem(
                        coverageFilenames,
                        inputPath + "cost_array",
                        inputPath + "fault_matrix",
                        true
                );

                populationSize = 250;
                maxEvaluations = 25000;
                mutationProbability = 1.0 / problem.getNumberOfVariables();
                crossoverProbability = 0.9;

                crossover = new PMXCrossover(crossoverProbability);

                mutation = new PermutationSwapMutation<>(mutationProbability);

                selection = new BinaryTournamentSelection<>(
                        new RankingAndCrowdingDistanceComparator<>()
                );

                algorithm = new NSGAIIBuilder<>(problem, crossover, mutation)
                        .setSelectionOperator(selection)
                        .setMaxEvaluations(maxEvaluations)
                        .setPopulationSize(populationSize)
                        .setSolutionListEvaluator(new MultithreadedSolutionListEvaluator<>(numberOfCores, problem))
                        .build();
                break;
            case "HGA":

                problem = new SingleObjectiveGeneralizedPrioritizationProblem(
                        coverageFilenames,
                        inputPath + "cost_array",
                        inputPath + "fault_matrix",
                        true
                );

                populationSize = 250;
                maxEvaluations = 25000;
                mutationProbability = 1.0 / problem.getNumberOfVariables();
                crossoverProbability = 0.9;

                crossover = new PMXCrossover(crossoverProbability);

                mutation = new PermutationSwapMutation<>(mutationProbability);

                selection = new BinaryTournamentSelection<>(
                        new RankingAndCrowdingDistanceComparator<>()
                );

                algorithm = new GeneticAlgorithmBuilder<>(problem, crossover, mutation)
                        .setSelectionOperator(selection)
                        .setMaxEvaluations(maxEvaluations)
                        .setPopulationSize(populationSize)
                        .setSolutionListEvaluator(new MultithreadedSolutionListEvaluator<>(numberOfCores, problem))
                        .build();
                break;
            case "AdditionalGreedy":

                problem = new SingleObjectiveGeneralizedPrioritizationProblem(
                        coverageFilenames,
                        inputPath + "cost_array",
                        inputPath + "fault_matrix",
                        true
                );

                algorithm = new AdditionalGreedyPrioritization(problem);
                independentRuns = 1;
                break;
        }

        if (algorithm != null) {
            for (int run = 0; run < independentRuns; run++) {
                long startTime = new Date().getTime();

                Thread algorithmThread = new Thread(algorithm);
                algorithmThread.start();

                algorithmThread.join();

                List<PermutationSolution<Integer>> population = new ArrayList<>();

                if (algorithm.getResult() instanceof PermutationSolution) {

                    population.add((PermutationSolution<Integer>) algorithm.getResult());
                } else if (algorithm.getResult() instanceof List) {
                    population.addAll((List<PermutationSolution<Integer>>) algorithm.getResult());
                }

                long finishTime = new Date().getTime();

                long computingTime = finishTime - startTime;

                JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

                File outputFolderFile = new File(outputFolder);
                outputFolderFile.mkdirs();

                new SolutionListOutput(population)
                        .setSeparator(",")
                        .setVarFileOutputContext(new DefaultFileOutputContext(outputFolder + "VAR." + run + ".tsv"))
                        .setFunFileOutputContext(new DefaultFileOutputContext(outputFolder + "FUN." + run + ".tsv"))
                        .print();

                printAFDPc(problem, population, outputFolder, run);
                printTime(computingTime, outputFolder, run);
            }
        }
    }
/** cambia tutto */
    private static void printAFDPc(GeneralizedPrioritizationProblem problem, List<PermutationSolution<Integer>> population, String outputFolder, int run) throws IOException {
        try (BufferedWriter afdpcBW = new DefaultFileOutputContext(outputFolder + "AFDP." + run).getFileWriter()) {
            List<Double> afdpcList = new ArrayList<>();
            for (PermutationSolution<Integer> solution : population) {
                afdpcList.add(GeneralizedAveragePercentage.calculate(solution, problem.faultMatrix, problem.costCriterion, false));
            }

            for (double afdpc : afdpcList) {
                afdpcBW.append(String.valueOf(afdpc)).append("\n");
            }
        }
    }

    private static void printTime(double time, String outputFolder, int run) throws IOException {
        try (BufferedWriter timeBW = new DefaultFileOutputContext(outputFolder + "TIME." + run).getFileWriter()) {
            timeBW.append(String.valueOf(time));
        }
    }

}
