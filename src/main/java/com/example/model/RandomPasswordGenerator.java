package com.example.model;
import java.util.Random;

public class RandomPasswordGenerator {
    private final String password;
    // TODO - ehsan - config it

    public RandomPasswordGenerator() {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        String password = "";
        for (int i = 0; i < 10; i++) {
            password += (char) (random.nextInt(75) + 48);
        }
        System.out.println(password);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
