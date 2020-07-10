import fmdb.Person;
import fmdb.Rating;
import fmdb.Movie;
import fmdb.Fmdb;

import org.junit.Before;
import org.junit.Test;

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
    public void testRemovePerson() {
        fmdb.removePerson(fmdb.getPeople().get(2));
        assertEquals(2, fmdb.getPeople().size());
    }
}