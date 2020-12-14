package display;

import javax.swing.*;
import java.awt.*;

//klas utama utk frame
public class Display extends JFrame {
    
    //ksi kt gambar grafik kya karakter, tile, dll
    //buat objeknya di setDisplay
    private Canvas canvas;

    ImageIcon img = new ImageIcon(Display.class.getResource("/textures/gameIcon.png"));
    //3 hal utama yg frame butuhkan, judul n pnjg lebar frame-nya
    private String title;
    private int width, height;

    public Display(String title, int width, int height){

        this.title = title;
        this.width = width;
        this.height = height;

        setDisplay();
    }

    private void setDisplay(){

        canvas = new Canvas();
        //biar dia ttp ukurannya sesuai width n height
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        this.setIconImage(img.getImage());
        this.add(canvas);

        this.pack();
        this.setTitle(title);
        this.setSize(width, height);
        //program auto close klo klik tombol x di windownya
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //atur size frame (geser n tarik di ujung window pke mouse), false 
        this.setResizable(false);
        //pas prtama d run framenya muncul di tengah2 screen PC
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return this;
    }
}
