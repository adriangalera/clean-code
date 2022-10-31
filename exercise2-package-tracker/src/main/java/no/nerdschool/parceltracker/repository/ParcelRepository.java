package no.nerdschool.parceltracker.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import no.nerdschool.parceltracker.events.ParcelRegistered;
import no.nerdschool.parceltracker.events.ParcelStatus;

public class ParcelRepository {

    private final Map<String, List<ParcelStatus>> parcelStatusMap = new HashMap<>();

    public void put(ParcelStatus parcelStatus) {
        final String parcelId = parcelStatus.getParcelId();
        final List<ParcelStatus> statuses = parcelStatusMap.getOrDefault(parcelId, new ArrayList<>());
        statuses.add(parcelStatus);
        statuses.sort(new ParcelStatusComparator());
        parcelStatusMap.put(parcelId, statuses);
    }

    public ParcelStatus getLatestStatus(String parcelId) {
        final List<ParcelStatus> statuses = parcelStatusMap.getOrDefault(parcelId, new ArrayList<>());
        if (!statuses.isEmpty()) {
            return statuses.get(statuses.size() - 1);
        }
        return null;
    }

    public List<ParcelStatus> getAllStatuses(String parcelId) {
        return parcelStatusMap.getOrDefault(parcelId, new ArrayList<>());
    }

    public String getDestination(String parcelId) {
        ParcelRegistered parcelRegistered = (ParcelRegistered) getAllStatuses(parcelId).stream()
            .filter(s -> s instanceof ParcelRegistered)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Parcel is not registered"));
        return parcelRegistered.getToLocation();
    }
}
