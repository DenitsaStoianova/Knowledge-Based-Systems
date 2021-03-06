package homework;

public class Individual {
    private int x;
    private int y;
    private int classification;

    public Individual(int x, int y, int classification) {
        this.setXValue(x);
        this.setYValue(y);
        this.setClassificationValue(classification);
    }

    public Individual(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private void setXValue(int x) {
        this.x = x <= 0 ? 1000 : x;
    }

    private void setYValue(int y){
        this.y = y < 0 ? 1 : y;
    }

    private void setClassificationValue(int classification){
        this.classification = classification;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getClassification() {
        return classification;
    }

    public double euclideanDistance(Individual other) {
        double xDist = x - other.x;
        double yDist = y - other.y;
        return Math.sqrt(xDist * xDist + yDist * yDist);
    }

    @Override
    public String toString() {
        return String.format("%d; %d; %d;", x, y, classification);
    }
}
