import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;

public class App {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "slawa111111";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

    public App() throws SQLException {
    }

    public void Register() throws SQLException {
        boolean check1 = false, check2 = false, check3 = false;
        String username1 = GUI.userTextReg.getText();
        char[] password1 = GUI.passwordTextReg.getText().toCharArray();
        if (password1.length > 6) {
            for (int k = 0; k < password1.length; k++) {
                if (password1[k] >= 48 && password1[k] <= 57) {
                    check1 = true;
                } else if (password1[k] >= 65 && password1[k] <= 90) {
                    check2 = true;
                } else if (password1[k] >= 97 && password1[k] <= 122) {
                    check3 = true;
                }
            }
            if ((check1 == true) && (check2 == true) && (check3 == true)) {
                GUI.registerSuccess.setText("Registration successful");
                String password = new String(password1);
                String encryptedPassword = encrypt(password);
                String sql = "insert into auth (username, password) values (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username1);
                preparedStatement.setString(2, encryptedPassword);
                preparedStatement.executeUpdate();
            } else {
                GUI.registerSuccess.setText("Your password must include at least one capital letter, lower letter and digit.");
            }
        } else {
            GUI.registerSuccess.setText("Registration error. Your password must be longer than 6 symbols.");
        }
    }

    int att = 3;

    public void Login() throws SQLException {
        String usernameInput = GUI.userTextLog.getText();
        String passwordInput = GUI.passwordTextLog.getText();
        String encryptedPassword = encrypt(passwordInput);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from auth");
        boolean flag = false;
        while (resultSet.next()) {
            String tempUsername = resultSet.getString(1);
            String tempPassword = resultSet.getString(2);
            if (usernameInput.equals(tempUsername) && encryptedPassword.equals(tempPassword)) {
                GUI.loginSuccess.setText("Authentication successful!");
                flag = true;
                break;
            }
        }
        if (flag == false && att == 0) {
            GUI.loginSuccess.setText("0 Attempts left. Blocked.");
            System.exit(0);
        } else if (flag == false) {
            GUI.loginSuccess.setText("Invalid login or password, try again. Attempts left: " + att);
            att--;
            System.out.println(att);
        }
    }

    public static String encrypt(String source) {
        String md5;
        try {
            MessageDigest md5Encryption = MessageDigest.getInstance("MD5");
            md5Encryption.update(source.getBytes(), 0, source.length());
            md5 = new BigInteger(1, md5Encryption.digest()).toString(16);
        } catch (Exception ex) {
            return null;
        }
        return md5;
    }
}
