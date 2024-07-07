package com.example.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
public class BlockingTime {
    public static int n = 0;
    private static long timeSeconds = 0;

    public static int remainingTime() {
        readFromFile();
        return (int)(n * 5 - (TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - timeSeconds));
    }

    public static void reset() {
        n = 0;
        timeSeconds = 0;
        writeToFile();
    }

    public static void increase() {
        n += 1;
        timeSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        writeToFile();
    }

    private static void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/com/example/BlockingTime.txt"))) {
            writer.write(n + "\n" + timeSeconds + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/BlockingTime.txt"))) {
            n = Integer.parseInt(reader.readLine().trim());
            timeSeconds = Long.parseLong(reader.readLine().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
