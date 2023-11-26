import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable{
    
    static int TILE_SIZE = 36;
    final static int WINDOW_SIZE = TILE_SIZE*22;
    final static int WIDTH = TILE_SIZE*22;
    final static int HEIGHT = TILE_SIZE*20;
    
    KeyHandler keyH;
    SoundHandler soundH;

    static Thread gameloop;
    boolean isGameRunnning = true;

    public Panel(){

        keyH = new KeyHandler(this);
        soundH = new SoundHandler();
        
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setFocusable(true);
        this.addKeyListener(keyH);

        new Snake(keyH, this, soundH);

        gameloop = new Thread(this);
        gameloop.start();
        
    }

    public void update(){
        Snake.update();
    }

    @Override
    public void run(){

        long updateInterval = 1000000000/60;
        long nextUpdateTime = System.nanoTime() + updateInterval;
        
        while(isGameRunnning){
            update();
            repaint();
            long timeRemaining = nextUpdateTime - System.nanoTime();
            try{
                if(timeRemaining>=0) Thread.sleep(timeRemaining/1000000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            nextUpdateTime = System.nanoTime() + updateInterval;
        }
        
    }

}
