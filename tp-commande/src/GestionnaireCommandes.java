import java.util.Stack;

public class GestionnaireCommandes {
    private Stack<Commande> historiqueCommandes;

    public GestionnaireCommandes() {
        this.historiqueCommandes = new Stack<>();
    }

    public void executerCommande(Commande commande) {
        commande.executer();
        historiqueCommandes.push(commande);
    }

    public void annulerDerniereCommande() {
        if (!historiqueCommandes.isEmpty()) {
            Commande derniereCommande = historiqueCommandes.pop();
            derniereCommande.annuler();
        }
    }
}