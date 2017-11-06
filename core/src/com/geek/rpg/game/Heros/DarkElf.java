package com.geek.rpg.game.Heros;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.geek.rpg.game.AbstractUnit;
import com.geek.rpg.game.Player;
import com.geek.rpg.game.RpgGame;

public class DarkElf extends AbstractUnit implements Player {
    public DarkElf( RpgGame game, Texture texture, Vector2 position ) {
        super(game, texture, position );
        this.name = "DarkElf";
        this.maxHp = 15;
        this.hp = this.maxHp;
        this.level = 1;
        this.strength = 5;
        this.dexterity = 20;
        this.endurance = 10;
        this.spellpower = 5;
        this.defence = 5;
        this.flip = false;

    }
}
