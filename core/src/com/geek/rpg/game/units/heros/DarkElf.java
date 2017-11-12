package com.geek.rpg.game.units.heros;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.geek.rpg.game.core.TextureLoader;
import com.geek.rpg.game.units.AbstractUnit;
import com.geek.rpg.game.units.Player;
import com.geek.rpg.game.RpgGame;
import com.geek.rpg.game.units.Stats;

public class DarkElf extends AbstractUnit implements Player {
    static{stName = "darkElf";}
    private static int baseStrength=5;
    private static float lvUpStrength=0.34f;
    private static int baseDexterity= 20;
    private static float lvUpDexterity=2f;
    private static int baseEndurance=3;
    private static float lvUpEndurance=0.25f;
    private static int baseSpellpower=5;
    private static float lvUpSpellpower=0.34f;
    private static int baseDefence=5;
    private static float lvUpDefence=1;


    public DarkElf( RpgGame game, Vector2 position){
        this(game,position,1);
    }


    public DarkElf( RpgGame game, Vector2 position, int level ) {
        super(game, TextureLoader.getTexture(stName), position);
        this.name = "DarkElf";
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
    }


}
