import fmdb.Person;
import fmdb.Rating;
import fmdb.Movie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTests {
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
            to the movie object through addRating(),
            to test the method, I am creating a "dummy movie"
            so that the rating does not get automatically attached
            to the movie.
        */

        Rating rating = new Rating(person, 10, new Movie("Dummy Movie"));
        movie.addRating(rating);

        Rating addedRating = movie.getRatings().get(1);

        assertEquals(rating.getMovie().getName(), addedRating.getMovie().getName());
        assertEquals(rating.getScore(), addedRating.getScore(), 0);
        assertEquals(rating.getPerson().getName(), addedRating.getPerson().getName());
    }

    @Test
    public void testRemoveRating() {
        movie.removeRating(movie.getRatings().get(0));
        assertEquals(0, movie.getRatings().size());
    }

    @Test
    public void testGetAverageRating() {
        // (9.5) / 1
        assertEquals(movie.getAverageRating(), 9.5, 0);

        // (9.5 + 7) / 2
        new Rating(new Person("Neo"), 7, movie);
        assertEquals(8.25, movie.getAverageRating(), 0);

        // (9.5 + 8.25 + 10) / 3
        new Rating(new Person("Marty McFly"), 10, movie);
        assertEquals(8.833333333333334, movie.getAverageRating(), 0);
    }

    @Test
    public void testToString() {
        assertEquals(movie.getName(), movie.toString());
    }
}