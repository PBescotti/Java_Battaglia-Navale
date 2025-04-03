package gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

//  Classe in cui vengono salvate tutte le immagini e font utilizzate nel programma
public class Assets {
    
    public BufferedImage[] retryConnectionButton;
    public BufferedImage icon, hitSymbol;

    public Font font25, font40, font50;

    public Assets(){
        loadUI();
        loadTextures();
        loadFonts();
    }

    private void loadTextures() {
        icon = ImageLoader.loadImage("res/textures/icon.png");
        hitSymbol = ImageLoader.loadImage("res/textures/x.png");
    }

    private void loadUI(){
        retryConnectionButton = new BufferedImage[2];

        retryConnectionButton[0] = ImageLoader.loadImage("res/textures/RetryConnectionButton1.png");
        retryConnectionButton[1] = ImageLoader.loadImage("res/textures/RetryConnectionButton2.png");
    }

    private void loadFonts() {
        font25 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 25);
        font40 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 40);
        font50 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 50);
    }

}
