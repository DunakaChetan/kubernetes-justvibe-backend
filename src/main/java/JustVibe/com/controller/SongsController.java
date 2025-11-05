package JustVibe.com.controller;

import JustVibe.com.model.Song;
import JustVibe.com.repository.SongRepository;
import JustVibe.com.service.AzureBlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/songs")
@CrossOrigin(origins = "*") // Allow frontend to access
public class SongsController {

    @Autowired
    private AzureBlobService azureBlobService;

    @Autowired
    private SongRepository songRepository;

    // Upload file to Azure, return URL
    @PostMapping("/upload")
    public String uploadSong(@RequestParam("file") MultipartFile file) throws IOException {
        return azureBlobService.uploadFile(file); // returns URL
    }

    // Delete file from Azure
    @DeleteMapping("/delete/{filename}")
    public String deleteSong(@PathVariable String filename) {
        azureBlobService.deleteFile(filename);
        return "Deleted " + filename;
    }

    // ✅ Fix: Get song by Integer ID
    @GetMapping("/{id}")
    public Song getSongById(@PathVariable Integer id) {
        return songRepository.findById(id).orElse(null);
    }
}
