package mumayank.mvp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainInterfaces.View, MainInterfaces.Dialog {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private MainPresenter mainPresenter;

    private static final int PERMISSIONS_REQUEST_READ_SMS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);
        checkPermissionAndProceed();
    }

    private void checkPermissionAndProceed() {
        // check for permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
                MyAlertDialog.show(MainActivity.this, "Permission Required", "The app cannot work without accessing your SMS Inbox. Please allow access for the same.", "Proceed", "Exit", R.mipmap.ic_launcher);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, PERMISSIONS_REQUEST_READ_SMS);
            }
        } else {
            mainPresenter.fetchData();
        }
    }

    @Override
    public void showData(ArrayList<Item> items) {
        progressBar.setVisibility(View.GONE);

        // setup rv
        CustomRecyclerViewAdapter customRecyclerViewAdapter = new CustomRecyclerViewAdapter(items);
        recyclerView.setAdapter(customRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_SMS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mainPresenter.fetchData();
                } else {
                    permissionDeniedExit();
                }
            }
        }
    }

    @Override
    public void permissionGrantedProceed() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, PERMISSIONS_REQUEST_READ_SMS);
    }

    @Override
    public void permissionDeniedExit() {
        System.exit(0);
    }

    @Override
    protected void onDestroy() {
        mainPresenter.stopAsyncTask();
        super.onDestroy();
    }
}
