public class CreerTacheCommande implements Commande {
    private GestionTaches gestionTaches;
    private Tache tache;
    private String description;

    public CreerTacheCommande(GestionTaches gestionTaches, String description) {
        this.gestionTaches = gestionTaches;
        this.description = description;
    }

    public void executer() {
        tache = new Tache(gestionTaches.genererNouvelId(), description);
        gestionTaches.ajouterTache(tache);
        System.out.println("Tâche créée: " + description);
    }

    public void annuler() {
        gestionTaches.supprimerTache(tache.getId());
        System.out.println("Création annulée: " + description);
    }
}