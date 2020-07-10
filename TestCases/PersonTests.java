import fmdb.Person;
import fmdb.Rating;
import fmdb.Movie;

import org.junit.Before;
import org.junit.Test;

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
    public void testToString() {
        assertEquals(person.getName(), person.toString());
    }
}