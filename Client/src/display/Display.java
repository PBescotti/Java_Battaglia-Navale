package display;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

import game.Game;
import game.input.MouseManager;

public class Display {
    
    public static Dimension resolution;

    private String title;

    private JFrame frame;
    private Canvas canvas;

    public Display(String title, int width, int height) {
        this.title = title;
        resolution = new Dimension(width, height);

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);                                  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //Quando viene premuta la X del frame si chiude                             
        frame.setResizable(false);  
        frame.setIconImage(Game.assets.icon);                    
        
        canvas = new Canvas();                                      
        canvas.setMinimumSize(new Dimension(resolution.width, resolution.height));        //Imposta la dimensione del canvas
        canvas.setPreferredSize(new Dimension(resolution.width, resolution.height));
        canvas.setMaximumSize(new Dimension(resolution.width, resolution.height));
        canvas.setFocusable(false);
        canvas.requestFocus();
        
        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);                        //La finistra compare al centro dello schermo
        frame.setVisible(true);
    }

    public void addMouseManager(MouseManager mouseManager) {
        canvas.addMouseListener(mouseManager);
        canvas.addMouseMotionListener(mouseManager);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

}