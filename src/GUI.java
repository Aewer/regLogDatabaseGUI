import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GUI {
    App app = new App();
    Recover recover = new Recover();
    public static JTextField userTextReg;
    public static JPasswordField passwordTextReg;
    public static JLabel registerSuccess;


    public static JTextField userTextLog;
    public static JPasswordField passwordTextLog;
    public static JLabel loginSuccess;

    public static JTextField codeText;
    public static JPasswordField newPasswordText;
    public static JLabel recoverSuccess;
    public static JTextField userTextRec;

    public GUI() throws SQLException {
    }

    public void register() throws SQLException {
        JFrame frameReg = makeFrame("Register", 500, 200);
        JPanel panelReg = new JPanel();
        panelReg.setLayout(null);
        frameReg.add(panelReg);

        JLabel userLabelReg = makeLabel("User", 10, 20, 80, 25);
        panelReg.add(userLabelReg);

        userTextReg = makeTextField(20, 100, 20, 200, 25);
        panelReg.add(userTextReg);

        JLabel passwordLabelReg = makeLabel("Password", 10, 50, 80, 25);
        panelReg.add(passwordLabelReg);

        passwordTextReg = makePasswordField(100, 50, 200, 25);
        panelReg.add(passwordTextReg);

        registerSuccess = makeLabel("", 10, 110, 550, 25);
        panelReg.add(registerSuccess);

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


        frameReg.setVisible(true);
    }

    public void login() {
        JFrame frameLog = makeFrame("Login", 500, 200);
        JPanel panelLog = new JPanel();
        panelLog.setLayout(null);
        frameLog.add(panelLog);

        JLabel userLabelLog = makeLabel("User", 10, 20, 80, 25);
        panelLog.add(userLabelLog);

        userTextLog = makeTextField(20, 100, 20, 200, 25);
        panelLog.add(userTextLog);

        JLabel passwordLabelLog = makeLabel("Password", 10, 50, 80, 25);
        panelLog.add(passwordLabelLog);

        passwordTextLog = makePasswordField(100, 50, 200, 25);
        panelLog.add(passwordTextLog);

        loginSuccess = makeLabel("", 10, 110, 300, 25);
        panelLog.add(loginSuccess);


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

        JButton recoverPasswordButton = new JButton("Recover password");
        recoverPasswordButton.setBounds(250, 130, 180, 25);
        recoverPasswordButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frameLog.setVisible(false);
                        recoverPassword();
                    }
                }
        );
        panelLog.add(recoverPasswordButton);

        frameLog.setVisible(true);

    }
    public void recoverPassword() {
        JFrame frameRec = makeFrame("Recover", 500, 200);
        JPanel panelLog = new JPanel();
        panelLog.setLayout(null);
        frameRec.add(panelLog);

        JLabel userLabelLog = makeLabel("User", 10, 20, 80, 25);
        panelLog.add(userLabelLog);

        userTextRec = makeTextField(20, 100, 20, 165, 25);
        panelLog.add(userTextRec);

        JLabel codeLabel = makeLabel("Code", 10, 50, 80, 25);
        panelLog.add(codeLabel);

        codeText = makeTextField(20, 100, 50, 165, 25);
        panelLog.add(codeText);

        JLabel newPasswordLabel = makeLabel("Password", 10, 80, 80, 25);
        panelLog.add(newPasswordLabel);

        newPasswordText = makePasswordField(100, 80, 165, 25);
        panelLog.add(newPasswordText);

        recoverSuccess = makeLabel("Enter your email into the user field and press send code to send recovery code", 10, 110, 500, 25);
        panelLog.add(recoverSuccess);

        JButton buttonBackGoToLogin = new JButton("Go back to login");
        buttonBackGoToLogin.setBounds(10, 130, 200, 25);
        buttonBackGoToLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frameRec.setVisible(false);
                        login();
                    }
                }
        );
        panelLog.add(buttonBackGoToLogin);

        JButton sendCode = new JButton("Send code");
        sendCode.setBounds(300, 20, 150, 25);
        sendCode.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            recover.sendCode();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );
        panelLog.add(sendCode);

        JButton checkCode = new JButton("Check code");
        checkCode.setBounds(300, 50, 150, 25);
        checkCode.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        recover.checkCode();
                    }
                }
        );
        panelLog.add(checkCode);

        JButton confirmPassword = new JButton("Confirm password");
        confirmPassword.setBounds(300, 80, 150, 25);
        confirmPassword.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            recover.makeNewPassword();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );
        panelLog.add(confirmPassword);

        frameRec.setVisible(true);
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