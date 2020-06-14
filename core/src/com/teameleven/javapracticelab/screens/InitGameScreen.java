package com.teameleven.javapracticelab.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.teameleven.javapracticelab.UsasengCrossing;
import com.teameleven.javapracticelab.BackgroundObjects.*;
import com.teameleven.javapracticelab.BackgroundObjects.Stone;
import com.teameleven.javapracticelab.characters.Player;
import com.teameleven.javapracticelab.characters.Villager;
import com.teameleven.javapracticelab.items.*;

import com.teameleven.javapracticelab.utils.Gender;
import com.teameleven.javapracticelab.utils.Skins;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.badlogic.gdx.audio.Music;

public class InitGameScreen implements Screen {
    final UsasengCrossing game;
    private final float UPDATE_TIME = 1/60f;
    float timer;

    private Stage stage;
    private Label lblPlayer;
    private Label lblIsland;
    private Music Bgm;
    
    //상호작용 테스트
    Space_icon space_icon;
    Wait_icon wait_icon;
    private boolean player_coli_move = false;
    private boolean player_coli_tree = false;
    private boolean try_tree = false;
    private boolean player_coli_stone = false;
    private boolean try_stone = false;
    private boolean player_coli_pond = false;
    private boolean try_pond = false;
    private boolean player_coli_house = false;
    private boolean try_house = false;
    private int cooltime_tree = 0;
    private int cooltime_stone = 0;
    private int cooltime_pond = 0;
    ArrayList<Boolean> villagers_coli_move = new ArrayList<Boolean>();
    //
    
    private Socket socket;

    Player player;
    Map_forest map;
    Sea sea;
    HashMap<String, Player> friendlyPlayers;
    ArrayList<Villager> villagers = new ArrayList<Villager>();
    ArrayList<House> houses = new ArrayList<House>();
    ArrayList<Tree> trees = new ArrayList<Tree>();
    ArrayList<Stone> stones = new ArrayList<Stone>();
    ArrayList<Pond> ponds = new ArrayList<Pond>();
    
    
    SpriteBatch batch;
    OrthographicCamera camera1;

    Button inventory;

    boolean[] inventoryOpenFlg = {false};

    public InitGameScreen(final UsasengCrossing game, String playerName, String islandName, String gender) {
        this.game = game;
        
        //충돌테스트
        space_icon = new Space_icon();
        wait_icon = new Wait_icon();
        //
        
        Bgm=Gdx.audio.newMusic(Gdx.files.internal("bgm.mp3"));
        Bgm.setLooping(true);
        Bgm.setVolume(0.1f);;
        Bgm.play();
        
        int row_height = Gdx.graphics.getWidth() / 12;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        

        batch = new SpriteBatch();

        lblPlayer = new Label("플레이어: "+playerName, Skins.korean, "black");
        lblPlayer.setSize(Gdx.graphics.getWidth(),row_height);
        lblPlayer.setPosition(+10,700);
        lblPlayer.setAlignment(Align.left);
        stage.addActor(lblPlayer);
        lblIsland = new Label("섬: "+islandName, Skins.korean, "black");
        lblIsland.setSize(Gdx.graphics.getWidth(),row_height);
        lblIsland.setPosition(+10,650);
        lblIsland.setAlignment(Align.left);
        stage.addActor(lblIsland);

        Gender playerGender = Gender.MALE;
        if (gender.equals("F") || gender.equals("f")) {
            playerGender = Gender.FEMALE;
        }
        
        map = new Map_forest();
        sea = new Sea();
        player = new Player(playerName, playerGender);
        friendlyPlayers = new HashMap<>();
        
        villagers.add(new Villager("잭슨", Gender.MALE));
        villagers.add(new Villager("너굴", Gender.MALE));
        villagers.add(new Villager("쭈니", Gender.MALE));
        villagers_coli_move.add(false);
        villagers_coli_move.add(false);
        villagers_coli_move.add(false);
        
        houses.add(new House(true,0,200));
        houses.add(new House(false,700,700));
        
        ponds.add(new Pond(-1000, 100));
        
        trees.add(new Tree(300,200));
        trees.add(new Tree(-300,-200));
        
        stones.add(new Stone(600,-100));
        stones.add(new Stone(500,-300));
        
        camera1 = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera1.position.set(player.getX(),player.getY(),0);
        camera1.update();

        this.connectSocket();
        this.configSocketEvents();


        
        for(int i=1;i<=100;i++) {
        	player.getInventory().addItem(new SoftWood());
        	player.getInventory().addItem(new NormalStones());
        	player.getInventory().addItem(new Branch());
        	player.getInventory().addItem(new Vine());
        	
        }
        // item test
//        player.getInventory().addItem(new SoftWood());
//        player.getInventory().addItem(new SoftWood());
//        player.getInventory().addItem(new SoftWood());
//        player.getInventory().ckItems(new SoftWood(), 3);
//        player.getInventory().addItem(new Apple());
//        player.getInventory().addItem(new Peach());
    }

    @Override
    public void show() {
    	
    }

    @Override
    public void render(float delta) {
        updateServer(delta);
        Gdx.gl.glClearColor(1,1,1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sea.action(batch);
        map.action(batch);
        
        for(House house : houses) {
        	house.action(batch);
        }
        for(Tree tree : trees) {
        	tree.action(batch);
        }
        for(Stone stone : stones) {
        	stone.action(batch);
        }
        for(Pond pond : ponds) {
        	pond.action(batch);
        }
        
        int vil_cnt = 0;
        
        for(Villager villager : villagers) {
        	
        	villagers_coli_move.set(vil_cnt, false);
        	villager.action(batch);
        	villager_coli_move_ck(vil_cnt, villager);
        	
        	if (villagers_coli_move.get(vil_cnt)) {
        		villager.back_pos();
            }
        	vil_cnt++;
        }
        for(Map.Entry<String, Player> entry : friendlyPlayers.entrySet()) {
            entry.getValue().draw(batch);
        }
        
        
        player.action(batch);
        
        
        //전체 움직임 충돌 
        player_coli_move_ck();
        
        if (player_coli_move) {
        	player.back_pos();
        }
        player_coli_work();
       
        batch.end();
        
        
        stage.act();
        stage.draw();
        

        
        camera1.position.set(player.getX()+100,player.getY()+100,0);
        camera1.update();
        batch.setProjectionMatrix(camera1.combined);

        if (inventoryOpenFlg[0]) {
            Gdx.app.log("ButtenEvent", "Flag catch");
            inventoryOpenFlg[0] = false;
			game.setScreen(new InventoryScreen( game, (InitGameScreen)game.getScreen(), player ));

        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
				game.setScreen(new InventoryScreen( game, (InitGameScreen)game.getScreen(), player ));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.C)) {
        	game.setScreen(new CraftingScreen( game, (InitGameScreen)game.getScreen(), player ));
        }
        
    }
    
    public void villager_coli_move_ck(int vil_cnt, Villager villager) {
    	
    	
    	for(Tree tree : trees) {
        	if(isCollition_for_move(tree, villager)) {
        		villagers_coli_move.set(vil_cnt, true);
        		break;
        	}
        }
        
        for(Stone stone : stones) {
        	if(isCollition_for_move(stone, villager)) {
        		villagers_coli_move.set(vil_cnt, true);
        		break;
        	}
        }

        for(House house : houses) {
        	if(isCollition_for_move(house, villager)) {
        		villagers_coli_move.set(vil_cnt, true);
        		break;
        	}
        }
        
        for(Pond pond : ponds) {
        	if(isCollition_for_move(pond, villager)) {
        		villagers_coli_move.set(vil_cnt, true);
        		break;
        	}
        }
        
    	if(!isCollition_for_move(map, villager)) {
    		villagers_coli_move.set(vil_cnt, true);
    	}
        
        
        
    }
    
    public void player_coli_work() {
    	 //나무테스트
        if (player_coli_tree == true) {
        	
	        space_icon.setPosition(player.getX(), player.getY()+175);
	        space_icon.action(batch);
	        
	        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
	        	try_tree = true;
	        }
	        if (try_tree == true && cooltime_tree < 100 ) {
	        	wait_icon.setPosition(player.getX()+10, player.getY()+220);
		        wait_icon.action(batch);
	        	cooltime_tree ++;
	        }
	        if (try_tree == true && cooltime_tree >= 100) {
	        	player.getInventory().addRadomItem_fruit(player.getInventory().ckItems(new Axe(), 1));
		        try_tree = false;
		        cooltime_tree = 0;
	        }
	        
        }
        else {
        	cooltime_tree = 0;
        	try_tree = false;
        	}
        
        //돌 테스트
        if (player_coli_stone == true) {
        	
	        space_icon.setPosition(player.getX(), player.getY()+175);
	        space_icon.action(batch);
	        
	        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
	        	try_stone = true;
	        }
	        if (try_stone == true && cooltime_stone < 100 ) {
	        	wait_icon.setPosition(player.getX()+10, player.getY()+220);
		        wait_icon.action(batch);
	        	cooltime_stone ++;
	        }
	        if (try_stone == true && cooltime_stone >= 100) {
	        	player.getInventory().addRadomItem_stone();
		        try_stone = false;
		        cooltime_stone = 0;
	        }
	        
        }
        else {
        	cooltime_stone = 0;
        	try_stone = false;
        }       
        
        //낚시 테스트
        if (player_coli_pond == true) {
        	
	        space_icon.setPosition(player.getX(), player.getY()+175);
	        space_icon.action(batch);
	        
	        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
	        	try_pond = true;
	        }
	        if (try_pond == true && cooltime_pond < 100 ) {
	        	wait_icon.setPosition(player.getX()+10, player.getY()+220);
		        wait_icon.action(batch);
	        	cooltime_pond ++;
	        }
	        if (try_pond == true && cooltime_pond >= 100) {
	        	player.getInventory().addRadomItem_pond(player.getInventory().ckItems(new FishingRod(), 1));
		        try_pond = false;
		        cooltime_pond = 0;
	        }
	        
        }
        
    }
    
    public void player_coli_move_ck() {
    	
    	player_coli_move = false;
    	
    	for(Tree tree : trees) {
        	if(isCollition_for_move(tree, player)) {
        		player_coli_move = true;
        		break;
        	}
        }
        
        for(Stone stone : stones) {
        	if(isCollition_for_move(stone, player)) {
        		player_coli_move = true;
        		break;
        	}
        }

        for(House house : houses) {
        	if(isCollition_for_move(house, player)) {
        		player_coli_move = true;
        		break;
        	}
        }
        
        for(Pond pond : ponds) {
        	if(isCollition_for_move(pond, player)) {
        		player_coli_move = true;
        		break;
        	}
        }
        
    	if(!isCollition(map, player)) {
    		player_coli_move = true;
    	}
        
        
        //나무와 충돌
        for(Tree tree : trees) {
        	if(isCollition(tree, player)) {
        		player_coli_tree = true;
        		break;
        	}
        	else {player_coli_tree = false;}
        }
        
        //돌과 충돌
        for(Stone stone : stones) {
        	if(isCollition(stone, player)) {
        		player_coli_stone = true;
        		break;
        	}
        	else {player_coli_stone = false;}
        }
        //집과 충돌
        for(House house : houses) {
        	if(isCollition(house, player)) {
        		player_coli_house = true;
        		break;
        	}
        	else {player_coli_house = false;}
        }
        
        //연못과 충돌
        for(Pond pond : ponds) {
        	if(isCollition(pond, player)) {
        		player_coli_pond = true;
        		break;
        	}
        	else {player_coli_pond = false;}
        }
        
        if(!isCollition_for_move(map, player)) {
        	player_coli_pond = true;
        }
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize");


        System.out.println("-----------before created----------------");

        for(Actor actor : stage.getActors())
        {
            System.out.println(actor.getName());
        }



        inventory = new TextButton("Inventory", Skins.craftacular);
        int buttonWidth = Gdx.graphics.getWidth() / 5;
        int buttonHeight = Gdx.graphics.getHeight() / 12;
        inventory.setSize(buttonWidth,buttonHeight);
        inventory.setPosition(0,0);
        inventory.addListener(new InputListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                inventoryOpenFlg[0] = true;
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        inventory.setName("btnInventory");
        inventory.setTouchable(Touchable.enabled);
        //stage.addActor(inventory);
        System.out.println("-------------after created-----------");

        for(Actor actor : stage.getActors())
        {
            System.out.println(actor.getName());
        }


    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");

        for(Actor actor : stage.getActors())
        {
            if (actor.getName() != null && actor.getName().equals("btnInventory")) {
                Gdx.app.log("Remove", "Success");
                actor.remove();
            }
        }
    }

    @Override
    public void dispose() {
    	Bgm.dispose();
    }

    public void updateServer(float dt) {
        timer += dt;
        if(timer >= UPDATE_TIME) {
            JSONObject data = new JSONObject();
            try {
                data.put("x", player.getX());
                data.put("y", player.getY());
                socket.emit("playerMoved", data);
            }
            catch (JSONException e) {
                Gdx.app.log("SocketIO", "Error Sending update to server");
            }
        }
    }

    public void connectSocket() {
        try {
            socket = IO.socket("http://localhost:8080");
            socket.connect();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void configSocketEvents() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO", "Connected");
                JSONObject data = new JSONObject();
                try {
                    data.put("x", player.getX());
                    data.put("y", player.getY());
                    data.put("name", player.getName());
                    data.put("gender", player.getGender().getGender());
                    socket.emit("playerData", data);
                }
                catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error Sending Init data to server");
                }
            }
        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject)args[0];
                String id = null;
                try {
                    id = data.getString("id");
                    Gdx.app.log("SocketIO", "My ID: " + id);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Gdx.app.log("SocketIO", "Error getting ID");
                }
            }
        }).on("newPlayer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject)args[0];
                Gdx.app.log("SocketIO", "새 플레이어 정보 : " + data.toString());
                String id = null;
                try {
                    id = data.getString("id");
                    String name = data.getString("name");
                    Gender gender = data.getString("gender").equals("남자") ? Gender.MALE : Gender.FEMALE;
                    Gdx.app.log("SocketIO", "New Player Connect: " + id);
                    Player oPlayer = new Player(name, gender);
                    friendlyPlayers.put(id, oPlayer);
                } catch (JSONException e) {
                    //e.printStackTrace();
                    Gdx.app.log("SocketIO", "Error getting New PlayerID");
                }
            }
        }).on("playerDisconnected", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject)args[0];
                String id = null;
                try {
                    id = data.getString("id");
                    Gdx.app.log("SocketIO", "Player Disconnect: " + id);
                    friendlyPlayers.remove(id);
                } catch (JSONException e) {
                    //e.printStackTrace();
                    Gdx.app.log("SocketIO", "Error getting disconnected PlayerID");
                }
            }
        }).on("getPlayers", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray objects = (JSONArray)args[0];
                try {
                    Gdx.app.log("SocketIO","기존 플레이어 정보 : " + objects.toString());
                    for(int i = 0; i < objects.length(); i++) {
                        String name = objects.getJSONObject(i).getString("name");
                        Gender gender = objects.getJSONObject(i).getString("gender").equals("남자") ? Gender.MALE : Gender.FEMALE;
                        Player oPlayer = new Player(name, gender);
                        Vector2 position = new Vector2();
                        position.x = (float)objects.getJSONObject(i).getDouble("x");
                        position.y = (float)objects.getJSONObject(i).getDouble("y");
                        oPlayer.setPosition(position.x, position.y);

                        friendlyPlayers.put(objects.getJSONObject(i).getString("id"), oPlayer);
                    }
                }
                catch (JSONException e) {
                    System.out.println(e);
                }
            }
        }).on("playerMoved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject)args[0];
                try {
                    String playerId = data.getString("id");
                    Double x = data.getDouble("x");
                    Double y = data.getDouble("y");
                    if (friendlyPlayers.get(playerId) != null) {
                        friendlyPlayers.get(playerId).setPosition(x.floatValue(), y.floatValue());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public boolean isCollition(Sprite x, Sprite y) {
        Boolean collide = false;
        float x_Left = x.getX() + 20;
        float x_Right = x.getX()+x.getWidth() - 20;
        float x_Top = x.getY()+x.getHeight() - 20;
        float x_Bottom = x.getY() + 20;
        
        float y_Left = y.getX() + 20;
        float y_Right = y.getX()+y.getWidth() - 20;
        float y_Top = y.getY()+y.getHeight() - 20;
        float y_Bottom = y.getY() + 20;
        

        if (x_Left < y_Right && x_Right > y_Left &&
              x_Top > y_Bottom && x_Bottom < y_Top) {
            collide = true;
        }
        return collide;
    }
    
    public boolean isCollition_for_move(Sprite x, Sprite y) {
        Boolean collide = false;
        float x_Left = x.getX() + 40;
        float x_Right = x.getX()+x.getWidth() - 40;
        float x_Top = x.getY()+x.getHeight() - 40;
        float x_Bottom = x.getY() + 40;
        
        float y_Left = y.getX() + 40;
        float y_Right = y.getX()+y.getWidth() - 40;
        float y_Top = y.getY()+y.getHeight() - 40;
        float y_Bottom = y.getY() + 40;
        

        if (x_Left < y_Right && x_Right > y_Left &&
              x_Top > y_Bottom && x_Bottom < y_Top) {
            collide = true;
        }
        return collide;
    }
}