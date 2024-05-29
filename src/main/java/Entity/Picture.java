package Entity;

public class Picture {
    private int id;
    private byte[] picture;
    public Picture (int id, byte[] picture){
        this.id= id;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public byte[] getPicture() {
        return picture;

    }
}
