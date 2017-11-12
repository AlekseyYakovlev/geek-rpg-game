package com.geek.rpg.game.units.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.geek.rpg.game.core.TextureLoader;
import com.geek.rpg.game.units.AbstractUnit;
import com.geek.rpg.game.units.Monster;
import com.geek.rpg.game.RpgGame;
import com.geek.rpg.game.units.Stats;

public class Basilisk extends AbstractUnit implements Monster {
    static{stName = "basilisk";}
    private static int baseStrength=15;
    private static float lvUpStrength=2;
    private static int baseDexterity= 5;
    private static float lvUpDexterity=0.5f;
    private static int baseEndurance=15;
    private static float lvUpEndurance=1f;
    private static int baseSpellpower=20;
    private static float lvUpSpellpower=2;
    private static int baseDefence=5;
    private static float lvUpDefence=1;

    public Basilisk( RpgGame game, Vector2 position){
        this(game,position,1);
    }


    public Basilisk( RpgGame game, Vector2 position, int level ) {
        super(game, TextureLoader.getTexture(stName), position);
        this.name = "Basilisk";
        stats=new Stats(
                level,
                baseStrength,
                lvUpStrength,
                baseDexterity,
                lvUpDexterity,
                baseEndurance,
                lvUpEndurance,
                baseSpellpower,
                lvUpSpellpower,
                baseDefence,
                lvUpDefence
        );
        hp=stats.getMaxHp();
        this.flip = true;
        actionAbilities.put("fireBall",true);
        extraBig=true;
    }
}
