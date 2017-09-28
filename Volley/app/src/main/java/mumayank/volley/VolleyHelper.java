package mumayank.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Mayank on 29-09-2017.
 */

class VolleyHelper {

    // interface
    interface View {
        void VolleySuccess(JSONObject jsonObject);
        void VolleyFailure(VolleyError e);
    }

    // singleton starts
    private static VolleyHelper INSTANCE;
    private RequestQueue queue;
    private JsonRequest jsonRequest;
    private VolleyHelper.View view;

    private VolleyHelper() {
    }

    static synchronized VolleyHelper getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new VolleyHelper();
            INSTANCE.queue = Volley.newRequestQueue(context);
        }

        INSTANCE.view = (VolleyHelper.View) context;

        return INSTANCE;
    }
    // singleton ends

    void get(String finalUrl) {

        jsonRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (view != null) {
                            view.VolleySuccess(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (view != null) {
                            view.VolleyFailure(error);
                        }
                    }
                });

        queue.add(jsonRequest);

    }

    void cancel() {
        jsonRequest.cancel();
    }
}
