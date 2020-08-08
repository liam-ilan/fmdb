package fmdb;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Fmdb implements Serializable {
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Person> people = new ArrayList<Person>();

    public ArrayList<Movie> getMovies() { return movies; }
    public ArrayList<Person> getPeople() { return people; }

    // Requires: nothing
    // Modifies: .fmdb file
    // Effects: reads serializable object from .fmdb file, and creates a new fmdb from it

    // note: read is static because we need to use this method to construct from file
    // (acts like constructor)
    public static Fmdb read() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(".fmdb");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Fmdb fmdb = (Fmdb) objectInputStream.readObject();
        objectInputStream.close();

        return fmdb;
    }

    // Requires: nothing
    // Modifies: .fmdb file
    // Effects: writes serializable object to .fmdb file
    public void write() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(".fmdb");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    // Requires: nothing
    // Modifies: nothing
    // Effects: returns movies ordered by average rating
    public ArrayList<Movie> topMovies () {
        ArrayList<Movie> topRated = new ArrayList(
                this.movies
                .stream()
                .sorted(Comparator.comparing(Movie::getAverageRating))
                .collect(Collectors.toList())
        );

        Collections.reverse(topRated);

        return topRated;
    }

    // Requires: Movie movie
    // Modifies: this.movies
    // Effects: adds a movie
    public void addMovie(Movie movie) { this.movies.add(movie); }

    // Requires: Movie movie
    // Modifies: this.movies
    // Effects: removes a movie
    public void removeMovie(Movie movie) { this.movies.remove(movie); }

    // Requires: Person person
    // Modifies: this.people
    // Effects: adds a person
    public void addPerson(Person person) { this.people.add(person); }

    // Requires: Person person
    // Modifies: this.people
    // Effects: removes a person
    public void removePerson(Person person) { this.people.remove(person); }
}
