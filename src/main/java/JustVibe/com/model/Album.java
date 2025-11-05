package JustVibe.com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    private String id;
    private String title;
    private String artist;
    private String img;       // Album image URL
    private String category;

    @Transient
    private List<Song> songs; // Will be populated dynamically

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public List<Song> getSongs() { return songs; }
    public void setSongs(List<Song> songs) { this.songs = songs; }
}
