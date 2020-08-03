package fmdb;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    // Requires: nothing
    // Modifies: nothing
    // Effects: returns average given rating
    public double getAverageRating() {
        return this.ratings.stream().map((item) -> item.getScore()).reduce(0d, (a, b) -> a + b) / this.ratings.size();
    }

    // Requires: nothing
    // Modifies: nothing
    // Effects: returns list of top 5 rated movies
    public ArrayList<Movie> getTopRated() {
        ArrayList<Movie> topRated = new ArrayList(this.ratings
                .stream()
                .sorted(Comparator.comparing(Rating::getScore))
                .map((item) -> item.getMovie())
                .collect(Collectors.toList())
                .subList(this.ratings.size() - 5 < 0 ? 0 : this.ratings.size() - 5, this.ratings.size()));

        Collections.reverse(topRated);

        return topRated;
    }
}
