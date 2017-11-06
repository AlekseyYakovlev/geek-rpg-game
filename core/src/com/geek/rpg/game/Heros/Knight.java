package com.geek.rpg.game.Heros;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.geek.rpg.game.AbstractUnit;
import com.geek.rpg.game.Player;
import com.geek.rpg.game.RpgGame;

public class Knight extends AbstractUnit implements Player {
    public Knight( RpgGame game, Texture texture, Vector2 position ) {
        super(game, texture, position );
        this.name = "Alexander";
        this.maxHp = 25;
        this.hp = this.maxHp;
        this.level = 1;
        this.strength = 10;
        this.dexterity = 10;
        this.endurance = 10;
        this.spellpower = 10;
        this.defence = 5;
        this.flip = false;
        actionAbilities.put("fireBall",false);
    }
}
