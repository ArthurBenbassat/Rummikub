package be.kdg.rummikub.view.start;

import be.kdg.rummikub.model.Spel;
import be.kdg.rummikub.view.about.AboutPresenter;
import be.kdg.rummikub.view.about.AboutView;
import be.kdg.rummikub.view.info.InfoPresenter;
import be.kdg.rummikub.view.info.InfoView;
import be.kdg.rummikub.view.inputNaam.InputNaamPresenter;
import be.kdg.rummikub.view.inputNaam.InputNaamView;
import be.kdg.rummikub.view.spel.SpelPresenter;
import be.kdg.rummikub.view.spel.SpelView;
import be.kdg.rummikub.view.spelregels.SpelregelsPresenter;
import be.kdg.rummikub.view.spelregels.SpelregelsView;
import be.kdg.rummikub.view.statistieken.StatsPresenter;
import be.kdg.rummikub.view.statistieken.StatsView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;

public class StartPresenter {
    private Spel model;
    private StartView view;

    public StartPresenter(Spel model, StartView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnSpelregels().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SpelregelsView spelregelsView = new SpelregelsView();
                new SpelregelsPresenter(model, spelregelsView);
                view.getScene().setRoot(spelregelsView);

            }
        });


        /**
         * Actie bij drukken op menu item Info
         * */
        view.getInfoMI().setOnAction(actionEvent -> {
            InfoView infoView = new InfoView();
            new InfoPresenter(model, infoView);


            Stage infoStage = new Stage();
            infoStage.initOwner(view.getScene().getWindow());
            infoStage.initModality(Modality.APPLICATION_MODAL);

            infoStage.setScene(new Scene(infoView));

            infoStage.setX(view.getScene().getWindow().getX() + 100);
            infoStage.setY(view.getScene().getWindow().getY() + 100);

            infoStage.getScene().getWindow().setHeight(200);
            infoStage.getScene().getWindow().setWidth(450);
            infoStage.setTitle("Info - Rummikub");
            infoStage.getIcons().add(new Image("/fotos/logo.png"));
            infoStage.showAndWait();
        });


        /**
         * Actie bij drukken op menu item Over ons
         * */
        view.getOverOnsMI().setOnAction(actionEvent -> {
            AboutView aboutView = new AboutView();
            new AboutPresenter(model, aboutView);

            Stage aboutStage = new Stage();
            aboutStage.initOwner(view.getScene().getWindow());
            aboutStage.initModality(Modality.APPLICATION_MODAL);

            aboutStage.setScene(new Scene(aboutView));

            aboutStage.setX(view.getScene().getWindow().getX() + 100);
            aboutStage.setY(view.getScene().getWindow().getY() + 100);

            aboutStage.getScene().getWindow().setHeight(200);
            aboutStage.getScene().getWindow().setWidth(650);
            aboutStage.setTitle("About - Rummikub");
            aboutStage.getIcons().add(new Image("/fotos/logo.png"));
            aboutStage.showAndWait();
        });


        /**
         * Actie bij drukken op menu item Exit
         * */
        view.getExitMI().setOnAction(actionEvent -> {
            Platform.exit();
            System.exit(0);
        });


        /**
         * Actie bij drukken op menu item Statistieken
         * */
        view.getStatistiekenMI().setOnAction(actionEvent -> {
            StatsView statsView = new StatsView();
            new StatsPresenter(model, statsView);

            Stage statsStage = new Stage();
            statsStage.initOwner(view.getScene().getWindow());
            statsStage.initModality(Modality.APPLICATION_MODAL);

            statsStage.setScene(new Scene(statsView));

            statsStage.setX(view.getScene().getWindow().getX() + 100);
            statsStage.setY(view.getScene().getWindow().getY() + 100);

            statsStage.getScene().getWindow().setHeight(600);
            statsStage.getScene().getWindow().setWidth(600);
            statsStage.setTitle("Statistieken - Rummikub");
            statsStage.getIcons().add(new Image("/fotos/logo.png"));
            statsStage.showAndWait();
        });

        view.getBtnStart().setOnAction(actionEvent -> {



            InputNaamView naamView = new InputNaamView();
            new InputNaamPresenter(model, naamView);
            view.getScene().setRoot(naamView);


        });

    }

}
