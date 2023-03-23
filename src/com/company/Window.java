package com.company;

import javax.swing.*;

public class Window extends JFrame {

    public Window(){
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 345);
        setLocation(400, 400);
        add(new Field());
        setVisible(true);
    }

}
