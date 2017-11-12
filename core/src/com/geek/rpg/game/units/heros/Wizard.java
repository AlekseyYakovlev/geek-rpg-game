package com.geek.rpg.game.units.heros;
import com.badlogic.gdx.math.Vector2;
import com.geek.rpg.game.RpgGame;
import com.geek.rpg.game.core.TextureLoader;
import com.geek.rpg.game.units.AbstractUnit;
import com.geek.rpg.game.units.Player;
import com.geek.rpg.game.units.Stats;

public class Wizard extends AbstractUnit implements Player {
    static{stName = "wizard";}
    private static int baseStrength=2;
    private static float lvUpStrength=0.25f;
    private static int baseDexterity= 10;
    private static float lvUpDexterity=1f;
    private static int baseEndurance=10;
    private static float lvUpEndurance=1f;
    private static int baseSpellpower=20;
    private static float lvUpSpellpower=3f;
    private static int baseDefence=3;
    private static float lvUpDefence=1;

    public Wizard( RpgGame game, Vector2 position){
        this(game,position,1);
    }

    public Wizard( RpgGame game, Vector2 position, int level ) {
        super(game, TextureLoader.getTexture(stName), position);
        this.name = "Merlin";
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
        actionAbilities.put("meleeAttack",false);
        actionAbilities.put("shield",false);

    }
}
