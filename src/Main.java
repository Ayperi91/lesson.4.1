import java.util.Random;

public class Main {

    public static int bossHealth = 750;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int medicHealth = 200;
    public static int superMedic = 50;
    public static String medicSuperAbilities = "Medical";
    public static int[] heroesHealth = {260, 250, 240};
    public static int[] heroesDamage = {20, 15, 25};
    public static String[] heroesSuperAbilities = {"Physical", "Magical", "Kinetic"};
    public static int roundNumber = 1;

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesSuperAbilities.length); // 0, 1, 2
        bossDefenceType = heroesSuperAbilities[randomIndex];

    }

    public static void medicFeature() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesHealth.length);
        superMedic = heroesHealth[randomIndex];
    }

    public static void main(String[] args) {
        printStatistics();
        System.out.println("######################");
        while (!isGameFinished()) {
            System.out.println("Round " + roundNumber);
            round();
        }
    }

    public static void round() {
        changeBossDefence();
        bossHit();
        heroesHit();
        superMedic();
        printStatistics();
        roundNumber++;
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (medicHealth <= 0) {
            System.out.println("medic died!");
            return true;
        }
        boolean allHeroesDead = true; // предполагаем что все герои мертвы
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
        System.out.println("______________________________");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesSuperAbilities[i] + " health: " + heroesHealth[i]);
        }
        System.out.println("______________________________");
    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (bossDefenceType == heroesSuperAbilities[i]) {
                    Random r = new Random();
                    int coef = r.nextInt(10);
                    System.out.println(heroesSuperAbilities[i] + " hit boss with critical damage "
                            + heroesDamage[i] * coef);
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void superMedic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (medicHealth > 0) {
                if (heroesHealth[i] < 0) {
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        int superMedic = 50;
                        heroesHealth[i] = superMedic + heroesHealth[i];

                    }
                }
            }

        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0) {
                if (heroesHealth[i] > 0) {
                    if (bossHealth > medicHealth) {
                        if (medicHealth - bossDamage < 0) {
                            medicHealth = 0;
                        } else {
                            medicHealth = medicHealth - bossDamage;
                        }
                    }
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] = heroesHealth[i] - bossDamage;
                    }

                }
            }
        }
    }
}
