package com.zanetta.auber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Random;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.*;

public class Tinkerer extends Infiltrator{
    private TextureRegion textureRegion, Mineregion;

    public Tinkerer(String textureName) {
        super(textureName);
        set_Tinkerer_state(Tinkerer_state.wandering);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (textureRegion == null || !isVisible()){
            return;
        }

        batch.draw(textureRegion,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation());
    }
    public enum Tinkerer_state{
        wandering, escaping, dead,
    }



    Tinkerer_state tinkerer_state;
    public void set_Tinkerer_state(Tinkerer_state state){
        tinkerer_state = state;
        switch (state){
            case wandering:
                break;
            case escaping:
                addAction(Actions.moveTo(20, 20, 2.0F));
                break;
            case dead:
                addAction(Actions.alpha(0, 0.5F));
                break;
        }
    }

    float wanderinglooptimecounter = 0;
    float rn_x1;
    float rn_y1;
    Random randonx;
    Random randony;
    
    @Override
    public void act(float deletaTime){
        super.act(deletaTime);
        float duration = 2.0f;
        if(tinkerer_state == Tinkerer_state.wandering && wanderinglooptimecounter == 0){
            randonx = new Random();
            randony = new Random();
            rn_x1 = randonx.nextInt(1900);
            rn_y1 = randony.nextInt(1000);
            addAction(Actions.moveTo(rn_x1,rn_y1, duration));
        }
        wanderinglooptimecounter += deletaTime;
        if(wanderinglooptimecounter > duration){
            wanderinglooptimecounter = 0;
        }
        //Gdx.app.log("wanderinglooptimecounter", String.valueOf(wanderinglooptimecounter));
    }

    public float getWanderinglooptimecounter(){
        return wanderinglooptimecounter;
    }


}
