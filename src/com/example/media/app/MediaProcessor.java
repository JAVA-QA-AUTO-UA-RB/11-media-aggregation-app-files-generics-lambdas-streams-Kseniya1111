package com.example.media.app;

import com.example.media.classes.Playlist;
import com.example.media.classes.Track;
import com.example.media.classes.Video;
import com.example.media.util.FileUtil;
import com.example.media.util.MediaStatisticsWriter;

import java.io.IOException;

public class MediaProcessor {

    private static final String TRACKS_INPUT = "data/tracks.txt";
    private static final String VIDEOS_INPUT = "data/videos.txt";
    private static final String TRACKS_OUTPUT = "tracks_output.txt";
    private static final String VIDEOS_OUTPUT = "videos_output.txt";

    public static void main(String[] args) throws IOException {

        Playlist<Track> trackPlaylist = new Playlist<>();
        trackPlaylist.getItems().addAll(FileUtil.readTracks(TRACKS_INPUT));

        Playlist<Video> videoPlaylist = new Playlist<>();
        videoPlaylist.getItems().addAll(FileUtil.readVideos(VIDEOS_INPUT));

        MediaStatisticsWriter.writeTrackStats(trackPlaylist, TRACKS_OUTPUT);

        MediaStatisticsWriter.writeVideoStats(videoPlaylist, VIDEOS_OUTPUT);

        System.out.println("Processing complete. Output files generated.");
    }
}
