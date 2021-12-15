package com.bomb.game.entities;

import com.bomb.game.GamePanel;
import com.bomb.game.graphics.Sprite;
import com.bomb.game.math.Vector2f;
import com.bomb.game.states.PlayState;
import com.bomb.game.util.AABB;
import com.bomb.game.util.KeyHandler;
import com.bomb.game.util.MouseHandler;

import java.awt.*;

public class Player extends Entity {

    public Player(Sprite sprite, Vector2f orgin, int size) {
        super(sprite, orgin, size);
        acc = 2f;
        maxSpeed = 3f;
        bounds.setWidth(30);
        bounds.setHeight(30);
        bounds.setXOffset(10);
        bounds.setYOffset(10);
    }

    public void move() {
        if (up) {
            //currentDirection = UP;
            dy -= acc;
            if (dy < -maxSpeed) {
                dy = -maxSpeed;
            }
        } else {
            if (dy < 0) {
                dy += deacc;
                if (dy > 0) {
                    dy = 0;
                }
            }
        }

        if (down) {
            //currentDirection = DOWN;
            dy += acc;
            if (dy > maxSpeed) {
                dy = maxSpeed;
            }
        } else {
            if (dy > 0) {
                dy -= deacc;
                if (dy < 0) {
                    dy = 0;
                }
            }
        }

        if (left) {
            //currentDirection = LEFT;
            dx -= acc;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }

        if (right) {
            //currentDirection = RIGHT;
            dx += acc;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
    }

    public void update() {
        super.update();
        move();
        if(!fallen) {
            if (!bounds.collisionTile(dx, 0)) {
                PlayState.map.x += dx;
                pos.x += dx;
            }
            if (!bounds.collisionTile(0, dy)) {
                PlayState.map.y += dy;
                pos.y += dy;
            }
        } else {
            if(ani.hasPlayedOnce()) {
                resetPosition();
                fallen = false;
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.blue);
        g.drawRect((int) (pos.getWorldVar().x + bounds.getxOffset()), (int) (pos.getWorldVar().y + bounds.getyOffset()), (int) bounds.getWidth(), (int) bounds.getHeight());
        g.drawImage(ani.getImage(), (int) (pos.getWorldVar().x), (int) (pos.getWorldVar().y), size, size, null);
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        if (mouse.getButton() == 1) {
            System.out.println("Player: " + pos.x + ", " + pos.y);
        }
        if(!fallen) {
            if (key.up.down) {
                up = true;
            } else {
                up = false;
            }
            if (key.down.down) {
                down = true;
            } else {
                down = false;
            }
            if (key.right.down) {
                right = true;
            } else {
                right = false;
            }
            if (key.left.down) {
                left = true;
            } else {
                left = false;
            }
            if (key.attack.down) {
                attack = true;
            } else {
                attack = false;
            }
        } else {
            up = false;
            right = false;
            down = false;
            left = false;
        }
    }

    public AABB getBounds() {
        return bounds;
    }

    private void resetPosition() {
        System.out.println("Resetting player ...");
        pos.x = GamePanel.width / 2 -50;
        PlayState.map.x = 0;

        pos.y = GamePanel.height / 2;
        PlayState.map.y = 0;
    }
}
