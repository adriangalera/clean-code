package no.nerdschool.parceltracker.repository;

import no.nerdschool.parceltracker.events.ParcelDelivered;
import org.junit.Assert;
import org.junit.Test;

public class ParcelStatusComparatorTest {

    @Test
    public void shouldCompareDifferentParcelStatus() {
        ParcelDelivered p1 = new ParcelDelivered("", "1", "", "");
        ParcelDelivered p2 = new ParcelDelivered("", "2", "", "");
        Assert.assertEquals(-1, new ParcelStatusComparator().compare(p1, p2));
    }

    @Test
    public void shouldCompareEqualParcelStatus() {
        ParcelDelivered p1 = new ParcelDelivered("", "1", "", "");
        ParcelDelivered p2 = new ParcelDelivered("", "1", "", "");
        Assert.assertEquals(0, new ParcelStatusComparator().compare(p1, p2));
    }
}