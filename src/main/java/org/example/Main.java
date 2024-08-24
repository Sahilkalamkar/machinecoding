package org.example;

import org.example.models.Song;
import org.example.models.User;
import org.example.service.DefaultSimilarityIndexStrategy;
import org.example.service.ISongSimilarityStrategy;
import org.example.service.MusicApplicationService;
import org.example.service.RecommendationService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Song song1 = new Song("song1","AB","Folk",60);
        Song song2 = new Song("song2","DEF","Rock",70);
        Song song3 = new Song("song3","AB","Country",55);
        Song song4 = new Song("song4","XYZ","Rock",60);
        Song song5 = new Song("song5","XYZ","Rock",75);
        Song song6 = new Song("song6","AB","Country",60);
        Song song7 = new Song("song7", "DEF", "Indie", 55);


        List<Song> songs = Arrays.asList(song1, song2, song3, song4, song5, song6, song7);

        User a = new User("a", Arrays.asList(song1,song2,song3));
        User b = new User("b", Arrays.asList(song6,song7,song3));
        User c = new User("c", Arrays.asList(song4,song3,song6));
        User d = new User("b", Arrays.asList(song7,song3,song1,song2));
        List<User> users = Arrays.asList(a,b,c,d);
        Map<String, User> usersMap = new HashMap<>();
        usersMap.put("a",a);
        usersMap.put("b",b);
        usersMap.put("c",c);
        usersMap.put("d",d);
        a.setFriends(Arrays.asList(b,c));
        b.setFriends(Arrays.asList(a,d));
        c.setFriends(Arrays.asList(a,d));
        d.setFriends(Arrays.asList(b,c));

        ISongSimilarityStrategy songSimilarityStrategy = new DefaultSimilarityIndexStrategy();

        RecommendationService recommendationService = new RecommendationService(songSimilarityStrategy);


        MusicApplicationService musicApplicationService = new MusicApplicationService(songs, usersMap, recommendationService);
        musicApplicationService.calculateRecommendation();

        List<Song> recsongs = musicApplicationService.getTopKRecommendations("a", 4);
        System.out.println(recsongs.size());
        for(Song song: recsongs) {
            System.out.println(song.toString());
        }
    }
}