package mumayank.retrofit.retrofit;

import mumayank.retrofit.model.ResponseItem;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mayank on 29-09-2017.
 */

public interface RetrofitInterface {

    @GET("v1/articles?source=techcrunch&apiKey=fee0dc5363ec44c7ac2e8f2ab10f2bf1")
    Call<ResponseItem> getResponseItems();
}