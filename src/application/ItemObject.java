package application;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ItemObject {
    public Pane pane;

    public ItemObject(double x, double y) {
        pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);

        pane.setLayoutX(pane.getLayoutX() + x);
        pane.setLayoutY(pane.getLayoutY() + y);
    }

    public ItemObject(double x, double y, String[] images) {
        pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);

        pane.setLayoutX(pane.getLayoutX() + x);
        pane.setLayoutY(pane.getLayoutY() + y);
//        pane.setStyle("-fx-background-color: green");

        int t = 0;
        for (String img : images) {
            Image image = new Image(img, 256, 256, false, false);
            ImageView imageView = new ImageView(image);
            t -= 1;
            imageView.setY(t);
            pane.getChildren().add(imageView);
        }
    }

    public ItemObject (Pane pane) {
        this.pane = pane;
    }

    public void setX(double x) {
        pane.setLayoutX(x);
    }

    public void setY(double y) {
        pane.setLayoutY(y);
    }

    public void changeX(double x) {
        pane.setLayoutX(pane.getLayoutX() + x);
    }

    public void changeY(double y) {
        pane.setLayoutY(pane.getLayoutY() + y);
    }

    public void goTo(double pos) {}

    public void update() {}

    public Pane getPane() {
        return pane;
    }
}
