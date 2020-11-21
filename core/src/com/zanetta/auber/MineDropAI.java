package com.zanetta.auber;


import com.badlogic.gdx.scenes.scene2d.Action;

public class MineDropAI{

    Action nextTinkererAction;
    public boolean Player_In_Room(){
        //true if there is player in room, false if not, algorithm required in stage
        return false;
    }
    public MineDropAI(final Tinkerer tinkerer){
        if(this.Player_In_Room()) {
            tinkerer.set_Tinkerer_state(Tinkerer.Tinkerer_state.escaping);
        }
        else{
            tinkerer.set_Tinkerer_state(Tinkerer.Tinkerer_state.wandering);
        }


    }





}
