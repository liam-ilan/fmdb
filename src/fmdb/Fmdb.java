package fmdb;

import java.util.ArrayList;

public class Fmdb {
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Person> people = new ArrayList<Person>();

    public ArrayList<Movie> getMovies() { return movies; }
    public ArrayList<Person> getPeople() { return people; }

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
