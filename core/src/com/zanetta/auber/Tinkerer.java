package com.zanetta.auber;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AfterAction;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;

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
        wandering, Mine_setting, escaping, dead,
    }

    public void set_Tinkerer_state(Tinkerer_state state){
        switch (state){
            case wandering:
                setPosition(0,0);
                SequenceAction sequence = Actions.sequence(Actions.moveTo(20,20), Actions.moveTo(20,400),Actions.moveTo(400,400),Actions.moveTo(400,20),Actions.moveTo(20,20));
                RepeatAction repeatAction = Actions.forever(sequence);
                this.addAction(repeatAction);
                break;
            case Mine_setting:
                DelayAction delay = Actions.delay(3.0F);
                this.addAction(delay);
                set_Tinkerer_state(Tinkerer_state.wandering);
                break;
            case escaping:
                MoveToAction action = Actions.moveTo(20, 20, 5.0F);
                this.addAction(action);
                break;
            case dead:
                
                break;

        }
    }


    public void setMine(Mine.MineMode mineMode){
        set_Tinkerer_state(Tinkerer_state.Mine_setting);
        this.Mineregion = region;
        new Mine(region).setPosition(this.getX(), this.getY());
        Mine.setMineMode(mineMode);
    }



}
