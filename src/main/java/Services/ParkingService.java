package Services;

import java.util.Map.Entry;
import java.util.Set;

import model.Vehicle;

public interface ParkingService {
	void createParkinglot(Integer NoOfParkingSlot);
	String parkVehicle(Vehicle vehicle);
	String findSlotNumberFromRegistrationNo(String registrationNo);
	String findAllSlotNumberForCarsWithColor(String color);
	String findAllRegistrationNumberForCarsWithColor(String color);
	String leaveVehicle(Integer slotNumber);
	Set<Entry<Integer, Vehicle>> parkingStatus();
}

