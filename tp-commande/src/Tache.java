public class Tache {
    private int id;
    private String description;
    private boolean estComplete;

    public Tache(int id, String description) {
        this.id = id;
        this.description = description;
        this.estComplete = false;
    }
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean estComplete() {
        return estComplete;
    }

    public void marquerCommeComplete() {
        this.estComplete = true;
    }

    public void marquerCommeIncomplete() {
        this.estComplete = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

}