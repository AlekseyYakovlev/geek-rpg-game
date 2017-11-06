package com.geek.rpg.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
    static final float BORDERLINE = 3.0f;
    static private Texture buttonBackground = new Texture("btn.png");

    private String action;
    private boolean instantAction;
    private Texture texture;
    private Rectangle rectangle;
    private boolean isPressed;
    private boolean hoveredOn;
    private boolean flipX,flipY;
    private boolean visible;

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setPressed( boolean pressed ) {
        isPressed = pressed;
    }


    public boolean isPressed() {
        return isPressed;
    }

    public void setVisible( AbstractUnit unit) {

        this.visible = unit.actionAbilities.get(action);
    }

    public String getAction() {
        return visible?action:null;
    }

    public boolean isInstantAction() {
        return instantAction;
    }

    public Button( String action, boolean instantAction, Texture texture, Rectangle rectangle, boolean flipX, boolean flipY) {
        this.action = action;
        this.instantAction=instantAction;
        this.texture = texture;
        this.rectangle = rectangle;
        this.flipX=flipX;
        this.flipY=flipY;
        visible=true;
    }

    public boolean checkClick() {

        return visible && InputHandler.checkClickInRect(rectangle);
    }
    public boolean checkHold() {
        return InputHandler.checkHold(rectangle);
    }
    public boolean checkHoveredOn() {
        return InputHandler.checkClickInRect(rectangle);
    }


    public void render(SpriteBatch batch) {
        if(visible) {
            float filter = isPressed ? 0.6f : 1.0f;
            batch.setColor(0.6f * filter, 0.6f * filter, 0.6f, 0.8f);
            batch.draw(buttonBackground, rectangle.getX(), rectangle.getY());
            batch.setColor(1.0f * filter, 1.0f * filter, 1.0f, 0.8f);
            drawButtonIcon(batch);
            batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    private void drawButtonIcon( SpriteBatch batch ) {
        float iPositionX, iPositionY;
        float iWidth=texture.getWidth();
        float iHeight=texture.getHeight();
        if (iWidth+BORDERLINE*2-rectangle.width>0.001){
            iWidth=rectangle.width-BORDERLINE*2;
            iHeight=texture.getHeight()*iWidth/texture.getWidth();
        }
        if (iHeight+BORDERLINE*2-rectangle.height>0.001){
            iHeight=rectangle.height-BORDERLINE*2;
            iWidth=texture.getWidth()*iHeight/texture.getHeight();
        }

        iPositionX=rectangle.getX()+(rectangle.width-iWidth)/2;
        iPositionY=rectangle.getY()+(rectangle.height-iHeight)/2;

        batch.draw(texture, iPositionX, iPositionY, iWidth, iHeight, 0, 0, texture.getWidth(),texture.getHeight(), flipX, flipY);
    }

}
