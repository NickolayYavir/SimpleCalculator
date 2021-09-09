import java.awt.*;
import javax.swing.*;

class MyPanel extends JPanel {

    final int PANEL_WIDTH = 406;
    final int PANEL_HEIGHT = 578;

    private JTextArea displayTextArea = new JTextArea(5, 5);
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

    private double tempForResult = 0;
    private String operation = "";
    private StringBuilder tempForNumber = new StringBuilder();
    private StringBuilder tempString = new StringBuilder();
    private boolean pressSign = false;
    private boolean firstInput = false;
    private boolean secondInput = false;

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
            numberInput(0);
        });
        oneButton.addActionListener(e -> {
            numberInput(1);
        });
        twoButton.addActionListener(e -> {
            numberInput(2);
        });
        threeButton.addActionListener(e -> {
            numberInput(3);
        });
        fourButton.addActionListener(e -> {
            numberInput(4);
        });
        fiveButton.addActionListener(e -> {
            numberInput(5);
        });
        sixButton.addActionListener(e -> {
            numberInput(6);
        });
        sevenButton.addActionListener(e -> {
            numberInput(7);
        });
        eightButton.addActionListener(e -> {
            numberInput(8);
        });
        nineButton.addActionListener(e -> {
            numberInput(9);
        });

        percentSignButton.addActionListener(e -> {
        });
        multiplySignButton.addActionListener(e -> {
            onSignButtonClick("multiply");
        });
        divideSignButton.addActionListener(e -> {
            onSignButtonClick("divide");
        });
        minusSignButton.addActionListener(e -> {
            onSignButtonClick("minus");
        });
        plusSignButton.addActionListener(e -> {
            onSignButtonClick("plus");
        });

        equalSignButton.addActionListener(e -> {
            if(!secondInput && firstInput){
                displayTextArea.setText(""+tempForResult);
                reset();
            }else if(secondInput){
                double operand = Double.parseDouble(tempForNumber.toString());
                calculateResult(operation,operand);
                displayTextArea.setText("" + tempForResult);
                reset();
            }
        });

        comaButton.addActionListener(e -> {
        });
        setSignButton.addActionListener(e -> {
        });

        clearAllButton.addActionListener(e -> {
        });
        clearButton.addActionListener(e -> {
        });

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

    private void numberInput(int number) {
        if (tempForNumber.length() < 8) {
            tempForNumber.append(number);
            tempForResult = Double.parseDouble(tempForNumber.toString());
            tempString.append(number);
            displayTextArea.setText(tempString.toString());
            firstInput = true;
            if (firstInput && pressSign) {
                secondInput = true;
            }
            pressSign = false;
        }
    }

    private void calculateResult(String operation, double operand){
        switch (operation) {
            case "plus":
                tempForResult += operand;
                break;
            case "minus":
                tempForResult -= operand;
                break;
            case "multiply":
                tempForResult *= operand;
                break;
            case "divide":
                tempForResult /= operand;
                break;
        }
    }

    private void chooseSign(String buttonOperation) {
        switch (buttonOperation) {
            case "plus":
                tempString.append(" + ");
                displayTextArea.setText(tempString.toString());
                break;
            case "minus":
                tempString.append(" - ");
                displayTextArea.setText(tempString.toString());
                break;
            case "multiply":
                tempString.append(" * ");
                displayTextArea.setText(tempString.toString());
                break;
            case "divide":
                tempString.append(" / ");
                displayTextArea.setText(tempString.toString());
                break;
        }
    }

    private void reset() {
        tempForResult = 0;
        tempForNumber.delete(0, tempForNumber.length());
        tempString.delete(0, tempString.length());
        firstInput = false;
        secondInput = false;
    }

    private void onSignButtonClick(String buttonOperation) {

        if (secondInput) { // if you input second value and press sing -> Create shortcut - output result with input sing
            double operand = Double.parseDouble(tempForNumber.toString());
            tempString.delete(0, tempString.length());

            calculateResult(operation,operand);
            tempString.append(tempForResult);
            chooseSign(buttonOperation);

            displayTextArea.setText(tempString.toString());
            tempForNumber.delete(0, tempForNumber.length());
            firstInput = true;
            secondInput = false;
            pressSign = true;

        } else if (pressSign) { // if you already choose sign -> replace it
            tempString.delete(tempString.length() - 3, tempString.length());
            chooseSign(buttonOperation);

        } else if (firstInput) { // if you input firs number -> select sign
            chooseSign(buttonOperation);
            tempForResult = Double.parseDouble(tempForNumber.toString());
            tempForNumber.delete(0, tempForNumber.length());
            pressSign = true;
        }
        operation = buttonOperation;
    }
}