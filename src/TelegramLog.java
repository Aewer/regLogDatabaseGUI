import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class TelegramLog {
    public static void sendToTelegram(String text) {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        String apiToken = "5672191839:AAHknPz4Wfj2sD7sjVIfkg56DEbyCvW5d70";

        String chatId = "1852555022";

        urlString = String.format(urlString, apiToken, chatId, text);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}