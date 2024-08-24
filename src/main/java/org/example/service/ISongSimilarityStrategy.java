package org.example.service;

import org.example.models.Song;

public interface ISongSimilarityStrategy {

    double getSimilarityIndex(Song a,Song b);
}

