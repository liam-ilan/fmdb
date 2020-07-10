import fmdb.Person;
import fmdb.Rating;
import fmdb.Movie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RatingTests {
    Movie movie;
    Person person;

    @Before
    public void setup(){
        movie = new Movie("Top Gun: Maverick");
        person = new Person("Goose");
    }

    @Test
    public void testRating() {
        Rating rate = new Rating(person, 10, movie);
        Rating personRate = person.getRatings().get(0);
        Rating movieRate = movie.getRatings().get(0);

        // check scores are same
        assertEquals(rate.getScore(), personRate.getScore(), 0);
        assertEquals(rate.getScore(), movieRate.getScore(), 0);

        // check people are same
        assertEquals(rate.getPerson().getName(), personRate.getPerson().getName());
        assertEquals(rate.getPerson().getName(), movieRate.getPerson().getName());

        // check movies are same
        assertEquals(rate.getMovie().getName(), personRate.getMovie().getName());
        assertEquals(rate.getMovie().getName(), movieRate.getMovie().getName());
    }

    @Test
    public void testDelete() {
        Rating rate = new Rating(person, 7.5, movie);
        rate.delete();

        assertEquals(0, movie.getRatings().size());
        assertEquals(0, person.getRatings().size());
    }

    @Test
    public void testToString() {
        Rating rate = new Rating(person, 5.5, movie);
        assertEquals(
                rate.getPerson().getName() +
                        " rated " +
                        rate.getMovie().getName() +
                        ": " +
                        rate.getScore(),
                rate.toString()
        );
    }
}