package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) {

        // Add variables --->
        int maxLengthInputField = 24;

        String currentVersion = "1.1";

        String backgroundColorScene = "#017200";

        String inputFieldBackgroundColor = "#3E2723";
        String inputFieldTextColor = "#FFE0B2";

        String buttonOperationColor = "#D35400";
        String buttonOperationTextColor = "#1A0F0A";

        String buttonCancelColor = "#A04000";
        String buttonCancelTextColor = "#1A0F0A";

        // Window size --->
        stage.setWidth(400);
        stage.setHeight(600);
        stage.setResizable(false);

        // Add node --->

        // Add labels --->
        Label inputLengthLabel = new Label("LEN: 0");
        inputLengthLabel.setStyle("-fx-font-size: 14; -fx-text-fill: black");

        Label currentCharLabel = new Label("CHAR: PRESS");
        currentCharLabel.setStyle("-fx-font-size: 14; -fx-text-fill: black");

        Label currentVersionLabel = new Label(String.format("VER: %s", currentVersion));
        currentVersionLabel.setStyle("-fx-font-size: 12; -fx-text-fill: black");
        StackPane.setAlignment(inputLengthLabel, Pos.CENTER);

        StackPane spacer = new StackPane();
        spacer.getChildren().addAll(currentVersionLabel);

        spacer.setStyle(String.format("-fx-background-color: derive(%s, -20%%)", backgroundColorScene));
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bottomStatusHBox = new HBox();
        bottomStatusHBox.getChildren().addAll(currentCharLabel, spacer, inputLengthLabel);

        // Add TextField (InputField) --->
        TextField inputField = new TextField();
        inputField.setPrefSize(Double.MAX_VALUE, 75);
        inputField.setStyle(String.format("-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", inputFieldBackgroundColor, inputFieldTextColor));

        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
            inputLengthLabel.setText(String.format("LEN: %s", inputField.getLength()));

            if (inputField.getText().length() > maxLengthInputField) {
                inputField.setText(oldValue);
            }
        });

        // Add Buttons --->
        Button plusButton = new Button("+");
        plusButton.setStyle(String.format("-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor));
        ButtonOperation(plusButton, inputField, plusButton.getText());

        Button minusButton = new Button("-");
        minusButton.setStyle(String.format("-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor));
        ButtonOperation(minusButton, inputField, minusButton.getText());

        Button multiplyButton = new Button("*");
        multiplyButton.setStyle(String.format("-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor));
        ButtonOperation(multiplyButton, inputField, multiplyButton.getText());

        Button divideButton = new Button("/");
        divideButton.setStyle(String.format("-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor));
        ButtonOperation(divideButton, inputField, divideButton.getText());

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(String.format("-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonCancelColor, buttonCancelTextColor));
        cancelButton.setOnAction(event -> {
            inputField.clear();
            inputField.requestFocus();
        });

        // Add GridPane --->
        GridPane gridPane = new GridPane();

        gridPane.add(plusButton, 0, 0);
        plusButton.setPrefSize(50, 50);

        gridPane.add(minusButton, 1, 0);
        minusButton.setPrefSize(50, 50);

        gridPane.add(multiplyButton, 2, 0);
        multiplyButton.setPrefSize(50, 50);

        gridPane.add(divideButton, 3, 0);
        divideButton.setPrefSize(50, 50);

        gridPane.add(cancelButton, 1, 1, 2, 1);
        cancelButton.setPrefSize(125, 50);

        gridPane.setHgap(25);
        gridPane.setVgap(25);

        gridPane.setAlignment(Pos.TOP_CENTER);

        // Add BorderPane --->
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(inputField);
        BorderPane.setMargin(inputField, new Insets(0, 0, 50, 0));

        borderPane.setCenter(gridPane);

        borderPane.setBottom(bottomStatusHBox);

        // Add scene --->
        Scene scene = new Scene(borderPane);

        scene.getRoot().setStyle(String.format("-fx-background-color: %s", backgroundColorScene));

        // Key reader/parser --->
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            currentCharLabel.setText(String.format("CHAR: %S", event.getCode().toString()));

            switch (event.getCode()) {
                case ENTER:
                    inputField.setText("");
                    break;
                case ESCAPE:
                    if (event.isShiftDown() || event.isControlDown()) {
                        Platform.exit();
                    }
            }
        });

        // Window init --->
        stage.setTitle("FXCalculator");

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