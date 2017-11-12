package com.geek.rpg.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.geek.rpg.game.core.FlyingText;
import com.geek.rpg.game.core.InputHandler;
import com.geek.rpg.game.ui.Background;
import com.geek.rpg.game.ui.Button;
import com.geek.rpg.game.units.Al;
import com.geek.rpg.game.units.Monster;
import com.geek.rpg.game.units.Player;
import com.geek.rpg.game.units.AbstractUnit;
import com.geek.rpg.game.units.heros.DarkElf;
import com.geek.rpg.game.units.heros.Knight;
import com.geek.rpg.game.units.heros.Wizard;
import com.geek.rpg.game.units.monsters.Basilisk;
import com.geek.rpg.game.units.monsters.Skeleton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RpgGame extends ApplicationAdapter {

    // Для игры за монстром необходимо выставить константы AUTOPLAY_MONSTER = false и AUTOPLAY_PLAYER = true
    private final boolean AUTOPLAY_MONSTER = true;
    private final boolean AUTOPLAY_PLAYER = false;

    private SpriteBatch batch;
    private BitmapFont font;
    private Background background;
    private AbstractUnit currentUnit, lastTurnUnit;
    public ArrayList <AbstractUnit> units;
    private Iterator <AbstractUnit> turn;

    private BitmapFont testText;

    private List<Button> btnGUI;
    private FlyingText[] msgs;
    private String tempText;


    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Background();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        msgs = new FlyingText[50];
        for (int i = 0; i < msgs.length; i++) {
            msgs[i] = new FlyingText();
        }


        units = new ArrayList<AbstractUnit>();

        units.add(new Knight(this, new Vector2(400, 300)));
        units.add(new Basilisk(this, new Vector2(700, 250)));
        units.add(new Wizard(this, new Vector2(150, 200)));
        units.add(new Skeleton(this, new Vector2(850, 150)));
        units.add(new Skeleton(this, new Vector2(750, 100)));
        units.add(new DarkElf(this, new Vector2(300, 100)));

        turn = units.listIterator();
        currentUnit = turn.next();
        testText=new BitmapFont();
        lastTurnUnit=currentUnit;

        btnGUI = new ArrayList<Button>();
        btnGUI.add(new Button("meleeAttack",false, new Texture("sword1.png"), new Rectangle(20, 100, 80, 80),true,true));
        btnGUI.add(new Button("fireBall",false, new Texture("fireball1.png"), new Rectangle(20, 200, 80, 80),false,false));
        btnGUI.add(new Button("shield",true, new Texture("shield.png"), new Rectangle(20, 300, 80, 80),false,false));
        btnGUI.add(new Button("heal",true, new Texture("hart.png"), new Rectangle(20, 400, 80, 80),false,false));
        tempText="";
    }

    public void addMessage(String text, float x, float y, Color color) {
        for (int i = 0; i < msgs.length; i++) {
            if (!msgs[i].isActive()) {
                msgs[i].setup(text, x, y,color);
                break;
            }
        }
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.render(batch);
        for (AbstractUnit unit: units) unit.render(batch);
        testText.draw(batch,"Current turn: "+currentUnit.getClass().getSimpleName() +"\n" +tempText,10,100);
        for (int i = 0; i < btnGUI.size(); i++) {
            btnGUI.get(i).render(batch);
        }
        for (int i = 0; i < msgs.length; i++) {
            if (msgs[i].isActive()) {
                msgs[i].render(batch, font);
            }
        }
        batch.end();
    }

    public void update(float dt) {
        for (int i = 0; i < units.size(); i++) units.get(i).update(dt);

        for (int i = 0; i < msgs.length; i++) {
            if (msgs[i].isActive()) {
                msgs[i].update(dt);
            }
        }

        pp: if (lastTurnUnit.attackAction <= 0) {
            // Проверка нажатия кнопок
            if (currentUnit.getClass().getInterfaces()[0].getClass().isInstance(Player.class)) {

                for (int i = 0; i < btnGUI.size(); i++) {
                    btnGUI.get(i).setVisible(currentUnit);
                    if (btnGUI.get(i).checkClick()) {
                        currentUnit.shield(false);
                        for (int j = 0; j < btnGUI.size(); j++) {
                            btnGUI.get(j).setPressed(j == i);
                            if (i==j) {
                                tempText=currentUnit.getClass().getSimpleName()+" "+btnGUI.get(j).getAction();
                                if ( btnGUI.get(j).isInstantAction()){
                                    currentUnit.action(btnGUI.get(j).getAction(),null);
                                    setAllButtonsUnpressed();
                                    nextTurn();
                                    break pp;
                                }
                            }
                        }
                    }
                }
            }

            // Проверка нажатия на экран
            if (Gdx.input.isTouched()) {
                for (AbstractUnit unit : units) {
                    if (InputHandler.checkClickInRect(unit.rect)&& !unit.isDead && !unit.getClass().getInterfaces()[0].equals(currentUnit.getClass().getInterfaces()[0])) {
                        currentUnit.shield(false);
                        String currentAction = "";
                        for (int i = 0; i <btnGUI.size() ; i++) {
                            if(btnGUI.get(i).isPressed()) currentAction = btnGUI.get(i).getAction();
                        }
                        if(currentUnit.action(currentAction,unit)) {
                            nextTurn();
                            setAllButtonsUnpressed();
                        }
                        break pp;
                    }
                }
            }
            if(AUTOPLAY_MONSTER) autoAttack(Monster.class, Player.class);
            if(AUTOPLAY_PLAYER) autoAttack(Player.class, Monster.class);
        }
    }

    private void nextTurn() {
        lastTurnUnit = currentUnit;
        currentUnit = getCurrentUnit();
    }

    private void setAllButtonsUnpressed() {
        for (int i = 0; i < btnGUI.size(); i++) btnGUI.get(i).setPressed(false);
    }

    private void autoAttack(Class attackerClass, Class targetedUnitsTypeClass) {
        AbstractUnit target;
        if (currentUnit.getClass().getInterfaces()[0].equals(attackerClass)) {
            currentUnit.shield(false);
            target = Al.getTarget(this,targetedUnitsTypeClass);
            if (target == null) return;
            Al.autoAction(currentUnit,target);
            nextTurn();
        }

    }


    private AbstractUnit getCurrentUnit(){
        do{
        if (!turn.hasNext()) turn = units.listIterator();
        currentUnit=turn.next();
        }while (currentUnit.isDead);
        return currentUnit;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
