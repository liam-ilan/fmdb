package fmdb;
import java.util.ArrayList;

public class Movie {
    private String name;

    private String imdbUrl;
    private String mpcr;
    private int year;
    private String genre;

    private ArrayList<Rating> ratings = new ArrayList<Rating>();

    public Movie(String name) {
        this.name = name;
    }

    public Movie(String name, String imdbUrl, String mpcr, int year, String genre) {
        this.name = name;
        this.imdbUrl = imdbUrl;
        this.mpcr = mpcr;
        this.year = year;
        this.genre = genre;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getImdbUrl() { return imdbUrl; }
    public void setImdbUrl(String imdbUrl) { this.imdbUrl = imdbUrl; }

    public String getMpcr() { return mpcr; }
    public void setMpcr(String mpcr) { this.mpcr = mpcr; }

    public void update(String name, String imdbUrl, String mpcr, int year, String genre) {
        this.name = name;
        this.imdbUrl = imdbUrl;
        this.mpcr = mpcr;
        this.year = year;
        this.genre = genre;
    }

    public ArrayList<Rating> getRatings() {return this.ratings;}

    // Requires: nothing
    // Modifies: nothing
    // Effects: returns average given rating
    public double getAverageRating() {
        return this.ratings.stream().map((item) -> item.getScore()).reduce(0d, (a, b) -> a + b) / this.ratings.size();
    }

    // Requires: Rating rating
    // Modifies: this.ratings
    // Effects: adds rating to ratings
    public void addRating(Rating rating) {this.ratings.add(rating);}

    // Requires: Rating rating
    // Modifies: this.ratings
    // Effects: removes rating from ratings
    public void removeRating(Rating rating) {this.ratings.remove(rating);}

    // Requires: nothing
    // Modifies: nothing
    // Effects: returns the name of the movie
    public String toString() {
        return this.name;
    }
}
