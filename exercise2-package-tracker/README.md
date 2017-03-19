# Exercise 2 - Package tracker

In this exercise you will be creating a package tracker. Some code is provided as a starting point.

# 1. Assignment

## Requirements - the package tracker should:
- Assign a unique ID to a package
- Send a package from a post office to another.
- Keep track of status changes (`sent`, `scanned`, `received`)
- Register several packages
- Keep track of delivered packages

## Tracker events
There are 3 events in the system:
- `ParcelRegistered` (fields: `id`, `timestamp`, `from`, `to`)
- `ParcelScanned` (fields: `id`, `timestamp`, `location`)
- `ParcelReceived` (`id`, `timestamp`)

## Event -> Status mapping
- `ParcelRegistered` => “Parcel is registered”
- `ParcelScanned` (`location != {To}`) => “Parcel last seen at {Location}”
- `ParcelScanned` (`location == {To}`) => “Parcel ready to be picked up”
- `ParcelDelivered` => “Parcel delivered”
