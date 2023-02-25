import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GUI {
    App app = new App();
    public static JTextField userTextReg;
    public static JPasswordField passwordTextReg;
    public static JLabel registerSuccess;


    public static JTextField userTextLog;
    public static JPasswordField passwordTextLog;
    public static JLabel loginSuccess;

    public GUI() throws SQLException {
    }

    public void register() throws SQLException {
        JFrame frameReg = makeFrame("Register", 450, 200);
        JPanel panelReg = new JPanel();
        panelReg.setLayout(null);
        frameReg.add(panelReg);

        JLabel userLabelReg = makeLabel("User", 10, 20, 80, 25);
        panelReg.add(userLabelReg);

        userTextReg = makeTextField(20, 100, 20, 165, 25);
        panelReg.add(userTextReg);

        JLabel passwordLabelReg = makeLabel("Password", 10, 50, 80, 25);
        panelReg.add(passwordLabelReg);

        passwordTextReg = makePasswordField(100, 50, 165, 25);
        panelReg.add(passwordTextReg);

        JButton buttonRegister = new JButton("Register");
        buttonRegister.setBounds(10, 80, 100, 25);
        buttonRegister.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            app.registerSequence();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );
        panelReg.add(buttonRegister);

        JButton buttonGoToLogin = new JButton("Log into existing account");
        buttonGoToLogin.setBounds(10, 130, 200, 25);
        buttonGoToLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frameReg.setVisible(false);
                        login();
                    }
                }
        );
        panelReg.add(buttonGoToLogin);

        registerSuccess = makeLabel("", 10, 110, 550, 25);
        panelReg.add(registerSuccess);

        frameReg.setVisible(true);
    }

    public void login() {
        JFrame frameLog = makeFrame("Login", 450, 200);
        JPanel panelLog = new JPanel();
        panelLog.setLayout(null);
        frameLog.add(panelLog);

        JLabel userLabelLog = makeLabel("User", 10, 20, 80, 25);
        panelLog.add(userLabelLog);

        userTextLog = makeTextField(20, 100, 20, 165, 25);
        panelLog.add(userTextLog);

        JLabel passwordLabelLog = makeLabel("Password", 10, 50, 80, 25);
        panelLog.add(passwordLabelLog);

        passwordTextLog = makePasswordField(100, 50, 165, 25);
        panelLog.add(passwordTextLog);

        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBounds(10, 80, 80, 25);
        buttonLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            app.loginSequence();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );
        panelLog.add(buttonLogin);
        JButton buttonGoToRegister = new JButton("Register a new account");
        buttonGoToRegister.setBounds(10, 130, 200, 25);
        buttonGoToRegister.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frameLog.setVisible(false);
                        try {
                            register();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );
        panelLog.add(buttonGoToRegister);

        loginSuccess = makeLabel("", 10, 110, 300, 25);
        panelLog.add(loginSuccess);

        frameLog.setVisible(true);
    }
    public JFrame makeFrame(String name, int width, int height) {
        JFrame newFrame = new JFrame(name);
        JPanel newPanel = new JPanel();
        newFrame.setSize(width, height);
        newFrame.setLocationRelativeTo(null);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.add(newPanel);

        newPanel.setLayout(null);
        return newFrame;
    }
    public JLabel makeLabel(String text, int x, int y, int width, int height) {
        JLabel newLabel = new JLabel(text);
        newLabel.setBounds(x, y, width, height);
        return newLabel;
    }
    public JTextField makeTextField(int length, int x, int y, int width, int height) {
        JTextField newTextField = new JTextField(length);
        newTextField.setBounds(x, y, width, height);
        return newTextField;
    }
    public JPasswordField makePasswordField(int x, int y, int width, int height) {
        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setBounds(x, y, width, height);
        return newPasswordField;
    }

}