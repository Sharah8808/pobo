package graphics;

import java.awt.image.BufferedImage;

//class utk simpen/atur gmbar2 dri sprite sheet
public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    //dri sprite sheet dri, dgn metode ini, tiap kotak2nya di crop
    //x y width height menyesuaikan ukuran tiap2 kotak sprite sheetnya
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
}
