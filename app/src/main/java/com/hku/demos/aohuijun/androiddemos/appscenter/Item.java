package com.hku.demos.aohuijun.androiddemos.appscenter;

/**
 * Created by aohuijun on 16/2/2.
 */
public class Item {
    private int type;
    private int group;
    private int data;
    private boolean isChosen = false;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setIsChosen(boolean isChosen) {
        this.isChosen = isChosen;
    }
}
