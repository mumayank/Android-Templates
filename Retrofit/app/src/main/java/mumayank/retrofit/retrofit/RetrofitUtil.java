package mumayank.retrofit.retrofit;

/**
 * Created by Mayank on 29-09-2017.
 */

public class RetrofitUtil {

    public static final String BASE_URL = "https://newsapi.org/";

    public static RetrofitInterface getInterface() {
        return RetrofitClient.getClient(BASE_URL).create(RetrofitInterface.class);
    }

}