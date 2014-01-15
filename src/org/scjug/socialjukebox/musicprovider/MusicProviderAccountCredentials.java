package org.scjug.socialjukebox.musicprovider;

/**
 * Represents a SpaceOwner's credentials with their MusicProvider
 */
public interface MusicProviderAccountCredentials {

    public String getUsername();

    public AuthenticationType getAuthenticationType();

    public enum AuthenticationType {
        BASIC_AUTH,
        OAUTH
    }
}
