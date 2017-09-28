package mumayank.volley;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mumayank.volley.model.Article;
import mumayank.volley.model.Response;

public class MainActivity extends AppCompatActivity implements VolleyHelper.View {

    @BindView(R.id.textView)
    TextView textView;

    private VolleyHelper volleyHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // create url
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(Constants.SCHEME)
                .authority(Constants.BASE_URL)
                .appendPath(Constants.BASE_URL_STEP_2)
                .appendPath(Constants.BASE_URL_STEP_3)
                .appendQueryParameter(Constants.SOURCE, Constants.SOURCE_VAL)
                .appendQueryParameter(Constants.API_KEY, Constants.API_KEY_VAL);
        String finalUrl = builder.build().toString();

        volleyHelper = VolleyHelper.getInstance(this);
        volleyHelper.get(finalUrl);
    }

    @Override
    public void VolleySuccess(JSONObject jsonObject) {
        Response response = new Gson().fromJson(jsonObject.toString(), Response.class);
        for (Article article : response.getArticles()) {
            textView.append(article.getAuthor() + "\n" + article.getDescription() + "\n\n");
        }
    }

    @Override
    public void VolleyFailure(VolleyError e) {
        Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        volleyHelper.cancel();
        super.onDestroy();
    }
}
