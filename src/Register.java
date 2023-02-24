import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class Register {
    private String username;
    private char[] password;
    private boolean check1 = false, check2 = false, check3 = false;
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "slawa111111";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    public Register() throws SQLException {
    }

    public boolean checkPassword() {
        if (password.length > 6) {
            for (int k = 0; k < password.length; k++) {
                if (password[k] >= 48 && password[k] <= 57) {
                    check1 = true;
                } else if (password[k] >= 65 && password[k] <= 90) {
                    check2 = true;
                } else if (password[k] >= 97 && password[k] <= 122) {
                    check3 = true;
                }
            }
            if ((check1) && (check2) && (check3)) {
                GUI.registerSuccess.setText("Registration successful");
                return true;
            } else {
                GUI.registerSuccess.setText("Your password must include at least one capital letter, lower letter and digit.");
                return false;
            }
        } else {
            GUI.registerSuccess.setText("Registration error. Your password must be longer than 6 symbols.");
            return false;
        }
    }

    public void registerToDatabase() throws SQLException {
        String passwordString = new String(password);
        String encryptedPassword = Encryption.encrypt(passwordString);
        String sql = "insert into auth (username, password) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, encryptedPassword);
        preparedStatement.executeUpdate();
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}