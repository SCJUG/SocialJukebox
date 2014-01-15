package org.scjug.socialjukebox.jukebox;

/**
 * Possible states a {@link JukeboxTrackRequest} can be in, base on its standing with a Jukebox's {@link JukeboxTrackRequestList}
 */
public enum JukeboxTrackRequestState {
    // Requested by a Patron, but yet to be played
    QUEUED,
    // Currently playing on the Jukebox
    PLAYING,
    // Began playing on the Jukebox but was then paused to be played or skipped
    PAUSED,
    // Began playing on the Jukebox but was then halted prior to the end of the Track
    SKIPPED,
    // Played to completion on the Jukebox
    PLAYED,
    // Requested by a Patron, but rejected from the Jukebox
    REJECTED
}
