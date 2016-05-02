package com.taofang.webapi.mock;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Mytest10 {
    public static Random random = new Random();
    public static int r(int min,int max){
        int num=0;
        num=random.nextInt(max-min)+min;
        return num;
    }
    public static void main(String[] args) throws IOException {
        java.util.List<String> vcodes = new ArrayList<>();
        for(int j=0; j<50; j++){
            // TODO Auto-generated method stub
            //在内存中创建一副图片
            int w=120;
            int h=50;
            BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            //在图片上画一个矩形当背景
            Graphics g = img.getGraphics();
            g.setColor(new Color(r(50,250),r(50,250),r(50,250)));
            g.fillRect(0, 0, w, h);

            String str = "aqzxswedcfrvgtbhyujklp23456789";
            String codeName = "";
            for(int i=0;i<4;i++){
                g.setColor(new Color(r(50,180),r(50,180),r(50,180)));
                g.setFont(new Font("黑体",Font.PLAIN,40));
                char c = str.charAt(r(0,str.length()));
                g.drawString(String.valueOf(c), 10+i*30, r(h-30,h));
                codeName += String.valueOf(c);
            }
            vcodes.add("\"" + codeName + "\"");
            //画随机线
            for(int i=0;i<25;i++){
                g.setColor(new Color(r(50,180),r(50,180),r(50,180)));
                g.drawLine(r(0,w), r(0,h),r(0,w), r(0,h));
            }
            //把内存中创建的图像输出到文件中
            File file =new File("/Users/Remilia/workspace/OhMyMoney/taofang/taofang-webapi/src/main/webapp/image/vcode/" + codeName + ".png");
            ImageIO.write(img, "png", file);
            System.out.println("图片输出完成");
        }
        System.out.println(vcodes);
    }

}
