package com.example;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class MainGUI {

    public static final double imageScale = 0.8;
    public static Point fieldMinPoint;
    public static Point fieldMaxPoint;
    public static int fieldWidthPixels;
    public static int fieldHeightPixels;

    /** A record that represents the x and y pixel positions on base gui of top left and bottom right pixel of a rectangle */
    public record Rectangle(int originX, int originY, int xWidth, int yHeight) {}
    

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

        fieldMinPoint = new Point(0,0);
        fieldMaxPoint = new Point(fieldWidthPixels, fieldHeightPixels);
        frame.getLayeredPane().add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        DraggableRectagle box = new DraggableRectagle(new Rectangle(0, 0, 100, 100), new Color(52, 152, 219, 200));
        frame.getLayeredPane().add(box.panel, JLayeredPane.PALETTE_LAYER);
        
        frame.getLayeredPane().revalidate();
        frame.getLayeredPane().repaint();

    }

}
