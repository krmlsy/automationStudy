import io.restassured.response.Response;
import io.restassured.specification.RequestSenderOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.when;

public class APP {

    public static void main(String[] args) throws IOException {


        System.out.println("hello");

        String url = "https://www.trendyol.com/sr?cid=567580,567581";

       Response response = when().get(url);

        System.out.println(response.getStatusCode());

        FileWriter pw = new FileWriter("companyResponseCodes.csv", true);

        pw.append(url+";"+response.getStatusCode()+"\n");

        pw.flush();
        pw.close();


        HttpURLConnection c=
                (HttpURLConnection)new
                        URL("https://www.tutorialspoint.com/questions/index.php")
                        .openConnection();
        // set the HEAD request with setRequestMethod
        c.setRequestMethod("HEAD");
        // connection started and get response code
        c.connect();
        int r = c.getResponseCode();

        System.out.println("r=" + r);
    }
}
