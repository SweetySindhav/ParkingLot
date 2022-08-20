package model;

public class Slot {
  private Vehicle vehicle;
  private Integer slotNumber;

  public Slot(final Integer slotNumber) {
    this.slotNumber = slotNumber;
  }

  public Integer getSlotNumber() {
    return slotNumber;
  }

  public Vehicle getParkedVehicle() {
    return vehicle;
  }

  public boolean isSlotFree() {
    return vehicle == null;
  }

  public void parkVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }

  public void unparkVehicle() {
    this.vehicle = null;
  }
}
