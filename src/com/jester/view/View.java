package com.jester.view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class View {
    private static final Border EmptyBorder = null;
    
    private static void view() {
        //Creates and sets up the title of the frame.
        JFrame frame = new JFrame("Jester's Tavern");
        
        //URL to get resource
        URL url = null;
        
        /*Declaration of the panels that will be placed inside the card layout
        and navigated through based on user interaction.*/
        CardLayout cl = new CardLayout();
        
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(cl);
        
        JPanel headerPanel = new JPanel();
        JPanel mainBorderPanel = new JPanel(new BorderLayout());
        JPanel mainPanel = new JPanel();
        JPanel musicLibrary = new JPanel();
        JPanel lyricsViewer = new JPanel();
        JPanel albumViewer = new JPanel();
        JPanel songAdder = new JPanel();
        
        cardPanel.add(mainPanel, "1");
        cardPanel.add(musicLibrary, "2");
        cardPanel.add(lyricsViewer, "3");
        //cardPanel.add(albumViewer, "4");
        cardPanel.add(songAdder, "5");
        
        musicLibrary.setBackground(Color.RED);
        lyricsViewer.setBackground(Color.YELLOW);
        albumViewer.setBackground(Color.LIGHT_GRAY);
        songAdder.setBackground(Color.BLACK);
        
        /*Main panel default look. This will change based on which button
        is pressed in the sidebar*/
        JLabel header = new JLabel("Main Panel");
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
        Image LGscale = LG.getScaledInstance(100, 65, Image.SCALE_SMOOTH);
        ImageIcon scaledLG = new ImageIcon(LGscale);
        logo.setIcon(scaledLG);
        logo.setBorder(EmptyBorder);
        logo.setHorizontalAlignment(JLabel.CENTER);
        logo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                header.setText("Main Panel");
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
            }
        });
        sideBar.add(aSong);
        
        //Creation of the play area panel w/ borders
        JPanel playArea = new JPanel(new GridLayout(1, 15, 2, 0));
        playArea.setPreferredSize(new Dimension(0, 60));
        playArea.setBackground(new Color(59, 56, 56, 255));
        
        //This is the picture of the album.
        JLabel albumPic = new JLabel();
        url = View.class.getResource("assets/logo.png");
        ImageIcon albumImg = new ImageIcon(url);
        Image AP = albumImg.getImage();
        Image APscale = AP.getScaledInstance(100, 65, Image.SCALE_SMOOTH);
        ImageIcon scaledAP = new ImageIcon(APscale);
        albumPic.setIcon(scaledAP);
        albumPic.setBorder(EmptyBorder);
        albumPic.setHorizontalAlignment(JLabel.CENTER);
        playArea.add(albumPic);
        //TODO: Scrolling when hovered on
        JLabel nowPlaying = new JLabel("Now Playing: <music title>");
        
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
        Image PBscale = PB.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        Image SBscale = SB.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon scaledSB = new ImageIcon(SBscale);
        ImageIcon scaledPB = new ImageIcon(PBscale);
        playButtonContainer.setIcon(scaledPB);
        playButtonContainer.addMouseListener(new MouseAdapter() {
            private boolean playing = false;
            @Override
            public void mouseClicked(MouseEvent e) {
                if(playing) {
                    playButtonContainer.setIcon(scaledSB);
                    nowPlaying.setText("Now Playing: <music title>");
                } else if (!playing) {
                    playButtonContainer.setIcon(scaledPB);
                    nowPlaying.setText("Music Stopped");
                }
                playing = !playing;
            }
        });
        playArea.add(playButtonContainer);
        
        //JButton for viewing the lyrics
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
                public void actionPerformed(ActionEvent e) {
                    if (viewing == true) {
                        header.setText("Main Panel");
                        cl.show(cardPanel, "1");
                        vLyrics.setText("View Lyrics");
                    } else if (!viewing) {
                        header.setText("View Lyrics");
                        cl.show(cardPanel, "3");
                        vLyrics.setText("Close Lyrics");
                    }
                    viewing = !viewing;
                }
            });
        
        playArea.add(vLyrics);
        
        /*Album Viewer Editor*/

        /*Value of the number of rows in the album column to be put in the int count */
        int count = 8;
        /*Value of the number of songs in the album */
        int count1 = 10;
        JPanel albumPanels[] = new JPanel[count];
        JPanel albumHeader[] = new JPanel[count];
        JPanel albumTitle[] = new JPanel[count];
        JPanel songContainer[] = new JPanel[count];
        JButton albumButton[] = new JButton[count];
        JLabel albumSongs[] = new JLabel[count1];
        JPanel albumContainer = new JPanel();
        JLabel albumName[] = new JLabel[count];
        JLabel albumArtist[] = new JLabel[count];
        JLabel albumPhoto[] = new JLabel[count];
        JLabel albumSongHeader[] = new JLabel[count];

        albumViewer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
        albumViewer.setLayout(new BorderLayout());
        JLabel albumLabel = new JLabel("LIST OF ALBUMS", JLabel.LEFT);
        albumLabel.setFont(new Font("Arial", Font.BOLD, 24));
        albumLabel.setBorder(BorderFactory.createEmptyBorder(10,30,10,10));
        albumContainer.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10));
        albumContainer.setLayout(new GridLayout(count,1));
        albumViewer.add(albumLabel, BorderLayout.NORTH);

        /*dynamic for loop. */
        for(int ctr = 0,ctr1 = 1; ctr < count; ctr++,ctr1++){
            albumButton[ctr] = new JButton();
            /*call album names on str1*/
            String str1 = "     Album " + ctr1;
            String str2 = "album" + ctr1;
            /*call artists on str3 */
            String str3 = "      by: Artist " + ctr1;

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
                String str4 = "   " + ctr3 + ".) Song " + ctr3;
                albumSongs[ctr2] = new JLabel(str4);
                albumSongs[ctr2].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                albumSongs[ctr2].setFont(new Font("Arial", Font.BOLD, 16));
                songContainer[ctr].add(albumSongs[ctr2]);
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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(View::view);
    }
    
}
