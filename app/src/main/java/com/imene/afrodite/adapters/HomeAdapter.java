package com.imene.afrodite.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.imene.afrodite.R;
import com.imene.afrodite.models.Cadeau;
import com.imene.afrodite.models.Item;

import java.util.ArrayList;

/*
 * Created by Sambhaji Karad on 04-Jan-18
 * Mobile 9423476192
 * Email sambhaji2134@gmail.com/
*/

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<Cadeau> mValues;
    private Context mContext;
    protected ItemListener mListener;

    public HomeAdapter(Context context, ArrayList<Cadeau> values, ItemListener itemListener) {
        mValues = values;
        mContext = context;
        mListener=itemListener;
    }

    public ItemListener getmListener() {
        return mListener;
    }

    public void setmListener(ItemListener mListener) {
        this.mListener = mListener;
    }

    public HomeAdapter(Context context, ArrayList<Cadeau> values) {
        mValues = values;
        mContext = context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;
        private ImageView imageView;
        private LinearLayout relativeLayout;
        private Cadeau item;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textview);
            imageView = (ImageView) v.findViewById(R.id.imageview);
            relativeLayout = (LinearLayout) v.findViewById(R.id.relativeLayout);
        }

        public void setData(Cadeau item) {
            this.item = item;
            textView.setText(item.getNom());
            if(item.getDrawable()==0)
            {
                imageView.setImageResource(R.drawable.parfumm);


            }else if (item.getDrawable()==1)
            {
                imageView.setImageResource(R.drawable.rougee);

            }else{
                imageView.setImageResource(R.drawable.itempanier);

            }
            relativeLayout.setBackgroundColor(Color.WHITE);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(Cadeau item);
    }
}