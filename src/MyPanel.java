import java.awt.*;
import javax.swing.*;

class MyPanel extends JPanel {

    private final int PANEL_WIDTH = 406;
    private final int PANEL_HEIGHT = 578;

    private JLabel displayTextArea = new JLabel();
    private JButton clearAllButton = new JButton("AC");
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
    private JLabel colorPanel = new JLabel();

    private double result = 0;
    private String operation = "";
    private StringBuilder tempForNumber = new StringBuilder();
    private StringBuilder displayString = new StringBuilder();

    private boolean isPressSign = false;
    private boolean isFirstInput = false;
    private boolean isSecondInput = false;
    private boolean isComaInput = false;
    private boolean isSetNumberSign = false;

    MyPanel() {

        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setLayout(null);

        add(displayTextArea);
        add(clearAllButton);
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
        add(colorPanel);

        zeroButton.addActionListener(e -> {
            numberInput("0");
        });

        oneButton.addActionListener(e -> {
            numberInput("1");
        });

        twoButton.addActionListener(e -> {
            numberInput("2");
        });

        threeButton.addActionListener(e -> {
            numberInput("3");
        });

        fourButton.addActionListener(e -> {
            numberInput("4");
        });

        fiveButton.addActionListener(e -> {
            numberInput("5");
        });

        sixButton.addActionListener(e -> {
            numberInput("6");
        });

        sevenButton.addActionListener(e -> {
            numberInput("7");
        });

        eightButton.addActionListener(e -> {
            numberInput("8");
        });

        nineButton.addActionListener(e -> {
            numberInput("9");
        });

        percentSignButton.addActionListener(e -> {
            if (isFirstInput && !isSecondInput && isPressSign) {
                double percentFromNumber = (Double.parseDouble(displayString.delete(displayString.length() - 3, displayString.length()).toString())) / 100;
                onClickPercent(percentFromNumber);
                displayText();
                reset();
            } else if (isSecondInput) {
                if (operation.equals("plus") || operation.equals("minus")) {
                    double percentFromNumber = result / 100 * Double.parseDouble(tempForNumber.toString());
                    displayString.delete(displayString.length() - tempForNumber.length(), displayString.length());
                    tempForNumber.delete(0, tempForNumber.length());
                    tempForNumber.append(percentFromNumber);
                    displayString.append(percentFromNumber);
                    displayText();
                } else {
                    double percentFromNumber = Double.parseDouble(tempForNumber.toString()) / 100;
                    displayString.delete(displayString.length() - tempForNumber.length(), displayString.length());
                    tempForNumber.delete(0, tempForNumber.length());
                    tempForNumber.append(percentFromNumber);
                    displayString.append(percentFromNumber);
                    displayText();
                }
            } else if (isFirstInput) {
                double percentFromNumber = (Double.parseDouble(displayString.toString())) / 100;
                onClickPercent(percentFromNumber);
                displayText();
            } else if (result != 0) {
                result /= 100;
                onClickPercent(result);
                displayText();
                reset();
            }
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
                if (!tempForNumber.isEmpty() && !isFirstInput) {
                    result = Double.parseDouble(tempForNumber.toString());
                } else if (isPressSign) {
                    result = Double.parseDouble(displayString.delete(displayString.length() - 3, displayString.length()).toString());
                } else {
                    result = Double.parseDouble(displayString.toString());
                }
                displayTextArea.setText("" + result);
                reset();
            } else if (isSecondInput) {
                double operand = Double.parseDouble(tempForNumber.toString());
                if (operand == 0 && operation.equals("divide")) {
                    displayString.delete(0, displayString.length());
                    displayString.append("Sorry, I can't divide by zero");
                    displayText();
                    result = 0;
                    reset();
                } else {
                    calculateResult(operation, operand);
                    displayTextArea.setText("" + result);
                    reset();
                }
            } else if (result != 0 && isPressSign) {
                displayTextArea.setText("" + result);
                reset();
            }
        });

        comaButton.addActionListener(e -> {
            if ((isFirstInput && !isComaInput) || (isSecondInput && !isComaInput)) {
                numberInput(".");
                isComaInput = true;
            }
        });

        setSignButton.addActionListener(e -> {

            if (!isSecondInput && !isPressSign) {
                if (!isSetNumberSign) {
                    displayString.delete(0, displayString.length());
                    tempForNumber.reverse().append('-').reverse();
                    displayString.append(tempForNumber.toString());
                    isSetNumberSign = true;
                } else {
                    tempForNumber.delete(0, 1);
                    displayString.delete(0, 1);
                    isSetNumberSign = false;
                }
            } else {

                if (!isSetNumberSign) {
                    displayString.delete(displayString.length() - tempForNumber.length(), displayString.length());
                    tempForNumber.reverse().append('-').reverse();
                    displayString.append(tempForNumber.toString());
                    isSetNumberSign = true;
                } else {
                    displayString.delete(displayString.length() - tempForNumber.length(), displayString.length() - tempForNumber.length() + 1);
                    tempForNumber.delete(0, 1);
                    isSetNumberSign = false;
                }
            }

            displayText();
        });

        clearAllButton.addActionListener(e -> {
            reset();
            result = 0;
            displayText();
        });


        this.setBackground(Color.DARK_GRAY);

        displayTextArea.setOpaque(true);
        displayTextArea.setForeground(Color.ORANGE);
        displayTextArea.setBackground(Color.BLACK);
        displayTextArea.setHorizontalAlignment(JLabel.RIGHT);
        displayTextArea.setVerticalAlignment(JLabel.BOTTOM);
        displayTextArea.setBounds(5, 10, 395, 160);
        displayTextArea.setFont(new Font("Consolas", Font.BOLD,24));

        clearAllButton.setBounds(5, 180, 195, 70);
        clearAllButton.setFocusable(false);
        clearAllButton.setBackground(Color.ORANGE);
        clearAllButton.setForeground(Color.BLACK);
        clearAllButton.setFont(new Font("Consolas",Font.BOLD,16));
        clearAllButton.setBorder(null);

        percentSignButton.setBounds(205, 180, 95, 70);
        percentSignButton.setFocusable(false);
        percentSignButton.setBackground(Color.ORANGE);
        percentSignButton.setForeground(Color.BLACK);
        percentSignButton.setFont(new Font("Consolas",Font.BOLD,16));
        percentSignButton.setBorder(null);

        divideSignButton.setBounds(305, 180, 95, 70);
        divideSignButton.setFocusable(false);
        divideSignButton.setBackground(Color.ORANGE);
        divideSignButton.setForeground(Color.BLACK);
        divideSignButton.setFont(new Font("Consolas",Font.BOLD,16));
        divideSignButton.setBorder(null);

        multiplySignButton.setBounds(305, 255, 95, 70);
        multiplySignButton.setFocusable(false);
        multiplySignButton.setBackground(Color.ORANGE);
        multiplySignButton.setForeground(Color.BLACK);
        multiplySignButton.setFont(new Font("Consolas",Font.BOLD,16));
        multiplySignButton.setBorder(null);

        minusSignButton.setBounds(305, 330, 95, 70);
        minusSignButton.setFocusable(false);
        minusSignButton.setBackground(Color.ORANGE);
        minusSignButton.setForeground(Color.BLACK);
        minusSignButton.setFont(new Font("Consolas",Font.BOLD,16));
        minusSignButton.setBorder(null);

        plusSignButton.setBounds(305, 405, 95, 70);
        plusSignButton.setFocusable(false);
        plusSignButton.setBackground(Color.ORANGE);
        plusSignButton.setForeground(Color.BLACK);
        plusSignButton.setFont(new Font("Consolas",Font.BOLD,16));
        plusSignButton.setBorder(null);

        setSignButton.setBounds(5, 480, 95, 70);
        setSignButton.setFocusable(false);
        setSignButton.setBackground(Color.ORANGE);
        setSignButton.setForeground(Color.BLACK);
        setSignButton.setFont(new Font("Consolas",Font.BOLD,16));
        setSignButton.setBorder(null);

        equalSignButton.setBounds(305, 480, 95, 70);
        equalSignButton.setFocusable(false);
        equalSignButton.setBackground(Color.ORANGE);
        equalSignButton.setForeground(Color.BLACK);
        equalSignButton.setFont(new Font("Consolas",Font.BOLD,16));
        equalSignButton.setBorder(null);

        sevenButton.setBounds(5, 255, 95, 70);
        sevenButton.setFocusable(false);
        sevenButton.setBackground(Color.BLACK);
        sevenButton.setForeground(Color.WHITE);
        sevenButton.setFont(new Font("Futura",Font.BOLD,18));
        sevenButton.setBorder(null);

        eightButton.setBounds(105, 255, 95, 70);
        eightButton.setFocusable(false);
        eightButton.setBackground(Color.BLACK);
        eightButton.setForeground(Color.WHITE);
        eightButton.setFont(new Font("Futura",Font.BOLD,18));
        eightButton.setBorder(null);

        nineButton.setBounds(205, 255, 95, 70);
        nineButton.setFocusable(false);
        nineButton.setBackground(Color.BLACK);
        nineButton.setForeground(Color.WHITE);
        nineButton.setFont(new Font("Futura",Font.BOLD,18));
        nineButton.setBorder(null);

        fourButton.setBounds(5, 330, 95, 70);
        fourButton.setFocusable(false);
        fourButton.setBackground(Color.BLACK);
        fourButton.setForeground(Color.WHITE);
        fourButton.setFont(new Font("Futura",Font.BOLD,18));
        fourButton.setBorder(null);

        fiveButton.setBounds(105, 330, 95, 70);
        fiveButton.setFocusable(false);
        fiveButton.setBackground(Color.BLACK);
        fiveButton.setForeground(Color.WHITE);
        fiveButton.setFont(new Font("Futura",Font.BOLD,18));
        fiveButton.setBorder(null);

        sixButton.setBounds(205, 330, 95, 70);
        sixButton.setFocusable(false);
        sixButton.setBackground(Color.BLACK);
        sixButton.setForeground(Color.WHITE);
        sixButton.setFont(new Font("Futura",Font.BOLD,18));
        sixButton.setBorder(null);

        oneButton.setBounds(5, 405, 95, 70);
        oneButton.setFocusable(false);
        oneButton.setBackground(Color.BLACK);
        oneButton.setForeground(Color.WHITE);
        oneButton.setFont(new Font("Futura",Font.BOLD,18));
        oneButton.setBorder(null);

        twoButton.setBounds(105, 405, 95, 70);
        twoButton.setFocusable(false);
        twoButton.setBackground(Color.BLACK);
        twoButton.setForeground(Color.WHITE);
        twoButton.setFont(new Font("Futura",Font.BOLD,18));
        twoButton.setBorder(null);

        threeButton.setBounds(205, 405, 95, 70);
        threeButton.setFocusable(false);
        threeButton.setBackground(Color.BLACK);
        threeButton.setForeground(Color.WHITE);
        threeButton.setFont(new Font("Futura",Font.BOLD,18));
        threeButton.setBorder(null);

        zeroButton.setBounds(105, 480, 95, 70);
        zeroButton.setFocusable(false);
        zeroButton.setBackground(Color.BLACK);
        zeroButton.setForeground(Color.WHITE);
        zeroButton.setFont(new Font("Futura",Font.BOLD,18));
        zeroButton.setBorder(null);

        comaButton.setBounds(205, 480, 95, 70);
        comaButton.setFocusable(false);
        comaButton.setBackground(Color.BLACK);
        comaButton.setForeground(Color.WHITE);
        comaButton.setFont(new Font("Futura",Font.BOLD,18));
        comaButton.setBorder(null);

        colorPanel.setBounds(5, 555, 395, 15);
        colorPanel.setOpaque(true);
        colorPanel.setBackground(Color.ORANGE);
    }

    private void displayText() {
        displayTextArea.setText(displayString.toString());
    }

    private void numberInput(String number) {

        if (tempForNumber.length() < 8) {
            tempForNumber.append(number);
            displayString.append(number);
            displayText();
            isFirstInput = true;
            if (isPressSign) {
                isSecondInput = true;
                isComaInput = false;
            }
            isPressSign = false;
        }
    }

    private void calculateResult(String operation, double operand) {
        switch (operation) {
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

    private void chooseSign(String buttonOperation) {
        switch (buttonOperation) {
            case "plus":
                displayString.append(" + ");
                break;
            case "minus":
                displayString.append(" - ");
                break;
            case "multiply":
                displayString.append(" * ");
                ;
                break;
            case "divide":
                displayString.append(" / ");
                break;
        }
        displayText();
    }

    private void reset() {
        tempForNumber.delete(0, tempForNumber.length());
        displayString.delete(0, displayString.length());
        operation = "";
        isFirstInput = false;
        isSecondInput = false;
        isPressSign = false;
        isComaInput = false;
        isSetNumberSign = false;
    }

    private void onClickSign(String buttonOperation) {

        if (isSecondInput) { // if you input second value and press sing -> Create shortcut - output result with input sing
            double operand = Double.parseDouble(tempForNumber.toString());
            displayString.delete(0, displayString.length());

            calculateResult(operation, operand);
            displayString.append(result);
            chooseSign(buttonOperation);

            tempForNumber.delete(0, tempForNumber.length());
            isFirstInput = true;
            isSecondInput = false;
            isPressSign = true;

        } else if (isPressSign) { // if you already choose sign -> replace it
            displayString.delete(displayString.length() - 3, displayString.length());
            chooseSign(buttonOperation);

        } else if (isFirstInput) { // if you input firs number -> select sign
            chooseSign(buttonOperation);
            result = Double.parseDouble(tempForNumber.toString());
            tempForNumber.delete(0, tempForNumber.length());
            isPressSign = true;
            isSetNumberSign = false;
        } else if (result != 0) {
            displayString.append(result);
            chooseSign(buttonOperation);
            isPressSign = true;
            isSetNumberSign = false;
        }
        operation = buttonOperation;
        displayText();
    }

    private void onClickPercent(double percentFromNumber) {
        displayString.delete(0, displayString.length());
        displayString.append(percentFromNumber);
        tempForNumber.delete(0, tempForNumber.length());
        tempForNumber.append(percentFromNumber);
    }
}