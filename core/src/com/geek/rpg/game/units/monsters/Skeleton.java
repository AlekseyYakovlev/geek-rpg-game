package com.geek.rpg.game.units.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.geek.rpg.game.core.TextureLoader;
import com.geek.rpg.game.units.AbstractUnit;
import com.geek.rpg.game.units.Monster;
import com.geek.rpg.game.RpgGame;
import com.geek.rpg.game.units.Stats;

public class Skeleton extends AbstractUnit implements Monster {
    static{stName = "skeleton";}
    private static int baseStrength=7;
    private static float lvUpStrength=1f;
    private static int baseDexterity= 10;
    private static float lvUpDexterity=1f;
    private static int baseEndurance=5;
    private static float lvUpEndurance=0.5f;
    private static int baseSpellpower=2;
    private static float lvUpSpellpower=0.25f;
    private static int baseDefence=1;
    private static float lvUpDefence=1;

    public Skeleton( RpgGame game, Vector2 position){
        this(game,position,1);
    }

    public Skeleton( RpgGame game, Vector2 position, int level ) {
        super(game, TextureLoader.getTexture(stName), position);
        this.name = "Monster";
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
        actionAbilities.put("fireBall",false);
    }
}
