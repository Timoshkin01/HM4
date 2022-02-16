package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 1000;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {260, 250, 270, 255, 245, 265, 275, 280};
    public static int[] heroesDamage = {20, 15, 10, 0, 10, 5, 20, 25};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Lucky", "Golem", "Berserk", "Thor"};
    public static int roundNumber;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefenceType);
    }

    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        printStatistics();
        abilityMedic();
    }

    public static void bossHits() {
        Random random = new Random();
        boolean lucky = random.nextBoolean();
        Random random1 = new Random();
        boolean thor = random1.nextBoolean();
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
        for (int j = 0; j <heroesHealth.length ; j++) {
            if (lucky == true){
                heroesHealth[5] +=40;
                break;
            }
            for (int k = 0; k < heroesHealth.length; k++) {
                if (thor == true){
                    bossDamage = 0;
                } else { bossDamage = 50;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i]; //  bossHealth -= heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(roundNumber + 1 + " ROUND -------------");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
    }

    public static void abilityMedic(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if(heroesHealth[3] > 0){
                if (heroesHealth[i] < 100 && heroesHealth[i] !=3){
                    heroesHealth[i]= heroesHealth[3] + 20;
                    System.out.println("Медик вылечил ");
                }
            }break;

        }
    }
}
