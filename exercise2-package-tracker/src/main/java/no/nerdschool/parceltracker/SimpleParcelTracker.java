package no.nerdschool.parceltracker;

import java.util.UUID;
import no.nerdschool.parceltracker.events.ParcelDelivered;
import no.nerdschool.parceltracker.events.ParcelRegistered;
import no.nerdschool.parceltracker.events.ParcelScanned;
import no.nerdschool.parceltracker.events.ParcelStatus;
import no.nerdschool.parceltracker.repository.ParcelRepository;

public class SimpleParcelTracker implements ParcelTracker {

    private final ParcelRepository parcelRepository;

    public SimpleParcelTracker(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Override
    public String send(String from, String to) {
        ParcelRegistered parcelRegistered = new ParcelRegistered(newParcelId(), currentTimestamp(), from, to);
        handleNewParcelStatus(parcelRegistered);
        return parcelRegistered.getParcelId();
    }

    @Override
    public void deliver(String parcelId) {
        ParcelDelivered parcelDelivered = new ParcelDelivered(parcelId, currentTimestamp(), "", "");
        handleNewParcelStatus(parcelDelivered);
    }

    @Override
    public void scan(String parcelId, String location) {
        ParcelScanned parcelScanned = new ParcelScanned(parcelId, currentTimestamp(), location);
        handleNewParcelStatus(parcelScanned);
    }

    @Override
    public String status(String parcelId) {
        return status(parcelRepository.getLatestStatus(parcelId));
    }

    public String newParcelId() {
        return UUID.randomUUID().toString();
    }

    public void handleNewParcelStatus(ParcelStatus parcelStatus) {
        parcelRepository.put(parcelStatus);
    }

    private String status(ParcelStatus parcelStatus) {
        if (parcelStatus instanceof ParcelRegistered) {
            return "Parcel is registered";
        }
        if (parcelStatus instanceof ParcelDelivered) {
            return "Parcel delivered";
        }
        if (parcelStatus instanceof ParcelScanned) {
            final String destination = parcelRepository.getDestination(parcelStatus.getParcelId());
            final String scannedLocation = ((ParcelScanned) parcelStatus).getLocation();
            if (scannedLocation.equals(destination)) {
                return "Parcel ready to be picked up";
            } else {
                return "Parcel last seen at " + scannedLocation;
            }
        }

        return null;
    }

    private String currentTimestamp() {
        return Long.toString(System.currentTimeMillis());
    }
}
