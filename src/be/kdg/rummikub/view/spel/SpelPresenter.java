package be.kdg.rummikub.view.spel;

import be.kdg.rummikub.model.Spel;
import be.kdg.rummikub.model.steen.Steen;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;

public class SpelPresenter {
    private final Spel model;
    private final SpelView view;

    public SpelPresenter(Spel model, SpelView view) {
        this.model = model;
        this.view = view;

        this.updateView();
        this.addEventHandlers();
    }

    private void addEventHandlers() {

        view.getBtnVraagExtraSteen().setOnMouseClicked(mouseEvent -> {
            model.volgendeSpeler();
            Steen extraSteen = model.getPot().getRandomSteen();
            model.getSpelers()[0].addSteen(extraSteen);
            model.getPot().getStenen().remove(extraSteen);
            this.updateView();
        });


        for (Node child : view.getHbxEigenStenen().getChildren()) {
            child.setOnDragDetected(mouseEvent -> {

                Dragboard db = child.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(child.getId());
                db.setContent(content);
                mouseEvent.consume();
                System.out.println("1");
            });
        }

        for (Node child : view.getHbxEigenStenen().getChildren()) {
            child.setOnDragOver(mouseEvent -> {

                if (mouseEvent.getGestureSource() != child) {
                    mouseEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                mouseEvent.consume();
                System.out.println(2);
            });
        }

        for (Node child : view.getHbxEigenStenen().getChildren()) {
            child.setOnDragDone(mouseEvent -> System.out.println(child.getId()));
        }

    }

    private void updateView() {
        view.getHbxEigenStenen().getChildren().clear();
        for (Steen steen : model.getSpelers()[0].getStenen()) {
            ImageView steenAfbeelding = new ImageView(new Image(steen.getPad()));
            steenAfbeelding.setFitHeight(40);
            steenAfbeelding.setFitWidth(55);
            view.getHbxEigenStenen().getChildren().add(steenAfbeelding);
            HBox.setMargin(steenAfbeelding, new Insets(5));
        }
        view.getHbxEigenStenen().getChildren().add(view.getBtnVraagExtraSteen());


    }
}

