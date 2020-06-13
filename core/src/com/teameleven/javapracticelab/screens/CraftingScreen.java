package com.teameleven.javapracticelab.screens;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.teameleven.javapracticelab.UsasengCrossing;
import com.teameleven.javapracticelab.characters.Player;
import com.teameleven.javapracticelab.items.*;
import com.teameleven.javapracticelab.utils.Skins;

public class CraftingScreen implements Screen {
	
	Item[] item=new Item[2];
    final UsasengCrossing game;
    final Player player;
    private Stage stage;
    JOptionPane msg = new JOptionPane();
    
    InitGameScreen initGameScreen;
    SpriteBatch batch;
      
    private Texture texture;
    private Label title;
    int row_height = Gdx.graphics.getWidth() / 10;
    ArrayList<Label> itemList = new ArrayList<Label>();

    ArrayList<Label> craftList = new ArrayList<Label>();
    ArrayList<Button> buttonList = new ArrayList<Button>();
    
    
    public CraftingScreen(final UsasengCrossing game, final InitGameScreen initGameScreen, final Player player) {
    	Axe axe=new Axe();
    	FishingRod frod=new FishingRod(); 
    	item[0]=axe;
    	item[1]=frod;
    	this.game = game;
        this.initGameScreen = initGameScreen;
        this.player = player;
        
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("inventory_img.png"));//인벤토리창과 동일 배경

        title = new Label("제작 목록   (나가기 Esc)", Skins.korean, "black");
        title.setSize(Gdx.graphics.getWidth(),row_height);
        title.setPosition(0,600);
        title.setAlignment(Align.center);
        stage.addActor(title);

        int buttonWidth = Gdx.graphics.getWidth() / 5;
        int buttonHeight = Gdx.graphics.getHeight() / 12;

        Button back = new TextButton("Back", Skins.craftacular);
        back.setSize(buttonWidth,buttonHeight);
        back.setPosition(600,0);

        back.addListener(new InputListener() {

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	
                //back to initscreen
                game.setScreen(initGameScreen);
            }
    ;
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


        });
        //stage.addActor(back);

        
        
        
        
    		craftList.add(new Label(item[0].getName()+": 나뭇가지 2개, 돌멩이 1개" ,Skins.korean, "black"));
            craftList.get(0).setAlignment(Align.left);
            craftList.get(0).setPosition(250,500+0*50);
            stage.addActor(craftList.get(0));
            
            buttonList.add(new TextButton("make", Skins.craftacular));
            buttonList.get(0).setSize(100,40);
            buttonList.get(0).setPosition(630,500+0*50);
            buttonList.get(0).addListener(new InputListener() {
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                	System.out.println(player.getInventory().ckItems(new SoftWood(), 2)+" "+player.getInventory().ckItems(new NormalStones(), 1));
                		if(player.getInventory().ckItems(new SoftWood(), 2) && player.getInventory().ckItems(new NormalStones(), 1)) {
                			player.getInventory().delItems(new SoftWood(), 2);
                			player.getInventory().delItems(new NormalStones(), 1);
                			player.getInventory().addItem(new Axe());
                			
                			msg.showMessageDialog(null, "제작 성공!");
                			System.out.println("제작완료");
                			//.성공 메세지
                		}
                		else {
                			//실패 메세지
                			msg.showMessageDialog(null, "재료가 부족합니다!");
                			System.out.println("제작실패");
                		}
                	
                	
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            stage.addActor(buttonList.get(0));
    	
            

    		craftList.add(new Label(item[1].getName()+": 나뭇가지 3개, 덩굴"
    				+ " 1개" ,Skins.korean, "black"));
            craftList.get(1).setAlignment(Align.left);
            craftList.get(1).setPosition(250,500+1*50);
            stage.addActor(craftList.get(1));
            
            buttonList.add(new TextButton("make", Skins.craftacular));
            buttonList.get(1).setSize(100,40);
            buttonList.get(1).setPosition(630,500+1*50);
            buttonList.get(1).addListener(new InputListener() {
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                	System.out.println(player.getInventory().ckItems(new SoftWood(), 3)+" "+player.getInventory().ckItems(new NormalStones(), 1));

                	if(player.getInventory().ckItems(new SoftWood(), 3) && player.getInventory().ckItems(new NormalStones(), 1)) {
                		player.getInventory().delItems(new SoftWood(), 3);
                		player.getInventory().delItems(new NormalStones(), 1);
            			player.getInventory().addItem(new FishingRod()); 
            			
            			

            			msg.showMessageDialog(null, "제작 성공!");
            			System.out.println("제작완료");
            			//.성공 메세지
            		}
            		else {

            			msg.showMessageDialog(null, "재료가 부족합니다!");
            			System.out.println("제작실패");
            			//실패 메세지
            		}
                	
      
 
                	
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            stage.addActor(buttonList.get(1));
    	
            
    }
    
    @Override
    public void show() {
    		
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)(151/255.0), (float)(108/255.0), (float)(39/255.0), 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        batch.draw(texture, 160, 30);
        batch.end();

        stage.act();
        stage.draw();


        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(this.initGameScreen);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    
    public void setList() {
    	int i;
    	for( i=0;i<=1;i++) {
    		craftList.add(new Label(item[i].getName()+": 나뭇가지 3개, 덩굴 1개" ,Skins.korean, "black"));
            craftList.get(i).setAlignment(Align.left);
            craftList.get(i).setPosition(250,500+i*50);
            stage.addActor(craftList.get(i));
            
            buttonList.add(new TextButton("make", Skins.craftacular));
            buttonList.get(i).setSize(100,40);
            buttonList.get(i).setPosition(630,500+i*50);
            buttonList.get(i).addListener(new InputListener() {
                @Override
                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                	
                	
                	
                	
                	
                }
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            stage.addActor(buttonList.get(i));
    	}
    
    }
}
