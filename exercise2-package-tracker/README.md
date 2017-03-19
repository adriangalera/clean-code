# Exercise 2 - Package tracker

In this exercise you will be creating a package tracker. Some code is provided as a starting point.

# 1. Package tracker system

## Tracker events
There are 3 events in the system:
- `ParcelRegistered` (fields: `id`, `timestamp`, `from`, `to`)
- `ParcelScanned` (fields: `id`, `timestamp`, `location`)
- `ParcelReceived` (`id`, `timestamp`)

## The `ParcelTracker` interface
The example code contains an interface called `ParcelTracker`:

```java
public interface ParcelTracker {
    public String getParcelStatusForParcelId(String parcelId);
    public void handleNewParcelStatus(ParcelStatus parcelStatus);
}
```

# 2. Exe

## Exercise
- Create an implementation of the `ParcelTracker` interface and write tests. The `getParcelStatusForParcelId` method should return the following depending on the package status:
    - `ParcelRegistered` => “Parcel is registered”
    - `ParcelScanned` (`location != {To}`) => “Parcel last seen at {Location}”
    - `ParcelScanned` (`location == {To}`) => “Parcel ready to be picked up”
    - `ParcelDelivered` => “Parcel delivered”
- The package tracker should:
    - Assign a unique ID to a package
    - Send a package from a post office to another.
    - Keep track of status changes (`sent`, `scanned`, `received`)
    - Register several packages
    - Keep track of delivered packages 
- Create a console application that calls your `ParcelTracker` class
