package JustVibe.com.controller;

import JustVibe.com.model.Album;
import JustVibe.com.model.Song;
import JustVibe.com.repository.AlbumRepository;
import JustVibe.com.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@CrossOrigin(origins = "*") // Allow requests from your frontend
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    // Get all albums with songs
    @GetMapping
    public List<Album> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        albums.forEach(album -> album.setSongs(songRepository.findByAlbumId(album.getId())));
        return albums;
    }

    // Get single album by id
    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable String id) {
        Album album = albumRepository.findById(id).orElse(null);
        if (album != null) {
            album.setSongs(songRepository.findByAlbumId(id));
        }
        return album;
    }
}
