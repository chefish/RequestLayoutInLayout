package com.fish.requestlayoutinlayout.detect;

import android.view.View;

class Element {
    public Element(int depth, View view) {
        this.depth = depth;
        this.view = view;
    }

    int depth;
    View view;
}