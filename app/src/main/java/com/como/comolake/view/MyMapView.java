package com.como.comolake.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.como.comolake.R;
import com.como.comolake.activity.MapActivity;

import org.osmdroid.views.MapView;

/**
 * Created by prolance on 6/22/16.
 */
public class MyMapView extends MapView {

    public float fXPos;
    public float fYPos;

    Context context;

    public MyMapView(Context context) {
        super(context);
    }

    public MyMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        this.context = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        fXPos = motionEvent.getX();
        fYPos = motionEvent.getY();
        ((MapActivity) context).findViewById(R.id.layout_popup).setVisibility(View.GONE);
        return super.onTouchEvent(motionEvent);
    }
}
