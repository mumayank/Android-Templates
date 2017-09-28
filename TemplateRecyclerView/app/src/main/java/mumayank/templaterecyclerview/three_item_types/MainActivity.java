package mumayank.templaterecyclerview.three_item_types;

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
        ArrayList<MyItem> myItems = new ArrayList<>();
        for (int i=0; i<10; i++) {
            MyEnum myEnum;
            if (i<3) {
                myEnum = MyEnum.LEFT;
            } else if (i>6) {
                myEnum = MyEnum.RIGHT;
            } else {
                myEnum = MyEnum.CENTRE;
            }

            myItems.add(new MyItem(i, myEnum));
        }

        // setup RV
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyRecyclerViewAdapter recyclerAdapter = new MyRecyclerViewAdapter(myItems);
        recyclerView.setAdapter(recyclerAdapter);
    }

}
