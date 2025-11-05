package JustVibe.com.repository;

import JustVibe.com.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    
    // Find all favorites for a specific user, ordered by most recent first
    List<Favorite> findByUserEmailOrderByAddedAtDesc(String userEmail);
    
    // Check if a song is already in favorites for a user
    Optional<Favorite> findByUserEmailAndSongTitle(String userEmail, String songTitle);
    
    // Delete a specific favorite by user email and song title
    void deleteByUserEmailAndSongTitle(String userEmail, String songTitle);
    
    // Count favorites for a user
    long countByUserEmail(String userEmail);
    
    // Check if a song exists in favorites for a user
    boolean existsByUserEmailAndSongTitle(String userEmail, String songTitle);
}
