package org.scjug.socialjukebox.track;

import org.scjug.socialjukebox.musicprovider.MusicProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an aggregation of metadata associated with a Track from a specific MusicProvider
 */
public class Track {

    private MusicProvider musicProvider;
    private String name;
    private String logoUrl;
    private int length;
    private Map<Metadata, List<String>> metadata;

    public Track(MusicProvider musicProvider) {
        this.musicProvider = musicProvider;
        this.metadata = new HashMap<Metadata, List<String>>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void addMetadata(Metadata key, String value) {
        if (!this.metadata.containsKey(key)) {
            this.metadata.put(key, new ArrayList<String>());
        }

        this.metadata.get(key).add(value);
    }

    public Map<Metadata, List<String>> getMetadata() {
        return metadata;
    }

    /**
     * Possible metadata categories to store when creating a snapshot of Track metadata from a MusicProvider
     */
    public enum Metadata {
        GENRE,
        ARTIST,
        ALBUM,
        RECORD_LABEL
    }
}
