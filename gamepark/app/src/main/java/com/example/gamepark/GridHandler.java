package com.example.gamepark;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

public class GridHandler extends GridLayoutManager {
    public GridHandler(Context context, int spanCount) {
        super(context, spanCount);
    }
}
