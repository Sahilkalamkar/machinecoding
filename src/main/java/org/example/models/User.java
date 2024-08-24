package org.example.models;


import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Getter
public class User {

    private String id;
    private List<User> friends;
    private List<Song> playList;
    private Set<Pair> recommendationList;
    

    public User(final String id,final List<Song> playList) {
        this.id = id;
        this.playList = playList;
        this.recommendationList = this.recommendationList = new TreeSet<>((p1, p2) -> {
            int scoreComparison = Double.compare(p2.getScore(), p1.getScore());
            if (scoreComparison != 0) {
                return scoreComparison;
            }
            // If scores are the same, further compare by Song (or any other criteria)
            return p1.getSong().getName().compareTo(p2.getSong().getName());
        });
    }


    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addSong(final Song song) {
        this.playList.add(song);
    }

    public void addSongInRecommendationList(Pair song) {
        this.recommendationList.add(song);
    }

    public void removeSongFromRecommendationList(Pair song) {
        this.recommendationList.remove(song);
    }

    public boolean checkIfSongPresentInPlaylist(Song song) {
        for(Song playListSong: this.playList) {
            if(playListSong.getName().equals(song.getName())) {
                return true;
            }
        }

        return false;
    }
}
