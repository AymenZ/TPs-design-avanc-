package main;
import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private List<SettingsMemento> mementoList = new ArrayList<>();
    private int nextId = 1;

    public void saveState(Settings settings) {
        mementoList.add(settings.save(nextId++));
    }

    public void restoreState(Settings settings, int id) {
        for (SettingsMemento memento : mementoList) {
            if (memento.getId() == id) {
                settings.restore(memento);
                return;
            }
        }
    }
    
    public List<SettingsMemento> getAllMementos() {
        return new ArrayList<>(mementoList);
    }
    
    public void deleteMemento(int id) {
        mementoList.removeIf(memento -> memento.getId() == id);
    }
    
    public boolean hasMementos() {
        return !mementoList.isEmpty();
    }
}
