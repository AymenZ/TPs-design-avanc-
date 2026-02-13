package main;

public class ComparisonTest {

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  Comparaison Mémoire : Flyweight vs Naïve                ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println();
        
        Runtime runtime = Runtime.getRuntime();
        int numberOfEnemies = 50;
        
        System.out.println("┌─ TEST AVEC FLYWEIGHT ─────────────────────────────────┐");
        runtime.gc();
        long memoryBeforeFlyweight = runtime.totalMemory() - runtime.freeMemory();
        
        GameWorldFlyweight worldFlyweight = new GameWorldFlyweight();
        worldFlyweight.generateEnemies(numberOfEnemies);
        
        runtime.gc();
        long memoryAfterFlyweight = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsedFlyweight = memoryAfterFlyweight - memoryBeforeFlyweight;
        
        System.out.println("  Ennemis générés: " + numberOfEnemies);
        System.out.println("  Sprites créés: " + EnemySpriteFactory.getSpriteCount());
        System.out.println("  Mémoire utilisée: " + formatMemory(memoryUsedFlyweight));
        System.out.println("└───────────────────────────────────────────────────────┘");
        System.out.println();
        
        System.out.println("┌─ TEST SANS FLYWEIGHT (Naïve) ─────────────────────────┐");
        runtime.gc();
        long memoryBeforeNaive = runtime.totalMemory() - runtime.freeMemory();
        
        GameWorldNaive worldNaive = new GameWorldNaive();
        worldNaive.generateEnemies(numberOfEnemies);
        
        runtime.gc();
        long memoryAfterNaive = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsedNaive = memoryAfterNaive - memoryBeforeNaive;
        
        System.out.println("  Ennemis générés: " + numberOfEnemies);
        System.out.println("  Images chargées: " + numberOfEnemies + " (une par ennemi)");
        System.out.println("  Mémoire utilisée: " + formatMemory(memoryUsedNaive));
        System.out.println("└───────────────────────────────────────────────────────┘");
        System.out.println();
        
        // Analyse comparative
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  RÉSULTATS DE LA COMPARAISON                             ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        System.out.println("  Flyweight:     " + formatMemory(memoryUsedFlyweight));
        System.out.println("  Naïve:         " + formatMemory(memoryUsedNaive));
        System.out.println("  ───────────────────────────────────────────────────────");
        System.out.println("  Économie:      " + formatMemory(memoryUsedNaive - memoryUsedFlyweight));
        
        if (memoryUsedNaive > 0) {
            double ratio = (double) memoryUsedFlyweight / memoryUsedNaive * 100;
            double economy = 100 - ratio;
            System.out.println("  Ratio:         " + String.format("%.2f%%", ratio) + " de la version naïve");
            System.out.println("  Gain:          " + String.format("%.2f%%", economy) + " de mémoire économisée");
        }
        System.out.println();
        System.out.println("  ⚡ Le pattern Flyweight partage " + EnemySpriteFactory.getSpriteCount() 
                           + " sprites entre " + numberOfEnemies + " ennemis !");
        System.out.println();
    }
    
    private static String formatMemory(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }
}
