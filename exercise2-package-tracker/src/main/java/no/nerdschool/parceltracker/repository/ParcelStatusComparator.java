package no.nerdschool.parceltracker.repository;

import java.util.Comparator;
import no.nerdschool.parceltracker.events.ParcelStatus;

public class ParcelStatusComparator implements Comparator<ParcelStatus> {

    @Override
    public int compare(ParcelStatus o1, ParcelStatus o2) {
        return o1.getTimestamp().compareTo(o2.getTimestamp());
    }
}
