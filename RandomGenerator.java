package sample;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class RandomGenerator {

    public static int n = 10000000;

    private double mathExpectation;
    private double seqDispersion;
    private double seqDeviation;
    private int seqPeriod;
    private int seqAperiodicLength;

    public int getSeqAperiodicLength() {
        return seqAperiodicLength;
    }

    private ArrayList<Double> randomNumbersList;

    public RandomGenerator()
    {
        randomNumbersList = new ArrayList<>(n);
    }

    public double getSeqDispersion() {
        return seqDispersion;
    }


    public int getSeqPeriod() {
        return seqPeriod;
    }


    public double getMathExpectation() {
        return mathExpectation;
    }


    public ArrayList<Double> getRandomNumbersList() {
        return randomNumbersList;
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
        int numbersCount = 1;
        int listSize = randomNumbersList.size();

        for(int i = 1; i < listSize; i++)
        {
            if(randomNumbersList.get(i) == x0) {
                numbersCount++;
            }
        }
        if(numbersCount == 1)
            seqPeriod = 0;
        else
            seqPeriod = numbersCount;
        System.out.println("Period: " + seqPeriod);
    }

    public void calculateAperiodicLength() {
        double testNumber = randomNumbersList.get(0);
        int listSize = randomNumbersList.size();
        seqAperiodicLength = 0;
        for(int i = 1; i < listSize; ++i)
            if(randomNumbersList.get(i) == testNumber)
            {
                seqAperiodicLength = i - 0;
                break;
            }
        if(seqAperiodicLength == 0)
            seqAperiodicLength = listSize;
    }

    public void calculateAverage()
    {
        double sum = 0;
        for(Double number : randomNumbersList)
            sum+= number;
        BigDecimal bigDecimal = new BigDecimal(sum/n);
        bigDecimal = bigDecimal.setScale(4, RoundingMode.HALF_UP);
        mathExpectation = bigDecimal.doubleValue();
    }

    public void calсulateDispersion()
    {
        double sum = 0;
        for(Double number : randomNumbersList)
            sum += Math.pow(number - mathExpectation, 2);

        BigDecimal bigDecimal = new BigDecimal(sum/n);
        bigDecimal = bigDecimal.setScale(4, RoundingMode.HALF_UP);
        seqDispersion = bigDecimal.doubleValue();
    }

    public void calculateDeviation()
    {
        BigDecimal bigDecimal = new BigDecimal(Math.sqrt(seqDispersion));
        bigDecimal = bigDecimal.setScale(4, RoundingMode.HALF_UP);
        seqDeviation = bigDecimal.doubleValue();
    }

    public double getSeqDeviation() {
        return seqDeviation;
    }

    public double getSecondaryQuality()
    {
        double counter = 0;
        for(int i = 0; i < n; i+= 2)
        {
            if(Math.pow(randomNumbersList.get(i), 2) + Math.pow(randomNumbersList.get(i+1),2) < 1)
                counter++;
        }

        System.out.println("Quality: " + 2 * counter / n);
        return 2 * counter / n;
    }
}
