package com.zanetta.auber;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.*;

public class Tinkerer extends Infiltrator implements Sprite{
    private TextureRegion textureRegion, Mineregion;

    public Tinkerer(TextureRegion region) {
        super(region);
        set_Tinkerer_state(Tinkerer_state.wandering);
    }

    public TextureRegion getRegion(){
        return textureRegion;
    }


    public void setRegion(TextureRegion region){
        this.textureRegion = region;
        setSize(this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (textureRegion == null || !isVisible()){
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
        batch.draw(textureRegion,
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
                setPosition(20,20);
                SequenceAction sequence = Actions.sequence(Actions.moveTo(20,20, 2), Actions.moveTo(20,400, 2),Actions.moveTo(400,400, 2),Actions.moveTo(400,20, 2));
                RepeatAction repeatAction = Actions.forever(sequence);
                this.addAction(repeatAction);
                break;
            case Mine_setting:
                DelayAction delay = Actions.delay(3.0F);
                RunnableAction runnableAction2 = Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        set_Tinkerer_state(Tinkerer_state.wandering);
                    }
                });
                SequenceAction sequenceAction = Actions.sequence(delay, runnableAction2);
                this.addAction(sequenceAction);
                break;
            case escaping:
                MoveToAction action = Actions.moveTo(20, 20, 2.0F);
                this.addAction(action);
                break;
            case dead:

                break;

        }
    }
    
    public void setMine(Mine.MineMode mineMode){
        set_Tinkerer_state(Tinkerer_state.Mine_setting);
        this.Mineregion = Textures.getTexture("badlogic");
        new Mine(Mineregion).setPosition(this.getX(), this.getY());
        Mine.setMineMode(mineMode);
    }
}