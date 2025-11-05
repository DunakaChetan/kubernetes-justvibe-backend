-- Create favorites table for JustVibe application
-- This table stores user's favorite songs

CREATE TABLE IF NOT EXISTS favorites (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL,
    song_title VARCHAR(500) NOT NULL,
    song_src VARCHAR(1000) NOT NULL,
    song_img VARCHAR(1000),
    album_id VARCHAR(255),
    album_cover VARCHAR(1000),
    artist VARCHAR(255),
    added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Indexes for better performance
    INDEX idx_user_email (user_email),
    INDEX idx_song_title (song_title),
    INDEX idx_added_at (added_at),
    
    -- Unique constraint to prevent duplicate favorites
    UNIQUE KEY unique_user_song (user_email, song_title)
);

-- Optional: Add foreign key constraint if you want strict referential integrity
-- ALTER TABLE favorites ADD CONSTRAINT fk_favorites_user_email 
-- FOREIGN KEY (user_email) REFERENCES users(email) ON DELETE CASCADE;
