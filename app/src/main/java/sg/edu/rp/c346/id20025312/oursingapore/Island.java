package sg.edu.rp.c346.id20025312.oursingapore;

import java.io.Serializable;

public class Island implements Serializable {

    private int id;
    private String name;
    private String description;
    private int km;
    private int stars;

    public Island(int id, String name, String description, int km, int stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.km = km;
        this.stars = stars;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        String starsString = "";
        for (int i = 0; i < stars; i++) {
            starsString += " *";
        }
        return starsString;
    }
}
