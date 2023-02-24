import java.sql.*;

public class App {
    public App() throws SQLException {
    }
    Register register = new Register();
    Login login = new Login();
    public void registerSequence() throws SQLException {
        register.setUsername(GUI.userTextReg.getText());
        register.setPassword(GUI.passwordTextReg.getText().toCharArray());
        if (register.checkPassword()) {
            register.registerToDatabase();
        }
    }
    public void loginSequence() throws SQLException {
        login.setUsername(GUI.userTextLog.getText());
        login.setPassword(GUI.passwordTextLog.getText());
        login.login();
    }
}
//5672191839:AAHknPz4Wfj2sD7sjVIfkg56DEbyCvW5d70
//1852555022
