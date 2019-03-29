package com.ei.mobilliumdemo.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.util.Log;

public class BaseProgress extends CircularProgressDrawable {

    public BaseProgress(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        this.setStrokeWidth(10);
        this.setCenterRadius(60);
        this.start();
    }


}
