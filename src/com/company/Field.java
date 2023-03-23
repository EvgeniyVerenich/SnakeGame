package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Field extends JPanel implements ActionListener {

    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 20;
    private Image dot;
    private Image apple;
    private Image coinImage;
    private int apple_x;
    private int apple_y;
    private int[] x_array = new int[ALL_DOTS];
    private int[] y_array = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left;
    private boolean right = true;
    private boolean up;
    private boolean down;
    private boolean inGame = true;
    private int score;
    private Coin coin;

    public Field(){
        setBackground(Color.BLACK);
        loadImages();
        InitGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void InitGame(){
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x_array[i] = 48 - i * DOT_SIZE;
            y_array[i] = 48;
        }
        timer = new Timer(250, this);
        timer.start();
        createApple();
        createCoin();

    }

    private void createCoin() {
        coin = new Coin(new Random().nextInt(20) * DOT_SIZE,new Random().nextInt(20) * DOT_SIZE);
    }

    private void createApple() {
        apple_x = new Random().nextInt(20) * DOT_SIZE;
        apple_y = new Random().nextInt(20) * DOT_SIZE;
    }

    public void loadImages(){
        ImageIcon iia = new ImageIcon("D:\\работы\\javalabs\\SnakeGame\\src\\com\\company\\Images\\apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("D:\\работы\\javalabs\\SnakeGame\\src\\com\\company\\Images\\dot.png");
        dot = iid.getImage();
        ImageIcon iic = new ImageIcon("D:\\работы\\javalabs\\SnakeGame\\src\\com\\company\\Images\\coin.png");
        coinImage = iic.getImage();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (inGame){
            g.drawImage(apple,apple_x,apple_y,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot,x_array[i],y_array[i],this);
            }

        }else {
            String str = "Game over";
            String info = "Your score : " + score;
            g.setColor(Color.WHITE);
            g.drawString(str,125, SIZE/2);
            g.drawString(info, 125, 180);
        }
    }

    public void move(){
        for (int i = dots; i > 0; i--) {
            x_array[i] = x_array[i - 1];
            y_array[i] = y_array[i - 1];
        }
        if (left){
            x_array[0] -= DOT_SIZE;
        }
        if (right){
            x_array[0] += DOT_SIZE;
        }
        if (up){
            y_array[0] -= DOT_SIZE;
        }
        if (down){
            y_array[0] += DOT_SIZE;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollisions();
            checkCoin();
            move();
        }
        repaint();
    }

    public void checkCollisions(){
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x_array[0] == x_array[i] && y_array[0] == y_array[i]){
                inGame = false;
            }
        }

        if(x_array[0] > SIZE){
            inGame = false;
        }
        if(x_array[0] < 0){
            inGame = false;
        }
        if(y_array[0] > SIZE){
            inGame = false;
        }
        if(y_array[0] < 0){
            inGame = false;
        }

    }

    private void checkApple() {
        if (x_array[0] == apple_x && y_array[0] == apple_y){
            if (dots < ALL_DOTS - 1){
                dots++;
            }
            score = score + 10;
            createApple();
        }
    }

    private void checkCoin(){
        if(x_array[0] == coin.getCoin_x() && y_array[0] == coin.getCoin_y()){
            score = score + 100;
            createCoin();
        }
    }

    public boolean getStatus(){
        return inGame;
    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && !right){
                left = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && !left){
                right = true;
                up = false;
                down = false;
            }

            if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && !down){
                left = false;
                right = false;
                up = true;
            }

            if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && !up){
                left = false;
                right = false;
                down = true;
            }
        }
    }

}
