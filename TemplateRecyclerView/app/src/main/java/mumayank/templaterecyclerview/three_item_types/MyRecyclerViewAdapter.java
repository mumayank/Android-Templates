package mumayank.templaterecyclerview.three_item_types;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mumayank.templaterecyclerview.R;

/**
 * Created by Mayank on 28-09-2017.
 */

class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // define view holder class
    // define it inside your recycler view adapter because it is tightly coupled
    private static class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView textView;
        MyViewHolder1(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
        }
    }

    private static class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView textView;
        MyViewHolder2(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
        }
    }

    private static class MyViewHolder3 extends RecyclerView.ViewHolder {
        TextView textView;
        MyViewHolder3(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
        }
    }

    // define instance var
    private ArrayList<MyItem> arrayList;

    // define int constants for each view type
    private final int VIEW_TYPE_1 = 100;
    private final int VIEW_TYPE_2 = 200;
    private final int VIEW_TYPE_3 = 300;

    // define constructor to init instance var
    MyRecyclerViewAdapter(ArrayList<MyItem> arrayList) {
        this.arrayList = arrayList;
    }

    // create view if not available
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_1:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.three_item_types_left, parent, false);
                return new MyViewHolder1(view);
            case VIEW_TYPE_2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.three_item_types_right, parent, false);
                return new MyViewHolder2(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.three_item_types_centre, parent, false);
                return new MyViewHolder3(view);
        }
    }

    // setup individual views
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_1:
                MyViewHolder1 myViewHolder1 = (MyViewHolder1) holder;
                int value = arrayList.get(position).getMyVal();
                myViewHolder1.textView.setText(value + "");
                break;
            case VIEW_TYPE_2:
                MyViewHolder2 myViewHolder2 = (MyViewHolder2) holder;
                value = arrayList.get(position).getMyVal();
                myViewHolder2.textView.setText(value + "");
                break;
            default:
                MyViewHolder3 myViewHolder3 = (MyViewHolder3) holder;
                value = arrayList.get(position).getMyVal();
                myViewHolder3.textView.setText(value + "");
                break;
        }
    }

    // return total items
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // setup a new method
    @Override
    public int getItemViewType(int position) {
        switch (arrayList.get(position).getMyEnum()) {
            case LEFT:
                return VIEW_TYPE_1;
            case RIGHT:
                return VIEW_TYPE_2;
            default:
                return VIEW_TYPE_3;
        }
    }
}