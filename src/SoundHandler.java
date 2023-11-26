import java.io.*;
import javax.sound.sampled.*;

public class SoundHandler{

    File crunch;
    File boing;
    AudioInputStream audioStream;
    Clip clip;
    
    public SoundHandler(){
        crunch = new File("lib/crunch.wav");
        boing = new File("lib/boing.wav");
    }

    public void crunch(){
        try{
            audioStream = AudioSystem.getAudioInputStream(crunch);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }
        catch(UnsupportedAudioFileException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(LineUnavailableException e){
            e.printStackTrace();
        }
        clip.start();
    }

    public void boing(){
        try{
            audioStream = AudioSystem.getAudioInputStream(boing);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }
        catch(UnsupportedAudioFileException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(LineUnavailableException e){
            e.printStackTrace();
        }
        clip.start();
    }


}
