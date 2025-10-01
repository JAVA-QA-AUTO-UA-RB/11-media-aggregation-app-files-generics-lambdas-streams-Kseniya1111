package com.example.media.util;

import com.example.media.classes.Track;
import com.example.media.classes.Video;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    public static List<Track> readTracks(String filename) throws IOException {

        List<Track> tracks = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));


        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(";");
            if (parts.length == 5) {
                String title = parts[0];
                String artist = parts[1];
                String genre = parts[2];
                int duration = Integer.parseInt(parts[3]);
                int rating = Integer.parseInt(parts[4]);
                tracks.add(new Track(title, artist, genre, duration, rating));
            }
        }
        return tracks;
    }

    public static List<Video> readVideos(String filename) throws IOException {
        List<Video> videos = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filename));


        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(";");
            if (parts.length == 5) {
                String title = parts[0];
                String channel = parts[1];
                String category = parts[2];
                int duration = Integer.parseInt(parts[3]);
                int views = Integer.parseInt(parts[4]);
                videos.add(new Video(title, channel, category, duration, views));
            }
        }
        return videos;
    }
}

