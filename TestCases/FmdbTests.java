import fmdb.Person;
import fmdb.Rating;
import fmdb.Movie;
import fmdb.Fmdb;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FmdbTests {
    Fmdb fmdb;
    @Before
    public void setup(){
        fmdb = new Fmdb();
        fmdb.addMovie(new Movie("Inception"));
        fmdb.addMovie(new Movie("Top Gun: Maverick"));
        fmdb.addMovie(new Movie("Tenet"));
        fmdb.addMovie(new Movie("Men In Black"));

        fmdb.addPerson(new Person("Goose"));
        fmdb.addPerson(new Person("Will Smith"));
        fmdb.addPerson(new Person("Tom Cruise"));
    }

    @Test
    public void testAddMovie() {
        fmdb.addMovie(new Movie("Back To The Future"));
        assertEquals(5, fmdb.getMovies().size());
    }

    @Test
    public void testRemoveMovie() {
        fmdb.removeMovie(fmdb.getMovies().get(3));
        assertEquals(3, fmdb.getMovies().size());
    }

    @Test
    public void testAddPerson() {
        fmdb.addPerson(new Person("Marty Mcfly"));
        assertEquals(4, fmdb.getPeople().size());
    }

    @Test
    public void testTopMovies() {
        Fmdb db = new Fmdb();

        Person test = new Person("test");

        Movie a = new Movie("a");
        Movie b = new Movie("b");
        Movie c = new Movie("c");
        Movie d = new Movie("d");

        db.addPerson(test);

        db.addMovie(a);
        db.addMovie(b);
        db.addMovie(c);
        db.addMovie(d);

        new Rating(test, 8, c);
        new Rating(test, 9, a);
        new Rating(test, 10, b);

        ArrayList<Movie> topMovies = db.topMovies();

        // note, d comes first, as it has no ratings
        assertEquals(d, topMovies.get(0));
        assertEquals(b, topMovies.get(1));
        assertEquals(a, topMovies.get(2));
        assertEquals(c, topMovies.get(3));
    }
}