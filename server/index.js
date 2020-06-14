const app = require('express')();
const server = require('http').Server(app);
const io = require('socket.io')(server);

let players = [];
let admin = '';

server.listen(8080, () => {
  console.log('Server is running on port 8080');
});

io.on('connection', (socket) => {
  console.log('<System> New Player connected');
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
    console.log('<System> New Player Information : ' + data.name + '\t' + socket.id);
    console.log(players);
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
  //주민들 이동시 정보 전달 (data: Array, in item : x, y, name object)
  socket.on('villagerMoved', (data) => {
    ///////////////////////////////////
    // 주민 위치정보 저장 코드 작성 예정
    ///////////////////////////////////

    if(players[0].id === socket.id) {
      socket.broadcast.emit('villagerMoved', data);
    }

  });

  socket.on('disconnect', () => {
    console.log('<System> Player disconnected');
    console.log('<System> Disconnected Player Information : ' + players.find(item => item.id === socket.id).name + '\t' + socket.id);
    socket.broadcast.emit('playerDisconnected', {
      id: socket.id,
    });
    players = players.filter((item) => item.id !== socket.id);
    console.log(players);
  });
});
