import javax.swing.*;

public class Main{
    public static void main(String[] args){

        JFrame frame = new JFrame("Snake Game");
        new MainMenu(frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(560, 90);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        
    } 
}
