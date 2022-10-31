package no.nerdschool.parceltracker;

import java.util.HashMap;
import java.util.Map;
import no.nerdschool.parceltracker.events.ParcelStatus;

public class SimpleParcelTracker implements ParcelTracker {

    private final Map<String, ParcelStatus> parcelStatusMap = new HashMap<>();

    @Override
    public String getParcelStatusForParcelId(String parcelId) {
        return status(parcelStatusMap.get(parcelId));
    }

    @Override
    public void handleNewParcelStatus(ParcelStatus parcelStatus) {
        parcelStatusMap.put(parcelStatus.getParcelId(), parcelStatus);
    }

    private String status(ParcelStatus parcelStatus) {
        return parcelStatus.getParcelId();
    }
}
