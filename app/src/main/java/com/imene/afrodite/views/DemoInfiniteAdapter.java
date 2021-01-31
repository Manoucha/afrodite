package com.imene.afrodite.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.imene.afrodite.R;

import java.util.ArrayList;

public class DemoInfiniteAdapter extends LoopingPagerAdapter<Integer> {

    private static final int VIEW_TYPE_NORMAL = 100;
    private static final int VIEW_TYPE_SPECIAL = 101;

    public DemoInfiniteAdapter(Context context, ArrayList<Integer> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected int getItemViewType(int listPosition) {
        if (itemList.get(listPosition) == 0) return VIEW_TYPE_SPECIAL;
        return VIEW_TYPE_NORMAL;
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        if (viewType == VIEW_TYPE_SPECIAL) return LayoutInflater.from(context).inflate(R.layout.item_special, container, false);
        return LayoutInflater.from(context).inflate(R.layout.item_pager, container, false);
    }

    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
        if (viewType == VIEW_TYPE_SPECIAL) return;
        convertView.findViewById(R.id.image).setBackground(context.getResources().getDrawable(getBackground(listPosition)));
       // TextView description = convertView.findViewById(R.id.description);
       // description.setText(String.valueOf(itemList.get(listPosition)));
    }

    private int getBackground (int number) {
        switch (number) {
            case 0:
                return R.drawable.slider4;
            case 1:
                return R.drawable.yespic;
            case 2:
                return R.drawable.slider5;
            case 3:
                return R.drawable.nopic;
            case 4:
                return R.drawable.slider2;
            case 5:
                return R.drawable.slider1;
            default:
                return R.drawable.slider6;
        }
    }
}
