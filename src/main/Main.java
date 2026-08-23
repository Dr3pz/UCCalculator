package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.geometry.Insets;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static javafx.geometry.Pos.TOP_CENTER;

public class Main extends Application {

    public void start (Stage stage){

        int maxLengthInputField = 32;

        // Size Window --->
        stage.setMaxWidth(600);
        stage.setMaxHeight(800);
        stage.setMinWidth(600);
        stage.setMinHeight(800);

        stage.setWidth(600);
        stage.setHeight(800);

        // Test typing label --->
        HBox labelHBox = new HBox();

        Label typeLabel = new Label("CHAR: PRESS");

        Label lengthLabel = new Label("L: HEX");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        labelHBox.getChildren().addAll(typeLabel, spacer, lengthLabel);

        // inputLine --->
        TextField inputField = new TextField();

        inputField.setMaxSize(stage.getWidth(), 75);
        inputField.setMinSize(stage.getWidth(), 75);

        inputField.setStyle("-fx-font-size: 28; -fx-background-color: #10241b; -fx-text-fill: white;");

        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
            lengthLabel.setText(String.format("L: %s", newValue.length()));
            if (newValue.length() > maxLengthInputField) {
                inputField.setText(oldValue);
            }
        });

        // Buttons --->
        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-font-size: 48; -fx-background-color: #dfd5c6; -fx-text-fill: #2c3531;");
        plusButton.setMaxSize(100, 100);
        plusButton.setMinSize(100, 100);

        Button minusButton = new Button("-");
        minusButton.setStyle("-fx-font-size: 48; -fx-background-color: #dfd5c6; -fx-text-fill: #2c3531;");
        minusButton.setMaxSize(100, 100);
        minusButton.setMinSize(100, 100);

        Button multiplyButton = new Button("*");
        multiplyButton.setStyle("-fx-font-size: 48; -fx-background-color: #dfd5c6; -fx-text-fill: #2c3531;");
        multiplyButton.setMaxSize(100, 100);
        multiplyButton.setMinSize(100, 100);

        Button divideButton = new Button("/");
        divideButton.setStyle("-fx-font-size: 48; -fx-background-color: #dfd5c6; -fx-text-fill: #2c3531;");
        divideButton.setMaxSize(100, 100);
        divideButton.setMinSize(100, 100);

        Button clearButton = new Button();
        clearButton.setText("Clear");

        clearButton.setStyle("-fx-font-size: 48; -fx-background-color: #d97a53; -fx-text-fill: #2c3531;");
        clearButton.setMaxSize(225, 100);
        clearButton.setMinSize(225, 100);

        // GridPane --->
        GridPane gridPane = new GridPane();

        // BorderPane --->
        BorderPane borderPane = new BorderPane();

        // Root settings --->
        Scene scene = new Scene(borderPane);

        // Other Settings --->

        stage.setTitle("FXCalculate");
        scene.getRoot().setStyle("-fx-background-color: #1a3a2b;"); // Background color

        // GridGeo node --->
        gridPane.setHgap(25);
        gridPane.setVgap(25);

        gridPane.setAlignment(TOP_CENTER);

        gridPane.add(plusButton,0,0);
        gridPane.add(minusButton,1,0);
        gridPane.add(multiplyButton,2,0);
        gridPane.add(divideButton,3,0);
        gridPane.add(clearButton,1,1,2,1);

        // BroderGeo node --->
        borderPane.setCenter(gridPane);

        borderPane.setTop(inputField);
        BorderPane.setMargin(inputField, new Insets(0,0,75,0));
        BorderPane.setAlignment(inputField, Pos.TOP_LEFT);

        borderPane.setBottom(labelHBox);
        BorderPane.setAlignment(labelHBox, Pos.BOTTOM_CENTER);

        typeLabel.setStyle("-fx-text-size: 12; -fx-text-fill: white;");

        lengthLabel.setStyle("-fx-text-size: 12; -fx-text-fill: white; -fx-translate-x: -15;");

        // Key init --->
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            typeLabel.setText(String.format("CHAR: %s", keyEvent.getCode()));

            switch (keyEvent.getCode()) {
                case ENTER:
                    inputField.clear();
                    String result = "TEST VALUE";
                    inputField.insertText(inputField.getCaretPosition(), result);
                    keyEvent.consume();
                    break;
                case ESCAPE:
                    if (keyEvent.isShiftDown() || keyEvent.isControlDown()) {
                        Platform.exit();
                    }
                    break;
                case TAB:
                    keyEvent.consume();
                    break;
            }
        });

        // Button logic --->
        ButtonOperation(plusButton, inputField, "+");
        ButtonOperation(minusButton, inputField, "-");
        ButtonOperation(multiplyButton, inputField, "*");
        ButtonOperation(divideButton, inputField, "/");

        clearButton.setOnAction(event -> {
            inputField.clear();
            inputField.requestFocus();
        });

        // Compiling --->
        stage.setScene(scene);

        stage.show();
    }

    private void ButtonOperation(Button currentButton, TextField inputField, String insertSymbol) {
        currentButton.setFocusTraversable(false);

        currentButton.setOnAction(event -> {
            inputField.setFocusTraversable(true);

            int oldLength = inputField.getLength();
            int currentCaretPosition = inputField.getCaretPosition();

            inputField.insertText(currentCaretPosition, insertSymbol);
            inputField.requestFocus();

            if (inputField.getLength() > oldLength) {
                inputField.positionCaret(currentCaretPosition + 1);
            } else {
                inputField.positionCaret(currentCaretPosition);
            }
        });
    }
}