/**
    Authors     : Ron Relayo, Karl Halcon, Cloyd Secuya, and Nadrin Caluya
    Filename   : View.java
    Package	   : com.jester.view;
    Date of Creation : June 13, 2022
    Description:
        This is the View level where the main user interaction happens. 
        As such it is where most event handling takes place.
*/

// PACKAGE SECTION
package com.jester.view;


// IMPORT SECTION
import com.jester.controller.Controller;
import com.jester.model.music_handler.Music;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class View {
    private static final Border EmptyBorder = null;
    
    static Controller controller = new Controller();
    static Music[] music_ls; 
    static Music music; 
    static DefaultTableModel model = new DefaultTableModel();
    static String title_play; 
    
    /*Declaration of the panels that will be placed inside the card layout
    and navigated through based on user interaction.*/
    static CardLayout cl = new CardLayout();
    static JPanel cardPanel = new JPanel();
    static JPanel albumViewer = new JPanel();
    
    private static void view() {
        //Creates and sets up the title of the frame.
        JFrame frame = new JFrame("Jester's Tavern");
        
        //URL to get resource
        URL url = null;
        
        // Setting the layou with CardPanel manager
        cardPanel.setLayout(cl);
        
        JPanel headerPanel = new JPanel();
        JPanel mainBorderPanel = new JPanel(new BorderLayout());
        JPanel mainPanel = new JPanel();
        JPanel musicLibrary = new JPanel();
        JPanel lyricsViewer = new JPanel();
        JPanel songAdder = new JPanel();
        
        cardPanel.add(mainPanel, "1");
        cardPanel.add(musicLibrary, "2");
        cardPanel.add(lyricsViewer, "3");
//        cardPanel.add(albumViewer, "4");
        cardPanel.add(songAdder, "5");
        
        musicLibrary.setBackground(new Color(217,217,217,255));
        lyricsViewer.setBackground(new Color(217,217,217,255));
        albumViewer.setBackground(new Color(217,217,217,255));
        songAdder.setBackground(new Color(217,217,217,255));
        
        //Codes for main panel.
        mainPanel.setLayout(new GridBagLayout());
        url = View.class.getResource("assets/listening-to-music-jake.gif");
        Icon icon = new ImageIcon(url);
        JLabel mainPanelContent = new JLabel(icon);
        mainPanelContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanelContent.setHorizontalAlignment(JLabel.WHEN_FOCUSED);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.add(mainPanelContent);
        
        //Codes for music library
        Object[] columns = {"Titles", "Artists", "Albums"};
        Object[] row = new Object[50];
        
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(960, 605));
        scrollPane.setBounds(300, 240, 490, 355);
        musicLibrary.add(scrollPane, BorderLayout.CENTER);
        
        JTable musicTable = new JTable();
        musicTable.setModel(model);
        musicTable.setSelectionBackground(new Color(130, 130, 130, 255));
        musicTable.setSelectionForeground(new Color(255, 255, 255, 255));
        musicTable.setBackground(new Color(118,113,113,255));
        musicTable.setGridColor(new Color(198,196,196,255));
        musicTable.setFont(new Font("Arial", Font.PLAIN, 18));
        musicTable.setRowHeight(30);
        musicTable.setAutoCreateRowSorter(true);
        musicTable.setFillsViewportHeight(true);
        musicTable.setDefaultEditor(Object.class, null); //cells are uneditable
        musicTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Ensures user only selects one music at a time
        scrollPane.setViewportView(musicTable);
        
        musicTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                if(!event.getValueIsAdjusting() && musicTable.getSelectedRow() != 1) {
                    //Place the algorithm here to play the music selected
                    ListSelectionModel lsm = (ListSelectionModel) event.getSource();
                    // use the code above as the listener and to find which
                    // row was selected
                    System.out.println("Row Selected!");
                    int row_selected = musicTable.getSelectedRow();
                    System.out.println("Row Index Selected at: " + row_selected);
                    
                    String music_val = musicTable.getModel().getValueAt(row_selected,0).toString();
                    System.out.println("Music Title to be played! " + music_val);
                    title_play = music_val;
                    
                    // A condition to see if the use selected row matches the event selected row
                    // which is notified by the ListSelectionListener
                    if (row_selected == musicTable.getSelectedColumn()) {
                        System.out.println("Current row is selected. READY FOR PLAYING!");
                    }
                }
            }
        });
        
        musicTable.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 24));
        //Placeholder just to show that musicTable exists and is working
        musicTable.getTableHeader().setBackground(new Color(118,113,113,255));
        for (int i = 0; i < 50; i++) {
//            model.addRow(row);
        }
        
        // Back-end developers can utilize this algo to insert the music in the music library.
        music_ls = controller.GetAllStoredMusic(); 
        for(int i = 0; i < music_ls.length; i++) {
            row[0] = music_ls[i].getMusic_title();
            row[1] = music_ls[i].getMusic_artist();
            row[2] = music_ls[i].getMusic_album();
            
            model.addRow(row);
        }
        
        
        //Codes for songAdder UI
        songAdder.setLayout(new BorderLayout());
        JPanel songAdderContent = new JPanel(new GridLayout(8, 1, 0, 3));
        JPanel holder = new JPanel();
        songAdderContent.setPreferredSize(new Dimension(640, 480));
        holder.setPreferredSize(new Dimension(1000, 715));
        
        JLabel buffer = new JLabel();
        JLabel music_title = new JLabel("Enter Music Title: ");
        JTextField musicTextField = new JTextField();
        JLabel artist_name = new JLabel("Enter Artist: ");
        JTextField artistTextField = new JTextField();
        JLabel album_name = new JLabel("Enter Album: ");
        JTextField albumTextField = new JTextField();
        JLabel music_path = new JLabel("Enter Music Path: ");
        JTextField pathTextField = new JTextField();
        JButton saveChanges = new JButton("Save Changes");
        JLabel lyrics_path = new JLabel("Enter Lyrics Path: ");
        JTextField lyricsTextField = new JTextField();
        
        music_title.setFont(new Font("Arial", Font.PLAIN, 20));
        artist_name.setFont(new Font("Arial", Font.PLAIN, 20));
        album_name.setFont(new Font("Arial", Font.PLAIN, 20));
        music_path.setFont(new Font("Arial", Font.PLAIN, 20));
        lyrics_path.setFont(new Font("Arial", Font.PLAIN, 20));
        
        // Set up placeholders for adding the paths
        pathTextField.setText("src/music_dir/<Enter your music here>.mp3");
        lyricsTextField.setText("src/com/view/<Enter your lyrics here>.txt");
        
        saveChanges.setBackground(new Color(118, 113, 113, 255));
        saveChanges.setFocusable(false);
        saveChanges.setFont(new Font("Arial", Font.PLAIN, 20));
        saveChanges.setForeground(Color.WHITE);
        
        musicTextField.setBackground(new Color(118,113,113,255));
        musicTextField.setBorder(EmptyBorder);
        musicTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        musicTextField.setForeground(Color.WHITE);
        artistTextField.setBackground(new Color(118,113,113,255));
        artistTextField.setBorder(EmptyBorder);
        artistTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        artistTextField.setForeground(Color.WHITE);
        albumTextField.setBackground(new Color(118,113,113,255));
        albumTextField.setBorder(EmptyBorder);
        albumTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        albumTextField.setForeground(Color.WHITE);
        pathTextField.setBackground(new Color(118,113,113,255));
        pathTextField.setBorder(EmptyBorder);
        pathTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        pathTextField.setForeground(Color.WHITE);
        lyricsTextField.setBackground(new Color(118, 113, 113, 255));
        lyricsTextField.setBorder(EmptyBorder);
        lyricsTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        lyricsTextField.setForeground(Color.WHITE);
        
        saveChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // @TODO - Add newly inserted records here
                    //Insert the code here to save the changes and add the music to
                    //your music library.
                    Thread.sleep(250);
                    JOptionPane.showMessageDialog(null, "Restarting application to apply changes", "Changes Saved", JOptionPane.PLAIN_MESSAGE);
                } catch (InterruptedException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Start generating unique ID
                music = new Music();  
                // Placeholders for attributes in music
                String new_music_title = musicTextField.getText(); 
                String new_music_artist = artistTextField.getText(); 
                String new_music_album = albumTextField.getText(); 
                String new_music_path = pathTextField.getText(); 
                String new_lyric_path = lyricsTextField.getText();
                
                System.out.println("Saved Changes!");
                System.out.println("=============================");
                System.out.println("Music title: " + new_music_title);
                System.out.println("Music artist: " + new_music_artist);
                System.out.println("Music album: " + new_music_album);
                System.out.println("Path file: " + new_music_path);
                System.out.println("Lyric file: " + new_lyric_path);
                System.out.println("=============================");
                
                // Set up and assign the rest of the attributes
                music.setMusic_title(new_music_title);
                music.setMusic_artist(new_music_artist);
                music.setMusic_album(new_music_album);
                music.setMusic_path_to_DIR(new_music_path);
                // Final instantiate to attributes
                music = new Music(music.getMusic_ID(), new_music_title, new_music_artist, new_music_album, new_music_path, new_lyric_path);
                // Call the controller to insert the records
                controller.SetNewMusic(music);
                // Refresh the library for new contents
                music_lib_refresher(music.getMusic_title(), music.getMusic_artist(), music.getMusic_album()); 
                // Add the newly listed album to the album viewer
                // Restart the main window and discard the old window to apply local changes
                main(null);
                frame.dispose();
            }
            
        });
        
        songAdderContent.add(music_title);
        songAdderContent.add(musicTextField);
        songAdderContent.add(artist_name);
        songAdderContent.add(artistTextField);
        songAdderContent.add(album_name);
        songAdderContent.add(albumTextField);
        songAdderContent.add(music_path);
        songAdderContent.add(pathTextField);
        songAdderContent.add(lyrics_path);
        songAdderContent.add(lyricsTextField);
        songAdderContent.add(buffer);
        songAdderContent.add(saveChanges);
        
        //holder.add(saveChanges);
        holder.add(songAdderContent);
        songAdder.add(holder, BorderLayout.CENTER);
//        musicLibrary.setBackground(Color.RED);
//        lyricsViewer.setBackground(Color.YELLOW);
//        albumViewer.setBackground(Color.LIGHT_GRAY);
//        songAdder.setBackground(Color.BLACK);
        
        /*Main panel default look. This will change based on which button
        is pressed in the sidebar*/
        JLabel header = new JLabel("Welcome to Jester's Tavern!");
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setForeground(Color.WHITE);
        
        headerPanel.add(header);
        headerPanel.setBackground(new Color(118,113,113,255));
        
        //Creation of the sidebar panel w/ borders
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new GridLayout(15, 0, 5, 6));
        sideBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sideBar.setBackground(new Color(175,171,171,255));
        
        //NOTE: The logo is a place holder for now
        //^ remove these comments once logo is finalized.
        JLabel logo = new JLabel();
        url = View.class.getResource("assets/logo.png");
        ImageIcon logoImg = new ImageIcon(url);
        Image LG = logoImg.getImage();
        Image LGscale = LG.getScaledInstance(80, 45, Image.SCALE_SMOOTH);
        ImageIcon scaledLG = new ImageIcon(LGscale);
        logo.setIcon(scaledLG);
        logo.setBorder(EmptyBorder);
        logo.setHorizontalAlignment(JLabel.CENTER);
        logo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                header.setText("Welcome to Jester's Tavern!");
                cl.show(cardPanel, "1");
            }
        });
        sideBar.add(logo);
        
        JButton mLib = new JButton("Music Library");
        mLib.setFocusable(false);
        mLib.setBorder(EmptyBorder);
        mLib.setBackground(new Color(118,113,113,255));
        mLib.setPreferredSize(new Dimension(160, 30));
        mLib.setAlignmentX(Component.CENTER_ALIGNMENT);
        mLib.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                header.setText("Music Library");
                cl.show(cardPanel, "2");
            }
        });
        sideBar.add(mLib);
        
        JButton vAlb = new JButton("View Albums");
        vAlb.setFocusable(false);
        vAlb.setBorder(EmptyBorder);
        vAlb.setBackground(new Color(118,113,113,255));
        vAlb.setPreferredSize(new Dimension(160, 30));
        vAlb.setAlignmentX(Component.CENTER_ALIGNMENT);
        vAlb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                header.setText("View Albums");
                cl.show(cardPanel, "4");
            }
        });
        sideBar.add(vAlb);
        
        JButton aSong = new JButton("Add Songs");
        aSong.setFocusable(false);
        aSong.setBorder(EmptyBorder);
        aSong.setBackground(new Color(118,113,113,255));
        aSong.setPreferredSize(new Dimension(160, 30));
        aSong.setAlignmentX(Component.RIGHT_ALIGNMENT);
        aSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                header.setText("Add Songs");
                cl.show(cardPanel, "5");
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Place your .mp3 files into the src/music_dir folder", "NOTICE!", JOptionPane.INFORMATION_MESSAGE);
                try {Thread.sleep(300);} catch (InterruptedException ex) {ex.printStackTrace();}
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Place your .txt files into the src/com/view folder", "NOTICE!", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        sideBar.add(aSong);
        
        //Creation of the play area panel w/ borders
        JPanel playArea = new JPanel(new GridLayout(1, 15, 2, 0));
        playArea.setPreferredSize(new Dimension(0, 60));
        playArea.setBackground(new Color(59, 56, 56, 255));
        
        //This is the picture of the album.
        JLabel albumPic = new JLabel();
        url = View.class.getResource("assets/logo2.png");
        ImageIcon albumImg = new ImageIcon(url);
        Image AP = albumImg.getImage();
        Image APscale = AP.getScaledInstance(50, 45, Image.SCALE_SMOOTH);
        ImageIcon scaledAP = new ImageIcon(APscale);
        albumPic.setIcon(scaledAP);
        albumPic.setBorder(EmptyBorder);
        albumPic.setHorizontalAlignment(JLabel.CENTER);
        playArea.add(albumPic);
        //TODO: Scrolling when hovered on
        JLabel nowPlaying = new JLabel("Play some music!");
        
        /*TODO: Get the title from the controller and create an if statement
        when the music stops */
        
        //nowPlaying.setText("Now Playing: " + titleOfMusic);
        nowPlaying.setFont(new Font("Arial", Font.PLAIN, 20));
        nowPlaying.setForeground(Color.WHITE);
        playArea.add(nowPlaying);
        
        JLabel playButtonContainer = new JLabel();
        url = View.class.getResource("assets/play.png");
        ImageIcon playButton = new ImageIcon(url);
        url = View.class.getResource("assets/stop.png");
        ImageIcon stopButton = new ImageIcon(url);
        Image PB = playButton.getImage();
        Image SB = stopButton.getImage();
        Image PBscale = PB.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        Image SBscale = SB.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledSB = new ImageIcon(SBscale);
        ImageIcon scaledPB = new ImageIcon(PBscale);
        playButtonContainer.setIcon(scaledPB);
        
        // TODO: Play and Stop music button
        playButtonContainer.addMouseListener(new MouseAdapter() {
            private boolean playing = false;
            @Override
            public void mouseClicked(MouseEvent e) {
                String to_play = getTitleVal();
                if(playing) {
                    playButtonContainer.setIcon(scaledSB);
                    nowPlaying.setText("Now Playing: " + to_play);
                    
                    controller.play_musicByTitle(to_play);
                } 
                
                else if (!playing) {
                    playButtonContainer.setIcon(scaledPB);
                    nowPlaying.setText("Music Stopped");
                    
                    try {
                        controller.stop_music();
                    }
                    catch(Exception err) {err.printStackTrace();}
                }
                playing = !playing;
            }
        });
        
        playArea.add(playButtonContainer);
        
        /*JButton for viewing the lyrics, in here is also the code for the lyrics
        viewing*/
        JButton vLyrics = new JButton("View Lyrics");
        vLyrics.setFocusable(false);
        vLyrics.setFont(new Font("Arial", Font.PLAIN, 20));
        vLyrics.setForeground(Color.WHITE);
        vLyrics.setBorder(EmptyBorder);
        vLyrics.setBackground(new Color(59, 56, 56, 255));
        vLyrics.setPreferredSize(new Dimension(160, 30));
        vLyrics.setAlignmentX(Component.CENTER_ALIGNMENT);
            vLyrics.addActionListener(new ActionListener() {
                private boolean viewing = false;
                JTextArea lyrics = new JTextArea(25, 50);
                JScrollPane scrollPane = new JScrollPane(lyrics);
                public void actionPerformed(ActionEvent e) {
                    if (viewing == true) {
                        header.setText("Music Library");
                        vLyrics.setText("View Lyrics");
                        cl.show(cardPanel, "2");
                    } else if (!viewing) {
                        header.setText("View Lyrics");
                        vLyrics.setText("Close Lyrics");
                        //Place case here based on which music is playing.
                        //TODO: dynamically changes based on which music is playing.
                            try {
                                String fetch_curr_title_play = getTitleVal();
                                String display_lyrics = controller.GetLyricsPath(fetch_curr_title_play);
                                String[] path_split = display_lyrics.split("/", 4);
                                System.out.println("Lyrics to view: " + fetch_curr_title_play);
                                System.out.println("Lyrical content path: " + display_lyrics);
                                System.out.println("\n\nTEXT FILE ITSELF");
                                System.out.println("\t===>>>" + path_split[3]);
                                
                                switch(fetch_curr_title_play) {
                                    case "Baka Mitai":
                                        lyrics.read(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("LYR_bakamitai_LYRICS.txt"))), null);
                                        lyrics.setBackground(new Color(118,113,113,255));
                                        lyrics.setForeground(Color.WHITE);
                                        
                                        break;
                                        
                                    case "Country Roads":
                                        lyrics.read(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("LYR_countryroads_LYRICS.txt"))), null);
                                        lyrics.setBackground(new Color(118, 113, 113, 255));
                                        lyrics.setForeground(Color.WHITE);
                                        break;
                                        
                                    case "Clair De Lune":
                                        lyrics.read(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("LYR_clairedelune_LYRICS.txt"))), null);
                                        lyrics.setBackground(new Color(118,113,113,255));
                                        lyrics.setForeground(Color.WHITE);
                                        break;
                                        
                                    case "Pixel Galaxy":
                                        lyrics.read(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("LYR_pixelgalaxy_LYRICS.txt"))), null);
                                        lyrics.setBackground(new Color(118, 113, 113, 255));
                                        lyrics.setForeground(Color.WHITE);
                                        break;
                                        
                                    case "Twinklestar":
                                        lyrics.read(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("LYR_twinkelstar_LYRICS.txt"))), null);
                                        lyrics.setBackground(new Color(118, 113, 113, 255));
                                        lyrics.setForeground(Color.WHITE);
                                        break;
                                    
                                    default:
                                        // Split up the path to get the FILE ITSELF
                                        System.out.println("AFTER SPLIT");
                                        for (int i = 0; i < path_split.length; i++) {
                                            System.out.println(path_split[i]);
                                        }
                                        
                                        System.out.println("\n\nTEXT FILE ITSELF");
                                        System.out.println("\t===>>>" + path_split[3]);
                                        
                                        lyrics.read(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(path_split[3]))), null);
                                        lyrics.setBackground(new Color(118, 113, 113, 255));
                                        lyrics.setForeground(Color.WHITE);
                                        break;
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        cl.show(cardPanel, "3");
                    }
                    viewing = !viewing;
                    lyrics.setEditable(false);
                    lyrics.setFont(new Font("Arial", Font.PLAIN, 20));
                    lyricsViewer.add(scrollPane, BorderLayout.CENTER);
                }
            });
        playArea.add(vLyrics);
        
         /*Album Viewer Editor*/
         // Fetched all stored albums using controller
         String[] albums_ls = controller.GetAllStoredAlbums(); 
         int size = albums_ls.length;
         System.out.println("Size " + size);
        /*Value of the number of rows in the album column to be put in the int count */
        int count = size;
        /*Value of the number of songs in the album */
        int size2 = music_ls.length;
        int count1 = size2;
        count = size2;
        JPanel albumPanels[] = new JPanel[count];
        JPanel albumHeader[] = new JPanel[count];
        JPanel albumTitle[] = new JPanel[count];
        JPanel songContainer[] = new JPanel[count];
        JButton albumButton[] = new JButton[count];
        JLabel albumSongs[] = new JLabel[count1];  //Names of the songs in the albums to be put here; Sidenote: Gonna have to make this a 2D array.
        JPanel albumContainer = new JPanel();
        JLabel albumName[] = new JLabel[count];    //Names of the albums to be put in this array
        JLabel albumArtist[] = new JLabel[count];  //Names of the album artists to be put in this array
        JLabel albumPhoto[] = new JLabel[count];   //Album photos to be put in this array
        JLabel albumSongHeader[] = new JLabel[count];

        albumViewer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
        albumViewer.setLayout(new BorderLayout());
        JLabel albumLabel = new JLabel("LIST OF ALBUMS", JLabel.LEFT);
        albumLabel.setFont(new Font("Arial", Font.BOLD, 24));
        albumLabel.setBorder(BorderFactory.createEmptyBorder(10,30,10,10));
        albumContainer.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10));
        albumContainer.setLayout(new GridLayout(count,1));
        albumViewer.add(albumLabel, BorderLayout.NORTH);
        
        // @TODO - List all albums form 
        /*dynamic for loop. */ 
        for(int ctr = 0,ctr1 = 1; ctr < count; ctr++,ctr1++){
            // Test to fetch out the albums list using the controller
            System.out.println("Fetched Albums Lists: " + albums_ls[ctr]);
            
            albumButton[ctr] = new JButton();
            /*call album names on str1*/
            String str1 = "   " + albums_ls[ctr];       // Album Buttons where it is fetched from the database
            String str2 = "album" + ctr1;
            /*call artists on str3 */
            String str3 = "      by: Artist " + music_ls[ctr].getMusic_artist();

            /*creation of album panels*/
            albumPanels[ctr] = new JPanel();
            albumHeader[ctr] = new JPanel();
            albumName[ctr] = new JLabel();
            albumArtist[ctr] = new JLabel();
            albumTitle[ctr] = new JPanel();
            albumHeader[ctr] = new JPanel();
            songContainer[ctr] = new JPanel();
            albumPhoto[ctr] = new JLabel();
            albumSongHeader[ctr] = new JLabel("   SONGS");
            albumTitle[ctr].setLayout(new GridLayout(6,1));
            albumHeader[ctr].setLayout(new BoxLayout(albumHeader[ctr], BoxLayout.X_AXIS));
            songContainer[ctr].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10));
            songContainer[ctr].setLayout(new GridLayout(count1+1,1));
            albumPanels[ctr].setBorder(BorderFactory.createLineBorder(Color.WHITE,10));
            albumPanels[ctr].setLayout(new BorderLayout());
            albumPanels[ctr].setBackground(Color.LIGHT_GRAY);
            albumHeader[ctr].setBackground(Color.LIGHT_GRAY);
            albumTitle[ctr].setBackground(Color.LIGHT_GRAY);
            albumSongHeader[ctr].setFont(new Font("Arial", Font.BOLD, 23));
            songContainer[ctr].add(albumSongHeader[ctr]);
            
            /* Album Picture Insertion Here */
            // @TODO - Set different album icons 
            albumPhoto[ctr].setIcon(scaledAP);
            albumPhoto[ctr].setBorder(EmptyBorder);
            albumPhoto[ctr].setHorizontalAlignment(JLabel.CENTER);
            albumHeader[ctr].add(albumPhoto[ctr]);
            albumName[ctr].setText(str1);
            albumName[ctr].setFont(new Font("Arial", Font.BOLD, 28));
            albumArtist[ctr].setText(str3);
            albumArtist[ctr].setFont(new Font("Arial", Font.BOLD, 23));
            albumTitle[ctr].add(albumName[ctr]);
            albumTitle[ctr].add(albumArtist[ctr]);
            albumHeader[ctr].add(albumTitle[ctr]);
            /* Song Insertion for Album Here */
            for(int ctr2 = 0, ctr3 = 1; ctr2 < count1; ctr2++, ctr3++){
                /*call album songs on str4 */
                String str4 = "   " + ctr3 + ".)  " + music_ls[ctr2].getMusic_title();
                String alName = music_ls[ctr2].getMusic_album();
                String alText = music_ls[ctr].getMusic_album();
                
                if (alName.equals(alText)) {
                    albumSongs[ctr2] = new JLabel(str4);
                    albumSongs[ctr2].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                    albumSongs[ctr2].setFont(new Font("Arial", Font.BOLD, 16));
                    songContainer[ctr].add(albumSongs[ctr2]);
                }
            }
            albumPanels[ctr].add(albumHeader[ctr], BorderLayout.NORTH);
            albumPanels[ctr].add(songContainer[ctr], BorderLayout.CENTER);
            /*album panels creation end */
            
            cardPanel.add(albumPanels[ctr], str2);
            albumButton[ctr].setText(str1);
            albumButton[ctr].setFont(new Font("Arial", Font.ITALIC, 16));
            albumButton[ctr].setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
            albumButton[ctr].setHorizontalAlignment(SwingConstants.LEFT);
            albumButton[ctr].setBackground(new Color(118,113,113,255));
            albumButton[ctr].setForeground(Color.WHITE);
            albumButton[ctr].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cl.show(cardPanel, str2);
                }
            });
            albumContainer.add(albumButton[ctr]);

        }
        albumViewer.add(albumContainer, BorderLayout.CENTER);
        JScrollPane scrollableArea = new JScrollPane(albumViewer);
        scrollableArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cardPanel.add(scrollableArea, "4");

        /*ALbum Viewer Editor End*/
        
        
       
        //Adding the cardPanel in the headerPanel
        mainBorderPanel.add(headerPanel, BorderLayout.NORTH);
        mainBorderPanel.add(cardPanel, BorderLayout.CENTER);
        
        //Adding the JPanels inside the frame
        frame.getContentPane().add(mainBorderPanel, BorderLayout.CENTER);
        frame.getContentPane().add(sideBar, BorderLayout.LINE_START);
        frame.getContentPane().add(playArea, BorderLayout.PAGE_END);
        
        //Specifying how the window closes and displaying the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(new Dimension(1200, 720));
        frame.pack();
        frame.setVisible(true);
    } 
    
    private static void music_lib_refresher(String music_title, String music_artist, String music_album) {
        Object[] row = new Object[50];
        music_ls = controller.GetAllStoredMusic(); 
        // Insert the new record to the table model ONE TIME ONLY! 
        for(int i = 0; i < 1; i++) {
            row[0] = music_title; 
            row[1] = music_artist; 
            row[2] = music_album; 
            
            model.addRow(row);
        }
    }
    
    private static String getTitleVal() {
        return title_play;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(View::view);
    }
    
}
