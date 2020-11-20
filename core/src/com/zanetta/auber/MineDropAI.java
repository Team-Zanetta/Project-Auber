package com.zanetta.auber;


import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class MineDropAI{

    public boolean Player_In_Room(){
        //true if there is player in room, false if not, algorithm required in stage
        return false;
    }
    public MineDropAI(final Tinkerer tinkerer){
        if(this.Player_In_Room()){
            tinkerer.set_Tinkerer_state(Tinkerer.Tinkerer_state.escaping);
        }else{
            DelayAction delay = Actions.delay(5.0F);
            RunnableAction runnableAction1 = Actions.run(new Runnable() {
                @Override
                public void run() {
                    tinkerer.set_Tinkerer_state(Tinkerer.Tinkerer_state.wandering);
                    tinkerer.set_Tinkerer_state(Tinkerer.Tinkerer_state.Mine_setting);
                    tinkerer.setMine(Mine.MineMode.explosive);
                }
            });
            SequenceAction sequenceAction = Actions.sequence(delay, runnableAction1);
            tinkerer.addAction(sequenceAction);
        }


    }





}
