package com.geek.rpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class FireBall {
//    static Texture texture = new Texture("fireball1.png");
    Texture texture;
    Vector2 startPosition;
    Vector2 endPosition;
    Vector2 direction;
    Vector2 position;
    float angle;
    AbstractUnit attacker;
    Vector2 size = new Vector2(72,45);

    public FireBall( AbstractUnit attacker, AbstractUnit target) {
        this.texture=attacker.game.fireBallTexture;
        this.attacker=attacker;
        startPosition = attacker.position.cpy().add(attacker.flip?0:attacker.texture.getWidth(),attacker.texture.getHeight()*0.75f);
        endPosition = target.position.cpy().add(target.texture.getWidth()/2,10);
        direction = endPosition.cpy().sub(startPosition);
        position=startPosition;
    }

    public void render(SpriteBatch batch) {
        batch.setColor(1f, 1f, 1f, 1f);
        if(attacker.fireBallAction>0) {
            update();
            batch.draw(texture, position.x-size.x/2, position.y-size.y/2, size.x, size.y, 0, 0, texture.getWidth(), texture.getHeight(), attacker.flip, false);
        }
    }

    public void update() {
        position=startPosition.cpy().add(direction.cpy().scl(1.0f-attacker.fireBallAction));
    }
}
