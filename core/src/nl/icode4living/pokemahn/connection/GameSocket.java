package nl.icode4living.pokemahn.connection;

import com.badlogic.gdx.Gdx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import nl.icode4living.pokemahn.ui.screens.GameScreen;

public class GameSocket {

    private static final float UPDATE_TIME = 1/15f;
    private static float timer;

    private static final String serverLocation = "http://localhost:8080";

    public static final String EVENT_SOCKET_ID = "socketID";
    public static final String EVENT_PLAYER_CONNECTED = "playerConnected";
    public static final String EVENT_PLAYER_DISCONNECTED = "playerDisconnected";
    public static final String EVENT_ONLINE_PLAYERS = "connectedPlayers";
    public static final String EVENT_PLAYER_MOVED = "playerMoved";

    private Socket socket;

    private static GameSocket instance;
    private String id;
    private GameScreen screen;

    public static GameSocket getInstance() {
        if (instance == null) {
            instance = new GameSocket();
        }
        return instance;
    }

    private GameSocket() {

    }

    public void connect() {
        try {
            socket = IO.socket(serverLocation);
            socket.connect();
            configureEvents();
        } catch (URISyntaxException e) {
            log("Error connecting to socket", e);
        }
    }

    public void disconnect() {
        socket.disconnect();
    }

    private void configureEvents() {

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                log("Connected");
            }
        }).on(EVENT_SOCKET_ID, new Emitter.Listener() { // called when connecting
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    id = data.getString("id");
                    log(String.format("You connected with id %s", id));
                } catch (JSONException e) {
                    log("Error getting id", e);
                }
            }
        }).on(EVENT_PLAYER_CONNECTED, new Emitter.Listener() { // Called
            @Override
            public void call(Object... args) {
                JSONObject object = (JSONObject) args[0];
                try {
                    log("Another player connected");
                    object = object.getJSONObject("p");
                    screen.playerJoins(
                        object.getString("id"),
                        object.getString("locationId"),
                        ((Double) object.getDouble("x")).floatValue(),
                        ((Double) object.getDouble("y")).floatValue()
                    );
                } catch (JSONException e) {
                    log("Error getting id", e);
                }
            }
        }).on(EVENT_PLAYER_DISCONNECTED, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    screen.getOnlinePlayers().remove(id);
                    log(String.format("online player id %s", id));
                } catch (JSONException e) {
                    log("Error getting id", e);
                }
            }
        }).on(EVENT_ONLINE_PLAYERS, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray objects = (JSONArray) args[0];
                try {
                    for (int i = 0; i < objects.length(); i++) {
                        screen.playerJoins(
                                objects.getJSONObject(i).getString("id"),
                                objects.getJSONObject(i).getString("locationId"),
                                ((Double) objects.getJSONObject(i).getDouble("x")).floatValue(),
                                ((Double) objects.getJSONObject(i).getDouble("y")).floatValue()
                        );
                    }
                } catch (JSONException e) {
                    log("Error getting id", e);
                }
            }
        }).on(EVENT_PLAYER_MOVED, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String playerId = data.getString("id");
                    Double x = data.getDouble("x");
                    Double y = data.getDouble("y");
                    String locationId = data.getString("l");

                    if(screen.getOnlinePlayers().get(playerId) != null) {
                        screen.getOnlinePlayers().get(playerId).setPosition(x.floatValue(), y.floatValue());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendUpdate(float delta){
        timer += delta;
        if(timer >= UPDATE_TIME && screen.getPlayer() != null && screen.getPlayer().hasMoved) {
            JSONObject data = new JSONObject();
            try {
                data.put("x", screen.getPlayer().getX());
                data.put("y", screen.getPlayer().getY());
                data.put("l", screen.getPlayer().getLocationIdentifier());

                socket.emit(EVENT_PLAYER_MOVED, data);
            } catch (JSONException e) {
                Gdx.app.log("SocketIO", "Error sending update");
            }
        }
    }

    private void log(String s){
        Gdx.app.log("SocketIO", s);
    }

    private void log(String s, Exception e) {
        Gdx.app.log("SocketIO", s, e);
    }

    public String getId() {
        return id;
    }

    public void linkToGameScreen(GameScreen gameScreen) {
        this.screen = gameScreen;
    }
}
