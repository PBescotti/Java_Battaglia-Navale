package gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

//  Classe in cui vengono salvate tutte le immagini e font utilizzate nel programma
public class Assets {
    
    public BufferedImage hitSymbol;

    public Font font25, font40, font50;

    public Assets(){
        loadUI();
        loadTextures();
        loadFonts();
    }

    private void loadTextures() {
        hitSymbol = ImageLoader.loadImage("res/textures/x.png");
    }

    private void loadUI(){
        
    }

    private void loadFonts() {
        font25 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 25);
        font50 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 50);
        font40 = FontLoader.loadFont("res/fonts/JetBrainsMono.ttf", 40);
    }

}
