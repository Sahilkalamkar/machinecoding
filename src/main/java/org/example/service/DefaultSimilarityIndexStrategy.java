package org.example.service;

import org.example.models.Song;

public class DefaultSimilarityIndexStrategy implements ISongSimilarityStrategy {

    @Override
    public double getSimilarityIndex(Song a, Song b) {
        int sameAttributeCount = 0;
        sameAttributeCount += (a.getName().equals(b.getName())) ? 1 : 0;
        sameAttributeCount += (a.getSinger().equals(b.getSinger())) ? 1 : 0;
        sameAttributeCount += (a.getGenre().equals(b.getGenre())) ? 1 : 0;
        sameAttributeCount += (a.getTempo()==b.getTempo()) ? 1 : 0;

        return (double)sameAttributeCount/4.0;
    }
}
