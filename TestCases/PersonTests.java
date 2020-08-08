import fmdb.Person;
import fmdb.Rating;
import fmdb.Movie;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PersonTests {
    Movie movie;
    Person person;

    @Before
    public void setup(){
        movie = new Movie("Top Gun: Maverick");
        person = new Person("Goose");
        new Rating(person, 9.5, movie);
    }

    @Test
    public void testAddRating() {
        /*
            Note, since the constructor
            of Rating adds a rating automatically
            to the person object through addRating(),
            to test the method, I am creating a "dummy person"
            so that the rating does not get automatically attached
            to the person.
        */

        Rating rating = new Rating(new Person("Joe Smith"), 10, movie);
        person.addRating(rating);

        Rating addedRating = person.getRatings().get(1);

        assertEquals(rating.getMovie().getName(), addedRating.getMovie().getName());
        assertEquals(rating.getScore(), addedRating.getScore(), 0);
        assertEquals(rating.getPerson().getName(), addedRating.getPerson().getName());
    }

    @Test
    public void testRemoveRating() {
        person.removeRating(person.getRatings().get(0));
        assertEquals(0, person.getRatings().size());
    }

    @Test
    public void testGetAverageRating() {
        // (9.5) / 1
        assertEquals(person.getAverageRating(), 9.5, 0);

        // (9.5 + 7) / 2
        new Rating(person, 7, new Movie("The Matrix"));
        assertEquals(8.25, person.getAverageRating(), 0);

        // (9.5 + 8.25 + 10) / 3
        new Rating(person, 10, new Movie("Back to the Future"));
        assertEquals(8.833333333333334, person.getAverageRating(), 0);
    }

    @Test
    public void testGetTopRated() {
        person = new Person("Tester");

        // add ratings
        for (int i = 1; i < 10; i++) {
            new Rating(person, i, new Movie(String.valueOf(10 - i)));
        }
        new Rating(person, 9, new Movie("1"));

        ArrayList<Movie> topRated = person.getTopRated();

        // check top
        assertEquals(topRated.get(0).getName(), "1");

        // check next 4
        for (int i = 1; i < 5; i++) {
            assertEquals(topRated.get(i).getName(), String.valueOf(i));
        }
    }

    @Test
    public void testToString() {
        assertEquals(person.getName(), person.toString());
    }
}