public class AppliCommande {

    public static void main(String[] args) {

        ApplicationGestionTaches app = new ApplicationGestionTaches();

        System.out.println("=== Création des tâches ===");
        app.creerTache("Réviser Java");
        app.creerTache("Faire le TP Commande");
        app.creerTache("Préparer l'examen");

        System.out.println("\n=== Modification ===");
        app.modifierTache("Réviser Java", "Réviser Java - Design Patterns");

        System.out.println("\n=== Suppression ===");
        app.supprimerTache("Préparer l'examen");

        System.out.println("\n=== Annulation ===");
        app.annulerDerniereCommande(); // annule suppression
        app.annulerDerniereCommande(); // annule modification
    }
}
