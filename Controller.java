package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.Main.randomGenerator;

public class Controller implements Initializable {


    @FXML
    private BarChart<String,Number> HistogramChart;
    @FXML
    private TextField aTextField;
    @FXML
    private TextField mTextField;
    @FXML
    private TextField RTextField;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private Label mathExpectationLabel;
    @FXML
    private Label dispersionLabel;
    @FXML
    private Label secondaryQualityIdentifierLabel;
    @FXML
    private Label periodLabel;
    @FXML
    private Label aperiodicLengthLabel;
    @FXML
    private Label deviationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    public void onButtonStartClicked() {

        Main.histogramDataRetriever = new HistogramDataRetriever();
        randomGenerator = new RandomGenerator();
        HistogramChart.getData().clear();
        String stringValue = null;
        int aFieldValue = 0;
        int mFieldValue = 0;
        int RFieldValue = 0;
        try {
            stringValue = aTextField.getText();
            aFieldValue = toInt(stringValue);
            stringValue = mTextField.getText();
            mFieldValue = toInt(stringValue);
            stringValue = RTextField.getText();
            RFieldValue = toInt(stringValue);

        }catch (NumberFormatException nfe)
        {
            System.out.println("NumberFormat Parsing failed! " + stringValue + " can not be an integer");
        }

        double tempR = RFieldValue;
        for(int i = 0; i < RandomGenerator.n; i++)
            tempR = randomGenerator.Lemer(aFieldValue, mFieldValue, tempR);

        Main.histogramDataRetriever.calculateFrequencies(randomGenerator.getRandomNumbersList());

        HistogramChart.getData().add(getHistogramBarChartSeries());

        randomGenerator.calculateSeqPeriod();
        randomGenerator.calculateAverage();
        randomGenerator.calсulateDispersion();
        randomGenerator.calculateAperiodicLength();
        randomGenerator.calculateDeviation();

        mathExpectationLabel.setText(String.valueOf(randomGenerator.getMathExpectation()));
        dispersionLabel.setText((String.valueOf(randomGenerator.getSeqDispersion())));
        secondaryQualityIdentifierLabel.setText(String.valueOf(randomGenerator.getSecondaryQuality()));
        periodLabel.setText(String.valueOf(randomGenerator.getSeqPeriod()));
        aperiodicLengthLabel.setText((String.valueOf(randomGenerator.getSeqAperiodicLength())));
        deviationLabel.setText(String.valueOf(randomGenerator.getSeqDeviation()));
    }

    @FXML
    public XYChart.Series<String, Number> getHistogramBarChartSeries() {

        int listSize = Main.histogramDataRetriever.getFrequenciesList().size();
        ArrayList<Integer> sourceFrequencyList = Main.histogramDataRetriever.getFrequenciesList();
        XYChart.Series<String, Number> tempSeries = new XYChart.Series<>();

        double range = 0;
        double step = (double) 1 / HistogramDataRetriever.k;
        BigDecimal bigDecimal = new BigDecimal(Double.toString(step));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        String valueOfStep = String.valueOf(bigDecimal.doubleValue());
        for(int i = 0; i < listSize; i++){
            tempSeries.getData().add(new XYChart.Data<>(valueOfStep, sourceFrequencyList.get(i)));
            range+= step;
            bigDecimal = new BigDecimal(Double.toString(range));
            bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
            valueOfStep = String.valueOf(bigDecimal.doubleValue());
        }
        return tempSeries;
    }

    public int toInt(String value) {
        return Integer.parseInt(value);
    }

}
