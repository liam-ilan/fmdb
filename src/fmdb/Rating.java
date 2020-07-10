package fmdb;

public class Rating {
    private Person person;
    private double score;
    private Movie movie;

    public Rating(Person person, double score, Movie movie) {
        this.person = person;
        this.score = score;
        this.movie = movie;

        this.person.addRating(this);
        this.movie.addRating(this);
    }

    // Requires: nothing
    // Modifies: this.person, this.movie
    // Effects: removes self from this.person and this.movie
    public void delete() {
        this.person.removeRating(this);
        this.movie.removeRating(this);
    }

    public void setScore(double score) { this.score = score; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public void setPerson(Person person) { this.person = person; }

    public double getScore() { return score; }
    public Movie getMovie() { return movie; }
    public Person getPerson() { return person; }

    // Requires: nothing
    // Modifies: nothing
    // Effects: returns a String in the format of "<person> rated <movie>: score"
    public String toString() {
        return person + " rated " + movie + ": " + score;
    }
}
