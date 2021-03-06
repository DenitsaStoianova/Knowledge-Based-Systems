package homework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class KNN {

    private static final String oftenFrequency = "Often";
    private static final String veryOftenFrequency = "Very often";
    private static final String sometimesFrequency = "Sometimes";
    private static final String rarelyFrequency = "Rarely";

    private static final int xColumnNum = 0;
    private static final int yColumnNum = 1;
    private static final int classificationColumnNum = 2;
    private final String fileName = "training_dataset.txt";
    private Individual[] kClosestIndividuals;

    private static final int k = 3;
    private final ArrayList<Individual> individuals;
    private final Map<String, Integer> mappedYValues;

    private Individual individualToClassify;

    public KNN() {
        this.individuals = new ArrayList<>();
        this.mappedYValues = new HashMap<>();
        initialiseMap();
    }

    private void initialiseMap() {
        mappedYValues.put(veryOftenFrequency, 0);
        mappedYValues.put(oftenFrequency, 1);
        mappedYValues.put(sometimesFrequency, 2);
        mappedYValues.put(rarelyFrequency, 3);
    }

    public void readFileAndFill() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            bufferedReader.readLine(); // skip first line
            String lineFromFile;
            while ((lineFromFile = bufferedReader.readLine()) != null) {
                String[] lineFromFileArguments = lineFromFile.split(";");

                if (lineFromFileArguments.length > 0) {
                    int x = Integer.parseInt(lineFromFileArguments[xColumnNum].trim());
                    int y = mappedYValues.get(lineFromFileArguments[yColumnNum].trim());
                    int classification = Integer.parseInt(lineFromFileArguments[classificationColumnNum].trim());

                    Individual currentLineIndividual = new Individual(x, y, classification);
                    individuals.add(currentLineIndividual);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add10Examples() {
        System.out.printf("Стойност на параметъра k: %d\n", k);
        printResult(603, oftenFrequency, 0);
        printResult(716, rarelyFrequency, 0);
        printResult(1240, veryOftenFrequency, 1);
        printResult(1565, sometimesFrequency, 1);
        printResult(720, oftenFrequency, 0);
        printResult(1460, rarelyFrequency, 1);
        printResult(1280, veryOftenFrequency, 1);
        printResult(660, sometimesFrequency, 0);
        printResult(946, oftenFrequency, 0);
        printResult(1080, rarelyFrequency, 0);

    }

    private void printResult(int amount, String frequency, int classification) {
        boolean result = predictType(amount, frequency) == classification;
        System.out.printf("|%-4d | %-10s | %d | -> k най-близки индивиди: %s, Резултат от класификацията: %s\n",
                amount, frequency, classification, Arrays.toString(kClosestIndividuals), result);
    }

    private int predictType(int amount, String frequency) {
        int individualY = mappedYValues.get(frequency.trim());
        this.individualToClassify = new Individual(amount, individualY);

        PriorityQueue<Individual> allDistances = new PriorityQueue<>(compareIndividuals);
        allDistances.addAll(individuals);

        kClosestIndividuals = new Individual[k];
        for (int i = 0; i < k; i++) {
            kClosestIndividuals[i] = Objects.requireNonNull(allDistances.poll());
        }

        int zeroesCount = 0;
        int onesCount = 0;

        for (int i = 0; i < k; i++) {
            if (kClosestIndividuals[i].getClassification() == 0) {
                zeroesCount++;
            } else {
                onesCount++;
            }
        }

        return onesCount < zeroesCount ? 0 : 1;
    }

    private final Comparator<Individual> compareIndividuals = new Comparator<Individual>() {
        @Override
        public int compare(Individual o1, Individual o2) {
            double distanceIndiv1 = individualToClassify.euclideanDistance(o1);
            double distanceIndiv2 = individualToClassify.euclideanDistance(o2);

            return Double.compare(distanceIndiv1, distanceIndiv2);
        }
    };

}
