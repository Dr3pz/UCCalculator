package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    public void start (Stage stage){

        // Window size --->
        stage.setWidth(400);
        stage.setHeight(600);
        stage.setResizable(false);

        // Add node --->

        // Add TextField (InputField)
        int maxLengthInputField = 32;

        TextField inputField = new TextField();
        inputField.setPrefSize(Double.MAX_VALUE, 75);

        // Add Buttons
        Button plusButton = new Button("+");
        Button minusButton = new Button("-");
        Button multiplyButton = new Button("*");
        Button divideButton = new Button("/");

        // Add GridPane --->
        GridPane gridPane = new GridPane();

        gridPane.add(plusButton,0,0);
        plusButton.setPrefSize(50, 50);

        gridPane.add(minusButton,1,0);
        minusButton.setPrefSize(50, 50);

        gridPane.add(multiplyButton,2,0);
        multiplyButton.setPrefSize(50, 50);

        gridPane.add(divideButton,3,0);
        divideButton.setPrefSize(50, 50);

        gridPane.setHgap(25);
        gridPane.setVgap(25);

        gridPane.setAlignment(Pos.TOP_CENTER);

        // Add BorderPane --->
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(inputField);
        BorderPane.setMargin(inputField, new Insets(0, 0, 75, 0));

        borderPane.setCenter(gridPane);

        // Add scene --->
        Scene scene = new Scene(borderPane);

        scene.getRoot().setStyle("-fx-background-color: #017200");

        // Key reader/parser --->
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case ENTER:
                    inputField.setText("");
                    break;
            }
        });

        // Window init --->
        String currentCommitVersion = "1.0 REB";
        stage.setTitle(String.format("FXCalculator v:%s", currentCommitVersion));

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