package be.kdg.rummikub;

import be.kdg.rummikub.model.Spel;
import be.kdg.rummikub.view.start.StartPresenter;
import be.kdg.rummikub.view.start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class rummikubMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        Spel model = new Spel(2);
        StartView view = new StartView();
        new StartPresenter(model, view);
        Scene scene = new Scene(view);

        primaryStage.setTitle("Rummikub");
        primaryStage.setMaximized(true);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
