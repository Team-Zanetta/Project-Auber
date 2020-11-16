package com.zanetta.auber;

public class Health {
	int health;
    int maxHealth;

    public void setHealth(int HP){
        this.health = HP;
    }


    public void setMaxHealth(int HP){
        this.maxHealth = HP;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }


    public int getHealth(){
        return this.health;
    }

    public void increaseHealth(int value){
        this.health += value;
        if(this.health > this.maxHealth){
            this.health = this.maxHealth;
        }
    }

    public void decreaseHealth(int value){
        this.health = this.health - value;
        if(this.health < 0){
            this.health = 0;
        }
    }
}
