package com.everis.hackaton.metropolitanoapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jquirogl on 19/10/2017.
 */

public class CustomAdapter extends PagerAdapter {

    private int[] imagenes = {R.drawable.estandar, R.drawable.universitaria, R.drawable.escolar};
    private LayoutInflater inflater;
    private Context context;

    public CustomAdapter(Context ctx){
        this.context=ctx;
    }

    @Override
    public int getCount() {
        return imagenes.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.swipe,container,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.myImageView);
        //TextView textView = (TextView)view.findViewById(R.id.myTextView);
        imageView.setImageResource(imagenes[position]);
        //textView.setText("Image " + position);
        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();

    }


}
