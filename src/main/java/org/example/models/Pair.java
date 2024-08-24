package org.example.models;


import lombok.Getter;

@Getter
public class Pair implements Comparable<Pair> {
    private double score;
    private Song song;

    public Pair(double score,Song song) {
        this.score = score;
        this.song = song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Double.compare(pair.score, score) == 0 &&
                song.equals(pair.song);
    }

    @Override
    public int hashCode() {
        int result = song.hashCode();
        long temp = Double.doubleToLongBits(score);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


    @Override
    public int compareTo(Pair other) {
        // Compare based on the Song object
        int songComparison = this.song.compareTo(other.song);

        if (songComparison != 0) {
            return songComparison;
        }

        // Optionally, include score in the comparison if needed
        return Double.compare(this.score, other.score);
    }
}
