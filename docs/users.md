# A social jukebox

Create a social experience utilizing online music services for the enjoyment of patrons of public spaces and social gatherings where Patrons can influence the music being played.  

Our application has the following types of users:

## Space Owners
    
Space Owners are users who provide the infrastructure for Patrons to listen to music in a  public space on a virtual Jukebox.  This could be a cafe with a set of bluetooth speakers, a retailer playing music on a PA system, or someone hosting a party who has a home receiver hooked up to an internet enabled device.  Space Owners must have their sound equipment connected to a device which has access to the Internet.  For a Space Owner to use our application with their infrastructure, they should be present at a fixed geographic Location so that Patrons may find them using online map-based technologies.  Space Owners also have the ability to dictate Rules for their Jukebox so that Space Owners can determine the boundaries for whic music can be provided by Patrons.  Space Owners may additionally be required to have a Service Agreement with a Music Provider to create and use a Jukebox.

## Patrons

Patrons are users who benefit from the enjoyment that comes from listening to music at a Location provided by a Space Owner via a Jukebox.  Patrons can find a Jukebox Location using online map-based technologies.  Once a Patron has selected a Location, and we can verify their presence at that location, they should have access to queue Tracks on the Location’s Jukebox.  Patrons select the music to play on a Jukebox using Track choices from a multitude of Music Providers with which they may or may not have a Service Agreement.  When a Patron selects a Track to play on a Jukebox, the Track should be queued in the order in which is was received to be played for other Patrons at a Location.  The acceptance of a Track into the queue of a Jukebox is dependent upon the Rules applied by the Space Owner on their Jukebox.  Once a Track is being played on a Jukebox, Patrons have the ability to Vote on whether they like or dislike the Track being played.  Votes are used to help build Metadata about the commonalities of Tracks played on a Jukebox.  This Metadata can then be used to help Patrons better understand the way other Patrons listen to music on Jukeboxes at different Locations.  Additionally, a Location should provide the ability to notify a Patron of the availability of a Jukebox based on the Patron’s proximity to the Location.

## Music Providers

Music Providers are the online aggregators and organizers of Tracks which can be queued and played in a Jukebox.   A Music Provider may or may not have a Service Agreement with a Patron which dictates the access to its library of Tracks.  A Space Owner may or may not have a Service Agreement with a Music Provider which dictates the ability for a Jukebox to play music to its Patrons.  We’ll also assume that no legalities exist around the playing of Tracks by a Space Owner to Patrons.   A Track from a Music Provider can integrate with a Jukebox if the Music Provider provides an API to access its Tracks and/or play them.

