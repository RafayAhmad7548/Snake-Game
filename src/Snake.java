import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Snake extends JLabel{

    static KeyHandler keyH;
    static SoundHandler soundH;
    static Panel panel;
    static Random random;

    static int speed = 6;
    static int x = Panel.TILE_SIZE*4;
    static int y = Panel.TILE_SIZE*12;

    static ArrayList<Snake> snakes = new ArrayList<>();
    static ArrayList<String> directions = new ArrayList<>();
    static int count;
    static int countLimit = Panel.TILE_SIZE/speed;
    
    static ArrayList<Food> apples = new ArrayList<>();
    static int foodMultiplier = 1;
    static String difficulty = "medium";
    static ArrayList<TileBlock> blockedTiles = new ArrayList<>();

    static JLabel gameOver;
    static JLabel trophy;
    static JLabel highScoreLabel;
    static JLabel foodScore;
    static JLabel scoreLabel;
    static JButton playAgain;

    public Snake(KeyHandler keyH, Panel panel, SoundHandler soundH){

        Snake.keyH = keyH;
        Snake.soundH = soundH;
        Snake.panel = panel;
        random = new Random();
        newSnake();
        
        //-----------------------------GAME OVER STUFF--------------------------//

        gameOver = new JLabel("Game Over");
        trophy = new JLabel(new ImageIcon("lib/trophy2.png"));
        highScoreLabel = new JLabel(String.valueOf(ScoreBoard.highScore));
        foodScore = new JLabel(new ImageIcon("lib/food.png"));
        scoreLabel = new JLabel(String.valueOf(ScoreBoard.score));
        playAgain = new JButton("Play Again");

        gameOver.setFont(new Font("Segoe Print", Font.PLAIN, 100));
        gameOver.setBounds(0, 150, 800, 100);
        gameOver.setHorizontalAlignment(SwingConstants.CENTER);
        gameOver.setForeground(Color.WHITE);

        trophy.setBounds(300, 300, 56, 64);
        highScoreLabel.setBounds(400, 300, 100, 50);
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setFont(new Font("Segoe Print", Font.PLAIN, 40));

        foodScore.setBounds(310, 415, 32, 32);
        scoreLabel.setBounds(400, 400, 100, 50);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Segoe Print", Font.PLAIN, 40));

        playAgain.setBounds(250, 500, 300, 100);
        playAgain.setContentAreaFilled(false);
        playAgain.setFocusable(false);
        playAgain.setForeground(Color.WHITE);
        playAgain.setFont(new Font("Segoe Print", Font.PLAIN, 40));
        playAgain.addActionListener((e) -> playAgain());

    }

    public Snake(){
        this.setBounds(x, y, Panel.TILE_SIZE,Panel.TILE_SIZE);
        this.setOpaque(true);
        this.setBackground(Color.GREEN);
        snakes.add(this);
        directions.add("right");
    }

    public static void update(){
        setSnakeDirection(keyH);
        count();
        moveSnake();
        turn();
        checkCollision();
    }

    public static void newSnake(){
        snakes.clear();
        directions.clear();
        apples.clear();
        blockedTiles.clear();
        count = 0;
        for(int i=0;i<3;i++){
            panel.add(new Snake());
            snakes.get(i).setLocation(x-(i*Panel.TILE_SIZE), y);
        }
        snakes.get(0).setBackground(Color.decode("#36B464"));
        for(int i=0;i<foodMultiplier;i++){
            apples.add(new Food(panel));
        }
        
    }

    public static void setSnakeDirection(KeyHandler keyH){
        for(int i=1;i<snakes.size();i++){
            if(keyH.upPressed==true && directions.get(0)!="down" && count==countLimit){
                directions.set(0, "up");
            }
            else if(keyH.downPressed==true && directions.get(0)!="up" && count==countLimit){
                directions.set(0, "down");
            }
            else if(keyH.leftPressed==true && directions.get(0)!="right" && count==countLimit){
                directions.set(0, "left");
            }
            else if(keyH.rightPressed==true && directions.get(0)!="left" && count==countLimit){
                directions.set(0, "right");
            }
        }
    }

    public static void moveSnake(){
        for(int i=0;i<snakes.size();i++){
            switch(directions.get(i)){
            case "up" :
                snakes.get(i).setLocation(snakes.get(i).getX(),snakes.get(i).getY()-speed);
            break;
            case "down" : 
                snakes.get(i).setLocation(snakes.get(i).getX(),snakes.get(i).getY()+speed);
            break;
            case "left" : 
                snakes.get(i).setLocation(snakes.get(i).getX()-speed,snakes.get(i).getY());
            break;
            case "right" : 
                snakes.get(i).setLocation(snakes.get(i).getX()+speed,snakes.get(i).getY());
            break;
            }
        } 
    }

    public static void count(){
        if(count==countLimit) count = 0;
        count++;
    }

    public static void turn(){
        for(int i=snakes.size()-1;i>0;i--){
            if(count==countLimit) directions.set(i, directions.get(i-1));
        }
    
    }

    public static void growSnake(){
        if(!ScoreBoard.muted) soundH.crunch();
        if(random.nextBoolean() && difficulty=="hard") blockedTiles.add(new TileBlock(panel));
        panel.add(new Snake());
        directions.set(snakes.size()-1, directions.get(snakes.size()-2));
        switch(directions.get(snakes.size()-1)){
            case "up": snakes.get(snakes.size()-1).setLocation(snakes.get(snakes.size()-2).getX(), snakes.get(snakes.size()-2).getY()+Panel.TILE_SIZE);
            break;
            case "down": snakes.get(snakes.size()-1).setLocation(snakes.get(snakes.size()-2).getX(), snakes.get(snakes.size()-2).getY()-Panel.TILE_SIZE);
            break;
            case "left": snakes.get(snakes.size()-1).setLocation(snakes.get(snakes.size()-2).getX()+Panel.TILE_SIZE, snakes.get(snakes.size()-2).getY());
            break;
            case "right": snakes.get(snakes.size()-1).setLocation(snakes.get(snakes.size()-2).getX()-Panel.TILE_SIZE, snakes.get(snakes.size()-2).getY());
            break;
        }
        ScoreBoard.score++;
        ScoreBoard.scoreLabel.setText(String.valueOf(ScoreBoard.score));
    }

    public static void checkCollision(){
        //eat food?
        for(int i=0;i<apples.size();i++){
            if(snakes.get(0).getX()==apples.get(i).getX() && snakes.get(0).getY()==apples.get(i).getY()){
                growSnake();
                apples.get(i).remove();
                apples.set(i, new Food(panel));
            }
        }
        //out of bound check
        if(snakes.get(0).getX()>(Panel.WIDTH-Panel.TILE_SIZE) || snakes.get(0).getX()<0 || snakes.get(0).getY()>(Panel.HEIGHT-Panel.TILE_SIZE) || snakes.get(0).getY()<0){
            gameOver(true);
        }
        //snake bite itself check
        if(difficulty!="easy"){
            switch(directions.get(0)){
                case "right":
                    for(int i=3;i<snakes.size();i++){
                        if(snakes.get(0).getX()+Panel.TILE_SIZE>snakes.get(i).getX() && snakes.get(0).getX()+Panel.TILE_SIZE<snakes.get(i).getX()+Panel.TILE_SIZE && snakes.get(0).getY()==snakes.get(i).getY()+speed && directions.get(i)=="up") gameOver(true);
                        if(snakes.get(0).getX()+Panel.TILE_SIZE>snakes.get(i).getX() && snakes.get(0).getX()+Panel.TILE_SIZE<snakes.get(i).getX()+Panel.TILE_SIZE && snakes.get(0).getY()==snakes.get(i).getY()-speed && directions.get(i)=="down") gameOver(true);
                    }
                break;
                case "left":
                    for(int i=3;i<snakes.size();i++){
                        if(snakes.get(0).getX()<snakes.get(i).getX()+Panel.TILE_SIZE && snakes.get(0).getX()>snakes.get(i).getX() && snakes.get(0).getY()==snakes.get(i).getY()+speed && directions.get(i)=="up") gameOver(true);
                        if(snakes.get(0).getX()<snakes.get(i).getX()+Panel.TILE_SIZE && snakes.get(0).getX()>snakes.get(i).getX() && snakes.get(0).getY()==snakes.get(i).getY()-speed && directions.get(i)=="down") gameOver(true);
                    }
                break;
                case "up":
                    for(int i=3;i<snakes.size();i++){
                        if(snakes.get(0).getY()<snakes.get(i).getY()+Panel.TILE_SIZE && snakes.get(0).getY()>snakes.get(i).getY() && snakes.get(0).getX()==snakes.get(i).getX()-speed && directions.get(i)=="right") gameOver(true);
                        if(snakes.get(0).getY()<snakes.get(i).getY()+Panel.TILE_SIZE && snakes.get(0).getY()>snakes.get(i).getY() && snakes.get(0).getX()==snakes.get(i).getX()+speed && directions.get(i)=="left") gameOver(true);
                    }
                break;
                case "down":
                    for(int i=3;i<snakes.size();i++){
                        if(snakes.get(0).getY()+Panel.TILE_SIZE>snakes.get(i).getY() && snakes.get(0).getY()+Panel.TILE_SIZE<snakes.get(i).getY()+Panel.TILE_SIZE && snakes.get(0).getX()==snakes.get(i).getX()-speed && directions.get(i)=="right") gameOver(true);
                        if(snakes.get(0).getY()+Panel.TILE_SIZE>snakes.get(i).getY() && snakes.get(0).getY()+Panel.TILE_SIZE<snakes.get(i).getY()+Panel.TILE_SIZE && snakes.get(0).getX()==snakes.get(i).getX()+speed && directions.get(i)=="left") gameOver(true);
                    }
                    break;
            }
        }
        //block tile check
        for(int i=0;i<blockedTiles.size();i++){
            switch(directions.get(0)){
                case "up" : if(snakes.get(0).getX()==blockedTiles.get(i).getX() && snakes.get(0).getY()==blockedTiles.get(i).getY()+Panel.TILE_SIZE) gameOver(true);
                break;
                case "down" : if(snakes.get(0).getX()==blockedTiles.get(i).getX() && snakes.get(0).getY()+Panel.TILE_SIZE==blockedTiles.get(i).getY()) gameOver(true);
                break;
                case "left" : if(snakes.get(0).getX()==blockedTiles.get(i).getX()+Panel.TILE_SIZE && snakes.get(0).getY()==blockedTiles.get(i).getY()) gameOver(true);
                break;
                case "right" : if(snakes.get(0).getX()+Panel.TILE_SIZE==blockedTiles.get(i).getX() && snakes.get(0).getY()==blockedTiles.get(i).getY()) gameOver(true);
                break;
            }
        }
    }

    public static void blockTile(){
        
    }
    
    public static void gameOver(boolean playSound){
        
        if(playSound && !ScoreBoard.muted) soundH.boing();
        panel.isGameRunnning = false;

        if(ScoreBoard.score>ScoreBoard.highScore) ScoreBoard.highScore = ScoreBoard.score;
        highScoreLabel.setText(String.valueOf(ScoreBoard.highScore));
        scoreLabel.setText(String.valueOf(ScoreBoard.score));
        ScoreBoard.score = 0;

        for(int i=0;i<apples.size();i++) apples.get(i).remove();
        for(int i=0;i<snakes.size();i++) panel.remove(snakes.get(i));
        for(int i=0;i<blockedTiles.size();i++) panel.remove(blockedTiles.get(i));
        panel.add(gameOver);
        panel.add(trophy);
        panel.add(highScoreLabel);
        panel.add(foodScore);
        panel.add(scoreLabel);
        panel.add(playAgain);
        for(int i=0;i<snakes.size();i++) panel.add(snakes.get(i));
        for(int i=0;i<blockedTiles.size();i++) panel.add(blockedTiles.get(i));
    }

    public static void playAgain(){

        panel.remove(gameOver);
        panel.remove(trophy);
        panel.remove(highScoreLabel);
        panel.remove(foodScore);
        panel.remove(scoreLabel);
        panel.remove(playAgain);
        for(int i=0;i<snakes.size();i++) panel.remove(snakes.get(i));
        for(int i=0;i<blockedTiles.size();i++) panel.remove(blockedTiles.get(i));
        panel.repaint();
        
        ScoreBoard.highScoreLabel.setText(String.valueOf(ScoreBoard.highScore));
        ScoreBoard.scoreLabel.setText(String.valueOf(ScoreBoard.score));
        
        newSnake();
        panel.isGameRunnning = true;
        Panel.gameloop = new Thread(panel);
        Panel.gameloop.start();
    }

}
