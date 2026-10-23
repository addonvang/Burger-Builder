package application;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class RotateObject extends ItemObject{

    double rotatingSpeed;

    public RotateObject(double x, double y) {
        super(x, y);

        rotatingSpeed = 3;
    }

    public RotateObject(double x, double y, String[] images) {
        super(x, y, images);

        rotatingSpeed = 3;
    }

    public RotateObject(Pane pane) {
        super(pane);

        rotatingSpeed = 3;
    }

    public void setRotation(double rotation) {
        for (Node image : getPane().getChildren())
            image.setRotate(rotation);
    }

    public void changeRotation(double rotation) {
        for (Node image : getPane().getChildren())
            image.setRotate(image.getRotate() + rotation);
    }

    public void rotate() {
        for (Node image : getPane().getChildren())
            image.setRotate(image.getRotate() + rotatingSpeed);
    }

    public void update() {
        rotate();
    }
}
