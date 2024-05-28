package Entity;

public class Picture {
    private int id;
    private String name;
    private String date;
    private int pID;
    private byte[] picture;
    public Picture (int id, String name, String date, int pID, byte[] picture){
        this.id= id;
        this.name = name;
        this.date = date;
        this.pID = pID;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public byte[] getPicture() {
        return picture;
    }

    public int getpID() {
        return pID;
    }
}
