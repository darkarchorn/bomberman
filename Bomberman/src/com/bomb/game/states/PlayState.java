package com.bomb.game.states;

import com.bomb.game.GamePanel;
import com.bomb.game.entities.Enemy;
import com.bomb.game.entities.Player;
import com.bomb.game.graphics.Font;
import com.bomb.game.graphics.Sprite;
import com.bomb.game.math.Vector2f;
import com.bomb.game.tiles.TileManager;
import com.bomb.game.util.KeyHandler;
import com.bomb.game.util.MouseHandler;

import java.awt.Graphics2D;

public class PlayState extends GameState {

    private Font font;
    private Player player;
    private Enemy enemy;
    private TileManager tm;

    public static Vector2f map;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);

        tm = new TileManager("tile/tilemap.xml");
        font = new Font("font/font.png", 10, 10);
        //enemy = new Enemy(new Sprite("entity/littlegirl.png"), new Vector2f(0 +(GamePanel.width / 2 ) +150, 0 + (GamePanel.height / 2) +150), 45);
        player = new Player(new Sprite("entity/linkformatted.png"), new Vector2f(0 +(GamePanel.width / 2 ) -50, 0 + (GamePanel.height / 2) ), 45);
    }

    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        player.update();
        //enemy.update(player.getBounds());
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);
    }

    public void render(Graphics2D g) {
        tm.render(g);
        Font.drawArray(g, font, GamePanel.oldFrameCount +" FPS", new Vector2f(GamePanel.width - 192, 32), 32, 32, 32, 0);
        player.render(g);
    }
}
