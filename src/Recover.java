import java.sql.*;

public class Recover {
    EmailSender emailSender = new EmailSender();
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "slawa111111";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    String usernameRec;
    public static String code = String.valueOf((int)(100000 + Math.random() * 899999));
    Register register = new Register();

    public Recover() throws SQLException {
    }

    public void sendCode() throws SQLException {
        String newPassword;
        usernameRec = GUI.userTextRec.getText();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from auth");
        while (resultSet.next()) {
            String tempUsername = resultSet.getString(1);
            if (usernameRec.equals(tempUsername) && emailSender.emailSender(usernameRec)) {
                GUI.recoverSuccess.setText("Enter new password to password field");
                while (true) {
                    if (GUI.passwordTextLog.getText().equals("")) {
                        GUI.loginSuccess.setText("Enter new password into password field");
                    } else {
                        newPassword = GUI.passwordTextLog.getText();
                        register.setPassword(newPassword.toCharArray());
                        if (register.checkPassword()) {
                            String passwordString = new String(newPassword);
                            String encryptedPassword = Encryption.encrypt(passwordString);
                            String sql = "update auth set password=(?) where username=(?)";
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setString(1, usernameRec);
                            preparedStatement.setString(2, encryptedPassword);
                            preparedStatement.executeUpdate();
                            TelegramLog.sendToTelegram("Recovery successful. Username: " + usernameRec);
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    public void checkCode() {

    }
}
