package com.example.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class ReturnCaptcha {

    public static ArrayList<String> captchaMaker() {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);


        int width = 200;
        int height = 40;
        int wordNumber = random.nextInt(3) + 5;
        String captchaAnswer = "";
        String captchaQuestion = "";
        for (int i = 0; i < wordNumber; i++) {
            captchaAnswer += (char) (random.nextInt(75) + 48);
        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 20));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(captchaAnswer, 0, 20);

        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");
            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }


            captchaQuestion += sb.toString() + "\n";


        }

        ArrayList<String> end = new ArrayList<>();
        end.add(captchaQuestion);
        end.add(captchaAnswer);

        return end;


    }
}