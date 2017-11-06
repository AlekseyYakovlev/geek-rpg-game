package com.geek.rpg.game.Monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.geek.rpg.game.AbstractUnit;
import com.geek.rpg.game.Monster;
import com.geek.rpg.game.RpgGame;

public class Skeleton extends AbstractUnit implements Monster {

    public Skeleton( RpgGame game, Texture texture, Vector2 position ) {
        super(game, texture, position );
        this.name = "Monster";
        this.maxHp = 30;
        this.hp = this.maxHp;
        this.level = 1;
        this.strength = 7;
        this.dexterity = 10;
        this.endurance = 5;
        this.spellpower = 0;
        this.defence = 1;
        this.flip = true;
        actionAbilities.put("fireBall",false);
    }
}
