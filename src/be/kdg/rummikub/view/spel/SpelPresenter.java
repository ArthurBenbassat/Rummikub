package be.kdg.rummikub.view.spel;

import be.kdg.rummikub.model.Spel;
import be.kdg.rummikub.model.steen.Steen;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SpelPresenter {
    private final Spel model;
    private final SpelView view;

    public SpelPresenter(Spel model, SpelView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.test.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("ikh aat dit");
                Dragboard db = view.test.startDragAndDrop(TransferMode.ANY);
                mouseEvent.consume();
            }
        });
        try {
            view.getHbxEigenStenen().getChildren().get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("test");

                    mouseEvent.consume();
                }
            });
        } catch (IndexOutOfBoundsException e) {
            System.out.println("f");
        }

        for (Node child : view.getHbxEigenStenen().getChildren()) {
            child.setOnDragDetected(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("test");
                }
            });
        }
    }

    private void updateView() {
        for (Steen steen : model.getSpelers()[0].getStenen()) {
            ImageView steenAfbeelding = new ImageView(steen.getPad());
            steenAfbeelding.setFitHeight(40);
            steenAfbeelding.setFitWidth(55);
            view.getHbxEigenStenen().getChildren().add(steenAfbeelding);
            HBox.setHgrow(steenAfbeelding, Priority.ALWAYS);
        }
        view.getHbxEigenStenen().setPadding(new Insets(10));

    }
}

