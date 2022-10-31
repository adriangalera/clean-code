package no.nerdschool.parceltracker;

public interface ParcelTracker {

    String send(String from, String to);

    void deliver(String parcelId);

    void scan(String parcelId, String location);

    String status(String parcelId);
}
