import java.util.List;
import java.util.ArrayList;

public class GestionTaches {
    private List<Tache> taches;
    private int prochainId = 1;

    public GestionTaches() {
        this.taches = new ArrayList<>();
    }

    public void ajouterTache(Tache tache) {
        taches.add(tache);
    }

    public void supprimerTache(int id) {
        taches.removeIf(tache -> tache.getId() == id);
    }

    public void modifierTache(int id, String nouvelleDescription) {
        for (Tache tache : taches) {
            if (tache.getId() == id) {
                tache.setDescription(nouvelleDescription);
                break;
            }
        }
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public Tache getTacheParDescription(String description) {
        for (Tache tache : taches) {
            if (tache.getDescription().equals(description)) {
                return tache;
            }
        }
        return null;
    }

    public int genererNouvelId() {
        return prochainId++;
    }
}