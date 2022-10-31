package no.nerdschool.parceltracker;

import no.nerdschool.parceltracker.events.ParcelStatus;

public interface ParcelTracker {

    String newParcelId();

    String sendParcel(String from, String to);

    void deliverParcel(String parcelId);

    void scan(String parcelId, String location);

    String getParcelStatusForParcelId(String parcelId);

    void handleNewParcelStatus(
        ParcelStatus parcelStatus); //TODO: Sounds like this should be internal to the implementation
}
