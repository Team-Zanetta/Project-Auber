package com.zanetta.auber;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

public class Mine extends Actor {
    private static int MineMode;
    //MineMode:0 = Explosive Mine, 1 = Teleport Mines, 2 = Slime Mine, 3 = Flashbang.

    private final TextureRegion region;

    public Mine(TextureRegion region){
        super();
        this.region = region;
        setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
    }

    public TextureRegion getRegion() {
        return region;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (region == null || !isVisible()){
            return;
        }
        batch.draw(region,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation());
    }

    public enum MineMode{
        explosive, teleport, slime, flashbang
    }

    public void setMineMode(int mineMode){
        MineMode = mineMode;
    }

    public double DistanceTo(Player player){
        double DistanceX = player.getX() + player.getWidth() / 2 - this.getX();
        double DistanceY = player.getY() + player.getHeight() / 2 - this.getY();
        double distance = Math.pow(DistanceX, 2) + Math.pow(DistanceY,2);
        return Math.sqrt(distance);
    }

    public Player[] PlayerInRoom(){
        //need a algorithm that return all Players in the room from Scanner
        return new Player[0];
    }



    public void Mine_Deactivate(){
        this.getColor().a = 0F;
        double explode_distance = 50;
        for (Player players: this.PlayerInRoom()){
            if(MineMode == 0){
                if(this.DistanceTo(players) <= explode_distance){
                    float DistanceX = players.getX() + players.getWidth() / 2 - this.getX();
                    float DistanceY = players.getY() + players.getHeight() / 2 - this.getY();
                    players.moveBy(5000/DistanceX, 5000/DistanceY);
                }
            }
            if(MineMode == 1){
                if(this.DistanceTo(players) <= explode_distance){
                    Random R_X = new Random();
                    Random R_Y = new Random();
                    float x1 = R_X.nextFloat();
                    float y1 = R_X.nextFloat();
                    players.setPosition(x1, y1);
                }
            }
            if(MineMode == 2){
                if(this.DistanceTo(players) <= explode_distance){
                    //players.MOVE_SPEED_CHANGE(-5);
                    //not created yet in player, fight
                }
            }
            else{
                //Reduces amount of screen visible for a period of time
                //need this algorithm from Screen, fight

            }
        }
    }


}
