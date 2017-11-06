package com.geek.rpg.game.Heros;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.geek.rpg.game.AbstractUnit;
import com.geek.rpg.game.Player;
import com.geek.rpg.game.RpgGame;

public class Wizard extends AbstractUnit implements Player {
    public Wizard( RpgGame game, Texture texture, Vector2 position ) {
        super(game, texture, position );
        this.name = "Merlin";
        this.maxHp = 15;
        this.hp = this.maxHp;
        this.level = 1;
        this.strength = 2;
        this.dexterity = 10;
        this.endurance = 10;
        this.spellpower = 20;
        this.defence = 3;
        this.flip = false;
        actionAbilities.put("meleeAttack",false);
    }
}
