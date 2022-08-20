package Services;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import DAO.ParkingDAO;
import DAO.ParkingDAOImpl;
import exception.ParkingSlotNotAvailableException;
import exception.SlotNotFoundException;
import model.Vehicle;
import Util.ParkingUtils;

public class ParkingServiceImpl implements ParkingService {

	private ParkingDAO parkingDAO = new ParkingDAOImpl();

	@Override
	public void createParkinglot(Integer NoOfParkingSlot) {
		parkingDAO.createParkinglot(NoOfParkingSlot);
	}

	@Override
	public String parkVehicle(Vehicle vehicle) {
		Integer parkingLotNumber;
		try {
			parkingLotNumber = parkingDAO.parkVehicle(vehicle);
			return "Allocated Slot Number: " + parkingLotNumber;
		} catch (ParkingSlotNotAvailableException e) {
			return "Sorry, parking lot is full";
		}
	}

	@Override
	public String findSlotNumberFromRegistrationNo(String registrationNo) {
		int slotNumber = parkingDAO.findSlotNumberFromRegistrationNo(registrationNo);
		return slotNumber == 0 ? "Not Found!" : Integer.toString(slotNumber);
	}

	@Override
	public String findAllSlotNumberForCarsWithColor(String color) {
		List<Integer> slotNumberList = parkingDAO.findAllSlotNumberForCarsWithColor(color);
		if(slotNumberList.size() != 0 ) {
			return ParkingUtils.convertListToString(slotNumberList);
			
		}
		return "Not Found!";
	}

	@Override
	public String findAllRegistrationNumberForCarsWithColor(String color) {
		List<String> allRegistrationNo = parkingDAO.findAllRegistrationNumberForCarsWithColor(color);
		if(allRegistrationNo.size() != 0) {
			return ParkingUtils.convertListToString(allRegistrationNo);
		}
		return "Not Found!";		
	}

	@Override
	public String leaveVehicle(Integer slotNumber) {
		try {
			return parkingDAO.leaveVehicle(slotNumber);
		} catch (SlotNotFoundException e) {
			return e.getMessage();
		}
	}

	@Override
	public Set<Entry<Integer, Vehicle>> parkingStatus() {
		return parkingDAO.parkingStatus();
	}

}
