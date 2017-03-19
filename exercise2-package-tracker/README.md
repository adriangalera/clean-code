# Exercise 2 - Package tracker

In this exercise you will be creating a package tracker. Some code is provided as a starting point.

# 1. Assignment

The package tracker should:
- Assign a unique ID to a package
- Send a package from a post office to another.
- Keep track of status changes (`sent`, `scanned`, `received`)
- Register several packages
- Keep track of delivered packages

There are 3 events in the system:
- `ParcelRegistered` (fields: `id`, `timestamp`, `from`, `to`)
- `ParcelScanned` (fields: `id`, `timestamp`, `location`)
- `ParcelReceived` (`id`, `timestamp`)
