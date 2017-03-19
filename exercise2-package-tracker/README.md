# Exercise 2 - Parcel tracker

In this exercise you will be creating a parcel tracker. Some code is provided as a starting point.

# 1. Parcel tracker system description

## Tracker events
There are 3 events in the system:
- `ParcelRegistered` (fields: `parcelId`, `timestamp`, `fromLocation`, `toLocation`)
- `ParcelScanned` (fields: `parcelId`, `timestamp`, `location`)
- `ParcelDelivered` (fields: `parcelId`, `timestamp`, `location`, `status`))

## The `ParcelTracker` interface
The example code contains an interface called `ParcelTracker`:

```java
public interface ParcelTracker {
    public String getParcelStatusForParcelId(String parcelId);
    public void handleNewParcelStatus(ParcelStatus parcelStatus);
}
```

# 2. Assignment

- Create an implementation of the `ParcelTracker` interface
- Extend the `ParcelTracker` to do the following:
    - Assign a unique ID to a parcel
    - Send a parcel from a post office to another.
    - Keep track of status changes (`sent`, `scanned`, `received`)
    - Register several parcel
    - Keep track of delivered parcel 
-  The `getParcelStatusForParcelId` method should return the following depending on parcel status:
    - `ParcelRegistered` => “Parcel is registered”
    - `ParcelScanned` (`location != {To}`) => “Parcel last seen at {Location}”
    - `ParcelScanned` (`location == {To}`) => “Parcel ready to be picked up”
    - `ParcelDelivered` => “Parcel delivered”
- Write tests
- Create a console application that calls your `ParcelTracker` class
