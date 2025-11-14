package br.com.infnet.client;

import java.util.concurrent.ThreadLocalRandom;

public class EmailBlackListClient implements BlackList {
    @Override
    public boolean isBlackListed(String email) {
        long delay = ThreadLocalRandom.current().nextLong(1_000, 4_001);
        delay = 0;
        try{
            Thread.sleep(delay);
        } catch(InterruptedException e){
            Thread.currentThread().interrupt();
            return false;
        }
        double chance = ThreadLocalRandom.current().nextDouble();
        System.out.println("random: " + (chance < 0.30));
        return chance < 0.30;
    }
}
