package main;

public class Settings {
    int volume;
    int brightness;
    boolean darkMode;


    public Settings(int volume, int brightness, boolean darkMode) {
        this.volume = volume;
        this.brightness = brightness;
        this.darkMode = darkMode;
    }

    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public int getBrightness() {
        return brightness;
    }
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
    public boolean isDarkMode() {
        return darkMode;
    }
    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public SettingsMemento save(int id) {
        return new SettingsMemento(volume, brightness, darkMode, id);
    }
    public void restore(SettingsMemento memento) {
        this.volume = memento.getVolume();
        this.brightness = memento.getBrightness();
        this.darkMode = memento.isDarkMode();
    }
}
