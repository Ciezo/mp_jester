/**
    Author: Cloyd Van S. Secuya
    Date of Creation: July 12, 2022
    Description:
        Populate the jester_users
*/

-- Start using the created database from the schema
USE Jester_DB;

INSERT INTO jester_music (music_ID, music_title, music_artist, music_album, music_path_to_DIR, lyric_path_to_dir)
    VALUES
    -- BEGIN POPULATING ROWS
        /** 
            @NOTE: 
                THESE ARE PRE-INSTALLED SONGS WITH THE APPLICATION. IT IS INSIDE THE src.music_dir 
        */        
        ROW("001", "Country Roads", "John Denver", "Poems, Prayers & Promises", "src/music_dir/countryroads.mp3", "src/com/view/LYR_countryroads_LYRICS.txt"),
        ROW("002", "Pixel Galaxy", "Ujico", "Single", "src/music_dir/pixelgalaxy.mp3", "src/com/view/LYR_pixelgalaxy_LYRICS.txt"),
        ROW("003", "Twinklestar", "Ujico", "Single", "src/music_dir/twinklestar.mp3", "src/com/view/LYR_twinkelstar_LYRICS.txt"),
        ROW("004", "Clair De Lune", "Claude Debussy", "Suite Bergamasque", "src/music_dir/clairedelune.mp3", "src/com/view/LYR_clairedelune_LYRICS.txt"),
        ROW("005", "Baka Mitai", "Takaya Kuroda", "Ryu ga Gotoku 5 Yume", "src/music_dir/bakamitai.mp3", "src/com/view/LYR_bakamitai_LYRICS.txt");
    -- END POPULATING ROWS
