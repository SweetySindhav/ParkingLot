package Services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Vehicle;

public class ParkingServiceTest {
	private ParkingService parkingService = new ParkingServiceImpl() ;
	private String vehicleColorWhite = "White";
	private String VehicleRegistrationNo = "GJ-04-AY-1111";
	private Vehicle vehicle = new Vehicle(VehicleRegistrationNo, vehicleColorWhite);
	
	@Test
	public void getParkingDetails_whenVehicleGotPark_parkVehicle() {
		parkingService.createParkinglot(1);
		String actualValue = "Allocated Slot Number: 1";
		String expectedValue = parkingService.parkVehicle(vehicle);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test   
	public void getParkingDetailsNotFound_whenSlotNotAvailable_parkVehicle() {
		parkingService.createParkinglot(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "Sorry, parking lot is full";
		String expectedValue = parkingService.parkVehicle(vehicle);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getSlotNumberFromRegistrationNo_findSlotNumberFromRegistrationNo() {
		parkingService.createParkinglot(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "1";
		String expectedValue = parkingService.findSlotNumberFromRegistrationNo(VehicleRegistrationNo);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getNotFoundMessageFromRegistrationNo_whenVehicleNotFound_findSlotNumberFromRegistrationNo() {
		String actualValue = "Not Found!";
		String expectedValue = parkingService.findSlotNumberFromRegistrationNo("GJ-13-AY-1111");
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getEmptySlotNumber_leaveVehicle_leaveVehicle() {
		parkingService.createParkinglot(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "Slot 1 is free";
		String expectedValue = parkingService.leaveVehicle(1);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getMessageWhenSlotNumberNotFound_leaveVehicle() {
		String actualValue = "2 Slot not found!";
		String expectedValue = parkingService.leaveVehicle(2);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getRegistrationNumberWithvehicleColor_findAllRegistrationNumberForCarsWithColor() {
		parkingService.createParkinglot(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = VehicleRegistrationNo;
		String expectedValue = parkingService.findAllRegistrationNumberForCarsWithColor(vehicleColorWhite);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getRegistrationNumberAsEmpty_whenvehicleColorNotFound_findAllRegistrationNumberForCarsWithColor() {
		parkingService.createParkinglot(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "Not Found!";
		String expectedValue = parkingService.findAllRegistrationNumberForCarsWithColor("Black");
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getSlotsNumberWithVehicleColor_findAllSlotNumberForCarsWithColor() {
		parkingService.createParkinglot(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "1";
		String expectedValue = parkingService.findAllSlotNumberForCarsWithColor(vehicleColorWhite);
		assertEquals(expectedValue, actualValue);
	}
	
	@Test
	public void getSlotsNumberIsEmpty_whenVehicleColorNotFound_findAllSlotNumberForCarsWithColor() {
		parkingService.createParkinglot(1);
		parkingService.parkVehicle(vehicle);
		String actualValue = "Not Found!";
		String expectedValue = parkingService.findAllSlotNumberForCarsWithColor("Black");
		assertEquals(expectedValue, actualValue);
	}
}
