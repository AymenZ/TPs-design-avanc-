public class ApplicationGestionTaches {

    private GestionTaches gestionTaches;
    private GestionnaireCommandes gestionnaire;

    public ApplicationGestionTaches() {
        this.gestionTaches = new GestionTaches();
        this.gestionnaire = new GestionnaireCommandes();
    }

    public void creerTache(String description) {
        Commande cmd = new CreerTacheCommande(gestionTaches, description);
        gestionnaire.executerCommande(cmd);
    }

    public void modifierTache(String ancienneDescription, String nouvelleDescription) {
        Commande cmd = new ModifierTacheCommande(
                gestionTaches,
                ancienneDescription,
                nouvelleDescription
        );
        gestionnaire.executerCommande(cmd);
    }

    public void supprimerTache(String description) {
        Commande cmd = new SupprimerTacheCommande(gestionTaches, description);
        gestionnaire.executerCommande(cmd);
    }

    public void annulerDerniereCommande() {
        gestionnaire.annulerDerniereCommande();
    }

    public GestionTaches getGestionTaches() {
        return gestionTaches;
    }
}
