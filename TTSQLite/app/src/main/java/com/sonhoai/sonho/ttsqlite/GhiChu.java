package com.sonhoai.sonho.ttsqlite;

/**
 * Created by sonho on 3/13/2018.
 */

public class GhiChu {
    private int ID;
    private String Content;

    public GhiChu(int ID, String content) {
        this.ID = ID;
        Content = content;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
