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

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Моделирование, лаб. №1");
        primaryStage.setScene(new Scene(root, 1400, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
