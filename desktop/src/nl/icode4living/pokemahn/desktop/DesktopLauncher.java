package nl.icode4living.pokemahn.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import nl.icode4living.pokemahn.GdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GdxGame.V_WIDTH;
		config.height= GdxGame.V_HEIGHT;
		new LwjglApplication(new GdxGame(), config);
	}
}
