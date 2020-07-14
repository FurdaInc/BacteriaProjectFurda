package furda.inc.world;

import java.util.HashMap;

public enum TileType {

    WALLT1L(1, true, "Wall-Top1-Left"),
    WALLT1M( 2, true, "Wall-Top1-Mid"),
    WALLT1R(3, true, "Wall-Top1-Right"),
    FEATURE1( 4, false, "Dots1"),
    FEATURE2( 5, false, "Dots2"),
    BLANK( 6, false, "Blank"),
    FEATURE3(7, false, "Feature3"),
    FEATURE4( 8, false, "Feature4"),
    FEATURE5(9, false, "Feature5"),
    FEATURE6( 10, false, "Feature6"),
    FEATURE7( 11, false, "Feature7"),
    FEATURE8( 12, false, "Feature8"),
    WALLT2L(13, true, "Wall-Top2-Left"),
    WALLT2M( 14, true, "Wall-Top2-Mid"),
    WALLT2R(15, false, "Wall-Top2-Right"),
    WALLBL( 16, true, "Wall-Bottom-Left"),
    WALLBM( 17, true, "Wall-Bottom-Mid"),
    WALLBR( 18, true, "Wall-Bottom-Right"),
    FEATURE9(19, false, "Feature9"),
    FEATURE10( 20, false, "Feature10"),
    FEATURE11(21, false, "Feature11"),
    FEATURE12( 22, false, "Feature12"),
    FEATURE13( 23, false, "Feature13"),
    FEATURE14( 24, false, "Feature14");

    public static final int TILE_SIZE = 16;

    private int id;
    private boolean collidable;
    private String name;
    private float damage;

    private TileType(int id, boolean collidable, String name){

        this(id, collidable, name, 0);

    }

    private TileType(int id, boolean collidable, String name, float damage){

        this.id = id;
        this.collidable = collidable;
        this.name = name;
        this.damage = damage;

    }

    public boolean isCollidable() {
        return collidable;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    private static HashMap<Integer, TileType> tileMap;


    //The below sets up a dictionary containing each of the tiles we have made
    //It has a key and a value (value, key)
    static{
        tileMap = new HashMap<Integer, TileType>();
        for(TileType tileType : TileType.values()){
            tileMap.put(tileType.getId(), tileType);
        }
    }

    //This returns what the tile type is when an id is entered
    public static TileType getTileTypeById (int id){
        return tileMap.get(id);
    }

}
