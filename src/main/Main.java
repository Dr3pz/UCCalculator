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
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        // Add variables --->
        int maxLengthInputField = 32;

        String currentVersion = "1.2";

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

        spacer.setStyle(String.format("-fx-background-color: derive(%s, -25%%)", backgroundColorScene));
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bottomStatusHBox = new HBox();
        bottomStatusHBox.getChildren().addAll(currentCharLabel, spacer, inputLengthLabel);

        // Add TextField (InputField) --->
        TextField inputField = new TextField();
        inputField.setPrefSize(Double.MAX_VALUE, 75);
        inputField.setStyle(String.format("-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", inputFieldBackgroundColor, inputFieldTextColor));

        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (inputField.getLength() == maxLengthInputField) {
                inputLengthLabel.setText(String.format("LEN: %s (MAXLEN)", inputField.getLength()));
            } else {
                inputLengthLabel.setText(String.format("LEN: %s", inputField.getLength()));
            }

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
                    inputField.setText(ParserExpression(inputField.getText()));
                    inputField.requestFocus();
                    break;
                case ESCAPE:
                    if (event.isShiftDown() || event.isControlDown()) {
                        Platform.exit();
                    } else {
                        cancelButton.fire();
                    }
                    break;
                case TAB: // future: need to correct the position of the caret when press tab
                    event.consume();
                    break;
            }
        });

        // Window init --->
        stage.setTitle("UUCalculator");

        stage.setScene(scene);
        stage.show();

    }

    // EN: parsing expression // RU: парсинг выражения --->
    private String ParserExpression(String inputValue) {
        String allowedCharacters = "0123456789+-*/";
        String errorInvalidMessage = "E:EI"; // Full text: "ERROR: EXPRESSION INVALID"

        // Input validation --->

        inputValue = inputValue.replaceAll("\\s+", ""); // \\s+ - space inspected cycle

        if (inputValue.isBlank()) {
            return inputValue;
        }
        if (!Character.isDigit(inputValue.charAt(0)) || !Character.isDigit(inputValue.charAt(inputValue.length() - 1))) {
            return errorInvalidMessage;
        }

        for (int i = 0; i < inputValue.length(); i++) {
            if (allowedCharacters.indexOf(inputValue.charAt(i)) == -1) {
                return errorInvalidMessage;
            }
        }

        // Tokenization --->
        /* logic tokenization
        1. Перебираем char inputValue
        2. Если текущий char - digit, то добавляем его в сборочную строку,
         когда текущий символ !char, то обрываем запись числа
        3. Кидаем записанное число в tokens
        4. Начинаем писать до последнего числа
        5. При окончании записи последнего числа, тоже его добавляем в tokens и цикл завершается
         */

        ArrayList<String> tokens = new ArrayList<>();
        StringBuilder token = new StringBuilder();

        for (char currentChar : inputValue.toCharArray()) {
            if (Character.isDigit(currentChar)) {
                token.append(currentChar);
            } else {
                tokens.add(token.toString());
                token.setLength(0);
                tokens.add(String.valueOf(currentChar));
            }
        }
        tokens.add(token.toString());

        // Array compression --->
        for (int i = 0; i < tokens.size(); i++) {
            String currentTokenValue = tokens.get(i);
            int nextTokenIndex = 0;
            int lastTokenIndex = 0;

            if (currentTokenValue.equals("+") || currentTokenValue.equals("-")) {
                nextTokenIndex = i + 1;
                lastTokenIndex = i - 1;
            }

            switch (currentTokenValue) {
                case "+":
                    int firstOperandPlus = Integer.parseInt(tokens.get(lastTokenIndex));
                    int secondOperandPlus = Integer.parseInt(tokens.get(nextTokenIndex));
                    tokens.set(i - 1, String.valueOf(firstOperandPlus + secondOperandPlus));
                    tokens.remove(i);
                    tokens.remove(i);
                    i--;
                    break;
                case  "-":
                    int firstOperandMinus = Integer.parseInt(tokens.get(lastTokenIndex));
                    int secondOperandMinus = Integer.parseInt(tokens.get(nextTokenIndex));
                    tokens.set(i - 1, String.valueOf(firstOperandMinus - secondOperandMinus));
                    tokens.remove(i);
                    tokens.remove(i);
                    i--;
                    break;
            }
        }

        // Return --->
        return tokens.get(0);
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