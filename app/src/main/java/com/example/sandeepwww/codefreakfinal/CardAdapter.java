package com.example.sandeepwww.codefreakfinal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sandeepwww on 6/7/15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<ListItem> mItems;

    public CardAdapter(List<ListItem> Items) {
        super();

        mItems=Items;

        //System.out.println("SIZE = " + mItems.size());

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ListItem nature = mItems.get(i);
        viewHolder.tvName.setText(nature.getmName());
        viewHolder.tvSite.setText(nature.getmSite());
        viewHolder.imgIcon.setImageResource(nature.getmIcon());
        viewHolder.tvEndTime.setText(nature.getmEndtime());
        viewHolder.tvDuration.setText(nature.getmDuration());

    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size();
        }
        else
        {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgIcon;
        public TextView tvName;
        public TextView tvSite;
        public TextView tvDuration;
        public TextView tvEndTime;

        public ViewHolder(View itemView) {
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.icon);
            tvName = (TextView) itemView.findViewById(R.id.contest);
            tvSite= (TextView) itemView.findViewById(R.id.site);
            tvDuration = (TextView) itemView.findViewById(R.id.duration);
            tvEndTime= (TextView) itemView.findViewById(R.id.endtime);

        }

    }
}
