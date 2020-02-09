import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHTTPTest {

    public static void main(String[] args) {
        //Βάλετε το κλειδί σας εδώ
        String key = "V-LthCRakZqR75s3zhi2";
        String urlToCall
                = "https://www.quandl.com/api/v3/datasets/WWDI/GRC_NY_GDP_MKTP_CN.json?api_key="+key;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(urlToCall).build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseString = response.body().string();
                System.out.println(responseString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
