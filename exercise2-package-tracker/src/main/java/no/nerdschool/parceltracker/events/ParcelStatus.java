package no.nerdschool.parceltracker.events;

public abstract class ParcelStatus {

    private final String parcelId;
    private final String timestamp;

    public ParcelStatus(String parcelId, String timestamp) {
        this.parcelId = parcelId;
        this.timestamp = timestamp;
    }

    public String getParcelId() {
        return parcelId;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
