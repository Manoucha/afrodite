 package com.imene.afrodite.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.imene.afrodite.R;
import com.imene.afrodite.views.DemoInfiniteAdapter;
import com.rd.PageIndicatorView;

import java.util.ArrayList;


 public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    ViewPager viewPager;
     //ViewPager Dynamic
     LoopingViewPager viewPagerD;
     DemoInfiniteAdapter adapterD;
     PageIndicatorView indicatorView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager = view.findViewById(R.id.viewpager);


        //ViewPager Dynamic ------------
        viewPagerD = view.findViewById(R.id.viewpager);
        indicatorView = view.findViewById(R.id.indicator);



        adapterD = new DemoInfiniteAdapter(getContext(), createDummyItems(), true);
        viewPagerD.setAdapter(adapterD);



        viewPagerD.setIndicatorPageChangeListener(new LoopingViewPager.IndicatorPageChangeListener() {
            @Override
            public void onIndicatorProgress(int selectingPosition, float progress) {
                indicatorView.setProgress(selectingPosition, progress);
            }

            @Override
            public void onIndicatorPageChange(int newIndicatorPosition) {
                indicatorView.setSelection(newIndicatorPosition);
            }
        });



        return view;
    }
     private ArrayList<Integer> createDummyItems () {
         ArrayList<Integer> items = new ArrayList<>();
         items.add(0, 1);
         items.add(1, 2);
         items.add(2, 3);
         items.add(3, 4);
         items.add(4, 5);
         items.add(5, 6);
         items.add(6, 0);
         return items;
     }

 }