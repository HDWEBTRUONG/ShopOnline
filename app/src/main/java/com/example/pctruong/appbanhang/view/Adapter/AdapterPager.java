package com.example.pctruong.appbanhang.view.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.pctruong.appbanhang.R;

/**
 * Created by PCTruong on 13/06/2018.
 */

public class AdapterPager extends PagerAdapter {
    int [] image={R.drawable.categorie,R.drawable.home};
    Context context;

    public AdapterPager(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_viewpager, container, false);
        ImageView imageView=layout.findViewById(R.id.imgPager);
        imageView.setImageResource(image[position]);
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
