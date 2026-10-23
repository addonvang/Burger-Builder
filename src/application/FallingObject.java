package application;

import javafx.scene.layout.Pane;

public class FallingObject extends ItemObject{

    double velocity;

    public FallingObject(double x, double y, String[] images) {
        super(x, y, images);

        velocity = 0;
    }

    public FallingObject(Pane pane) {
        super(pane);

        velocity = 0;
    }

    public void goTo(double pos) {
        changeY((pos - pane.getLayoutY()) * 0.02);
    }

    public void update() {
        velocity = Math.min(velocity + .3, 15);

        changeY(velocity);
    }

}
