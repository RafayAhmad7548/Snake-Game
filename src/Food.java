import javax.swing.*;
import java.util.*;

public class Food extends JLabel{

    Random random = new Random();
    Panel panel;
    int x, y;
    
    public Food(Panel panel){

        this.panel = panel;
        if(Snake.blockedTiles.size()==0){
            x = random.nextInt(22);
            y = random.nextInt(20);
        }
        else{
            for(int i=0;i<Snake.blockedTiles.size();i++){
                do x = random.nextInt(22);
                while(x==Snake.blockedTiles.get(i).getX()/Panel.TILE_SIZE);
                do y = random.nextInt(20);
                while(y==Snake.blockedTiles.get(i).getY()/Panel.TILE_SIZE);
            }
        }
        
        
        this.setIcon(new ImageIcon("lib/food.png"));
        this.setSize(32, 32);
        this.setLocation(x*Panel.TILE_SIZE, y*Panel.TILE_SIZE);
        panel.add(this);

    }

    public void remove(){
        panel.remove(this);
    }

}
