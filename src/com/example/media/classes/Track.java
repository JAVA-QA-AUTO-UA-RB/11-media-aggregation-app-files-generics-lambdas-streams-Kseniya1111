package com.example.media.classes;

public class Track extends Media {
    private String artist;
    private String genre;
    private int rating;

    public Track(String title, String artist, String genre, int duration, int rating) {
        super(title, duration);
        this.artist = artist;
        this.genre = genre;
        this.rating = rating;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return getTitle() + " by " + artist +
                " [" + genre + "]" +
                " (rating " + rating + ", " + getDuration() + " sec)";
    }
}
