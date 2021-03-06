package homework;

public class KNNExecution {
    public static void main(String[] args) {
        KNN knn = new KNN();

        knn.readFileAndFill();

        knn.add10Examples();
    }
}
