package JustVibe.com.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorites")
public class Favorite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "user_email", nullable = false)
    private String userEmail;
    
    @Column(name = "song_title", nullable = false)
    private String songTitle;
    
    @Column(name = "song_src", nullable = false)
    private String songSrc;
    
    @Column(name = "song_img")
    private String songImg;
    
    @Column(name = "album_id")
    private String albumId;
    
    @Column(name = "album_cover")
    private String albumCover;
    
    @Column(name = "artist")
    private String artist;
    
    @Column(name = "added_at")
    private LocalDateTime addedAt;
    
    // Constructors
    public Favorite() {
        this.addedAt = LocalDateTime.now();
    }
    
    public Favorite(String userEmail, String songTitle, String songSrc, String songImg, 
                   String albumId, String albumCover, String artist) {
        this.userEmail = userEmail;
        this.songTitle = songTitle;
        this.songSrc = songSrc;
        this.songImg = songImg;
        this.albumId = albumId;
        this.albumCover = albumCover;
        this.artist = artist;
        this.addedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getSongTitle() {
        return songTitle;
    }
    
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
    
    public String getSongSrc() {
        return songSrc;
    }
    
    public void setSongSrc(String songSrc) {
        this.songSrc = songSrc;
    }
    
    public String getSongImg() {
        return songImg;
    }
    
    public void setSongImg(String songImg) {
        this.songImg = songImg;
    }
    
    public String getAlbumId() {
        return albumId;
    }
    
    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }
    
    public String getAlbumCover() {
        return albumCover;
    }
    
    public void setAlbumCover(String albumCover) {
        this.albumCover = albumCover;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    public LocalDateTime getAddedAt() {
        return addedAt;
    }
    
    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
    
    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", songTitle='" + songTitle + '\'' +
                ", songSrc='" + songSrc + '\'' +
                ", albumId='" + albumId + '\'' +
                ", artist='" + artist + '\'' +
                ", addedAt=" + addedAt +
                '}';
    }
}
