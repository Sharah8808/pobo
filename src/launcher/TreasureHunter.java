package launcher;

import display.Display;
import input.KeyManager;
import input.MouseManager;
import states.*;
import states.State;
import graphics.Assets;
import graphics.GameCamera;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;

public class TreasureHunter implements Runnable {
    private Display display;
    public String title;
    private int width, height;

    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State state;
    public State gameState;
    public State gameOverState;
    public State gameFinishedState;
    public State menuState;
    public State pauseState;
    public State scoreState;

    //input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //camera
    private GameCamera gameCam;

    //handler
    private Handler handler;

    public TreasureHunter(String title, int width, int height) {

        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    //method ni jalan sekali, inisiali grafiknya
    //start > run > init
    private void init(){
        display = new Display(title, width, height);
        //nmbah keylistnet ke frame yg ada d class TreasureHunter
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();

        handler = new Handler(this);
        gameCam = new GameCamera(handler, 0, 0);
        
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        gameOverState = new GameOverr(handler);
        gameFinishedState = new GameFinished(handler, null);
        pauseState = new PauseState(handler);
        scoreState = new ScoreState(handler);

        State.setState(menuState);

    }

    //update var, posisi char, dll
    private void tick(){
        //essential
        keyManager.tick();

        if(State.getState() != null){
            State.getState().tick();
        }
    }

    //draw things to the screen
    private void render(){
        //cara utk komputer gambar ssuatu ke layar, how to draw thing to the screen
        //buffer = a hidden computer screen within ur computer
        //utk prevent flickering
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        //kya tool utk gambar ke canvas, kya paint brush misalnya lol
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, width, height);

        //draw here
        
        if(State.getState() != null){
            State.getState().render(g);
        }

        //end draw
        bs.show();
        g.dispose();


    }

    @Override
    // majority of game code
    //metode ini yg manggil metode tick() n render() tiap satuan waktu
    public void run() {
        init();

        //ticks per second, 60 tu jmlh wktu kt mw panggil tick() n render() perdetik
        int fps = 60;
        //ad 1 miliar nano detik di 1 detik, jdi ukur waktu di program tu lbh baik pke nano detik biar lbh spesifik
        //var ini buat izinin program jalanin while(running) tu scr maksimum utk mndapatkan fps = 60
        double timePerTick = 1000000000/fps;
        //jumlh wktu yg dmiliki smpe metode tick() n render() d pnggil lagi 
        double delta = 0;
        //current time of our computer
        //diinisialisasi di awal2 tiap game loop brlangsung
        long now;
        //system nanotime return jumlah waktu dlm nano detik yg komputer jalanin, currently
        long lastTime = System.nanoTime();
        //hitung wktu sampai dapet 1 detik, n stlh 1 detik, var ticks akan muncul utk ksi tw dh brp kali metode tick() render() dipanggil (dipanggil brulang2)
        long timer = 0;
        int ticks = 0;

        //game loop
        while(running){
            now = System.nanoTime();
            //delta ksi tw komputer when n when not jalanin metode tick() n render()
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            //delta akan slalu bertambah tiap running dri statement delta += (now - lastTime) / timePerTick
            //tick n render akan jalan jika delta d atas/sma dgn 1
            //stlh tick render, delta dikurangi 1 
            if(delta >= 1){
                tick();
                render();
                ticks++;
                delta--;
            }
            //representasi visual ke kt utk tunjukin brapa bnyak metode tick n render dipanggil tiap detik
            //ksi tw brp fps yg dihasilkan program tiap 1 detik
            if(timer >= 1000000000){
                System.out.println("Ticks and Frames : "+ticks);
                ticks = 0;
                timer = 0;
            }
            
        }

        stop();
    }

    // sinkron = izinin 1 thread yg jalan utk akses resource (?)
    // utk kunci objek
    // slesein 1 thread dlu bru yg lain
    public synchronized void start() {
        //klo running true, maka program keluar dri method ini (return) biar ga kepanggil method start lbh dri sekali
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        // thread.start otomatis manggil method run
        thread.start();
    }

    public synchronized void stop() {
        //sama kya if statement di method start
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //getterrs

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MouseManager getMouseManager(){
        return mouseManager;
    }

    public GameCamera getGameCam(){
        return gameCam;
    }

    public State getGameState(){
        return gameState;
    }

    public State getGameFinishedState(){
        return gameFinishedState;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    
}
