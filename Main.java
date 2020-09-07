package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static RandomGenerator randomGenerator;
    public static HistogramDataRetriever histogramDataRetriever;


    @Override
    public void start(Stage primaryStage) throws Exception{

        double a = 1987;
        double m = 1000000;
        double R = 2287;
        double tempR = R;
        randomGenerator = new RandomGenerator();
        histogramDataRetriever = new HistogramDataRetriever();
        for(int i = 0; i < RandomGenerator.n; i++)
        {
            tempR = randomGenerator.Lemer(a, m, tempR);
        }
       System.out.println(randomGenerator.getRandomNumbersList());

        randomGenerator.calculateSeqPeriod();
        histogramDataRetriever.calculateFrequencies(randomGenerator.getRandomNumbersList());
        System.out.println(histogramDataRetriever.getFrequenciesList());
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1400, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
