package com.geek.rpg.game.units;

import com.geek.rpg.game.RpgGame;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Al {
    static Random random = new Random();
    static public ArrayList<AbstractUnit> targets=new ArrayList<>();

    public static AbstractUnit getTarget( RpgGame game , Class unitsTypeClass) {

        targets.clear();
        for (AbstractUnit unit1 : game.units) {
            if (!unit1.isDead && unit1.getClass().getInterfaces()[0].equals(unitsTypeClass)) targets.add(unit1);
        }
        targets.sort(new Comparator<AbstractUnit>() {
            @Override
            public int compare( AbstractUnit o1, AbstractUnit o2 ) {
                return o1.hp-o2.hp;
            }
        });
        return targets.isEmpty() ? null : targets.get(0);
    }

    public static void autoAction( AbstractUnit currentUnit, AbstractUnit target ) {
        ArrayList<String> actions = new ArrayList<>();
        currentUnit.actionAbilities.forEach((k,v)-> {
            if(v) actions.add(k);
        });

//        if currentUnit.hp

        int i = random.nextInt(actions.size());
        currentUnit.action(actions.get(i),target);
    }
}
