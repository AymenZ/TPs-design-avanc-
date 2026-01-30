public class ModifierTacheCommande implements Commande {
    private GestionTaches gestionTaches;
    private String ancienneDescription;
    private String nouvelleDescription;
    private int idTache;

    public ModifierTacheCommande(GestionTaches gestionTaches, String ancienneDescription, String nouvelleDescription) {
        this.gestionTaches = gestionTaches;
        this.ancienneDescription = ancienneDescription;
        this.nouvelleDescription = nouvelleDescription;
    }

    public void executer() {
        Tache tache = gestionTaches.getTacheParDescription(ancienneDescription);
        if (tache != null) {
            idTache = tache.getId();
            gestionTaches.modifierTache(idTache, nouvelleDescription);
            System.out.println("Tâche modifiée: " + ancienneDescription + " -> " + nouvelleDescription);
        }
    }

    public void annuler() {
        gestionTaches.modifierTache(idTache, ancienneDescription);
        System.out.println("Modification annulée: " + nouvelleDescription + " -> " + ancienneDescription);
    }
}