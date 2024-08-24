package org.example.service;

import org.example.models.Pair;
import org.example.models.Song;
import org.example.models.User;

import java.util.List;

public class RecommendationService {

    private ISongSimilarityStrategy songSimilarityStrategy;


    public RecommendationService(ISongSimilarityStrategy songSimilarityStrategy) {
        this.songSimilarityStrategy = songSimilarityStrategy;
    }


    public void calculateRecommendation(List<Song> librarySongs, User user) {

        int playListSize = user.getPlayList().size();
        int friendsSize = user.getFriends().size();



        for(Song librarySong: librarySongs) {
            double ups = 0.0;
            double ufps = 0.0;

            if (user.checkIfSongPresentInPlaylist(librarySong)) continue;
            for (Song playListSong : user.getPlayList()) {
                ups = ups + songSimilarityStrategy.getSimilarityIndex(librarySong, playListSong);
            }
            ups = ups/playListSize;

            for(User friend: user.getFriends()) {
                if(friend.checkIfSongPresentInPlaylist(librarySong)) {
                    ufps += 1.0;
                }
            }

            ufps = ufps/friendsSize;

            double uas = ups + ufps;
            Pair currentScorePair = new Pair(uas,librarySong);
            System.out.println("RECO " + user.getId());
            user.addSongInRecommendationList(currentScorePair);
        }
    }



}
