package com.bomb.game.entities;

import com.bomb.game.graphics.Sprite;
import com.bomb.game.math.Vector2f;
import com.bomb.game.util.AABB;

import java.awt.*;

public class Enemy extends Entity{

    private AABB sense;
    private int r;

    public Enemy(Sprite sprite, Vector2f orgin, int size) {
        super(sprite, orgin, size);

        acc = 1f;
        maxSpeed = 3f;
        r= 135;

        bounds.setWidth(30);
        bounds.setHeight(30);
        bounds.setXOffset(10);
        bounds.setYOffset(10);

        sense = new AABB(new Vector2f(orgin.x - size / 2, orgin.y - size /2), r, this);
    }

    public void update(AABB player) {
        if(sense.colCircleBox(player)) {
            System.out.println("YEP");
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.green);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getxOffset()), (int) (pos.getWorldVar().y + bounds.getyOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        g.setColor(Color.blue);
        g.drawOval((int) (sense.getPos().getWorldVar().x), (int) (sense.getPos().getWorldVar().y), r, r);

        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }


}
