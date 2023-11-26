import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel{

    static JLabel trophy;
    static JLabel highScoreLabel;
    static JLabel food;
    static JLabel scoreLabel;
    static JButton mute;
    static JButton settings;

    static int highScore;
    static int score;
    static boolean muted = false;
    
    public ScoreBoard(JFrame frame, Panel panel, Options options){

        trophy = new JLabel(new ImageIcon("lib/trophy.png"));
        highScoreLabel = new JLabel(String.valueOf(highScore));
        food = new JLabel(new ImageIcon("lib/food.png"));
        scoreLabel = new JLabel(String.valueOf(score));
        if(muted) mute = new JButton(new ImageIcon("lib/muted.png"));
        else mute = new JButton(new ImageIcon("lib/sound.png"));
        settings = new JButton(new ImageIcon("lib/settings.png"));
        
        trophy.setBounds(15, 9, 28, 32);
        highScoreLabel.setBounds(58, 0, 50, 50);
        highScoreLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));

        food.setBounds(133, 7, 36, 36);
        scoreLabel.setBounds(188, 0, 50, 50);
        scoreLabel.setFont(new Font("Segoe Print", Font.PLAIN, 20));

        mute.setBounds(700, 7, 36, 36);
        mute.setContentAreaFilled(false);
        mute.setFocusable(false);
        mute.addActionListener((e) -> {
            if(muted){
                mute.setIcon(new ImageIcon("lib/sound.png"));
                muted = false;
            }
            else{
                mute.setIcon(new ImageIcon("lib/muted.png"));
                muted = true;
            }
        });

        settings.setBounds(746, 7, 36, 36);
        settings.setContentAreaFilled(false);
        settings.setFocusable(false);
        settings.addActionListener((e) -> {
            Snake.gameOver(false);
            frame.remove(this);
            frame.remove(panel);
            frame.add(options);
            frame.repaint();
        });

        this.setPreferredSize(new Dimension(Panel.WIDTH, 50));
        this.setBackground(Color.GRAY);
        this.setLayout(null);

        this.add(trophy);
        this.add(highScoreLabel);
        this.add(food);
        this.add(scoreLabel);
        this.add(mute);
        this.add(settings);

    }

}
