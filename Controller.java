package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import sun.rmi.runtime.Log;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static sample.Main.randomGenerator;

public class Controller implements Initializable {


    @FXML
    private Button calculateButton;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        randomGenerator = new RandomGenerator();
        //Main.histogramDataRetriever = new HistogramDataRetriever();

    }

    @FXML
    public void onButtonStartClicked() {

        Main.histogramDataRetriever = new HistogramDataRetriever();
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

        System.out.println(randomGenerator.getRandomNumbersList());

        randomGenerator.calculateSeqPeriod();
        Main.histogramDataRetriever.calculateFrequencies(randomGenerator.getRandomNumbersList());
        System.out.println(Main.histogramDataRetriever.getFrequenciesList());

        HistogramChart.getData().add(getHistogramBarChartSeries());

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
            valueOfStep = String.valueOf(bigDecimal.doubleValue());
        }
        return tempSeries;
    }

    public int toInt(String value) {
        return Integer.parseInt(value);
    }

}
