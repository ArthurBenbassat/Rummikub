package be.kdg.rummikub;

import be.kdg.rummikub.model.Spel;
import be.kdg.rummikub.view.start.StartPresenter;
import be.kdg.rummikub.view.start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.Optional;

public class rummikubMain extends Application {
    @Override
    public void start(Stage primaryStage) {


        Spel model = new Spel(2);
        StartView startView = new StartView();
        new StartPresenter(model, startView);
        Scene scene = new Scene(startView);

        primaryStage.setTitle("Rummikub");
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("/fotos/logo-wit.png"));

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
