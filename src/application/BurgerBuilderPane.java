package application;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BackgroundFill;

/**
 * The type Pizza pane.
 */
public class BurgerBuilderPane extends Application {

    //declare variables for access in both the start and processButtonPress methods
    private Button build;
    private Button toppingChoicesLeft;
    private Button toppingChoicesLeft1;
    private Button toppingChoicesRight;
    private Button toppingChoicesRight1;

    private Pane paneCenter;
    private ArrayList<ItemObject> looseObjects;
    private BurgerObject burgerObject;

    private final String[] bottomBunImages = new String[]{"bottom_bun_0.png", "bottom_bun_0.png", "bottom_bun_0.png", "bottom_bun_0.png", "bottom_bun_1.png", "bottom_bun_1.png", "bottom_bun_1.png"};
    private final String[] greenImages = new String[]{"green.png", "green.png", "green.png", "green2.png", "green2.png"};
    private final String[] cheeseImages = new String[]{"cheese1.png", "cheese.png"};
    private final String[] tomatoImages = new String[]{"tomato1.png", "tomato1.png", "tomato.png"};
    private final String[] topBunImages = new String[]{"top_bun_0.png", "top_bun_0.png", "top_bun_0.png", "top_bun_0.png", "top_bun_0.png", "top_bun_1.png", "top_bun_1.png", "top_bun_2.png"};
    private final String[] burgerImages = new String[]{"burger_0.png", "burger_0.png", "burger_0.png", "burger_0.png", "burger_1.png", "burger_1.png", "burger_1.png", "burger_2.png"};

    private final String[][] list = {tomatoImages, cheeseImages, greenImages, burgerImages};

    private boolean burgerDone = false;

    /**
     * The List for crust type and check boxes for toppings.
     */
    ArrayList<CheckBox>listCheckBoxes = new ArrayList<CheckBox>();
    @Override
    public void start(Stage primaryStage) {

        //Beginning of Leftside

        toppingChoicesLeft = new Button("Meat");
        toppingChoicesLeft1 = new Button("Lettuce");
        toppingChoicesLeft.setMaxWidth(60);
        toppingChoicesLeft1.setMaxWidth(60);

        toppingChoicesLeft.setOnAction(this::processButtonPress);
        toppingChoicesLeft1.setOnAction(this::processButtonPress);

        VBox paneLeft = new VBox(toppingChoicesLeft, toppingChoicesLeft1);
        paneLeft.setStyle("-fx-background-color: rgb(95,100,130);");
        paneLeft.setPrefSize(100,400);

        paneLeft.setAlignment(Pos.CENTER);
        paneLeft.setSpacing(20);

        //End of Leftside

        //Beginning of Rightside

        toppingChoicesRight = new Button("Tomato");
        toppingChoicesRight1 = new Button("Cheese");
        toppingChoicesRight.setMaxWidth(60);
        toppingChoicesRight1.setMaxWidth(60);

        toppingChoicesRight.setOnAction(this::processButtonPress);
        toppingChoicesRight1.setOnAction(this::processButtonPress);

        VBox paneRight = new VBox(toppingChoicesRight, toppingChoicesRight1);
        paneRight.setStyle("-fx-background-color: rgb(95,100,130);");
        paneRight.setPrefSize(100, 400);
        paneRight.setAlignment(Pos.CENTER);
        paneRight.setSpacing(20);

        //Beginning of Center
        paneCenter = new Pane();
        looseObjects = new ArrayList<>();
        burgerObject = new BurgerObject(170, 200);

        addItem(bottomBunImages);

        paneCenter.getChildren().add(burgerObject.getPane());

        //Add two buttons to paneBottom with alignment and horizontal gap spacing
        build = new Button("Burger Build");

        build.setOnAction(this::processButtonPress);

        FlowPane paneBottom = new FlowPane(build);
        paneBottom.setStyle("-fx-background-color: rgb(95,100,130);");
        paneBottom.setPrefSize(3000,100);
        paneBottom.setAlignment(Pos.CENTER);
        paneBottom.setHgap(20);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(paneLeft);
        borderPane.setCenter(paneCenter);
        borderPane.setRight(paneRight);
        borderPane.setBottom(paneBottom);

        Scene scene = new Scene(borderPane,600,400);
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                burgerObject.update();

                for (ItemObject object : looseObjects) {
                    object.update();

                    if (object.getPane().getLayoutY() >= burgerObject.getHeight()) {
                        burgerObject.addItem(object.getPane());
                        paneCenter.getChildren().remove(object.getPane());
                        looseObjects.remove(object);
                        break;
                    }
                }

                if (looseObjects.isEmpty() && !burgerObject.getPane().getChildren().isEmpty() && burgerDone)
                    burgerObject.burgerFinished();

                if (looseObjects.isEmpty() && burgerObject.objects.isEmpty() && burgerDone) {
                    buildBurger();
                }
            }
        };
        timer.start();

    }//end of start method

    public void buildBurger() {
        burgerDone = false;
        burgerObject.clear();
        burgerObject.pane.setLayoutX(170);
        addItem(bottomBunImages);
    }

    public void addItem(String[] images) {
        FallingObject object = new FallingObject(170, -65, images);
        looseObjects.add(object);
        paneCenter.getChildren().add(object.getPane());
    }

    public void processButtonPress(ActionEvent event) {
        if (!burgerObject.getPane().getChildren().isEmpty() && !burgerDone) {
            if (event.getSource() == build) {
                addItem(topBunImages);
                burgerDone = true;
            }
            if (event.getSource() == toppingChoicesRight) {
                addItem(tomatoImages);
            }
            if (event.getSource() == toppingChoicesRight1) {
                addItem(cheeseImages);
            }
            if (event.getSource() == toppingChoicesLeft) {
                addItem(burgerImages);
            }
            if (event.getSource() == toppingChoicesLeft1) {
                addItem(greenImages);
            }
        }
    }

    /**
     * Method processButtonPress implements the listener for both the order and cancel buttons in the GUI
     *
     * @param event the button fires the event that is passed to the method
     */

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
