package furda.inc.entities;

public enum EntityType {


    //id name, width (here 14 as bricks are 16 wide, being 14 the player can slip through)
    //Height - player is 2 bricks high
    //The weight is going to be an assigned value for gravity. This affects fall speed

    BACTERIABLUE("bacteriaBlue", 56, 64, 40),
    BACTERIARED("bacteriaRed", 56,64,40),
    BLUESPORE("bluespore", 16,16,40),
    REDSPORE("redspore", 16,16,40),
    GARLIC("garlic", 16,16,40),
    ANTIBIOTIC("antibiotic",16,16,40),
    WBC("WBC",32,32,40),
    SPAWNBUTTON("spawnButton",32,16,40);


    private String id;
    private int width, height;
    private float weight;

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    EntityType(String id, int width, int height, float weight) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }


}
