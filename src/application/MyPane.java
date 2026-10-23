package application;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class MyPane extends Pane {
    private Pane pane;
    private Group root = new Group();

    public MyPane() {

        for (int y = 0; y < 10; y++) {
            Image image = new Image("eagle.jpg");
            ImageView imageView = new ImageView(image);
            imageView.setY(-y * 5);
            imageView.setRotate(imageView.getRotate() + 45);
            root.getChildren().add(imageView);
        }

        pane = new Pane(root);
        pane.setLayoutX(400);
    }

    public Group getRoot() {
        return root;
    }

    public Pane getPane() {
        return pane;
    }
}
