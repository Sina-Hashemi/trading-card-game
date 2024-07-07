package com.example.model;
import java.util.Random;

public class RandomPasswordGenerator {
    private final String password;

    public RandomPasswordGenerator() {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);

        String password = "";
        password += (char) (random.nextInt(10) + 33) ;
        password += (char) (random.nextInt(26) + 65) ;

        for (int i = 0; i < 4; i++) {
            password += (char) (random.nextInt(75) + 48);
        }
        password += (char) (random.nextInt(26) + 97) ;
        password += (char) (random.nextInt(10) + 48) ;
        for (int i = 0; i < 2; i++) {
            password += (char) (random.nextInt(75) + 48);
        }
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
