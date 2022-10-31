package no.nerdschool.parceltracker;

import no.nerdschool.parceltracker.events.ParcelDelivered;
import no.nerdschool.parceltracker.events.ParcelStatus;
import no.nerdschool.parceltracker.repository.ParcelRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleParcelTrackerTest {

    private ParcelTracker parcelTracker;
    private ParcelRepository parcelRepository;

    @Before
    public void setUp() {
        parcelRepository = new ParcelRepository();
        parcelTracker = new SimpleParcelTracker(parcelRepository);
    }

    @Test
    public void shouldStoreAndRetrieve() {
        final String parcelId = "id";
        ParcelStatus delivered = new ParcelDelivered(parcelId, "timestamp", "location", "status");
        parcelTracker.handleNewParcelStatus(delivered);
        verifyParcelStatusIsStored(parcelId);
    }

    @Test
    public void shouldGenerateUniqueId() {
        Assert.assertFalse(parcelTracker.newParcelId().isEmpty());
    }

    @Test
    public void shouldRegisterMultipleParcelsWhileTryingToSend() {
        String parcelId = parcelTracker.sendParcel("from", "to");
        verifyParcelStatusIsStored(parcelId);

        String parcelId2 = parcelTracker.sendParcel("from", "to2");
        verifyParcelStatusIsStored(parcelId2);
    }

    @Test
    public void shouldReturnMessageForRegisteredParcel() {
        final String expectedMessage = "Parcel is registered";
        final String parcelId = parcelTracker.sendParcel("from", "to");
        final String message = parcelTracker.getParcelStatusForParcelId(parcelId);
        Assert.assertEquals(expectedMessage, message);
    }

    @Test
    public void shouldReturnMessageForDeliveredParcel() {
        final String expectedMessage = "Parcel delivered";
        final String parcelId = parcelTracker.sendParcel("from", "to");
        parcelTracker.deliverParcel(parcelId);
        final String message = parcelTracker.getParcelStatusForParcelId(parcelId);
        Assert.assertEquals(expectedMessage, message);
    }

    @Test
    public void shouldReturnMessageForParcelScannedInIntermediateLocation() {
        final String intermediateLocation = "intermediate-location";
        final String expectedMessage = "Parcel last seen at " + intermediateLocation;
        final String parcelId = parcelTracker.sendParcel("from", "to");
        parcelTracker.scan(parcelId, intermediateLocation);
        final String message = parcelTracker.getParcelStatusForParcelId(parcelId);
        Assert.assertEquals(expectedMessage, message);
    }

    @Test
    public void shouldReturnMessageForParcelScannedInFinalLocation() {
        final String finalLocation = "final-location";
        final String expectedMessage = "Parcel ready to be picked up";
        final String parcelId = parcelTracker.sendParcel("from", finalLocation);
        parcelTracker.scan(parcelId, finalLocation);
        final String message = parcelTracker.getParcelStatusForParcelId(parcelId);
        Assert.assertEquals(expectedMessage, message);
    }

    private void verifyParcelStatusIsStored(String parcelId) {
        Assert.assertNotNull(parcelRepository.getLatestStatus(parcelId));
    }
}