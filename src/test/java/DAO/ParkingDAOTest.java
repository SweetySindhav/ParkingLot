package DAO;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import exception.ParkingSlotNotAvailableException;
import exception.SlotNotFoundException;
import model.Vehicle;

public class ParkingDAOTest {

	private ParkingDAO parkingDAO = new ParkingDAOImpl();
	private String vehicleColorWhite = "White";
	private String vehicleColorBlack = "Black";
	private String VehicleRegistrationNo = "GJ-AVS-123";

	@Test
	public void createParkinglotUsingNoOfParking() {
		parkingDAO.createParkinglot(1);
	}

	@Test
	public void getStoredVehicleWhenParkingSlotsisEmpty() throws ParkingSlotNotAvailableException {
		parkingDAO.createParkinglot(1);
		Integer actualValue = 1;
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		Integer expectedValue = parkingDAO.parkVehicle(vehicle);
		assertEquals(expectedValue, actualValue);
	}

	@Test(expected = ParkingSlotNotAvailableException.class)
	public void getExceptionWhenParkingLotsIsNotEmpty() throws ParkingSlotNotAvailableException {
		parkingDAO.createParkinglot(1);
		Vehicle vehicle1 = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		parkingDAO.parkVehicle(vehicle1);

		Vehicle vehicle2 = new Vehicle("REG-BLR-456", vehicleColorBlack);
		parkingDAO.parkVehicle(vehicle2);
	}

	@Test
	public void getSlotNumberWhenVechicleIsInPark_findSlotNumberFromRegistrationNo()
			throws ParkingSlotNotAvailableException {
		parkingDAO.createParkinglot(1);
		Integer actualValue = 1;
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		parkingDAO.parkVehicle(vehicle);
		Integer slotNumber = parkingDAO.findSlotNumberFromRegistrationNo(VehicleRegistrationNo);
		assertEquals(slotNumber, actualValue);
	}

	@Test
	public void getZeroSlotNumberWhenVechileIsNotInPark_findSlotNumberFromRegistrationNo() {
		Integer actualValue = 0;
		String registrationNo = "BLR-REG-123";
		Integer slotNumber = parkingDAO.findSlotNumberFromRegistrationNo(registrationNo);
		assertEquals(slotNumber, actualValue);
	}

	@Test
	public void getAllSlotsNumberForCarColor_findAllSlotNumberForCarsWithColor()
			throws ParkingSlotNotAvailableException {
		parkingDAO.createParkinglot(1);
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		parkingDAO.parkVehicle(vehicle);
		List<Integer> actualValue = new ArrayList<Integer>();
		actualValue.add(1);
		List<Integer> expectedValue = parkingDAO.findAllSlotNumberForCarsWithColor(vehicleColorWhite);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void getEmptySlotsNumberForCarColor_WhenVeichleColorNotFound_findAllSlotNumberForCarsWithColor()
			throws ParkingSlotNotAvailableException {
		parkingDAO.createParkinglot(1);
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorBlack);
		parkingDAO.parkVehicle(vehicle);
		List<Integer> actualValue = new ArrayList<Integer>();
		List<Integer> expectedValue = parkingDAO.findAllSlotNumberForCarsWithColor(vehicleColorWhite);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void getAllRegistrationNoForCarFromColor_findAllRegistrationNumberForCarsWithColor()
			throws ParkingSlotNotAvailableException {
		parkingDAO.createParkinglot(1);
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		parkingDAO.parkVehicle(vehicle);
		List<String> actualValue = new ArrayList<>();
		actualValue.add(VehicleRegistrationNo);
		List<String> expectedValue = parkingDAO.findAllRegistrationNumberForCarsWithColor(vehicleColorWhite);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void getEmptyRegistrationNoForCarFromColor_whenCarNotFound_findAllRegistrationNumberForCarsWithColor()
			throws ParkingSlotNotAvailableException {
		parkingDAO.createParkinglot(1);
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		parkingDAO.parkVehicle(vehicle);
		List<String> actualValue = new ArrayList<>();
		List<String> expectedValue = parkingDAO.findAllRegistrationNumberForCarsWithColor(vehicleColorBlack);
		assertEquals(expectedValue, actualValue);
	}

	@Test
	public void leaveVehicle_whenCarIsAvailable() throws ParkingSlotNotAvailableException, SlotNotFoundException {
		parkingDAO.createParkinglot(1);
		Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
		parkingDAO.parkVehicle(vehicle);
		String actualValue = "Slot 1 is free";
		String expectedValue =  parkingDAO.leaveVehicle(1);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test(expected = SlotNotFoundException.class)
	public void getMessageNotFoundAtleaveVehicle_whenCarIsNotAvailable() throws SlotNotFoundException {
		String actualValue = "3 Slot not found!";
		String expectedValue =  parkingDAO.leaveVehicle(3);
		assertEquals(expectedValue, actualValue);
	}
}

