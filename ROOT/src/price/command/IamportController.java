package price.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@WebServlet("/IamportController")
public class IamportController extends HttpServlet {
    private static final String IMP_API_KEY = "1180868864152861";
    private static final String IMP_SECRET_KEY = "97VXdqpMFuOzah0OX9s7RKXTazF8PH4e7bZFKMP4BDzSgHrVe9SJs3yoLIS20EoGdrhb8OCqZk4ZHhJj";
    private static final String IMP_HOST = "https://api.iamport.kr";
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JSONObject tokenData = getAccessToken();
            request.setAttribute("tokenData", tokenData);

            request.getRequestDispatcher("/reservation.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private JSONObject getAccessToken() throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = HttpUrl.parse(IMP_HOST + "/users/getToken").newBuilder()
                .addQueryParameter("imp_key", IMP_API_KEY)
                .addQueryParameter("imp_secret", IMP_SECRET_KEY)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();

        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(jsonData);
            return (JSONObject) jsonObject.get("response");
        } catch (ParseException e) {
            throw new IOException("JSON 파싱 오류", e);
        }
    }
}
