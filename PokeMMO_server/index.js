var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);

var clients = [];

var EVENT_SOCKET_ID = "socketID";
var EVENT_PLAYER_CONNECTED = "playerConnected";
var EVENT_PLAYER_DISCONNECTED = "playerDisconnected";
var EVENT_ONLINE_PLAYERS = "connectedPlayers";
var EVENT_PLAYER_MOVED = "playerMoved";

server.listen(8080, function(){
  console.log("Server has been started...");  
});

io.on('connection', function(client){
    console.log("Player connected!");

    var p = new player(client.id, "tmx/Aldbrough.tmx", 18, 37)

    client.emit(EVENT_SOCKET_ID, { id: client.id });
    client.broadcast.emit(EVENT_PLAYER_CONNECTED, { p });
    client.emit(EVENT_ONLINE_PLAYERS, clients);

    clients.push(p);

    client.on("playerMoved", function(data) {
        data.id = client.id;
        client.broadcast.emit("playerMoved", data)
        for (var i = 0; i < clients.length; i++) {
            if(clients[i].id == client.id) {
                clients[i].x = data.x;
                clients[i].y = data.y;
                clients[i].locationId = data.l;
            }
        }
    });

    client.on('disconnect', function(){

        console.log("Player disconnected!");
        client.broadcast.emit(EVENT_PLAYER_DISCONNECTED, { id: client.id });

        for (var i = 0; i < clients.length; i++) {
            if(clients[i].id == client.id) {
                clients.splice(i, 1);
            }
        }
    });
});

function player(id, locationId, x, y) {
    this.id = id;
    this.locationId = locationId;
    this.x = x;
    this.y = y;
}
