package com.zanetta.auber;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Textures {
	
	private static TextureAtlas atlas;
	
	public static void importTextures(String filepath) {
		atlas = new TextureAtlas(Gdx.files.internal(filepath));
	}
	
	public static AtlasRegion getTexture(String textureName) {
		return atlas.findRegion(textureName);
	}
	
	public static void dispose() {
		atlas.dispose();
	}
}
