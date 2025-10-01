package com.example.media.util;

import com.example.media.classes.Playlist;
import com.example.media.classes.Track;
import com.example.media.classes.Video;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MediaStatisticsWriter {

    public static void writeTrackStats(Playlist<Track> playlist, String filename) throws IOException {
        List<Track> tracks = playlist.getItems();

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(filename)))) {

            writer.println("Tracks count: " + tracks.size());

            double avgDuration = tracks.stream()
                    .mapToInt(Track::getDuration)
                    .average()
                    .orElse(0);
            writer.println("Average duration: " + (int) avgDuration + " seconds\n");

            writer.println("Top 3 tracks by rating:");
            AtomicInteger counter = new AtomicInteger(1); // Счётчик для нумерации
            tracks.stream()
                    .sorted(Comparator.comparingInt(Track::getRating).reversed()
                            .thenComparing(Comparator.comparingInt(Track::getDuration).reversed()))
                    .limit(3)
                    .forEachOrdered(t -> writer.println(counter.getAndIncrement() + ". "
                            + t.getTitle() + " (rating " + t.getRating() + ")"));

            writer.println();

            writer.println("Pop tracks:");
            tracks.stream()
                    .filter(t -> t.getGenre().equalsIgnoreCase("Pop"))
                    .forEach(t -> writer.println("- " + t.getTitle()));

            writer.println("--------------------------------------------");
        }
    }

    public static void writeVideoStats(Playlist<Video> playlist, String filename) throws IOException {
        List<Video> videos = playlist.getItems();

        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(filename)))) {

            writer.println("Videos count: " + videos.size());

            double avgDuration = videos.stream()
                    .mapToInt(Video::getDuration)
                    .average()
                    .orElse(0);
            writer.println("Average duration: " + (int) avgDuration + " seconds\n");
            writer.println("Top 3 videos by views:");
            AtomicInteger counter = new AtomicInteger(1); // Счётчик для нумерации
            videos.stream()
                    .sorted(Comparator.comparingInt(Video::getViews).reversed())
                    .limit(3)
                    .forEachOrdered(v -> writer.println(counter.getAndIncrement() + ". "
                            + v.getTitle() + " (" + v.getViews() + " views)"));

            writer.println();
            writer.println("Education videos:");
            videos.stream()
                    .filter(v -> v.getCategory().equalsIgnoreCase("Education"))
                    .forEach(v -> writer.println("- " + v.getTitle()));

            writer.println("--------------------------------------------");
        }
    }

}

