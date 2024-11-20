CREATE DATABASE IF NOT EXISTS talenavi_test;

USE talenavi_test;

CREATE TABLE IF NOT EXISTS movies
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(100) NOT NULL,
    director   VARCHAR(100) NOT NULL,
    summary    TEXT         NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE InnoDB;

CREATE TABLE IF NOT EXISTS genres
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE InnoDB;

INSERT INTO genres (name)
VALUES ('Action'), ('Adventure'), ('Comedy'), ('Crime'), ('Drama'), ('Fantasy'), ('Historical'), ('Horror'), ('Mystery'), ('Romance'), ('Science Fiction'), ('Thriller'), ('Western');

CREATE TABLE IF NOT EXISTS movie_genres
(
    movie_id   INT,
    genre_id   INT,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE InnoDB;