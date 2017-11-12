package com.geek.rpg.game.units.heros;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.geek.rpg.game.core.TextureLoader;
import com.geek.rpg.game.units.AbstractUnit;
import com.geek.rpg.game.units.Player;
import com.geek.rpg.game.RpgGame;
import com.geek.rpg.game.units.Stats;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Knight extends AbstractUnit implements Player {
    static {stName = "knight";}
    private static int baseStrength=1;
    private static float lvUpStrength=3f;
    private static int baseDexterity= 10;
    private static float lvUpDexterity=1f;
    private static int baseEndurance=10;
    private static float lvUpEndurance=1f;
    private static int baseSpellpower=3;
    private static float lvUpSpellpower=0.34f;
    private static int baseDefence=5;
    private static float lvUpDefence=2;


    public Knight( RpgGame game, Vector2 position){
        this(game,position,1);
    }


    public Knight( RpgGame game, Vector2 position, int level ) {
        super(game, TextureLoader.getTexture(stName), position);
        this.name = "Knight";
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
        this.flip = false;
        actionAbilities.put("fireBall",false);
    }


}