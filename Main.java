package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        double a = 4;
        double m = 10000;
        double R = 16;
        double tempR = R;
        RandomGenerator randomGenerator = new RandomGenerator();
        for(int i = 0; i < RandomGenerator.n; i++)
        {
            tempR = randomGenerator.Lemer(a, m, tempR);

        }
       // for(Double number : randomGenerator.getRandomNumbersList())
       //     System.out.print(" " + number.doubleValue());

        randomGenerator.calculateSeqPeriod();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1400, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
