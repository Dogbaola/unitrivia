package ng.com.techsoft.unitrivia;

public class Category {
    public static final int GNS = 1;
    public static final int POL111 = 2;
    public static final int FIN112 = 3;
    public static final int ECN112 = 4;
    public static final int EDT114 = 5;

    private int id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}