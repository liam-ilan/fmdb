package fmdb;
import java.util.ArrayList;

public class Person {
    private String name;
    private ArrayList<Rating> ratings = new ArrayList<Rating>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public ArrayList<Rating> getRatings() { return this.ratings; }

    // Requires: Rating rating
    // Modifies: this.ratings
    // Effects: adds rating to ratings
    public void addRating(Rating rating) { this.ratings.add(rating); }

    // Requires: Rating rating
    // Modifies: this.ratings
    // Effects: removes rating from ratings
    public void removeRating(Rating rating) { this.ratings.remove(rating); }

    // Requires: nothing
    // Modifies: nothing
    // Effects: returns the name of the person
    public String toString() { return this.name; }
}
