package main;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SettingsMemento {
    private int volume;
    private int brightness;
    private boolean darkMode;
    private LocalDateTime timestamp;
    private int id;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public SettingsMemento(int volume, int brightness, boolean darkMode, int id) {
        this.volume = volume;
        this.brightness = brightness;
        this.darkMode = darkMode;
        this.timestamp = LocalDateTime.now();
        this.id = id;
    }

    public int getVolume() {
        return volume;
    }
    public int getBrightness() {
        return brightness;
    }
    public boolean isDarkMode() {
        return darkMode;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public int getId() {
        return id;
    }
    
    public String getFormattedTimestamp() {
        return timestamp.format(formatter);
    }
    
    @Override
    public String toString() {
        return "#" + id + " - " + getFormattedTimestamp() + " (Vol:" + volume + ", Bright:" + brightness + ", Dark:" + darkMode + ")";
    }
}
