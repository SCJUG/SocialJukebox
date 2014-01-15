package org.scjug.socialjukebox.musicprovider;

import org.scjug.socialjukebox.SpaceOwner;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the configuration settings captured between the SpaceOwner and the MusicProvider
 */
public class MusicProviderAccount {

    private SpaceOwner spaceOwner;
    private MusicProvider musicProvider;
    private MusicProviderAccountCredentials credentials;

    private Map<String, String> settings;

    public MusicProviderAccount(SpaceOwner spaceOwner, MusicProvider musicProvider) {
        this.spaceOwner = spaceOwner;
        this.musicProvider = musicProvider;
        this.settings = new HashMap<String, String>();
    }

    public SpaceOwner getSpaceOwner() {
        return spaceOwner;
    }

    public void setSpaceOwner(SpaceOwner spaceOwner) {
        this.spaceOwner = spaceOwner;
    }

    public MusicProvider getMusicProvider() {
        return musicProvider;
    }

    public void setMusicProvider(MusicProvider musicProvider) {
        this.musicProvider = musicProvider;
    }

    public MusicProviderAccountCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(MusicProviderAccountCredentials credentials) {
        this.credentials = credentials;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }

    /**
     * Authenticates a SpaceOwner against their MusicProvider.
     * TODO: Implement
     *
     * @return
     */
    public boolean authenticate() {
        //Identify strategy and instantiate
        //Authenticate with the credentials on this account

        return false;
    }
}
