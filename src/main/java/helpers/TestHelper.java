package helpers;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface TestHelper {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static final Gson gson = new Gson();
    public static final OkHttpClient client = new OkHttpClient();
    public static final String AuthorizationHeader = "Authorization";

    int i = new Random().nextInt(5000);

}
