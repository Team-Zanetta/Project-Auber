package com.zanetta.auber;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Controller extends Actor{
	private boolean infiltratorWin = false;
	private boolean initialised;
	private ArrayList<System> systems = new ArrayList<>();
	private ArrayList<Infiltrator> infiltrators = new ArrayList<>();
	private float timeBetweenSabotages = 5, decreaseFactor = 0.8f, timeToNextSabotage;
	
	
	public Controller() {
		super();
		initialised = false;
		timeToNextSabotage = timeBetweenSabotages;
	}
	
	public void initialise() {
		Array <Actor> actors = this.getStage().getActors();
		for(Actor actor : actors){
			if (actor instanceof System) {
				systems.add((System)actor);
			}else if (actor instanceof Infiltrator) {
				infiltrators.add((Infiltrator)actor);
			}
		}
		initialised = true;
	}
	
	public void act(float delta) {
		if(!initialised) {
			initialise();
		}
		destroyedCheck();
		if(systemsRemain() == 0) {
			infiltratorWin = true;
		}else {
			timeToNextSabotage -= delta;
			
			if(timeToNextSabotage < 0) {
				doASabotage();
				if(systemsRemain() % 3 == 0) {
					timeToNextSabotage *= decreaseFactor;
				}
				timeToNextSabotage = timeBetweenSabotages;
			}
		}
	}
	
	private void doASabotage() {
		Collections.shuffle(infiltrators);
		boolean foundFreeInf = false;
		int i = 0;
		while(!foundFreeInf & i < infiltrators.size()) {
			Infiltrator inf = infiltrators.get(i);
			
			if(inf.state == Infiltrator.State.IDLE) {
				foundFreeInf = true;
				inf.PerformSabotage();
			}
			else {
				i += 1;
			}
		}
	}
	 
	 public void destroyedCheck() {
		 for (int i = 0; i < systems.size(); i++) {
			 if (systems.get(i).getHealth() == 0) {
				 systems.get(i).setDestroyed(true);
			 }
		 }
	 }
	 
	 public int systemsRemain() {
		 int output = 0;
		 for (int i = 0; i < systems.size(); i++) {
			 if (systems.get(i).getDestroyed() == false & systems.get(i).claimed == false) {
				 output += 1;
			 }
		 }
		return output;
	 }
	 
	 public boolean infiltratorWin() {
		 return infiltratorWin;
	 }
}
