package com.zanetta.auber;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.Random;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.*;

public class Tinkerer extends Infiltrator{
    private TextureRegion Mineregion;

    public Tinkerer(TextureRegion region) {
        super(region);
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
    public void act(float delta) {
        super.act(delta);
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


    public void set_Tinkerer_state(Tinkerer_state state){
        Action wander;
        switch (state){
            case wandering:
                Random rn_x1 = new Random();
                Random rn_y1 = new Random();
                wander = Actions.moveTo(rn_x1.nextInt(200),rn_y1.nextInt(400), 5);
                SequenceAction sequenceAction1 = Actions.sequence(wander, Actions.delay(3.0F));
                addAction(Actions.forever(sequenceAction1));
                break;
            case escaping:
                addAction(Actions.moveTo(20, 20, 2.0F));
                break;
            case dead:
                addAction(Actions.alpha(0, 0.5F));
                break;
        }
    }


    public Action setMine(final Mine.MineMode mineMode){
        return Actions.run(new Runnable() {
            @Override
            public void run() {
                Mineregion = region;
                Mine firstMine = new Mine(region);
                firstMine.setPosition(getX(), getY());
                Mine.setMineMode(mineMode);
            }
        });
    }



}
