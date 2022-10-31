package no.nerdschool.parceltracker;

import no.nerdschool.parceltracker.events.ParcelDelivered;
import no.nerdschool.parceltracker.events.ParcelStatus;
import org.junit.Assert;
import org.junit.Test;

public class SimpleParcelTrackerTest {

    @Test
    public void shouldStoreAndRetrieve() {
        final String parcelId = "id";
        ParcelStatus delivered = new ParcelDelivered(parcelId, "timestamp", "location", "status");
        ParcelTracker parcelTracker = new SimpleParcelTracker();
        parcelTracker.handleNewParcelStatus(delivered);

        Assert.assertEquals(parcelId, parcelTracker.getParcelStatusForParcelId(parcelId));
    }
}