import javax.swing.*;

public class main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(new MyPanel());
        frame.pack();
        frame.setVisible(true);
    }
}
