package org.scjug.socialjukebox.jukebox;

import java.util.*;

/**
 * Collection of {@link JukeboxTrackRequest} instances which can be queried for sets of like tracks
 */
public class JukeboxTrackRequestList extends AbstractList<JukeboxTrackRequest> {
    private ArrayList<JukeboxTrackRequest> requests;

    public JukeboxTrackRequestList() {
        super();
        this.requests = new ArrayList<JukeboxTrackRequest>();
    }

    @Override
    public JukeboxTrackRequest get(int index) {
        return this.requests.get(index);
    }

    @Override
    public Iterator<JukeboxTrackRequest> iterator() {
        return this.requests.iterator();
    }

    @Override
    public int size() {
        return this.requests.size();
    }

    /**
     * Returns all tracks which have passed through the provided state order of entry into the collection.
     *
     * @param state
     * @return
     */
    public List<JukeboxTrackRequest> filterByState(JukeboxTrackRequestState state) {
        return filterByState(state, SortOrder.NONE);
    }

    /**
     * Returns all tracks which have passed through the provided state in the provided order by the timestamp in which the
     * state transition occurred.
     *
     * @param state
     * @param sortOrder
     * @return
     */
    public List<JukeboxTrackRequest> filterByState(JukeboxTrackRequestState state, SortOrder sortOrder) {
        List<JukeboxTrackRequest> filteredRequests = new ArrayList<JukeboxTrackRequest>();

        //create sub-list of applicable requests
        for (JukeboxTrackRequest request : this.requests) {
            if (request.hasState(state)) {
                filteredRequests.add(request);
            }
        }

        //if we've found matches and the caller wants to sort
        if (filteredRequests.size() > 0 && !SortOrder.NONE.equals(sortOrder)) {
            Collections.sort(filteredRequests, new JukeboxTrackComparator(state, sortOrder));
        }

        return filteredRequests;
    }

    //TODO: Add overloaded implementations which restrict applicable tracks by date range or since X date
    //TODO: Add distribution/aggregation implementations which can generate statistics such as top N tracks, track frequency, patron frequency, etc.

    /**
     * Used to compare requests assuming order by date ascending, or descending, associated with the provided state
     */
    protected class JukeboxTrackComparator implements Comparator<JukeboxTrackRequest> {

        private final JukeboxTrackRequestState state;
        private final SortOrder sortOrder;

        public JukeboxTrackComparator(JukeboxTrackRequestState state, SortOrder sortOrder) {
            this.state = state;
            this.sortOrder = sortOrder;
        }

        @Override
        public int compare(JukeboxTrackRequest request1, JukeboxTrackRequest request2) {
            boolean greaterThan = request1.getDateForState(state).getTime() > request1.getDateForState(state).getTime();
            boolean lessThan = request1.getDateForState(state).getTime() < request1.getDateForState(state).getTime();

            if (SortOrder.BY_DATE_ASC.equals(sortOrder)) {
                return greaterThan ? 1 : (lessThan ? -1 : 0);
            } else {
                return lessThan ? 1 : (greaterThan ? -1 : 0);
            }
        }
    }

    /**
     * Constants to help with how to sort queries to the collection
     */
    public enum SortOrder {
        BY_DATE_DESC,
        BY_DATE_ASC,
        NONE
    }
}
