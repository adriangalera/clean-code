package no.nerdschool.parceltracker;

import no.nerdschool.parceltracker.events.ParcelStatus;

public interface ParcelTracker {
    public String getParcelStatusForParcelId(String parcelId);
    public void handleNewParcelStatus(ParcelStatus parcelStatus);
}
