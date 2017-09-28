package mumayank.mvp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayank on 28-09-2017.
 */

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // view holder
    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sender)
        TextView sender;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.body)
        TextView body;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    // RV methods:
    private ArrayList<Item> items;

    public CustomRecyclerViewAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CustomViewHolder customViewHolder = (CustomViewHolder) holder;
        Item item = items.get(position);
        customViewHolder.sender.setText(item.getSender());
        customViewHolder.date.setText(item.getDate());
        customViewHolder.body.setText(item.getBody());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
