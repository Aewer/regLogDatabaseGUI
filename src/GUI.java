import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class GUI {
    App app = new App();

    public static JFrame frameReg;
    private static JPanel panelReg;
    private static JLabel userLabelReg;
    public static JTextField userTextReg;
    private static JLabel passwordLabelReg;
    public static JPasswordField passwordTextReg;
    private static JButton buttonGoToLogin;
    private static JButton buttonRegister;
    public static JLabel registerSuccess;


    public static JFrame frameLog;
    private static JPanel panelLog;
    private static JLabel userLabelLog;
    public static JTextField userTextLog;
    private static JLabel passwordLabelLog;
    public static JPasswordField passwordTextLog;
    private static JButton buttonLogin;
    private static JButton buttonGoToRegister;
    public static JLabel loginSuccess;

    public GUI() throws SQLException {
    }

    public void register() {
        frameReg = new JFrame();
        panelReg = new JPanel();
        frameReg.setSize(450, 200);
        frameReg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameReg.add(panelReg);

        panelReg.setLayout(null);

        userLabelReg = new JLabel("User");
        userLabelReg.setBounds(10, 20, 80, 25);
        panelReg.add(userLabelReg);

        userTextReg = new JTextField(20);
        userTextReg.setBounds(100, 20, 165, 25);
        panelReg.add(userTextReg);

        passwordLabelReg = new JLabel("Password");
        passwordLabelReg.setBounds(10, 50, 80, 25);
        panelReg.add(passwordLabelReg);

        passwordTextReg = new JPasswordField();
        passwordTextReg.setBounds(100, 50, 165, 25);
        panelReg.add(passwordTextReg);

        buttonRegister = new JButton("Register");
        buttonRegister.setBounds(10, 80, 100, 25);
        buttonRegister.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            app.Register();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );
        panelReg.add(buttonRegister);

        buttonGoToLogin = new JButton("Log into existing account");
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

        registerSuccess = new JLabel("");
        registerSuccess.setBounds(10, 110, 550, 25);
        panelReg.add(registerSuccess);

        frameReg.setVisible(true);
    }

    public void login() {
        frameLog = new JFrame();
        panelLog = new JPanel();

        frameLog.setSize(450, 200);
        frameLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLog.add(panelLog);

        panelLog.setLayout(null);

        userLabelLog = new JLabel("User");
        userLabelLog.setBounds(10, 20, 80, 25);
        panelLog.add(userLabelLog);

        userTextLog = new JTextField(20);
        userTextLog.setBounds(100, 20, 165, 25);
        panelLog.add(userTextLog);

        passwordLabelLog = new JLabel("Password");
        passwordLabelLog.setBounds(10, 50, 80, 25);
        panelLog.add(passwordLabelLog);

        passwordTextLog = new JPasswordField();
        passwordTextLog.setBounds(100, 50, 165, 25);
        panelLog.add(passwordTextLog);

        buttonLogin = new JButton("Login");
        buttonLogin.setBounds(10, 80, 80, 25);
        buttonLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            app.Login();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
        );
        panelLog.add(buttonLogin);
        buttonGoToRegister = new JButton("Register a new account");
        buttonGoToRegister.setBounds(10, 130, 200, 25);
        buttonGoToRegister.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frameLog.setVisible(false);
                        register();
                    }
                }
        );
        panelLog.add(buttonGoToRegister);

        loginSuccess = new JLabel("");
        loginSuccess.setBounds(10, 110, 300, 25);
        panelLog.add(loginSuccess);

        frameLog.setVisible(true);
    }
}