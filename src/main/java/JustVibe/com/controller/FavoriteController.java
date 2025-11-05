package JustVibe.com.controller;

import JustVibe.com.model.Favorite;
import JustVibe.com.repository.FavoriteRepository;
import JustVibe.com.model.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    
    @Autowired
    private FavoriteRepository favoriteRepository;
    
    @Autowired
    private JWTManager jwtManager;
    
    // Get all favorites for a user
    @GetMapping("/user")
    public ResponseEntity<?> getUserFavorites(@RequestHeader("Authorization") String token) {
        try {
            String userEmail = jwtManager.validateToken(token);
            if ("401".equals(userEmail)) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));
            }
            
            List<Favorite> favorites = favoriteRepository.findByUserEmailOrderByAddedAtDesc(userEmail);
            return ResponseEntity.ok(Map.of("favorites", favorites));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error retrieving favorites: " + e.getMessage()));
        }
    }
    
    // Add a song to favorites
    @PostMapping("/add")
    public ResponseEntity<?> addToFavorites(@RequestHeader("Authorization") String token, 
                                          @RequestBody Map<String, Object> songData) {
        try {
            String userEmail = jwtManager.validateToken(token);
            if ("401".equals(userEmail)) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));
            }
            
            String songTitle = (String) songData.get("songTitle");
            String songSrc = (String) songData.get("songSrc");
            String songImg = (String) songData.get("songImg");
            String albumId = (String) songData.get("albumId");
            String albumCover = (String) songData.get("albumCover");
            String artist = (String) songData.get("artist");
            
            // Check if song is already in favorites
            if (favoriteRepository.existsByUserEmailAndSongTitle(userEmail, songTitle)) {
                return ResponseEntity.status(400).body(Map.of("error", "Song already in favorites"));
            }
            
            Favorite favorite = new Favorite(userEmail, songTitle, songSrc, songImg, 
                                           albumId, albumCover, artist);
            favoriteRepository.save(favorite);
            
            return ResponseEntity.ok(Map.of(
                "message", "Song added to favorites successfully",
                "favorite", favorite
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error adding to favorites: " + e.getMessage()));
        }
    }
    
    // Remove a song from favorites
    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFromFavorites(@RequestHeader("Authorization") String token,
                                               @RequestBody Map<String, String> request) {
        try {
            String userEmail = jwtManager.validateToken(token);
            if ("401".equals(userEmail)) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));
            }
            
            String songTitle = request.get("songTitle");
            
            if (songTitle == null || songTitle.isEmpty()) {
                return ResponseEntity.status(400).body(Map.of("error", "Song title is required"));
            }
            
            favoriteRepository.deleteByUserEmailAndSongTitle(userEmail, songTitle);
            
            return ResponseEntity.ok(Map.of("message", "Song removed from favorites successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error removing from favorites: " + e.getMessage()));
        }
    }
    
    // Check if a song is in favorites
    @PostMapping("/check")
    public ResponseEntity<?> checkFavorite(@RequestHeader("Authorization") String token,
                                         @RequestBody Map<String, String> request) {
        try {
            String userEmail = jwtManager.validateToken(token);
            if ("401".equals(userEmail)) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));
            }
            
            String songTitle = request.get("songTitle");
            
            if (songTitle == null || songTitle.isEmpty()) {
                return ResponseEntity.status(400).body(Map.of("error", "Song title is required"));
            }
            
            boolean isFavorite = favoriteRepository.existsByUserEmailAndSongTitle(userEmail, songTitle);
            
            return ResponseEntity.ok(Map.of("isFavorite", isFavorite));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error checking favorite: " + e.getMessage()));
        }
    }
    
    // Toggle favorite (add if not exists, remove if exists)
    @PostMapping("/toggle")
    public ResponseEntity<?> toggleFavorite(@RequestHeader("Authorization") String token,
                                          @RequestBody Map<String, Object> songData) {
        try {
            String userEmail = jwtManager.validateToken(token);
            if ("401".equals(userEmail)) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));
            }
            
            String songTitle = (String) songData.get("songTitle");
            String songSrc = (String) songData.get("songSrc");
            String songImg = (String) songData.get("songImg");
            String albumId = (String) songData.get("albumId");
            String albumCover = (String) songData.get("albumCover");
            String artist = (String) songData.get("artist");
            
            if (songTitle == null || songTitle.isEmpty()) {
                return ResponseEntity.status(400).body(Map.of("error", "Song title is required"));
            }
            
            boolean isFavorite = favoriteRepository.existsByUserEmailAndSongTitle(userEmail, songTitle);
            
            Map<String, Object> response = new HashMap<>();
            
            if (isFavorite) {
                // Remove from favorites
                favoriteRepository.deleteByUserEmailAndSongTitle(userEmail, songTitle);
                response.put("action", "removed");
                response.put("message", "Song removed from favorites");
                response.put("isFavorite", false);
            } else {
                // Add to favorites
                Favorite favorite = new Favorite(userEmail, songTitle, songSrc, songImg, 
                                               albumId, albumCover, artist);
                favoriteRepository.save(favorite);
                response.put("action", "added");
                response.put("message", "Song added to favorites");
                response.put("isFavorite", true);
                response.put("favorite", favorite);
            }
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error toggling favorite: " + e.getMessage()));
        }
    }
    
    // Get favorites count for a user
    @GetMapping("/count")
    public ResponseEntity<?> getFavoritesCount(@RequestHeader("Authorization") String token) {
        try {
            String userEmail = jwtManager.validateToken(token);
            if ("401".equals(userEmail)) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));
            }
            
            long count = favoriteRepository.countByUserEmail(userEmail);
            
            return ResponseEntity.ok(Map.of("count", count));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error getting favorites count: " + e.getMessage()));
        }
    }
}
