package com.zanetta.auber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Random;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.*;

public class Tinkerer extends Infiltrator{
    private TextureRegion Mineregion;

    public Tinkerer(TextureRegion region) {
        super(region);
        tinkerer_state = Tinkerer_state.wandering;
        setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
    }

    public TextureRegion getRegion(){
        return region;
    }


    public void setRegion(TextureRegion region){
        this.region = region;
        setSize(this.region.getRegionWidth(), this.region.getRegionHeight());
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (region == null || !isVisible()){
            return;
        }

    /* batch.draw(
				region,
				x, y,
				originX, originY,
				width, height,
				scaleX, scaleY,
				rotation
		);*/
        batch.draw(region,
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
    public MoveToAction ToNextRandom(Tinkerer tinkerer){
        float rn_x1;
        float rn_y1;
        double distance;
        Random randonx;
        Random randony;
        randonx = new Random();
        randony = new Random();
        float speed = 300;
        float duration;
        rn_x1 = randonx.nextInt(1900);
        rn_y1 = randony.nextInt(1000);
        distance = rn_x1 * rn_x1 + rn_y1 * rn_y1;
        duration =  (float) (Math.sqrt(distance) / speed);
        return Actions.moveTo(rn_x1,rn_y1, duration);
    }

    public void stop(){
        for(Action Actiones: getActions()){
            removeAction(Actiones);
        }
    }


    float wanderinglooptimecounter = 0;

    @Override
    public void act(float deletaTime){
        super.act(deletaTime);

        if(tinkerer_state == Tinkerer_state.wandering && wanderinglooptimecounter == 0){
            addAction(ToNextRandom(this));
        }

        wanderinglooptimecounter += deletaTime;
        if(wanderinglooptimecounter > 5.0f){
            wanderinglooptimecounter = 0;
        }
        //Gdx.app.log("wanderinglooptimecounter", String.valueOf(wanderinglooptimecounter));
    }

    public float getWanderinglooptimecounter(){
        return wanderinglooptimecounter;
    }


}
