package org.scjug.socialjukebox.musicprovider;

/**
 * Abstraction of different approaches to authenticating a SpaceOwner with their MusicProvider
 */
public interface MusicProviderAuthenticationStrategy {

    public boolean authenticate(MusicProviderAccountCredentials credentials);
}
