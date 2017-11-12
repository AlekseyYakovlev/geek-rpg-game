package com.geek.rpg.game.units;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.geek.rpg.game.RpgGame;
import com.geek.rpg.game.core.Calc;
import com.geek.rpg.game.visualEffects.Blast;
import com.geek.rpg.game.visualEffects.FireBall;

import java.util.HashMap;

public abstract class AbstractUnit {
    protected static String stName;

    private static Texture barTexture = new Texture("Atlas1.png");//текстура для индикатора здоровья
    private static Texture blood = new Texture("blood.png");
    private static Label.LabelStyle labelStyleWhite = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
    private Label hpLabel = new Label("",labelStyleWhite);

    public RpgGame game;

    public Texture texture;

    public String name;
    public int hp;
    public int level;

    public Rectangle rect;

    public Stats stats;

    public int shieldDefence;

    public Vector2 position;

    public boolean flip;

    public float attackAction;
    public float meleeAttackAction;
    public float takeDamageAction;
    public float fireBallAction;
    public float blastCounter;

    //protected String lastTakenDamage;
    public boolean isDead;

    public HashMap <String,Boolean>  actionAbilities;
    public boolean extraBig;
    public FireBall fireBall;
    public Blast blast;



    public AbstractUnit(RpgGame game, Texture texture, Vector2 position){
        this.game = game;
        this.texture = texture;
        this.position = position;
        rect =  new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        actionAbilities= new HashMap<>();
        actionAbilities.put("meleeAttack",true);
        actionAbilities.put("fireBall",true);
        actionAbilities.put("shield",true);
        actionAbilities.put("heal",true);
    }

    public Rectangle getRect() {
        return rect;
    }
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        this.rect = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }



    public void takeDamage(int dmg) {
        this.takeDamageAction = 1.0f;
        int tempHp=hp;
        boolean dodge=Calc.checkDodge(this);
        if (!dodge) hp -= dmg;
        if (hp<=0) {
            hp=0;
            isDead=true;
        }
        if (dodge) game.addMessage("Dodge!",position.x+texture.getWidth(),position.y+texture.getHeight(),Color.WHITE);
        else game.addMessage("Dmg "+(hp-tempHp),position.x+texture.getWidth(),position.y+texture.getHeight(),Color.RED);
    }

    public void render(SpriteBatch batch) {
        if (takeDamageAction > 0) {
            batch.setColor(1f, 1f - takeDamageAction, 1f - takeDamageAction, 1f);
        }
        float dx = (150f * (float) Math.sin((1f - meleeAttackAction) * 3.14f));
        if (flip) dx *= -1;

        float rotation =0;
        if (isDead) {
            rotation=flip?270:90;
            rotation=extraBig?180:rotation; // Для василиска
            batch.draw(blood,position.x + dx -50, position.y+15);
        }

        batch.draw(texture, position.x + dx, position.y, texture.getWidth()/2, texture.getHeight()/2, texture.getWidth(), texture.getHeight(), 1, 1, rotation, 0, 0, texture.getWidth(), texture.getHeight(), flip,false);

        batch.setColor(1f, 1f, 1f, 1f);

        if(!isDead) drawHealthBar(batch, dx);

        if(fireBallAction>0){
            fireBall.render(batch);
        }
        if(blastCounter>0 && blastCounter<1){
            batch.setColor(1.0f, 1.0f, 1.0f, (float) Math.sin((1f - blastCounter) * 3.14f));
            blast.render(batch);
        }
    }

    public void drawHealthBar( SpriteBatch batch, float dx ) {

        batch.draw(barTexture,position.x+dx+6,position.y+texture.getHeight(),0,0,104,24);
        batch.draw(barTexture,position.x+dx+6+2,position.y+texture.getHeight()+2,0,0,1,20,100*hp/stats.getMaxHp(),1,0,0,35,1,20,false,false );

        hpLabel.setPosition(position.x+dx+6+42,position.y+texture.getHeight()+11);
        hpLabel.setText(""+hp);
        hpLabel.draw(batch,1);


    }

    public void update(float dt) {
        if (takeDamageAction > 0) {
            takeDamageAction -= dt;
        }
        if (attackAction > 0) {
            attackAction -= dt;
        }
        if (meleeAttackAction > 0) {
            meleeAttackAction -= dt;
        }
        if (fireBallAction > 0) {
            fireBallAction -= dt;
        }
        if (blastCounter > 0) {
            blastCounter -= dt;
        }
    }

    public boolean action(String buttonAction, AbstractUnit enemy) {
        switch (buttonAction) {
            case "meleeAttack":
                meleeAttack(enemy);
                break;
            case "fireBall":
                fireBall(enemy);
                break;
            case "heal":
                heal();
                break;
            case "shield":
                shield(true);
                break;

            default:
                return false;
        }
        return true;
    }

    public void shield(boolean isActive) {
        shieldDefence=isActive?5:0;
        if (isActive) game.addMessage("Defence +5",position.x+texture.getWidth(),position.y+texture.getHeight(),Color.BLUE);
        attackAction = 1.0f;
    }

    private void heal() {
        int deltaHp=Calc.heal(this,this);
        game.addMessage("Heal +"+(deltaHp),position.x+texture.getWidth(),position.y+texture.getHeight(),Color.GREEN);
        attackAction = 1.0f;
    }

    private void fireBall(AbstractUnit enemy ) {
        int dmg = Calc.fireBallDmg(this,enemy);

        fireBall=new FireBall(this,enemy);
        attackAction = 2.0f;
        fireBallAction = 1.0f;
        enemy.takeDamage(dmg);
        blast(enemy);

    }

    private void blast( AbstractUnit unit ) {
        blast = new Blast(unit);
        blastCounter=2.0f;
    }

    public void meleeAttack(AbstractUnit enemy) {
        int dmg = Calc.meleeDmg(this,enemy);
        attackAction = 1.0f;
        meleeAttackAction = 1.0f;
        enemy.takeDamage(dmg);
    }
}
