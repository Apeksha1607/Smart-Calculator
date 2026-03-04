import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class SmartCalculator extends JFrame implements ActionListener {

    private JTextField display;
    private StringBuilder currentInput;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator = '\0';
    private boolean isNewOp = true;

    SmartCalculator() {
        setTitle("🧮 Smart Calculator");
        setSize(400, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        display = new JTextField();
        display.setBounds(20, 20, 340, 60);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display);

        currentInput = new StringBuilder();

        // Buttons Layout
        String[] buttons = {
            "C", "←", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", "√",
            "sin", "cos", "tan", "log", "x²"
        };

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 10, 10));
        panel.setBounds(20, 100, 340, 380);

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            switch (command) {
                case "C":
                    currentInput.setLength(0);
                    display.setText("");
                    num1 = num2 = result = 0;
                    operator = '\0';
                    break;

                case "←":
                    if (currentInput.length() > 0)
                        currentInput.deleteCharAt(currentInput.length() - 1);
                    display.setText(currentInput.toString());
                    break;

                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                    num1 = Double.parseDouble(display.getText());
                    operator = command.charAt(0);
                    currentInput.setLength(0);
                    break;

                case "=":
                    num2 = Double.parseDouble(display.getText());
                    result = calculate(num1, num2, operator);
                    displayResult(result);
                    isNewOp = true;
                    break;

                case "√":
                    result = Math.sqrt(Double.parseDouble(display.getText()));
                    displayResult(result);
                    break;

                case "x²":
                    result = Math.pow(Double.parseDouble(display.getText()), 2);
                    displayResult(result);
                    break;

                case "sin":
                    result = Math.sin(Math.toRadians(Double.parseDouble(display.getText())));
                    displayResult(result);
                    break;

                case "cos":
                    result = Math.cos(Math.toRadians(Double.parseDouble(display.getText())));
                    displayResult(result);
                    break;

                case "tan":
                    result = Math.tan(Math.toRadians(Double.parseDouble(display.getText())));
                    displayResult(result);
                    break;

                case "log":
                    result = Math.log10(Double.parseDouble(display.getText()));
                    displayResult(result);
                    break;

                default:
                    if (isNewOp) {
                        currentInput.setLength(0);
                        isNewOp = false;
                    }
                    currentInput.append(command);
                    display.setText(currentInput.toString());
                    break;
            }
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    private double calculate(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return b != 0 ? a / b : 0;
            case '%': return a % b;
            default: return 0;
        }
    }

    private void displayResult(double res) {
        DecimalFormat df = new DecimalFormat("#.######");
        display.setText(df.format(res));
        currentInput.setLength(0);
        currentInput.append(df.format(res));
    }

    public static void main(String[] args) {
        new SmartCalculator();
    }
}
