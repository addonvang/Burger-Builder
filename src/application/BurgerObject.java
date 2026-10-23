package application;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

public class BurgerObject extends ItemObject{

    ArrayList<RotateObject> objects;

    double height;
    double velocity;
    boolean burgerFinished;

    public BurgerObject(double x, double y) {
        super(x, y);

        objects = new ArrayList<>();

        height = 0;
        velocity = 0;
        burgerFinished = false;
    }

    public void clear() {
        pane.getChildren().clear();
        objects.clear();

        height = 0;
        velocity = 0;
        burgerFinished = false;
    }

    public void addItem(Pane pane) {

        RotateObject object = new RotateObject(pane);

        object.pane.setLayoutY(height);
        object.pane.setLayoutX(0);

        this.pane.getChildren().add(object.getPane());
        objects.add(object);
        height -= object.getPane().getChildren().size() + 1;
    }

    public void addItem(String[] images) {
        RotateObject object = new RotateObject(pane.getLayoutX(), pane.getLayoutY() + height, images);
        pane.getChildren().add(object.getPane());
        objects.add(object);
        height -= object.getPane().getChildren().size() + 1;
    }

    public double getHeight() {
        return pane.getLayoutY() + height;
    }

    public void burgerFinished() {
        burgerFinished = true;
    }

    public void update() {
        for (RotateObject object : objects)
            object.rotate();

        if (burgerFinished) {
            velocity += 0.5;
            changeX(velocity);
            if (pane.getLayoutX() > 800) {
                clear();
            }
        }
    }
}
