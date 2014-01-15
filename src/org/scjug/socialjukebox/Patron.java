package org.scjug.socialjukebox;

/**
 * A user of a Jukebox who wishes to request and listen to Tracks
 */
public class Patron {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
