package com.zanetta.auber;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Controller {
	
	
	int total_systems = 15;
	ArrayList<System> systems = new ArrayList<>();
	
	public void Set_Systems(ArrayList systems) {
		System o1 = new System(100, false);
		for (int i = 0; i < total_systems; i ++) {
			systems.add(-1, o1);
		}
	}
	 
	 public void DestroyedCheck() {
		 for (int i = 0; i < systems.size(); i++) {
			 if (systems.get(i).getHealth() == 0) {
				 systems.get(i).setDestroyed(true);
			 }
		 }
	 }
	 
	 public int SystemsRemain() {
		 int output = 0;
		 for (int i = 0; i < systems.size(); i++) {
			 if (systems.get(i).getDestroyed() == false) {
				 output += 1;
			 }
		 }
		return output;
	 }
	 
}
