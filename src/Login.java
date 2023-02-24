import java.sql.*;

public class Login {
    private String username;
    private String password;
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "slawa111111";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    TelegramLog log = new TelegramLog();
    private int att = 3;

    public Login() throws SQLException {
    }

    public void login() throws SQLException {
        String encryptedPassword = Encryption.encrypt(password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from auth");
        boolean flag = false;
        while (resultSet.next()) {
            String tempUsername = resultSet.getString(1);
            String tempPassword = resultSet.getString(2);
            if (encryptedPassword != null && username.equals(tempUsername) && encryptedPassword.equals(tempPassword)) {
                GUI.loginSuccess.setText("Authentication successful!");
                TelegramLog.sendToTelegram("Authentication successful. Username: " + username);
                flag = true;
                break;
            }
        }
        if (!flag && att == 0) {
            GUI.loginSuccess.setText("0 Attempts left. Blocked.");
            TelegramLog.sendToTelegram("0 Attempts left. Blocked. Username: " + username);
            System.exit(0);
        } else if (!flag) {
            GUI.loginSuccess.setText("Invalid login or password, try again. Attempts left: " + att);
            TelegramLog.sendToTelegram("Invalid login or password. Attempts left: " + att + " Username: " + username);
            att--;
            System.out.println(att);
        }
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
