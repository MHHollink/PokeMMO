package nl.icode4living.pokemahn.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

public class GdxSoundsManager {

    private String currentlyPlaying;
    private Music music;

    private AssetManager manager;
    private static GdxSoundsManager instance;

    public static GdxSoundsManager getInstance() {
        if (instance == null) {
            instance = new GdxSoundsManager();
        }
        return instance;
    }

    private GdxSoundsManager() {
        manager = new AssetManager();
    }

    public void loadAssets() {
        manager.load("sfx/music/route_104.mp3", Music.class);
        manager.finishLoading();
    }

    public AssetManager getManager() {
        return manager;
    }

    public void playMusic(String file) {
        if (music != null) {
            if(currentlyPlaying.equals(file))
                return;
            music.stop();
            music.dispose();
        }
        music = manager.get(file, Music.class);
        music.setLooping(true);
        music.setVolume(.3f);
        music.play();
        currentlyPlaying = file;
    }
}
