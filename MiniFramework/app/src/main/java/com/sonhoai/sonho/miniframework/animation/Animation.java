package com.sonhoai.sonho.miniframework.animation;

/**
 * Created by sonho on 3/29/2018.
 */

public class Animation {
    private Frame[] frames; //chứa cac fram
    private double[] frameEndTime; //thời điểm kết thúc của các frame
    private int currentFrameIndex = 0;//chỉ số của frame hiện tại đang dc vẽ

    private double totalDuration = 0;//thời gian tồng cộng của 1 frame
    private double currentTime = 0;//

    public Animation(Frame... frames) {
        this.frames = frames;

        frameEndTime = new double[frames.length];

    }
}
