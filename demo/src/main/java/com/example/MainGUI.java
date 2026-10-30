package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MainGUI {

    public static final double imageScale = 0.8;
    public static int fieldWidthPixels;
    public static int fieldHeightPixels;

    public MainGUI() {
        JFrame frame = new JFrame("Test GUI");

        frame.setSize(1600, 800);
        frame.setLayout(null);
        frame.setVisible(true);

        ImageIcon imageIcon = new ImageIcon("demo\\src\\rebuilt_field.png");
        Image originalScaleImage = imageIcon.getImage();

        fieldWidthPixels = (int)Math.round(imageIcon.getIconWidth()*imageScale);
        fieldHeightPixels = (int)Math.round(imageIcon.getIconHeight()*imageScale);

        Image scaledImage = originalScaleImage.getScaledInstance(fieldWidthPixels, fieldHeightPixels, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0,0,fieldWidthPixels,fieldHeightPixels);
        frame.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        JPanel box = new JPanel();

        box.setBackground(new Color(52, 152, 219, 200));
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        // box.setPreferredSize(new Dimension(100, 100));
        // box.setBounds(200,400,100, 100);

        MouseAdapter mouseHandler = new MouseAdapter() {
            private int initialX;
            private int initialY;
        };

        box.addMouseListener(mouseHandler);
        box.addMouseMotionListener(mouseHandler);

        frame.add(box, JLayeredPane.PALETTE_LAYER);
        
        frame.revalidate();
        frame.repaint();

    }

}
