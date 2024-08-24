package org.example.service;

import org.example.models.Pair;
import org.example.models.Song;
import org.example.models.User;

import java.util.*;

public class MusicApplicationService {

    private List<Song> librarySongs;
    private Map<String,User> users;
    private final RecommendationService recommendationService;

    public MusicApplicationService(final List<Song> librarySongs,
                                   final Map<String,User> users,
                                   final RecommendationService recommendationService) {
        this.users = users;
        this.librarySongs = librarySongs;
        this.recommendationService = recommendationService;
    }

    public void calculateRecommendation() {
        for(User user: users.values()) {
            recommendationService.calculateRecommendation(librarySongs,user);
        }
    }

    public List<Song> getTopKRecommendations(String userId, int k) {
        User user = users.get(userId);
        TreeSet<Pair> recommendationList = (TreeSet<Pair>) user.getRecommendationList();
        System.out.println(recommendationList.size());
        List<Song> finalRecommendations = new ArrayList<>();
        Iterator<Pair> iterator = recommendationList.iterator();

//        while(iterator.hasNext()) {
//              finalRecommendations.add(iterator.next().getSong());
//        }

        while(iterator.hasNext() && finalRecommendations.size()<k) {
            finalRecommendations.add(iterator.next().getSong());
        }

        return finalRecommendations;
    }
}
