package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 105;
        solidArea.y = 65;
        solidArea.width = 5;
        solidArea.height = 13;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

        try{
            System.out.println("Image loading started");

            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walk up1.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walk down1.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walk left1.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/walk right1.png"));

            System.out.println("Image loading ended");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed) {
            direction = "up";
        }
        else if(keyH.downPressed) {
                direction = "down";
            }
        else if(keyH.leftPressed) {
            direction = "left";
        }
        else if(keyH.rightPressed) {
            direction = "right";
        }

        //collision check
        collisionOn = false;
        gp.cChecker.checkTile(this);

        //if collision is false, player can move
        if(!collisionOn) {

            switch(direction) {
                case"up": worldY -= speed;
                    break;
                case"down": worldY += speed;
                    break;
                case"left": worldX -= speed;
                    break;
                case"right": worldX += speed;
                    break;
            }
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction) {

            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2.drawImage(image, screenX, screenY, 200, 200, null);
    }
}
