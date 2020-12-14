package graphics;

import java.awt.image.BufferedImage;

//class utk atur/simpen gambar, musik, pkoknya aset2 game
public class Assets {
    //lebar n tinggi tiap kotak2 dlm sprite sheet yg disana tu ukurannya 32 pixel
    private static final int width = 32, height = 32;

    //tile
    public static BufferedImage water, dirt, grass, stone, hana;

    //player
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage player_win, player_die;

    //items n objects
    public static BufferedImage coin, key, heart, coin_chest_open, coin_chest_closed, key_chest_open, key_chest_closed;
    public static BufferedImage tree, big_tree, rock, bush;
    public static BufferedImage[] coins;
    public static BufferedImage gate_open, gate_closed;

    //menu n display
    public static BufferedImage title_treasure, title_hunter;
    public static BufferedImage[] start_option, score_option, exit_option, resume_option;
    public static BufferedImage scoreBox;
    public static BufferedImage die_text, win_text, name_text;

    //for monster
    public static BufferedImage[] monster_left, monster_right, monster_up, monster_down;

    //for backgrounds
    public static BufferedImage bgMenu, bgPause, bgAfterGame;

    //utk load smuanya utk gamenya, n d panggil skali
    public static void init(){

        SpriteSheet sheet_tile_and_object = new SpriteSheet(ImageLoader.loadImage("/textures/tileAndObject.png"));
        SpriteSheet sheet_player = new SpriteSheet(ImageLoader.loadImage("/textures/player.png"));
        SpriteSheet sheet_player_status = new SpriteSheet(ImageLoader.loadImage("/textures/aftergame.png"));
        SpriteSheet options = new SpriteSheet(ImageLoader.loadImage("/textures/textImg.png"));
        SpriteSheet sheet_items = new SpriteSheet(ImageLoader.loadImage("/textures/items.png"));
        SpriteSheet gameTitle = new SpriteSheet(ImageLoader.loadImage("/textures/title.png"));
        SpriteSheet sheet_slime = new SpriteSheet(ImageLoader.loadImage("/textures/slime.png"));
        SpriteSheet sheet_afterGame = new SpriteSheet(ImageLoader.loadImage("/textures/afterGameText.png"));
        SpriteSheet sheet_gate = new SpriteSheet(ImageLoader.loadImage("/textures/gate.png"));

        scoreBox = ImageLoader.loadImage("/textures/scoreBg.png");

        //backgrounds
        bgMenu = ImageLoader.loadImage("/textures/bGMenu.png");
        bgPause = ImageLoader.loadImage("/textures/bGPause.png");
        bgAfterGame = ImageLoader.loadImage("/textures/bGAfterGame.png");

        //items & object
        coins = new BufferedImage[4];
        coin = sheet_items.crop(width * 2, height, width, height);
        heart = sheet_items.crop(0, 0, width, height);
        key = sheet_items.crop(width, 0, width, height);

        coin_chest_open = sheet_items.crop(width * 3, height * 3, width, height);
        coin_chest_closed = sheet_items.crop(width * 2, height * 3, width, height);
        key_chest_open = sheet_items.crop(width * 3, height * 2, width, height);
        key_chest_closed = sheet_items.crop(width * 2, height * 2, width, height);

        gate_closed = sheet_gate.crop(0, 0, width * 2, height * 3);
        gate_open = sheet_gate.crop(width * 2, 0, width * 2, height * 3);

        coins[0] = sheet_items.crop(0, height, width, height);
        coins[1] = sheet_items.crop(width, height, width, height);
        coins[2] = sheet_items.crop(width * 2, height, width, height);
        coins[3] = sheet_items.crop(width * 3, height, width, height);

        //player animations
        //4 frame
        player_down = new BufferedImage[4];
        player_up = new BufferedImage[4];
        player_left = new BufferedImage[4];
        player_right = new BufferedImage[4];

        player_down[0] = sheet_player.crop(0, 0, width, height);
        player_down[1] = sheet_player.crop(width, 0, width, height);
        player_down[2] = sheet_player.crop(width * 2, 0, width, height);
        player_down[3] = sheet_player.crop(width * 3, 0, width, height);
        
        player_left[0] = sheet_player.crop(0, height, width, height);
        player_left[1] = sheet_player.crop(width, height, width, height);
        player_left[2] = sheet_player.crop(width * 2, height, width, height);
        player_left[3] = sheet_player.crop(width * 3, height, width, height);

        player_right[0] = sheet_player.crop(0, height * 2, width, height);
        player_right[1] = sheet_player.crop(width, height * 2, width, height);
        player_right[2] = sheet_player.crop(width * 2, height * 2, width, height);
        player_right[3] = sheet_player.crop(width * 3, height * 2, width, height);

        player_up[0] = sheet_player.crop(0, height * 3, width, height);
        player_up[1] = sheet_player.crop(width, height * 3, width, height);
        player_up[2] = sheet_player.crop(width * 2, height * 3, width, height);
        player_up[3] = sheet_player.crop(width * 3, height * 3, width, height);

        player_win = sheet_player_status.crop(0, 0, width, height);
        player_die = sheet_player_status.crop(width, 0, width, height);

        //monster

        monster_down = new BufferedImage[4];
        monster_up = new BufferedImage[4];
        monster_left = new BufferedImage[4];
        monster_right = new BufferedImage[4];

        monster_down[0] = sheet_slime.crop(0, 0, width, height);
        monster_down[1] = sheet_slime.crop(width, 0, width, height);
        monster_down[2] = sheet_slime.crop(width * 2, 0, width, height);
        monster_down[3] = sheet_slime.crop(width * 3, 0, width, height);

        monster_up[0] = sheet_slime.crop(0, height, width, height);
        monster_up[1] = sheet_slime.crop(width, height, width, height);
        monster_up[2] = sheet_slime.crop(width * 2, height, width, height);
        monster_up[3] = sheet_slime.crop(width * 3, height, width, height);

        monster_left[0] = sheet_slime.crop(0, height * 2, width, height);
        monster_left[1] = sheet_slime.crop(width, height * 2, width, height);
        monster_left[2] = sheet_slime.crop(width * 2, height * 2, width, height);
        monster_left[3] = sheet_slime.crop(width * 3, height * 2, width, height);
        
        monster_right[0] = sheet_slime.crop(0, height * 3, width, height);
        monster_right[1] = sheet_slime.crop(width, height * 3, width, height);
        monster_right[2] = sheet_slime.crop(width * 2, height * 3, width, height);
        monster_right[3] = sheet_slime.crop(width * 3, height * 3, width, height);

        // tile
        water = sheet_tile_and_object.crop(0, 0, width, height);
        dirt = sheet_tile_and_object.crop(width, 0, width, height);
        grass = sheet_tile_and_object.crop(width * 2, 0, width, height);
        stone = sheet_tile_and_object.crop(width * 3, 0, width, height);

        //object alam
        bush = sheet_tile_and_object.crop(0, height, width, height);
        rock = sheet_tile_and_object.crop(width, height, width, height);
        tree = sheet_tile_and_object.crop(0, height * 2, width * 2, height * 2);
        big_tree = sheet_tile_and_object.crop(width * 2, height, width * 2, height * 3);

        //menu n display
        start_option = new BufferedImage[2];
        score_option = new BufferedImage[2];
        exit_option = new BufferedImage[2];
        resume_option = new BufferedImage[2];

        win_text = sheet_afterGame.crop(0, 0, width * 4, height);
        die_text = sheet_afterGame.crop(0, height, width * 4, height);
        name_text = sheet_afterGame.crop(0, height * 2, width * 4, height);

        start_option[0] = options.crop(0, 0, width * 4, height);
        start_option[1] = options.crop(0, height, width * 4, height);
        score_option[0] = options.crop(0, height * 2, width * 4, height);
        score_option[1] = options.crop(0, height * 3, width * 4, height);
        resume_option[0] = options.crop(0, height * 4, width * 4, height);
        resume_option[1] = options.crop(0, height * 5, width * 4, height);
        exit_option[0] = options.crop(0, height * 6, width * 4, height);
        exit_option[1] = options.crop(0, height * 7, width * 4, height);

        title_treasure = gameTitle.crop(0, height * 2, width * 4, height);
        title_hunter = gameTitle.crop(0, height * 3, width * 4, height);
    }
}
