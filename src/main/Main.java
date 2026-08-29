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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        // Add variables --->
        int maxLengthInputField = 32;

        String currentVersion = "1.3";

        String backgroundColorScene = "#017200";

        String inputFieldBackgroundColor = "#3E2723";
        String inputFieldTextColor = "#FFE0B2";

        String buttonOperationColor = "#D35400";
        String buttonOperationTextColor = "#1A0F0A";

        String buttonSubmitColor = "#A04000";
        String buttonSubmitTextColor = "#1A0F0A";

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

        Button zeroButton = CreateButton("0", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button oneButton = CreateButton("1", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button twoButton = CreateButton("2", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button threeButton = CreateButton("3", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button fourButton = CreateButton("4", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button fiveButton = CreateButton("5", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button sixButton = CreateButton("6", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button sevenButton = CreateButton("7", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button eightButton = CreateButton("8", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button nineButton = CreateButton("9", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button plusButton = CreateButton("+", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button minusButton = CreateButton("-", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button multiplyButton = CreateButton("*", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button divideButton = CreateButton("/", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s", buttonOperationColor, buttonOperationTextColor, inputField);

        Button submitButton = new Button("SUBMIT");
        submitButton.setStyle(String.format("-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonSubmitColor, buttonSubmitTextColor));
        submitButton.setFocusTraversable(false);
        submitButton.setOnAction(event -> {
            inputField.setText(ParserExpression(inputField.getText()));
            CaretReturnOnEnd(inputField);
        });

        Button cancelButton = new Button("CANCEL");
        cancelButton.setStyle(String.format("-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonCancelColor, buttonCancelTextColor));
        cancelButton.setOnAction(event -> {
            inputField.clear();
            inputField.requestFocus();
        });

        // Add GridPane --->
        GridPane gridPane = new GridPane();

        gridPane.add(zeroButton,3,2);
        zeroButton.setPrefSize(50,50);

        gridPane.add(oneButton,0,2);
        oneButton.setPrefSize(50,50);

        gridPane.add(twoButton,1,2);
        twoButton.setPrefSize(50,50);

        gridPane.add(threeButton,2,2);
        threeButton.setPrefSize(50,50);

        gridPane.add(fourButton,0,3);
        fourButton.setPrefSize(50,50);

        gridPane.add(fiveButton,1,3);
        fiveButton.setPrefSize(50,50);

        gridPane.add(sixButton,2,3);
        sixButton.setPrefSize(50,50);

        gridPane.add(sevenButton,0,4);
        sevenButton.setPrefSize(50,50);

        gridPane.add(eightButton,1,4);
        eightButton.setPrefSize(50,50);

        gridPane.add(nineButton,2,4);
        nineButton.setPrefSize(50,50);

        gridPane.add(plusButton,0,0);
        plusButton.setPrefSize(50,50);

        gridPane.add(minusButton,1,0);
        minusButton.setPrefSize(50,50);

        gridPane.add(multiplyButton,2,0);
        multiplyButton.setPrefSize(50,50);

        gridPane.add(divideButton,3,0);
        divideButton.setPrefSize(50,50);

        gridPane.add(submitButton,0,1,2,1);
        submitButton.setPrefSize(125,50);

        gridPane.add(cancelButton,2,1,2,1);
        cancelButton.setPrefSize(125,50);

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
                    submitButton.fire();
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
        String allowedCharacters = "0123456789+-*/.";
        String allowedStartCharacters = "+-";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();

        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#.######", symbols);

        String errorInvalidMessage = "ERROR: EXPRESSION INVALID";
        String errorEmptyMessage = "ERROR: EXPRESSION EMPTY";
        String errorDivisionByZero = "ERROR: DIVISION BY ZERO";
        String errorOverflow = "ERROR: OVERFLOW";

        // Boundary and syntax validation --->
        inputValue = inputValue.replaceAll("\\s+", ""); // \\s+ - space inspected cycle
        inputValue = inputValue.replaceAll("\\++", "+");
        inputValue = inputValue.replaceAll(",+", ".");
        inputValue = inputValue.replaceAll("\\.+", ".");
        inputValue = inputValue.replaceAll("\\*+", "*");
        inputValue = inputValue.replaceAll("/+", "/");

        while (inputValue.contains("--") || inputValue.contains("+-") || inputValue.contains("-+") || inputValue.contains("++")) {
            inputValue = inputValue.replace("--", "+");
            inputValue = inputValue.replace("-+", "-");
            inputValue = inputValue.replace("+-", "-");
            inputValue = inputValue.replace("++", "+");
        }

        if (inputValue.isBlank()) {
            return inputValue;
        }
        if ((!Character.isDigit(inputValue.charAt(0)) || !allowedStartCharacters.contains(inputValue.substring(0, 1))) && !Character.isDigit(inputValue.charAt(inputValue.length() - 1))) {
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

        for (int i = 0; i < inputValue.length(); i++) {
            Character currentChar = inputValue.charAt(i);
            if (i == 0 && allowedStartCharacters.contains(currentChar.toString())) {
                tokens.add("0");
                tokens.add(currentChar.toString());
            } else if (Character.isDigit(currentChar)) {
                token.append(currentChar);
            } else if (currentChar == '.') {
                if (!token.isEmpty() && !token.toString().contains(".")) {
                    token.append(currentChar);
                } else {
                    return errorInvalidMessage;
                }
            } else {
                if (!token.isEmpty()) {
                    tokens.add(token.toString());
                    token.setLength(0);
                    tokens.add(String.valueOf(currentChar));
                } else {
                    return errorInvalidMessage;
                }
            }
        }
        if (!token.isEmpty()) {
            tokens.add(token.toString());
        }

        //System.out.println(tokens);

        // Token reduction --->
        for (int i = 0; i < tokens.size(); i++) {

            switch (tokens.get(i)) {
                case "*":
                    Double firstOperandMultiplication = Double.parseDouble(tokens.get(i - 1));
                    Double secondOperandMultiplication = Double.parseDouble(tokens.get(i + 1));
                    tokens.set(i - 1, String.valueOf(firstOperandMultiplication * secondOperandMultiplication));
                    tokens.remove(i + 1);
                    tokens.remove(i);
                    i--;
                    break;
                case "/":
                    Double firstOperandDivide = Double.parseDouble(tokens.get(i - 1));
                    Double secondOperandDivide = Double.parseDouble(tokens.get(i + 1));
                    if (secondOperandDivide != 0) {
                        tokens.set(i - 1, String.valueOf(firstOperandDivide / secondOperandDivide));
                        tokens.remove(i + 1);
                        tokens.remove(i);
                        i--;
                        break;
                    } else {
                        return errorDivisionByZero;
                    }
            }
        }

        for (int i = 0; i < tokens.size(); i++) {

            switch (tokens.get(i)) {
                case "+":
                    Double firstOperandPlus = Double.parseDouble(tokens.get(i - 1));
                    Double secondOperandPlus = Double.parseDouble(tokens.get(i + 1));
                    tokens.set(i - 1, String.valueOf(firstOperandPlus + secondOperandPlus));
                    tokens.remove(i + 1);
                    tokens.remove(i);
                    i--;
                    break;
                case  "-":
                    Double firstOperandMinus = Double.parseDouble(tokens.get(i - 1));
                    Double secondOperandMinus = Double.parseDouble(tokens.get(i + 1));
                    tokens.set(i - 1, String.valueOf(firstOperandMinus - secondOperandMinus));
                    tokens.remove(i + 1);
                    tokens.remove(i);
                    i--;
                    break;
            }
        }

        // Return --->
        Double finalValue = Double.parseDouble(tokens.get(0));
        if (!Double.isInfinite(finalValue)) {
            String resultValue = decimalFormat.format(finalValue);
            resultValue = resultValue.replaceAll("\\.+", ",");

            // System.out.println(resultValue);
            return resultValue;
        } else {
            return errorOverflow;
        }
    }

    private Button CreateButton(String textOperation, String buttonStyle, String buttonOperationColor, String buttonOperationTextColor, TextField inputField) {
        Button buttonName = new Button(textOperation);
        buttonName.setStyle(String.format(buttonStyle, buttonOperationColor, buttonOperationTextColor));
        ButtonOperation(buttonName, inputField, buttonName.getText());
        return buttonName;
    }

    private void ButtonOperation(Button currentButton, TextField inputField, String insertSymbol) {

        currentButton.setFocusTraversable(false);

        currentButton.setOnAction(event -> {
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

    private void CaretReturnOnEnd(TextField textField) {
        textField.positionCaret(textField.getLength());
        textField.requestFocus();
    }
}