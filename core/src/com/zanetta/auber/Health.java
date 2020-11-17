package com.mygdx.game;

public class Health {

    int Health;
    int MaxHealth;

    public void setHealth(int HP){
        this.Health = HP;
    }


    public void setMaxHealth(int HP){
        this.MaxHealth = HP;
    }


    public int getMaxHealth(){
        return this.MaxHealth;
    }


    public int getHealth(){
        return this.Health;
    }


    public void increaseHealth(int Value){
        this.Health += Value;
        if(this.Health > this.MaxHealth){
            this.Health = this.MaxHealth;
        }
    }


    public void decreaseHealth(int Value){
        this.Health = this.Health - Value;
        if(this.Health < 0){
            this.Health = 0;
        }
    }


}
