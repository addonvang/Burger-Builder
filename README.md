# Burger Builder

A small JavaFX game where you build a burger one layer at a time. Pick toppings and watch each layer fall from the top of the screen and stack onto the burger. When you finish, the burger slides off screen and a new one starts.

## How to play

1. A bottom bun drops in automatically.
2. Use the side buttons to add layers:
   - **Meat** and **Lettuce** (left side)
   - **Tomato** and **Cheese** (right side)
3. Click **Burger Build** to drop the top bun and finish the burger.
4. The finished burger slides away, and a fresh bottom bun drops in for the next one.

## Features

- Falling ingredients with simple gravity (acceleration with a capped fall speed)
- Layers that stack and spin on the burger as you build it
- Pixel-art sprites for each ingredient, drawn as layered images to give a 3D look
- An animated exit when a burger is finished

## Built with

- Java 21
- JavaFX 22 (`javafx.controls`, `javafx.graphics`, `javafx.fxml`, `javafx.media`)
- IntelliJ IDEA

## Project structure

```
src/
├── module-info.java
├── application/
│   ├── BurgerBuilderPane.java   # Main game: UI, buttons, and game loop
│   ├── ItemObject.java          # Base class: a positioned, layered sprite
│   ├── FallingObject.java       # An ingredient falling with gravity
│   ├── RotateObject.java        # A layer that spins once it's on the burger
│   ├── BurgerObject.java        # The burger stack: holds layers, slides off when done
│   ├── MyStage.java             # Auto-play demo that builds random burgers by itself
│   └── MyPane.java              # Early experiment (not used by the game)
└── *.png                        # Ingredient sprites
```

## Running it

### Requirements
- JDK 21 or newer
- The [JavaFX SDK](https://gluonhq.com/products/javafx/) (version 22 or newer)

### In IntelliJ IDEA
1. Open the project folder.
2. Go to **File → Project Structure → Libraries**. Add the `lib` folder of your JavaFX SDK (for example `C:\Program Files\javafx-sdk-22\lib`).
3. Run `application.BurgerBuilderPane`.

To watch the auto-play demo, run `application.MyStage` instead.

### From the command line
From the `src` folder, with `PATH_TO_FX` set to your JavaFX SDK's `lib` folder:

```bash
javac --module-path "$PATH_TO_FX" -d ../out module-info.java application/*.java
cp *.png ../out/
java --module-path "$PATH_TO_FX;../out" -m untitled/application.BurgerBuilderPane
```

On macOS or Linux, use `:` instead of `;` in the module path.

## Author

Xyrus and Addison
