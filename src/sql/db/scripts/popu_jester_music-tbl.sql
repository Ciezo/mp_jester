/**
    Author: Cloyd Van S. Secuya
    Date of Creation: July 12, 2022
    Description:
        Populate the jester_users
*/

-- Start using the created database from the schema
USE Jester_DB;

INSERT INTO jester_music (music_ID, music_title, music_artist, music_album, music_path_to_DIR)
    VALUES
    -- BEGIN POPULATING ROWS
        /** 
            @NOTE: 
                THESE ARE PRE-INSTALLED SONGS WITH THE APPLICATION. IT IS INSIDE THE src.music_dir 
        */        
        ROW("001", "Country Roads", "John Denver", "Poems, Prayers & Promises", "music_dir/countryroads.mp3"),
        ROW("002", "Pixel Galaxy", "Ujico", "Single", "music_dir/pixelgalaxy.mp3"),
        ROW("003", "Twinklestar", "Ujico", "Single", "music_dir/twinklestar.mp3"),
        ROW("004", "Clair De Lune", "Claude Debussy", "Suite Bergamasque", "music_dir/clairedelune.mp3"),
        ROW("005", "Baka Mitai", "Takaya Kuroda", "Ryu ga Gotoku 5 Yume", "music_dir/bakamitai.mp3");
    -- END POPULATING ROWS