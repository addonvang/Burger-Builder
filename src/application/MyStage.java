package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

// TODO: Clean up
// TODO: SFX
// TODO: Particles

public class MyStage extends Application {

    String[] bottomBunImages = new String[]{"bottom_bun_0.png", "bottom_bun_0.png", "bottom_bun_0.png", "bottom_bun_0.png", "bottom_bun_1.png", "bottom_bun_1.png", "bottom_bun_1.png"};
    String[] greenImages = new String[]{"green.png", "green.png", "green.png", "green2.png", "green2.png"};
    String[] cheeseImages = new String[]{"cheese1.png", "cheese.png"};
    String[] tomatoImages = new String[]{"tomato1.png", "tomato1.png", "tomato.png"};
    String[] topBunImages = new String[]{"top_bun_0.png", "top_bun_0.png", "top_bun_0.png", "top_bun_0.png", "top_bun_0.png", "top_bun_1.png", "top_bun_1.png", "top_bun_2.png"};
    String[] burgerImages = new String[]{"burger_0.png", "burger_0.png", "burger_0.png", "burger_0.png", "burger_1.png", "burger_1.png", "burger_1.png", "burger_2.png"};

    String[][] list = {tomatoImages, cheeseImages, greenImages, burgerImages};

    ArrayList<ItemObject> looseObjects;
    Group allPanes;

    BurgerObject burgerObject;

    Random random;

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Title");

            random = new Random();

            burgerObject = new BurgerObject(-1000, 300);
            looseObjects = new ArrayList<>();
            allPanes = new Group(burgerObject.getPane());

            RotateObject rotateObject = new RotateObject(20, 20, greenImages);
            looseObjects.add(rotateObject);
            allPanes.getChildren().add(rotateObject.getPane());

            Scene scene = new Scene(allPanes, 600, 400);
            primaryStage.setScene(scene);
            primaryStage.show();

            buildBurger(20);

            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    burgerObject.update();

                    for (ItemObject object : looseObjects) {
                        object.update();

//                        object.goTo(burgerObject.getHeight() + 80);

                        if (object.getPane().getLayoutY() >= burgerObject.getHeight()) {
                            burgerObject.addItem(object.getPane());
                            allPanes.getChildren().remove(object.getPane());
                            looseObjects.remove(object);
                            break;
                        }
                    }

                    if (looseObjects.isEmpty() && !burgerObject.getPane().getChildren().isEmpty())
                        burgerObject.burgerFinished();

                    if (looseObjects.isEmpty() && burgerObject.objects.isEmpty()) {
                        buildBurger(random.nextInt(Math.max(1, (int)burgerObject.getPane().getLayoutY() / 10)) + 1);
                    }
                }
            };
            timer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropItem(double x, double y, String[] images) {
        FallingObject object = new FallingObject(x, y, images);
        allPanes.getChildren().add(object.getPane());
        looseObjects.add(object);
    }

    public void buildBurger(int size) {
//        dropItem(burgerObject.getPane().getLayoutX(), -80, bottomBunImages);
//        for (int i = 2; i < size; i++) {
//            dropItem(burgerObject.getPane().getLayoutX(), -i * 80, list[random.nextInt(list.length)]);
//        }
//        dropItem(burgerObject.getPane().getLayoutX(), -size * 80, topBunImages);

        dropItem(burgerObject.getPane().getLayoutX(), burgerObject.getPane().getLayoutY(), bottomBunImages);
        for (int i = 1; i < size; i++) {
            dropItem(burgerObject.getPane().getLayoutX(), burgerObject.getPane().getLayoutY() - i * 20, list[random.nextInt(list.length)]);
        }
        dropItem(burgerObject.getPane().getLayoutX(), burgerObject.getPane().getLayoutY() - size * 20, topBunImages);
    }

    public static void main(String[] args) {
        launch(args);
    }
}