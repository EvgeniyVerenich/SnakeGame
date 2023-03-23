package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Coin {

    private int coin_x;
    private int coin_y;

    public Coin(int coin_x, int coin_y){
        this.coin_x = coin_x;
        this.coin_y = coin_y;
    }

    public int getCoin_y() {
        return coin_y;
    }

    public void setCoin_y(int coin_y) {
        this.coin_y = coin_y;
    }

    public int getCoin_x() {
        return coin_x;
    }

    public void setCoin_x(int coin_x) {
        this.coin_x = coin_x;
    }
}
