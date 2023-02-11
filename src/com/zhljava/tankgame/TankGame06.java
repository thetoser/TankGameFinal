package com.zhljava.tankgame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * 增加功能 :
 * 记录玩家的总成绩
 * 记录退出游戏时敌人坦克坐标和方向
 * 可以选择开新游戏还是继续上一局游戏
 */
public class TankGame06 extends JFrame {
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TankGame06 tankGame01 = new TankGame06();
    }

    public TankGame06() {
        System.out.println("请选择 1: 新游戏 2: 继续上局");
        String key = scanner.next();

        mp = new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1300, 950);
        this.addKeyListener(mp);//让JFrame 监听mp的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.saveRecord();
                System.exit(0);
            }
        });
    }
}
