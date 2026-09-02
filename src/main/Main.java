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

        String currentVersion = "1.3.2";

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

        Button[] emptyButtons = new Button[5];

        for (int i = 0; i < emptyButtons.length; i++) {
            emptyButtons[i] = buttonEmptyCreate("-fx-background-color: %s;",  buttonOperationColor);
        }

        Button zeroButton = buttonCreate("0", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button oneButton = buttonCreate("1", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button twoButton = buttonCreate("2", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button threeButton = buttonCreate("3", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button fourButton = buttonCreate("4", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button fiveButton = buttonCreate("5", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button sixButton = buttonCreate("6", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button sevenButton = buttonCreate("7", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button eightButton = buttonCreate("8", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button nineButton = buttonCreate("9", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button plusButton = buttonCreate("+", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button minusButton = buttonCreate("-", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button multiplyButton = buttonCreate("*", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button divideButton = buttonCreate("/", "-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button dotButton = buttonCreate(".", "-fx-font-size: 32; -fx-background-color: %s; -fx-text-fill: %s; -fx-padding: -7.5 0 7.5 0;", buttonOperationColor, buttonOperationTextColor, inputField, currentCharLabel);

        Button submitButton = new Button("SUBMIT");
        submitButton.setStyle(String.format("-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonSubmitColor, buttonSubmitTextColor));
        submitButton.setFocusTraversable(false);
        submitButton.setOnAction(event -> {
            charLabelUpdate(currentCharLabel, submitButton.getText());

            inputField.setText(parserExpression(inputField.getText()));
            caretReturnOnEnd(inputField);
        });

        Button cancelButton = new Button("CANCEL");
        cancelButton.setStyle(String.format("-fx-font-size: 24; -fx-background-color: %s; -fx-text-fill: %s;", buttonCancelColor, buttonCancelTextColor));
        cancelButton.setOnAction(event -> {
            charLabelUpdate(currentCharLabel, cancelButton.getText());

            inputField.clear();
            inputField.requestFocus();
        });

        Button backspaceButton = new Button("⌫");
        backspaceButton.setStyle(String.format("-fx-font-size: 18; -fx-background-color: %s; -fx-text-fill: %s;", buttonCancelColor, buttonCancelTextColor));
        backspaceButton.setFocusTraversable(false);
        backspaceButton.setOnAction(event -> {
            charLabelUpdate(currentCharLabel, backspaceButton.getText());

            if (!inputField.getText().isBlank())
            {
                StringBuilder resultUpdate = new StringBuilder();
                int currentCaretPosition = inputField.getCaretPosition();
                for (int i = 0; i < inputField.getLength() - 1; i++) {
                    if (i != currentCaretPosition) {
                        resultUpdate.append(inputField.getText().charAt(i));
                    }
                }
                inputField.setText(resultUpdate.toString());
                caretReturnOnEnd(inputField);
            }
        });

        // Add GridPane --->
        GridPane gridPane = new GridPane();

        // first value - column; second value - line;

        gridPane.add(emptyButtons[0], 2, 0);
        emptyButtons[0].setPrefSize(50, 50);

        gridPane.add(emptyButtons[1], 3, 0, 2, 1);
        emptyButtons[1].setPrefSize(125, 50);

        gridPane.add(emptyButtons[2], 3, 1, 2, 1);
        emptyButtons[2].setPrefSize(125, 50);

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

        gridPane.add(multiplyButton,0,1);
        multiplyButton.setPrefSize(50,50);

        gridPane.add(divideButton,1,1);
        divideButton.setPrefSize(50,50);

        gridPane.add(dotButton,2,1);
        dotButton.setPrefSize(50,50);

        gridPane.add(submitButton,3,3,2,1);
        submitButton.setPrefSize(125,50);

        gridPane.add(cancelButton,3,4,2,1);
        cancelButton.setPrefSize(125,50);

        gridPane.add(backspaceButton,4,2);
        backspaceButton.setPrefSize(50,50);

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
            charLabelUpdate(currentCharLabel, event.getCode().toString());

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

    // EN: parsing expression --->
    private String parserExpression(String inputValue) {
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

        boolean isFirstCharDigit = Character.isDigit(inputValue.charAt(0));
        boolean isLastCharDigit = Character.isDigit(inputValue.charAt(inputValue.length() - 1));
        boolean isFirstCharAllowedSymbol = allowedStartCharacters.contains(inputValue.substring(0, 1));
        boolean isFirstCharDot = ".".contains(inputValue.substring(0, 1));
        boolean isLastCharDot =  ".".contains(inputValue.substring(inputValue.length() - 1));

        //System.out.println(!isFirstCharDigit && !isFirstCharAllowedSymbol && !isFirstCharDot);
        //System.out.println(!isLastCharDigit && !isLastCharDot);
        // TRUE - ERROR          FALSE - CONTINUE

        if ((!isFirstCharDigit && !isFirstCharAllowedSymbol && !isFirstCharDot) || (!isLastCharDigit && !isLastCharDot)) {
            return errorInvalidMessage;
        }

        for (int i = 0; i < inputValue.length(); i++) {
            if (allowedCharacters.indexOf(inputValue.charAt(i)) == -1) {
                return errorInvalidMessage;
            }
        }

        // Tokenization --->
        /* logic tokenization RU
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
                if (!token.toString().contains(".")) {
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
                    double firstOperandMultiplication = Double.parseDouble(tokens.get(i - 1));
                    double secondOperandMultiplication = Double.parseDouble(tokens.get(i + 1));
                    tokens.set(i - 1, String.valueOf(firstOperandMultiplication * secondOperandMultiplication));
                    tokens.remove(i + 1);
                    tokens.remove(i);
                    i--;
                    break;
                case "/":
                    double firstOperandDivide = Double.parseDouble(tokens.get(i - 1));
                    double secondOperandDivide = Double.parseDouble(tokens.get(i + 1));
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
                    double firstOperandPlus = Double.parseDouble(tokens.get(i - 1));
                    double secondOperandPlus = Double.parseDouble(tokens.get(i + 1));
                    tokens.set(i - 1, String.valueOf(firstOperandPlus + secondOperandPlus));
                    tokens.remove(i + 1);
                    tokens.remove(i);
                    i--;
                    break;
                case  "-":
                    double firstOperandMinus = Double.parseDouble(tokens.get(i - 1));
                    double secondOperandMinus = Double.parseDouble(tokens.get(i + 1));
                    tokens.set(i - 1, String.valueOf(firstOperandMinus - secondOperandMinus));
                    tokens.remove(i + 1);
                    tokens.remove(i);
                    i--;
                    break;
            }
        }

        // Return --->
        double finalValue = Double.parseDouble(tokens.get(0));
        if (!Double.isInfinite(finalValue)) {
            String resultValue = decimalFormat.format(finalValue);
            resultValue = resultValue.replaceAll("\\.+", ",");

            // System.out.println(resultValue);
            return resultValue;
        } else {
            return errorOverflow;
        }
    }

    private Button buttonCreate(String textOperation, String buttonStyle, String buttonOperationColor, String buttonOperationTextColor, TextField inputField, Label currentCharLabel) {
        Button buttonName = new Button(textOperation);
        buttonName.setStyle(String.format(buttonStyle, buttonOperationColor, buttonOperationTextColor));
        buttonOperation(buttonName, inputField, buttonName.getText(), currentCharLabel);
        return buttonName;
    }

    private void buttonOperation(Button currentButton, TextField inputField, String insertSymbol, Label currentCharLabel) {

        currentButton.setFocusTraversable(false);

        currentButton.setOnAction(event -> {
            charLabelUpdate(currentCharLabel, insertSymbol);

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

    private Button buttonEmptyCreate (String buttonStyle, String buttonColor) {
        Button buttonName = new Button();
        buttonName.setStyle(String.format(buttonStyle, buttonColor));
        return buttonName;
    }

    private void charLabelUpdate(Label CharLabel, String inputField) {
        CharLabel.setText(String.format("CHAR: %s", inputField));
    }

    private void caretReturnOnEnd(TextField textField) {
        textField.positionCaret(textField.getLength());
        textField.requestFocus();
    }
}