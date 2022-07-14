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
        cardPanel.add(albumViewer, "4");
        cardPanel.add(songAdder, "5");
        
        musicLibrary.setBackground(Color.RED);
        lyricsViewer.setBackground(Color.YELLOW);
        albumViewer.setBackground(Color.GREEN);
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
