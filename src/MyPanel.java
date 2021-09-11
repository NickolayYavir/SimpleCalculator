import java.awt.*;
import javax.swing.*;

class MyPanel extends JPanel {

    private final int PANEL_WIDTH = 406;
    private final int PANEL_HEIGHT = 578;

    private JTextArea displayTextArea = new JTextArea(1, 1);
    private JButton clearAllButton = new JButton("AC");
    private JButton clearButton = new JButton("C");
    private JButton percentSignButton = new JButton("%");
    private JButton divideSignButton = new JButton("/");
    private JButton sevenButton = new JButton("7");
    private JButton eightButton = new JButton("8");
    private JButton nineButton = new JButton("9");
    private JButton multiplySignButton = new JButton("*");
    private JButton fourButton = new JButton("4");
    private JButton fiveButton = new JButton("5");
    private JButton sixButton = new JButton("6");
    private JButton minusSignButton = new JButton("-");
    private JButton oneButton = new JButton("1");
    private JButton twoButton = new JButton("2");
    private JButton threeButton = new JButton("3");
    private JButton plusSignButton = new JButton("+");
    private JButton setSignButton = new JButton("+/-");
    private JButton zeroButton = new JButton("0");
    private JButton comaButton = new JButton(",");
    private JButton equalSignButton = new JButton("=");
    private JLabel jcomp22 = new JLabel("newLabel");

    private double result = 0;
    private String operationSign = "";
    private StringBuilder inputStringNumber = new StringBuilder();
    private StringBuilder displayString = new StringBuilder();
    private boolean isPressSign = false;
    private boolean isFirstInput = false;
    private boolean isSecondInput = false;

    MyPanel() {

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);

        add(displayTextArea);
        add(clearAllButton);
        add(clearButton);
        add(percentSignButton);
        add(divideSignButton);
        add(sevenButton);
        add(eightButton);
        add(nineButton);
        add(multiplySignButton);
        add(fourButton);
        add(fiveButton);
        add(sixButton);
        add(minusSignButton);
        add(oneButton);
        add(twoButton);
        add(threeButton);
        add(plusSignButton);
        add(setSignButton);
        add(zeroButton);
        add(comaButton);
        add(equalSignButton);
        add(jcomp22);

        zeroButton.addActionListener(e -> {
            inputNumber(0);
        });
        oneButton.addActionListener(e -> {
            inputNumber(1);
        });
        twoButton.addActionListener(e -> {
            inputNumber(2);
        });
        threeButton.addActionListener(e -> {
            inputNumber(3);
        });
        fourButton.addActionListener(e -> {
            inputNumber(4);
        });
        fiveButton.addActionListener(e -> {
            inputNumber(5);
        });
        sixButton.addActionListener(e -> {
            inputNumber(6);
        });
        sevenButton.addActionListener(e -> {
            inputNumber(7);
        });
        eightButton.addActionListener(e -> {
            inputNumber(8);
        });
        nineButton.addActionListener(e -> {
            inputNumber(9);
        });

        percentSignButton.addActionListener(e -> {
        });
        multiplySignButton.addActionListener(e -> {
            onClickSign("multiply");
        });
        divideSignButton.addActionListener(e -> {
            onClickSign("divide");
        });
        minusSignButton.addActionListener(e -> {
            onClickSign("minus");
        });
        plusSignButton.addActionListener(e -> {
            onClickSign("plus");
        });

        equalSignButton.addActionListener(e -> {
            if (isFirstInput && !isSecondInput) {
                displayTextArea.setText("" + result);
                reset();
            } else if (isSecondInput) {
                double operand = Double.parseDouble(inputStringNumber.toString());
                calculateResult(operationSign, operand);
                displayTextArea.setText("" + result);
                reset();
            }
        });

        comaButton.addActionListener(e -> {
        });
        setSignButton.addActionListener(e -> {
        });

        clearAllButton.addActionListener(e -> {
            reset();
            result = 0;
            displayTextArea.setText("");
        });
        clearButton.addActionListener(e -> {
        });

        displayTextArea.setEditable(false);
        displayTextArea.setBounds(5, 10, 395, 160);
        clearAllButton.setBounds(5, 180, 95, 70);
        clearButton.setBounds(105, 180, 95, 70);
        percentSignButton.setBounds(205, 180, 95, 70);
        divideSignButton.setBounds(305, 180, 95, 70);
        sevenButton.setBounds(5, 255, 95, 70);
        eightButton.setBounds(105, 255, 95, 70);
        nineButton.setBounds(205, 255, 95, 70);
        multiplySignButton.setBounds(305, 255, 95, 70);
        fourButton.setBounds(5, 330, 95, 70);
        fiveButton.setBounds(105, 330, 95, 70);
        sixButton.setBounds(205, 330, 95, 70);
        minusSignButton.setBounds(305, 330, 95, 70);
        oneButton.setBounds(5, 405, 95, 70);
        twoButton.setBounds(105, 405, 95, 70);
        threeButton.setBounds(205, 405, 95, 70);
        plusSignButton.setBounds(305, 405, 95, 70);
        setSignButton.setBounds(5, 480, 95, 70);
        zeroButton.setBounds(105, 480, 95, 70);
        comaButton.setBounds(205, 480, 95, 70);
        equalSignButton.setBounds(305, 480, 95, 70);
        jcomp22.setBounds(5, 555, 395, 15);
    }

    private void inputNumber(int inputNumber) {

        if (inputStringNumber.length() < 8) {
            inputStringNumber.append(inputNumber);
            displayString.append(inputNumber);
            displayTextArea.setText(displayString.toString());
            isFirstInput = true;
            if (isPressSign) {
                isSecondInput = true;
            }
            isPressSign = false;
        }
    }

    private void calculateResult(String operationSign, double operand) {
        switch (operationSign) {
            case "plus":
                result += operand;
                break;
            case "minus":
                result -= operand;
                break;
            case "multiply":
                result *= operand;
                break;
            case "divide": 
                result /= operand;
                break;
        }
    }

    private void chooseSign(String operationSign) {
        switch (operationSign) {
            case "plus":
                displayString.append(" + ");
                displayTextArea.setText(displayString.toString());
                break;
            case "minus":
                displayString.append(" - ");
                displayTextArea.setText(displayString.toString());
                break;
            case "multiply":
                displayString.append(" * ");
                displayTextArea.setText(displayString.toString());
                break;
            case "divide":
                displayString.append(" / ");
                displayTextArea.setText(displayString.toString());
                break;
        }
    }

    private void reset() {
        inputStringNumber.delete(0, inputStringNumber.length());
        displayString.delete(0, displayString.length());
        operationSign = "";
        isFirstInput = false;
        isSecondInput = false;
        isPressSign = false;
    }

    private void onClickSign(String operationSign) {


        if (isSecondInput) { // if you input second value and press sing -> Create shortcut - output result with input sing
            double operand = Double.parseDouble(inputStringNumber.toString());
            displayString.delete(0, displayString.length());

            calculateResult(operationSign, operand);
            displayString.append(result);
            chooseSign(operationSign);

            displayTextArea.setText(displayString.toString());
            inputStringNumber.delete(0, inputStringNumber.length());
            isFirstInput = true;
            isSecondInput = false;
            isPressSign = true;

        } else if (isPressSign) { // if you already choose sign -> replace it
            displayString.delete(displayString.length() - 3, displayString.length());
            chooseSign(operationSign);

        } else if (isFirstInput) { // if you input firs inputNumber -> select sign
            chooseSign(operationSign);
            result = Double.parseDouble(inputStringNumber.toString());
            inputStringNumber.delete(0, inputStringNumber.length());
            isPressSign = true;
        } else if (result != 0) {
            displayString.append(result);
            chooseSign(operationSign);
            isPressSign = true;
        }
        operationSign = operationSign;
    }
}