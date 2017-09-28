package mumayank.templaterecyclerview.simplerv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import mumayank.templaterecyclerview.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        // create data
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i=0; i<1000; i++) {
            arrayList.add(i);
        }

        // setup RV
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerViewAdapter recyclerAdapter = new MyRecyclerViewAdapter(arrayList);
        recyclerView.setAdapter(recyclerAdapter);
    }

}
