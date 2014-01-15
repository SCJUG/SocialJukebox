package org.scjug.socialjukebox.jukebox;

import org.scjug.socialjukebox.Patron;
import org.scjug.socialjukebox.track.Track;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a {@link Patron}'s request to play a {@link org.scjug.socialjukebox.track.Track} from a Jukebox's {@link org.scjug.socialjukebox.musicprovider.MusicProvider}
 */
public class JukeboxTrackRequest {

    private Patron patron;
    private Track track;
    private JukeboxTrackRequestState currentState;
    private Map<JukeboxTrackRequestState, Date> requestStateHistory;

    public JukeboxTrackRequest(Patron patron, Track track) {
        this.patron = patron;
        this.track = track;
        this.requestStateHistory = new HashMap<JukeboxTrackRequestState, Date>();
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Map<JukeboxTrackRequestState, Date> getRequestStateHistory() {
        return requestStateHistory;
    }

    public void setRequestStateHistory(Map<JukeboxTrackRequestState, Date> requestStateHistory) {
        this.requestStateHistory = requestStateHistory;
    }

    public boolean isQueued() {
        return this.currentState == JukeboxTrackRequestState.QUEUED;
    }

    public boolean isRejected() {
        return this.currentState == JukeboxTrackRequestState.REJECTED;
    }

    public boolean isPlaying() {
        return this.currentState == JukeboxTrackRequestState.PLAYING;
    }

    public boolean isPaused() {
        return this.currentState == JukeboxTrackRequestState.PAUSED;
    }

    public boolean isSkipped() {
        return this.currentState == JukeboxTrackRequestState.SKIPPED;
    }

    public boolean isPlayed() {
        return this.currentState == JukeboxTrackRequestState.PLAYED;
    }

    public boolean hasBeenQueued() {
        return this.requestStateHistory.containsKey(JukeboxTrackRequestState.QUEUED);
    }

    public boolean hasBeenRejected() {
        return this.requestStateHistory.containsKey(JukeboxTrackRequestState.REJECTED);
    }

    public boolean hasBeenPlayed() {
        return this.requestStateHistory.containsKey(JukeboxTrackRequestState.PLAYING) || this.requestStateHistory.containsKey(JukeboxTrackRequestState.PLAYED);
    }

    public boolean hasBeenPaused() {
        return this.requestStateHistory.containsKey(JukeboxTrackRequestState.PAUSED);
    }

    public boolean hasBeenSkipped() {
        return this.requestStateHistory.containsKey(JukeboxTrackRequestState.SKIPPED);
    }

    public Date getQueuedDate() {
        return getDateForState(JukeboxTrackRequestState.QUEUED);
    }

    public Date getRejectedDate() {
        return getDateForState(JukeboxTrackRequestState.REJECTED);
    }

    //TODO: Extend implementation to allow for multiple play timestamps
    public Date getPlayDate() {
        return getDateForState(JukeboxTrackRequestState.PLAYING);
    }

    //TODO: Extend implementation to allow for multiple pause timestamps
    public Date getPauseDate() {
        return getDateForState(JukeboxTrackRequestState.PLAYING);
    }

    public Date getSkippedDate() {
        return getDateForState(JukeboxTrackRequestState.SKIPPED);
    }

    public Date getPlayedDate() {
        return getDateForState(JukeboxTrackRequestState.PLAYED);
    }

    /**
     * Helper method to find applicable timestamp for state if recorded
     *
     * @param state
     * @return Timestamp if found, otherwise null
     */
    protected Date getDateForState(JukeboxTrackRequestState state) {
        if (this.requestStateHistory.containsKey(state)) {
            return this.requestStateHistory.get(state);
        }

        return null;
    }

    /**
     * Exposes if a request has transitioned through from a particular state
     *
     * @param state
     * @return
     */
    public boolean hasState(JukeboxTrackRequestState state) {
        return this.requestStateHistory.containsKey(state);
    }

    /**
     * Updates the current state of the request and records its change with a timestamp.
     * <p/>
     * TODO:
     * Consider adding hooks to enforce a FSM which has the following rules:
     * - Initial state is QUEUED
     * - Transitions are QUEUED to REJECTED OR PLAYING, PLAYING to PAUSED, SKIPPED OR PLAYED, PAUSED TO SKIPPED OR PLAYING
     * - REJECTED, SKIPPED, AND PLAYED are final states
     * - Timestamps for each transition must be older than the last transition timestamp
     * - No duplicate keys allowed
     *
     * @param state
     */
    public void updateState(JukeboxTrackRequestState state) {
        this.currentState = state;
        this.requestStateHistory.put(state, new Date());
    }
}
