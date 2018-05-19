package com.sonhoai.sonho.miniframework.ulti;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by sonho on 3/29/2018.
 */

public class UIButton {
    private Rect buttonRect;
    private boolean buttonDown = false;
    private Bitmap buttonImage, buttonDownImage;

    public UIButton(Rect buttonRect, boolean buttonDown, Bitmap buttonImage, Bitmap buttonDownImage) {
        this.buttonRect = buttonRect;
        this.buttonDown = buttonDown;
        this.buttonImage = buttonImage;
        this.buttonDownImage = buttonDownImage;
    }

    public void render(Painter p){
        Bitmap currentButtonImage = buttonDown ? buttonDownImage : buttonImage;

    }
}
