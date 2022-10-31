package no.nerdschool.parceltracker;

import no.nerdschool.parceltracker.events.ParcelDelivered;
import no.nerdschool.parceltracker.events.ParcelStatus;
import no.nerdschool.parceltracker.repository.ParcelRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleParcelTrackerTest {

    private ParcelTracker parcelTracker;

    @Before
    public void setUp() {
        parcelTracker = new SimpleParcelTracker(new ParcelRepository());
    }

    @Test
    public void shouldStoreAndRetrieve() {
        final String parcelId = "id";
        ParcelStatus delivered = new ParcelDelivered(parcelId, "timestamp", "location", "status");
        parcelTracker.handleNewParcelStatus(delivered);
        Assert.assertEquals(parcelId, parcelTracker.getParcelStatusForParcelId(parcelId));
    }

    @Test
    public void shouldGenerateUniqueId() {
        Assert.assertFalse(parcelTracker.newParcelId().isEmpty());
    }

    @Test
    public void shouldRegisterMultipleParcelsWhileTryingToSend() {
        String parcelId = parcelTracker.sendParcel("from", "to");
        Assert.assertEquals(parcelId, parcelTracker.getParcelStatusForParcelId(parcelId));

        String parcelId2 = parcelTracker.sendParcel("from", "to2");
        Assert.assertEquals(parcelId2, parcelTracker.getParcelStatusForParcelId(parcelId2));
    }
}