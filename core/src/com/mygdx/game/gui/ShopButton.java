package com.mygdx.game.gui;

/**
 * Created by Whizzpered on 30.05.2018.
 * Only for uncommercial and learnin use <3;
 */
public abstract class ShopButton extends Button {

    public int price;
    public float coef, ch;
    private Description desc;
    public int limit = Integer.MAX_VALUE, clicked = 0;

    public ShopButton(int x, int y, String name) {
        super(x, y, name);
        setWidth(150);
    }

    public Description getDesc() {
        return desc;
    }

    public void setDesc(String[] text) {
        desc = new Description(this);
        desc.setText(text);
        desc.update();
    }

    public abstract void action();


    @Override
    public boolean pressed(int x, int y) {
        if(active) {
            boolean b = super.pressed(x, y);
            desc.update();
            if (b) clicked++;
            if (clicked >= limit) getDesc().setText("MAXED OUT", 0);
            return b;
        }
        return false;
    }
}
