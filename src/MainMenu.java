import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel{

    Options options;

    JLabel title;
    JLabel snake;
    JLabel food;
    JButton startGame;
    JButton mute;
    
    public MainMenu(JFrame frame){

        mute = new JButton(new ImageIcon("lib/sound.png"));
        title = new JLabel("Snake");
        snake = new JLabel();
        food = new JLabel(new ImageIcon("lib/food.png"));
        startGame = new JButton("Start Game");
        
        mute.setPreferredSize(new Dimension(36, 36));
        mute.setContentAreaFilled(false);
        mute.setFocusable(false);
        mute.addActionListener((e) -> {
            if(ScoreBoard.muted){
                mute.setIcon(new ImageIcon("lib/sound.png"));
                ScoreBoard.muted = false;
            }
            else{
                mute.setIcon(new ImageIcon("lib/muted.png"));
                ScoreBoard.muted = true;
            }
        });

        title.setFont(new Font("Segoe Print", Font.PLAIN, 100));
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(800, 100));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        snake.setBackground(Color.GREEN);
        snake.setOpaque(true);
        snake.setPreferredSize(new Dimension(160, 32));

        food.setPreferredSize(new Dimension(96, 32));

        startGame.setPreferredSize(new Dimension(500, 100));
        startGame.setContentAreaFilled(false);
        startGame.setFocusable(false);
        startGame.setFont(new Font("Segoe Print", Font.PLAIN, 50));
        startGame.setForeground(Color.WHITE);
        startGame.addActionListener((e) -> {
            options = new Options(frame);
            frame.remove(this);
            frame.add(options);
            frame.repaint();
            frame.revalidate();
        });

        this.setPreferredSize(new Dimension(Panel.WIDTH, Panel.HEIGHT+50));
        this.setBackground(Color.BLACK);
        this.setLayout(new FlowLayout());

        this.add(Box.createRigidArea(new Dimension(720, 50)));
        this.add(mute);
        this.add(Box.createRigidArea(new Dimension(800, 200)));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(800, 50)));
        this.add(snake);
        this.add(food);
        this.add(Box.createRigidArea(new Dimension(800, 50)));
        this.add(startGame);

        frame.add(this);
    
    }

}
