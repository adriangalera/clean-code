package no.nerdschool.parceltracker.repository;

import java.util.List;
import java.util.UUID;
import no.nerdschool.parceltracker.events.ParcelRegistered;
import no.nerdschool.parceltracker.events.ParcelScanned;
import no.nerdschool.parceltracker.events.ParcelStatus;
import org.junit.Assert;
import org.junit.Test;

public class ParcelRepositoryTest {

    private final ParcelRepository parcelRepository = new ParcelRepository();

    @Test
    public void shouldPutAndRetrieveLatestStatus() {
        final String parcelId = UUID.randomUUID().toString();
        final ParcelStatus registered = new ParcelRegistered(parcelId, "1", "", "");
        parcelRepository.put(registered);

        Assert.assertTrue(parcelRepository.getLatestStatus(parcelId) instanceof ParcelRegistered);
    }

    @Test
    public void shouldPutAndRetrieveAllStatuses() {
        final String parcelId = UUID.randomUUID().toString();
        final ParcelStatus registered = new ParcelRegistered(parcelId, "1", "", "");
        parcelRepository.put(registered);
        final ParcelStatus scanned = new ParcelScanned(parcelId, "2", "");
        parcelRepository.put(scanned);

        List<ParcelStatus> parcelStatuses = parcelRepository.getAllStatuses(parcelId);
        Assert.assertEquals(2, parcelStatuses.size());
        Assert.assertTrue(parcelStatuses.get(0) instanceof ParcelRegistered);
        Assert.assertTrue(parcelStatuses.get(1) instanceof ParcelScanned);
    }
}