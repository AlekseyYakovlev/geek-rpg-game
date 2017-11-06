package com.geek.rpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Blast {
    Texture texture;
    Vector2 position;
    AbstractUnit unit;

    public Blast( AbstractUnit unit) {
        this.texture=unit.game.blastTexture;
        this.unit=unit;
        position=unit.position.cpy().add(unit.texture.getWidth()/2,0);
    }

    public void render(SpriteBatch batch) {

        batch.draw(texture, position.x-texture.getWidth()/2, position.y);

    }


}
