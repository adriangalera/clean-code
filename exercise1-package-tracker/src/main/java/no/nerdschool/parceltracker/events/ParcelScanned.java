package no.nerdschool.parceltracker.events;

public class ParcelScanned extends ParcelStatus {

    private final String location;

    public ParcelScanned(String parcelId, String timestamp, String location) {
        super(parcelId, timestamp);

        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
