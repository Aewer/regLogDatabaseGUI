import java.sql.*;

public class Recover {
    EmailSender emailSender = new EmailSender();
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "slawa111111";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    String usernameRec;
    String newPassword;
    public static String code = String.valueOf((int)(100000 + Math.random() * 899999));
    Statement statement = connection.createStatement();
    Register register = new Register();

    public Recover() throws SQLException {
    }

    public void sendCode() throws SQLException {
        usernameRec = GUI.userTextRec.getText();
        ResultSet resultSet = statement.executeQuery("select * from auth");
        while (resultSet.next()) {
            String tempUsername = resultSet.getString(1);
            if (usernameRec.equals(tempUsername)) {
                emailSender.emailSender(usernameRec);
            }
        }
        GUI.loginSuccess.setText("Enter recovery code into password field");
    }
    public void checkCode() {
        String check = GUI.codeText.getText();
        if(check.equals(code))
        {
            GUI.recoverSuccess.setText("Correct code. Enter new password and press confirm password");
        }
    }
    public void makeNewPassword() throws SQLException {
                newPassword = GUI.newPasswordText.getText();
                    String encryptedPassword = Encryption.encrypt(newPassword);
                    String sql = "update auth set password=(?) where username=(?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, encryptedPassword);
                    preparedStatement.setString(2, usernameRec);
                    preparedStatement.executeUpdate();
                    TelegramLog.sendToTelegram("Recovery successful. Username: " + usernameRec);
                    GUI.recoverSuccess.setText("Recovery successful");
        }


}
