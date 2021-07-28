package TankGame;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * The Sound Class handles all sounds. It is static since
 * the alternative would be creating one Sound object that is
 * shared by all objects that use it, so it made more sense
 * to make the methods static.
 *
 * @author Alicia Ramirez
 */
public class Sound {

    private static Clip musicLoop;

    Sound() {
        musicLoop = null;
    }

    // Play main music loop
    public static void playMusicLoop(){
        try {
            musicLoop = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sound.class.getClassLoader().getResource("Piano Remix.wav"));
            musicLoop.open(inputStream);
            FloatControl gainControl =
                    (FloatControl) musicLoop.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            musicLoop.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ignored) {

        }
    }

    // Stop main music loop
    public static void stopMusicLoop() {
        musicLoop.stop();
    }

    // Play Large Explosion sound
    public static void playLargeExplosion(){
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sound.class.getClassLoader().getResource("death.wav"));
            clip.open(inputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-5.0f);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ignored) {

        }
    }

    // Play Small Explosion sound
    public static void playSmallExplosion(){
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sound.class.getClassLoader().getResource("fireball.wav"));
            clip.open(inputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ignored) {

        }
    }

    //Play Grunt Sound
    public static void playGrunt(){
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sound.class.getClassLoader().getResource("grunt.wav"));
            clip.open(inputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ignored) {

        }
    }

    // Play HP PowerUp sound
    public static void playHPPowerUp(){
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sound.class.getClassLoader().getResource("Powerup2.wav"));
            clip.open(inputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-5.0f);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ignored) {

        }
    }

    // Play Game Over sound
    public static void playGameOver(){
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sound.class.getClassLoader().getResource("Success 2.wav"));
            clip.open(inputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException ignored) {

        }
    }


}
