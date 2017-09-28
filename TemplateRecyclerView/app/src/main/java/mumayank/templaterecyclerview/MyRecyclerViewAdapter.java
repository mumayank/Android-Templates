package mumayank.templaterecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mayank on 28-09-2017.
 */

class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // define view holder class
    // define it inside your recycler view adapter because it is tightly coupled
    private static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
        }
    }

    // define instance var
    private ArrayList<Integer> arrayList;

    // define constructor to init instance var
    MyRecyclerViewAdapter(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    // create view if not available
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, null);
        return new MyViewHolder(view);
    }

    // setup individual views
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        int value = arrayList.get(position);
        myViewHolder.textView.setText(value + "");
    }

    // return total items
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}