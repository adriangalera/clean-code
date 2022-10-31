package no.nerdschool.parceltracker;

import java.util.Date;
import java.util.UUID;
import no.nerdschool.parceltracker.events.ParcelRegistered;
import no.nerdschool.parceltracker.events.ParcelStatus;
import no.nerdschool.parceltracker.repository.ParcelRepository;

public class SimpleParcelTracker implements ParcelTracker {

    private final ParcelRepository parcelRepository;

    public SimpleParcelTracker(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Override
    public String newParcelId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String sendParcel(String from, String to) {
        ParcelRegistered parcelRegistered = new ParcelRegistered(newParcelId(), new Date().toString(), from, to);
        handleNewParcelStatus(parcelRegistered);
        return parcelRegistered.getParcelId();
    }

    @Override
    public String getParcelStatusForParcelId(String parcelId) {
        return status(parcelRepository.getLatestStatus(parcelId));
    }

    @Override
    public void handleNewParcelStatus(ParcelStatus parcelStatus) {
        parcelRepository.put(parcelStatus);
    }

    private String status(ParcelStatus parcelStatus) {
        return parcelStatus.getParcelId();
    }
}
