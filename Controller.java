package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private Button calculateButton;
    @FXML
    private BarChart<Number,Number> HistogramChart;
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

    }

    @FXML
    public void onButtonStartClicked(ActionEvent event) {
        HistogramChart.getData().add(getHistogramBarChartSeries());
    }

    @FXML
    public XYChart.Series<Number, Number> getHistogramBarChartSeries() {

        int listSize = Main.histogramDataRetriever.getFrequenciesList().size();
        ArrayList<Integer> sourceFrequencyList = Main.histogramDataRetriever.getFrequenciesList();
        XYChart.Series<Number, Number> tempSeries = new XYChart.Series<>();

        double range = 0;
        double step = (double) 1 / HistogramDataRetriever.k;
        for(int i = 0; i < listSize; i++){
            tempSeries.getData().add(new XYChart.Data<>(step, sourceFrequencyList.get(i)));
            range+= step;
        }
        return tempSeries;
    }


}
