package com.sonhoai.sonho.miniframework.ulti;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sonho on 3/29/2018.
 */
//lang nghe sự kiện ontouch
public class InputHandler implements View.OnTouchListener{
    private State currentState;//mà hình hiện tại.
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    public void setCurrentState(State currentState){this.currentState = currentState;}
}
