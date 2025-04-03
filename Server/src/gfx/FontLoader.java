package gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

//  Serve a prendere il font dal path specificato
public class FontLoader {

	public FontLoader() {
		
	}
	
	public static Font loadFont(String path, float size) {
		try {
			return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Font not found");
            System.exit(2);
		}
		
		return null;
	}

}
