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
    public record Rectangle(int x1, int y1, int x2, int y2) {}
    

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

        JPanel box = new JPanel();

        box.setBackground(new Color(52, 152, 219, 200));
        box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        // box.setPreferredSize(new Dimension(100, 100));
        box.setBounds(200,400,100, 100);

        MouseAdapter mouseHandler = new MouseAdapter() {
            private int initialX;
            private int initialY;

            private Point initialClick;

            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Component component = e.getComponent();
                Point currentLocation = component.getLocation();
                
                int newX = currentLocation.x + e.getX() - initialClick.x;
                int newY = currentLocation.y + e.getY() - initialClick.y;
                
                component.setLocation(getClamppedPointToField(new Rectangle(newX, newY, newX+100, newY+100)));
                // System.out.println(getClamppedPointToField(new Point(newX, newY), new Point(newX+200, newY+400)).getY());
                // System.out.println(newY+400);
            }
        };


        box.addMouseListener(mouseHandler);
        box.addMouseMotionListener(mouseHandler);

        frame.getLayeredPane().add(box, JLayeredPane.PALETTE_LAYER);
        
        frame.getLayeredPane().revalidate();
        frame.getLayeredPane().repaint();

    }

    /**
     * @return Returns the top right position of the rectagle clamped to be in the field
     */
    public Point getClamppedPointToField(Rectangle rectagle) {
        Point output = new Point(rectagle.x1(), rectagle.y1());

        if(rectagle.x1() < fieldMinPoint.getX()) output.x = (int)fieldMinPoint.getX();
        if(rectagle.y1() < fieldMinPoint.getY()) output.y = (int)fieldMinPoint.getY();

        if(rectagle.x2() > fieldMaxPoint.getX()) output.x = (int)(fieldMaxPoint.getX() - (rectagle.x2()-rectagle.x1()));
        if(rectagle.y2() > fieldMaxPoint.getY()) output.y = (int)(fieldMaxPoint.getY() - (rectagle.y2()-rectagle.y1()));

        return output;
    }

}
