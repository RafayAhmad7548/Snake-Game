import javax.swing.*;
import java.awt.*;

public class Options extends JPanel{

    Panel panel;
    ScoreBoard scoreBoard;

    int centerX = 296;
    Timer timer;

    JLabel speedSelect;
    JButton fast, normal, slow;
    String centerS = "normal";

    JLabel foodSelect;
    JButton f1, f3, f5;
    String centerF = "f1";

    JLabel diffcultySelect;
    JButton easy, medium, hard;
    String centerD = "medium";

    JButton startGame;

    public Options(JFrame frame){

        speedSelect = new JLabel("Select Speed");
        fast = new JButton("Fast");
        normal = new JButton("Normal");
        slow = new JButton("Slow");
        
        foodSelect = new JLabel("Food Multiplier");
        f1 = new JButton(new ImageIcon("lib/f1.png"));
        f3 = new JButton(new ImageIcon("lib/f3g.png"));
        f5 = new JButton(new ImageIcon("lib/f5g.png"));

        diffcultySelect = new JLabel("Difficulty");
        easy = new JButton("Easy");
        medium = new JButton("Medium");
        hard = new JButton("Hard");

        startGame = new JButton("Play");

        //---------------------------------------SPEED SELECT-------------------------------------//

        speedSelect.setBounds(0, 0, Panel.WIDTH, 100);
        speedSelect.setHorizontalAlignment(SwingConstants.CENTER);
        speedSelect.setFont(new Font("Segoe Print", Font.PLAIN, 50));
        speedSelect.setForeground(Color.WHITE);

        fast.setBounds(96, 125, 200, 75);
        fast.setContentAreaFilled(false);
        fast.setBorder(null);
        fast.setFocusable(false);
        fast.setFont(new Font("Segoe Print", Font.PLAIN, 40));
        fast.setForeground(Color.GRAY);
        fast.addActionListener((e) -> {
            fast.setForeground(Color.WHITE);
            moveToCenterS(fast, centerS);
            centerS = "fast";
            Snake.speed = 9;
            Snake.countLimit = Panel.TILE_SIZE/Snake.speed;
        });

        normal.setBounds(296, 100, 200, 75);
        normal.setContentAreaFilled(false);
        normal.setBorder(null);
        normal.setFocusable(false);
        normal.setFont(new Font("Segoe Print", Font.PLAIN, 40));
        normal.setForeground(Color.WHITE);
        normal.addActionListener((e) -> {
            normal.setForeground(Color.WHITE);
            moveToCenterS(normal, centerS);
            centerS = "normal";
            Snake.speed = 6;
            Snake.countLimit = Panel.TILE_SIZE/Snake.speed;
        });

        slow.setBounds(496, 125, 200, 75);
        slow.setContentAreaFilled(false);
        slow.setBorder(null);
        slow.setFocusable(false);
        slow.setFont(new Font("Segoe Print", Font.PLAIN, 40));
        slow.setForeground(Color.GRAY);
        slow.addActionListener((e) -> {
            slow.setForeground(Color.WHITE);
            moveToCenterS(slow, centerS);
            centerS = "slow";
            Snake.speed = 4;
            Snake.countLimit = Panel.TILE_SIZE/Snake.speed;
        });

        //-------------------------------------FOOD SELECT-----------------------------------------//

        foodSelect.setBounds(0, 200, Panel.WIDTH, 100);
        foodSelect.setHorizontalAlignment(SwingConstants.CENTER);
        foodSelect.setFont(new Font("Segoe Print", Font.PLAIN, 50));
        foodSelect.setForeground(Color.WHITE);

        f3.setBounds(96, 325, 200, 80);
        f3.setContentAreaFilled(false);
        f3.setFocusable(false);
        f3.setBorder(null);
        f3.addActionListener((e) -> {
            f3.setIcon(new ImageIcon("lib/f3.png"));
            moveToCenterF(f3, centerF);
            centerF = "f3";
            Snake.foodMultiplier = 3;
        });

        f1.setBounds(296, 300, 200, 80);
        f1.setContentAreaFilled(false);
        f1.setFocusable(false);
        f1.setBorder(null);
        f1.addActionListener((e) -> {
            f1.setIcon(new ImageIcon("lib/f1.png"));
            moveToCenterF(f1, centerF);
            centerF = "f1";
            Snake.foodMultiplier = 1;
        });

        f5.setBounds(496, 325, 200, 80);
        f5.setContentAreaFilled(false);
        f5.setFocusable(false);
        f5.setBorder(null);
        f5.addActionListener((e) -> {
            f5.setIcon(new ImageIcon("lib/f5.png"));
            moveToCenterF(f5, centerF);
            centerF = "f5";
            Snake.foodMultiplier = 5;
        });

        //-----------------------------------DIFFICULTY SELECT-----------------------------------------//

        diffcultySelect.setBounds(0, 425, Panel.WIDTH, 100);
        diffcultySelect.setHorizontalAlignment(SwingConstants.CENTER);
        diffcultySelect.setFont(new Font("Segoe Print", Font.PLAIN, 50));
        diffcultySelect.setForeground(Color.WHITE);

        easy.setBounds(96, 550, 200, 75);
        easy.setContentAreaFilled(false);
        easy.setFocusable(false);
        easy.setBorder(null);
        easy.setFont(new Font("Segoe Print", Font.PLAIN, 40));
        easy.setForeground(Color.GRAY);
        easy.addActionListener((e) -> {
            easy.setForeground(Color.WHITE);
            moveToCenterD(easy, centerD);
            centerD = "easy";
            Snake.difficulty = "easy";
        });
        
        medium.setBounds(296, 525, 200, 75);
        medium.setContentAreaFilled(false);
        medium.setFocusable(false);
        medium.setBorder(null);
        medium.setFont(new Font("Segoe Print", Font.PLAIN, 40));
        medium.setForeground(Color.WHITE);
        medium.addActionListener((e) -> {
            medium.setForeground(Color.WHITE);
            moveToCenterD(medium, centerD);
            centerD = "medium";
            Snake.difficulty = "medium";
        });

        hard.setBounds(496, 550, 200, 75);
        hard.setContentAreaFilled(false);
        hard.setFocusable(false);
        hard.setBorder(null);
        hard.setFont(new Font("Segoe Print", Font.PLAIN, 40));
        hard.setForeground(Color.GRAY);
        hard.addActionListener((e) -> {
            hard.setForeground(Color.WHITE);
            moveToCenterD(hard, centerD);
            centerD = "hard";
            Snake.difficulty = "hard";
        });

        //----------------------------PLAY BUTTON------------------------//

        startGame.setBounds(296, 675, 200, 50);
        startGame.setContentAreaFilled(false);
        startGame.setFocusable(false);
        startGame.setFont(new Font("Segoe Print", Font.PLAIN, 30));
        startGame.setForeground(Color.WHITE);
        startGame.addActionListener((e) -> {
            panel = new Panel();
            scoreBoard = new ScoreBoard(frame, panel, this);
            frame.remove(this);
            frame.add(scoreBoard, BorderLayout.NORTH);
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            panel.requestFocus();
            frame.repaint();
            frame.revalidate();
        });


        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        this.add(speedSelect);
        this.add(fast);
        this.add(normal);
        this.add(slow);

        this.add(foodSelect);
        this.add(f1);
        this.add(f3);
        this.add(f5);

        this.add(diffcultySelect);
        this.add(easy);
        this.add(medium);
        this.add(hard);
        
        this.add(startGame);

    }

    public void moveToCenterS(JButton button, String center){
        timer = new Timer(1, (e) -> {
            if(button.getX()>centerX){
                switch(center){
                    case "normal" :
                        normal.setLocation(normal.getX()+8, normal.getY()+1);
                        normal.setForeground(Color.GRAY);
                    break;
                    case "fast" :
                        fast.setLocation(fast.getX()+8, fast.getY()+1);
                        fast.setForeground(Color.GRAY);
                    break;
                    case "slow" :
                        slow.setLocation(slow.getX()+8, slow.getY()+1);
                        slow.setForeground(Color.GRAY);
                    break;
                }
                button.setLocation(button.getX()-8, button.getY()-1);
            }
            else{
                switch(center){
                    case "normal" :
                        normal.setLocation(normal.getX()-8, normal.getY()+1);
                        normal.setForeground(Color.GRAY);
                    break;
                    case "fast" :
                        fast.setLocation(fast.getX()-8, fast.getY()+1);
                        fast.setForeground(Color.GRAY);
                    break;
                    case "slow" :
                        slow.setLocation(slow.getX()-8, slow.getY()+1);
                        slow.setForeground(Color.GRAY);
                    break;
                }
                button.setLocation(button.getX()+8, button.getY()-1);
            }
            if(button.getX()==centerX) timer.stop();
        });
        timer.start();
    }

    public void moveToCenterF(JButton button,String center){
        timer = new Timer(1, (e) -> {
            if(button.getX()>centerX){
                switch(center){
                    case "f1" :
                        f1.setLocation(f1.getX()+8, f1.getY()+1);
                        f1.setIcon(new ImageIcon("lib/f1g.png"));
                    break;
                    case "f3" :
                        f3.setLocation(f3.getX()+8, f3.getY()+1);
                        f3.setIcon(new ImageIcon("lib/f3g.png"));
                    break;
                    case "f5" :
                        f5.setLocation(f5.getX()+8, f5.getY()+1);
                        f5.setIcon(new ImageIcon("lib/f5g.png"));
                    break;
                }
                button.setLocation(button.getX()-8, button.getY()-1);
            }
            else{
                switch(center){
                    case "f1" :
                        f1.setLocation(f1.getX()-8, f1.getY()+1);
                        f1.setIcon(new ImageIcon("lib/f1g.png"));
                    break;
                    case "f3" :
                        f3.setLocation(f3.getX()-8, f3.getY()+1);
                        f3.setIcon(new ImageIcon("lib/f3g.png"));
                    break;
                    case "f5" :
                        f5.setLocation(f5.getX()-8, f5.getY()+1);
                        f5.setIcon(new ImageIcon("lib/f5g.png"));
                    break;
                }
                button.setLocation(button.getX()+8, button.getY()-1);
            }
            if(button.getX()==centerX) timer.stop();
        });
        timer.start();
    }

    public void moveToCenterD(JButton button, String center){
        timer = new Timer(1, (e) -> {
            if(button.getX()>centerX){
                switch(center){
                    case "easy" :
                        easy.setLocation(easy.getX()+8, easy.getY()+1);
                        easy.setForeground(Color.GRAY);
                    break;
                    case "medium" :
                        medium.setLocation(medium.getX()+8, medium.getY()+1);
                        medium.setForeground(Color.GRAY);
                    break;
                    case "hard" :
                        hard.setLocation(hard.getX()+8, hard.getY()+1);
                        hard.setForeground(Color.GRAY);
                    break;
                }
                button.setLocation(button.getX()-8, button.getY()-1);
            }
            else{
                switch(center){
                    case "easy" :
                        easy.setLocation(easy.getX()-8, easy.getY()+1);
                        easy.setForeground(Color.GRAY);
                    break;
                    case "medium" :
                        medium.setLocation(medium.getX()-8, medium.getY()+1);
                        medium.setForeground(Color.GRAY);
                    break;
                    case "hard" :
                        hard.setLocation(hard.getX()-8, hard.getY()+1);
                        hard.setForeground(Color.GRAY);
                    break;
                }
                button.setLocation(button.getX()+8, button.getY()-1);
            }
            if(button.getX()==centerX) timer.stop();
        });
        timer.start();
    }

}

