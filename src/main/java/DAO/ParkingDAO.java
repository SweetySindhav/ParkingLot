package DAO;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import exception.ParkingSlotNotAvailableException;
import exception.SlotNotFoundException;
import model.Vehicle;

public interface ParkingDAO {
	void createParkinglot(Integer NoOfParkingSlot);
	Integer parkVehicle(Vehicle vehicle) throws ParkingSlotNotAvailableException;
	Integer findSlotNumberFromRegistrationNo(String registrationNo);
	List<Integer> findAllSlotNumberForCarsWithColor(String color);
	List<String> findAllRegistrationNumberForCarsWithColor(String color);
	String leaveVehicle(Integer slotNumber) throws SlotNotFoundException;
	Set<Entry<Integer, Vehicle>> parkingStatus();
}

