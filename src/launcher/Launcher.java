package launcher;

//main class
public class Launcher {
    public static void main(String[] args) {
        // new Display("Treasure Hunter", 640, 480);
        TreasureHunter th_game = new TreasureHunter("Treasure Hunter", 640, 480);

        th_game.start();
    }
}

