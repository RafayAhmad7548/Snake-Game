import java.awt.event.*;

public class KeyHandler implements KeyListener{

    boolean upPressed, downPressed, rightPressed, leftPressed;
    Panel panel;

    public KeyHandler(Panel panel){
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
            case KeyEvent.VK_W : upPressed = true;
            break;
            case KeyEvent.VK_DOWN :
            case KeyEvent.VK_S : downPressed = true;
            break;
            case KeyEvent.VK_LEFT :
            case KeyEvent.VK_A : leftPressed = true;
            break;
            case KeyEvent.VK_RIGHT :
            case KeyEvent.VK_D : rightPressed = true;
            break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP :
            case KeyEvent.VK_W : upPressed = false;
            break;
            case KeyEvent.VK_DOWN :
            case KeyEvent.VK_S : downPressed = false;
            break;
            case KeyEvent.VK_LEFT :
            case KeyEvent.VK_A : leftPressed = false;
            break;
            case KeyEvent.VK_RIGHT :
            case KeyEvent.VK_D : rightPressed = false;
            break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
}
