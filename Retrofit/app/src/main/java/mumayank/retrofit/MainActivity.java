package mumayank.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mumayank.retrofit.model.Article;
import mumayank.retrofit.model.ResponseItem;
import mumayank.retrofit.retrofit.RetrofitInterface;
import mumayank.retrofit.retrofit.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;

    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        retrofitInterface = RetrofitUtil.getInterface();
        fetchNews();
    }

    private void fetchNews() {

        retrofitInterface.getResponseItems().enqueue(new Callback<ResponseItem>() {
            @Override
            public void onResponse(Call<ResponseItem> call, Response<ResponseItem> response) {

                if(response.isSuccessful()) {
                    for (Article articleItem : response.body().getArticles()) {
                        textView.append(articleItem.getTitle() + "\n" + articleItem.getDescription() + "\n\n");
                    }
                }else {
                    Toast.makeText(MainActivity.this, "error = " + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseItem> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error = " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
