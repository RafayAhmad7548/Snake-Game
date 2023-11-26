import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class TileBlock extends JLabel{
    
    Random random;

    public TileBlock(Panel panel){
        random = new Random();
        this.setSize(Panel.TILE_SIZE, Panel.TILE_SIZE);
        this.setBackground(Color.GRAY);
        this.setOpaque(true);
        this.setLocation(random.nextInt(22)*Panel.TILE_SIZE, random.nextInt(20)*Panel.TILE_SIZE);
        panel.add(this);
        panel.repaint();
    }

}
