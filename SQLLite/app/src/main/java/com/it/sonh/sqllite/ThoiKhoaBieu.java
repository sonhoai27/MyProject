package com.it.sonh.sqllite;

/**
 * Created by sonho on 3/8/2018.
 */

public class ThoiKhoaBieu {
    private String Id;
    private String Name;

    public ThoiKhoaBieu(String id, String name) {
        Id = id;
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
