package org.scjug.socialjukebox.jukebox;

import org.scjug.socialjukebox.SpaceOwner;
import org.scjug.socialjukebox.musicprovider.MusicProvider;

import java.util.Date;

/**
 * Represents a relationship between a SpaceOwner's {@link org.scjug.socialjukebox.musicprovider.MusicProvider},
 * their geographic {@link Location}, control of requests to play music from Patrons, as well as the history of
 * those play requests.
 */
public class Jukebox {

    private String name;
    private SpaceOwner spaceOwner;
    private Location location;
    private MusicProvider musicProvider;
    private JukeboxTrackRequestList trackRequests;

    private int currentTrackIndex = 0;

    public Jukebox(String name, SpaceOwner spaceOwner, Location location, MusicProvider musicProvider) {
        this.name = name;
        this.spaceOwner = spaceOwner;
        this.location = location;
        this.musicProvider = musicProvider;
        this.trackRequests = new JukeboxTrackRequestList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpaceOwner getSpaceOwner() {
        return spaceOwner;
    }

    public void setSpaceOwner(SpaceOwner spaceOwner) {
        this.spaceOwner = spaceOwner;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public MusicProvider getMusicProvider() {
        return musicProvider;
    }

    public void setMusicProvider(MusicProvider musicProvider) {
        this.musicProvider = musicProvider;
    }

    public JukeboxTrackRequestList getTrackRequests() {
        return trackRequests;
    }

    public void setTrackRequests(JukeboxTrackRequestList trackRequests) {
        this.trackRequests = trackRequests;
    }

    public JukeboxTrackRequest getCurrentTrackRequest() {
        return this.trackRequests.get(currentTrackIndex);
    }

    /**
     * Appends a request to the end of the list
     *
     * @param request The request to queue
     */
    public void queue(JukeboxTrackRequest request) {
        request.updateState(JukeboxTrackRequestState.QUEUED);
        this.trackRequests.add(request);
    }

    /**
     * Used to reject a track which is queued that the needle isn't pointing to
     *
     * @param requestIndex
     * @return The track that has been rejected
     */
    public JukeboxTrackRequest reject(int requestIndex) {
        // we can't reject the current track since the needle is pointing at it
        if (currentTrackIndex == requestIndex) {
            return null;
        }

        // does the requested track even exist?
        if (currentTrackIndex < this.trackRequests.size()) {
            JukeboxTrackRequest trackToReject = this.trackRequests.get(currentTrackIndex);

            //if the track isn't queued, we can't reject it
            if (!trackToReject.isQueued()) {
                return null;
            }

            //otherwise, reject it
            trackToReject.updateState(JukeboxTrackRequestState.REJECTED);

            return trackToReject;
        }

        //index was invalid
        return null;
    }

    /**
     * Marks the previous song as played or skipped, if necessary, moves the needle forward, and marks the next song as playing
     *
     * @return track request now playing
     */
    //TODO: Integrate the use of track lengths into the Jukebox so it can make smarter decisions about changing the state of requests when playing songs
    public JukeboxTrackRequest playNext() {
        // if the needle isn't on the first song
        if (currentTrackIndex > 0) {
            JukeboxTrackRequest maybePlaying = this.trackRequests.get(currentTrackIndex - 1);

            // mark the last song as played, if it was playing
            if (maybePlaying.isPlaying()) {
                maybePlaying.updateState(JukeboxTrackRequestState.PLAYED);
            }
            // mark the last song as skipped if it was paused
            else if (maybePlaying.isPaused()) {
                maybePlaying.updateState(JukeboxTrackRequestState.SKIPPED);
            }
        }

        //Move the needle forward one track and play it
        currentTrackIndex++;
        return play();
    }

    /**
     * Used to play a track which is queued which the needle is pointing to
     *
     * @return The track now playing
     */
    public JukeboxTrackRequest play() {
        JukeboxTrackRequest currentTrack = this.trackRequests.get(currentTrackIndex);

        //if the current track isn't queued, we can't play it
        if (!currentTrack.isQueued()) {
            return null;
        }

        //otherwise, play it
        currentTrack.updateState(JukeboxTrackRequestState.PLAYING);

        return currentTrack;
    }

    /**
     * Used to pause the current playing track that the needle is pointing to
     *
     * @return The track now paused
     */
    public JukeboxTrackRequest pause() {
        JukeboxTrackRequest currentTrack = this.trackRequests.get(currentTrackIndex);

        //if the current track isn't playing, we can't pause it
        if (!currentTrack.isPlaying()) {
            return null;
        }

        //otherwise, pause it
        currentTrack.updateState(JukeboxTrackRequestState.PAUSED);

        return currentTrack;
    }

    /**
     * Used to resume playing a paused track that the needle is pointing to
     *
     * @return The track request that has been resumed
     */
    public JukeboxTrackRequest resume() {
        JukeboxTrackRequest currentTrack = this.trackRequests.get(currentTrackIndex);

        //if the current track isn't playing, we can't pause it
        if (!currentTrack.isPaused()) {
            return null;
        }

        //otherwise, start it back up
        currentTrack.updateState(JukeboxTrackRequestState.PLAYING);

        return currentTrack;
    }

    /**
     * Used to skip a playing or paused track that the needle is pointing to
     *
     * @return
     */
    public JukeboxTrackRequest skip() {
        JukeboxTrackRequest currentTrack = this.trackRequests.get(currentTrackIndex);

        //if the current track isn't playing, we can't pause it
        if (!currentTrack.isPaused() && !currentTrack.isPlaying()) {
            return null;
        }

        //otherwise, skip it
        currentTrack.updateState(JukeboxTrackRequestState.SKIPPED);

        return currentTrack;
    }

    //TODO: Add conveience methods to expose common statistics such as top 50 tracks, most loyal patron, top 10 rejected songs, top 25 skipped tracks, skip/pause rates, etc.
}
