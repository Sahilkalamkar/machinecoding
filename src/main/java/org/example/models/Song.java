package org.example.models;

import lombok.Data;
import lombok.Getter;

import java.util.Comparator;
import java.util.Objects;

@Data
@Getter
public class Song implements Comparable<Song> {
    private String name;
    private String singer;
    private String genre;
    private int tempo;

    public Song(final String name,final String singer,final String genre,final int tempo) {
        this.name = name;
        this.genre = genre;
        this.singer = singer;
        this.tempo = tempo;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return tempo == song.tempo &&
                Objects.equals(name, song.name) &&
                Objects.equals(singer, song.singer) &&
                Objects.equals(genre, song.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, singer, genre, tempo);
    }

    @Override
    public int compareTo(Song other) {
        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        int singerComparison = this.singer.compareTo(other.singer);
        if (singerComparison != 0) {
            return singerComparison;
        }

        int genreComparison = this.genre.compareTo(other.genre);
        if (genreComparison != 0) {
            return genreComparison;
        }

        return Integer.compare(this.tempo, other.tempo);
    }
}
