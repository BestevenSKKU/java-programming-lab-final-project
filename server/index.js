const app = require('express')();
const server = require('http').Server(app);
const io = require('socket.io')(server);

let players = [];

server.listen(8080, () => {
  console.log('Server is running on port 8080');
});

io.on('connection', (socket) => {
  console.log('Player connected ' + socket.id);
  socket.emit('socketID', {
    id: socket.id,
  });

  // 게임 접속시 기존 플레이어 정보 전달
  socket.emit(
    'getPlayers',
    players.filter((item) => item.id !== socket.id)
  );

  // 게임 접속시 players에 새 플레이어 정보 저장
  socket.on('playerData', (data) => {
    players.push({
      id: socket.id,
      x: data.x,
      y: data.y,
      name: data.name,
      gender: data.gender,
    });
    //console.log(players);
    // 새로운 플레이어 접속시 기존 플레이어들에게 새 플레이어 정보 전달
    socket.broadcast.emit(
      'newPlayer',
      players.find((item) => item.id === socket.id)
    );
  });

  // 플레이어 이동시 정보 전달 (data에 x, y좌표가 들어옴)
  socket.on('playerMoved', (data) => {
    data.id = socket.id;
    ///////////////////////////////////
    // 플레이어 위치정보 저장 코드 작성 예정
    ///////////////////////////////////
    socket.broadcast.emit('playerMoved', data);
  });

  socket.on('disconnect', () => {
    console.log('Player disconnected ' + socket.id);
    socket.broadcast.emit('playerDisconnected', {
      id: socket.id,
    });
    players = players.filter((item) => item.id !== socket.id);
  });
});
