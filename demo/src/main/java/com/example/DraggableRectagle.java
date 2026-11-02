package com.example;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.example.MainGUI.Rectangle;

public class DraggableRectagle {
    
    public JPanel panel;
    public Rectangle rectangle;
    public final int resizeTolerancePixels = 10;

    public DraggableRectagle(Rectangle initRectagle, Color color) {
        panel = new JPanel();
        this.rectangle=initRectagle;

        panel.setBackground(color);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.setBounds(rectangle.originX(),rectangle.originY(), rectangle.xWidth(), rectangle.yHeight());

        MouseAdapter mouseHandler = new MouseAdapter() {

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
                System.out.println(rectangle.originX());

                boolean resizeLeft = initialClick.x - resizeTolerancePixels <= 0;
                boolean resizeRight = initialClick.x + resizeTolerancePixels >= rectangle.xWidth();
                boolean resizeUp = initialClick.y - resizeTolerancePixels <= 0;
                boolean resizeDown = initialClick.y + resizeTolerancePixels >= rectangle.yHeight();

                if(resizeLeft || resizeRight || resizeUp || resizeDown) {
                    // if should resize in x
                    if(resizeLeft || resizeRight) {

                    }

                    // if should resize y
                    if(resizeUp || resizeDown) {

                    }

                } else {
                    Point clammpedPoint = getClamppedPointToField(new Rectangle(newX, newY, newX+rectangle.xWidth(), newY+rectangle.yHeight()));
                    rectangle = new Rectangle(clammpedPoint.x, clammpedPoint.y, rectangle.xWidth(), rectangle.yHeight());
                    component.setLocation(clammpedPoint);
                }
            
            }
        };


        panel.addMouseListener(mouseHandler);
        panel.addMouseMotionListener(mouseHandler);
    }

    /**
     * @return Returns the top right position of the rectagle clamped to be in the field
     */
    public Point getClamppedPointToField(Rectangle newRectagle) {
        Point output = new Point(newRectagle.originX(), newRectagle.originY());

        if(newRectagle.originX() < MainGUI.fieldMinPoint.getX()) output.x = (int)MainGUI.fieldMinPoint.getX();
        if(newRectagle.originY() < MainGUI.fieldMinPoint.getY()) output.y = (int)MainGUI.fieldMinPoint.getY();

        if(newRectagle.xWidth() > MainGUI.fieldMaxPoint.getX()) output.x = (int)(MainGUI.fieldMaxPoint.getX()-(newRectagle.xWidth()-newRectagle.originX()));
        if(newRectagle.yHeight() > MainGUI.fieldMaxPoint.getY()) output.y = (int)(MainGUI.fieldMaxPoint.getY()-(newRectagle.yHeight()-newRectagle.originY()));

        return output;
    }

}
