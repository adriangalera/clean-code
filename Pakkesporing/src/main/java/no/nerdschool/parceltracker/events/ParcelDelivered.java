package no.nerdschool.parceltracker.events;

public class ParcelDelivered extends ParcelStatus {

    private final String sted;
    private final String status;

    public ParcelDelivered(String pakkeId, String tidspunkt, String sted, String status) {
        super(pakkeId, tidspunkt);

        this.sted = sted;
        this.status = status;
    }

    public String getSted() {
        return sted;
    }

    public String getStatus() {
        return status;
    }
}
