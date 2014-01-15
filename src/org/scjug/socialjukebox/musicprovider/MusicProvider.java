package org.scjug.socialjukebox.musicprovider;

import org.scjug.socialjukebox.track.Track;

import java.util.List;

/**
 * A music provider with which a SpaceOwner has a relationship to authorize the playing of music on a Jukebox and which will
 * allow a Space Owner's Patrons to search for Tracks.
 */
public interface MusicProvider {

    public MusicProviderAccount getAccount();

    /**
     * Uses free form text to query a MusicProvider for a list of applicable tracks
     */
    public List<Track> searchForTracks(String queryString);
}
