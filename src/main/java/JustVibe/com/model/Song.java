package JustVibe.com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String albumId;
    private String title;
    private String src; // Song URL
    private String img; // Song image URL

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getAlbumId() { return albumId; }
    public void setAlbumId(String albumId) { this.albumId = albumId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSrc() { return src; }
    public void setSrc(String src) { this.src = src; }
    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }
}
