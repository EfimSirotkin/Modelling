package sample;

import java.util.ArrayList;


public class RandomGenerator {

    public static int n = 100000;

    double mathExpectation;
    double seqDispersion;
    double seqPeriod;
    private ArrayList<Double> randomNumbersList;

    public RandomGenerator()
    {
        randomNumbersList = new ArrayList<>(n);
    }

    public double getSeqDispersion() {
        return seqDispersion;
    }

    public void setSeqDispersion(double seqDispersion) {
        this.seqDispersion = seqDispersion;
    }

    public double getSeqPeriod() {
        return seqPeriod;
    }

    public void setSeqPeriod(double seqPeriod) {
        this.seqPeriod = seqPeriod;
    }

    public double getMathExpectation() {
        return mathExpectation;
    }

    public void setMathExpectation(double mathExpectation) {
        this.mathExpectation = mathExpectation;
    }

    public ArrayList<Double> getRandomNumbersList() {
        return randomNumbersList;
    }

    public void setRandomNumbersList(ArrayList<Double> randomNumbersList) {
        this.randomNumbersList = randomNumbersList;
    }

    public double Lemer(double a, double m, double R)
    {
        double step1 = a * R;
        double step2 = step1 % m;
        randomNumbersList.add(new Double(step2 / m));
        return step2;
    }

    public void calculateSeqPeriod()
    {
        double x0 = randomNumbersList.get(0);

        int seqPeriod1 = 0;
        int seqPeriod2 = 0;
        int seqPeriod3 = 0;
        for(int i = 1; i < randomNumbersList.size() - 1; i++)
        {
            if(randomNumbersList.get(i) == x0) {
                seqPeriod = i - 0;
                break;
            }
        }

        System.out.println("\n*seq period: 1) " + seqPeriod1 + "  2) " + seqPeriod2 + " 3) " + seqPeriod3);
    }

    public void calculateAperiodic()
    {
        if(seqPeriod != 0)
        {

        }
    }

    public void calculateAverage()
    {
        double sum = 0;
        for(Double number : randomNumbersList)
            sum+= number;

        mathExpectation = sum / n;
    }

    public void calсulateDispersion()
    {
        double sum = 0;
        for(Double number : randomNumbersList)
            sum += Math.pow(number - mathExpectation, 2);

        seqDispersion = sum / n;
    }

    public void checkSecondaryQuality()
    {
        double counter = 0;
        double qualityIdentifier = 0;
        for(int i = 0; i < n; i+= 2)
        {
            if(Math.pow(randomNumbersList.get(i), 2) + Math.pow(randomNumbersList.get(i+1),2) < 1)
                counter++;
        }
        qualityIdentifier = 2 * counter / n;
    }
}
