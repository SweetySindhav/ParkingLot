package DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import exception.ParkingSlotNotAvailableException;
import exception.SlotNotFoundException;
import model.Vehicle;

public class ParkingDAOImpl implements ParkingDAO{
	public static Map<Integer, Vehicle> parkingDetailsMap = new HashMap<>();
	public static TreeSet<Integer> availableParkingSlots = new TreeSet<>();
	
	public void createParkinglot(Integer NoOfParkingSlot) {
		for (int i = 0; i < NoOfParkingSlot; i++) {
			parkingDetailsMap.put(i+1, null); 
			availableParkingSlots.add(i+1);
		}
	}

	@Override
	public Integer parkVehicle(Vehicle vehicle) throws ParkingSlotNotAvailableException {
		if(availableParkingSlots.isEmpty()) {
			throw new ParkingSlotNotAvailableException("Sorry, parking lot is full");
		}else {
			final int emptyParking = availableParkingSlots.pollFirst();
			parkingDetailsMap.put(emptyParking, vehicle);
			return emptyParking;
		}
	}

	@Override
	public Integer findSlotNumberFromRegistrationNo(String registrationNo) {
		int slotNumber = 0;
		for (Map.Entry<Integer, Vehicle> entry : parkingDetailsMap.entrySet()) {
			if (entry.getValue() != null && entry.getValue().getRegistrationNo().equalsIgnoreCase(registrationNo)) {
				return entry.getKey();
			}
		}
		return slotNumber;
	}

	@Override
	public List<Integer> findAllSlotNumberForCarsWithColor(String color) {
		List<Integer> slotList = new ArrayList<>();
		for (Map.Entry<Integer, Vehicle> entry : parkingDetailsMap.entrySet()) {
			if (entry.getValue() != null && entry.getValue().getColor().equalsIgnoreCase(color)) {
				slotList.add(entry.getKey());
			}
		}
		return slotList;
	}

	@Override
	public List<String> findAllRegistrationNumberForCarsWithColor(String color) {
		List<String> registrationNoList = new ArrayList<>();
		for (Map.Entry<Integer, Vehicle> entry : parkingDetailsMap.entrySet()) {
			if (entry.getValue() 
					!= null && entry.getValue().getColor().equalsIgnoreCase(color)) {
				registrationNoList.add(entry.getValue().getRegistrationNo());
			}
		}
		return registrationNoList;
	}

	@Override
	public String leaveVehicle(Integer slotNumber) throws SlotNotFoundException {
		for (Map.Entry<Integer, Vehicle> entry : parkingDetailsMap.entrySet()) {
			if(entry.getKey() == slotNumber) {
				parkingDetailsMap.put(entry.getKey(), null);
				availableParkingSlots.add(slotNumber);
				return "Slot " + slotNumber + " is free";
			}
		}
		throw new SlotNotFoundException(slotNumber + " Slot not found!");
	}

	@Override
	public Set<Entry<Integer, Vehicle>> parkingStatus() {
		Set<Entry<Integer, Vehicle>> entry =  parkingDetailsMap.entrySet();
		return entry;
	}	
}
