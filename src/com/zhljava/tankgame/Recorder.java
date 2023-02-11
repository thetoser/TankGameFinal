package com.zhljava.tankgame;

import java.io.*;
import java.util.Vector;

public class Recorder {
    private static int allEnemyNum = 0;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\myRecord.txt";
    private static Vector<Enemy> enemies = null;
    private static Vector<Node> nodes = new Vector<>();

    //读取文件，恢复相关信息
    public static Vector<Node> getNodesAndEnemyRecord() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyNum = Integer.parseInt(br.readLine());
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]),
                        Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }

    public static void saveRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyNum + "\r\n");
            //敌人坦克坐标和方向
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                if (enemy.isLive) {
                    String record = enemy.getX() + " " + enemy.getY() + " " + enemy.getDirect();
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setEnemies(Vector<Enemy> enemies) {
        Recorder.enemies = enemies;
    }

    public static int getAllEnemyNum() {
        return allEnemyNum;
    }

    public static void setAllEnemyNum(int allEnemyNum) {
        Recorder.allEnemyNum = allEnemyNum;
    }

    public static void addAllEnemyNum() {
        Recorder.allEnemyNum++;
    }
}
