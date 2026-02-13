public class SupprimerTacheCommande implements Commande {
    private GestionTaches gestionTaches;
    private String description;
    private Tache tacheSupprimee;

    public SupprimerTacheCommande(GestionTaches gestionTaches, String description) {
        this.gestionTaches = gestionTaches;
        this.description = description;
    }

    public void executer() {
        tacheSupprimee = gestionTaches.getTacheParDescription(description);
        if (tacheSupprimee != null) {
            gestionTaches.supprimerTache(tacheSupprimee.getId());
            System.out.println("Tâche supprimée: " + description);
        }
    }

    public void annuler() {
        if (tacheSupprimee != null) {
            gestionTaches.ajouterTache(tacheSupprimee);
            System.out.println("Suppression annulée: " + description);
        }
    }
}