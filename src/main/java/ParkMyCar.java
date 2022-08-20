import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import model.Vehicle;
import Services.ParkingService;
import Services.ParkingServiceImpl;
import Util.ParkingCommands;
import Util.ParkingUtils;

public class ParkMyCar {

	static ParkingService parkingService = new ParkingServiceImpl();
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		
		int noOfParkingSlots = 0;
		while (true) {
			try {
				System.out.println("create_parking_lot : ");
				noOfParkingSlots = in.nextInt();
				break; 
			} catch (Exception e) {
				in.next();
				System.out.println("Please input correct number.");
			}
		}

		parkingService.createParkinglot(noOfParkingSlots);
		System.out.println("Created a parking lot having " + noOfParkingSlots + " slots");
		
		in.nextLine();
		do {
			getParkingMenuInput(in.nextLine());
		} while (true);
	}
	
	private static void getParkingMenuInput(String input) {
		String[] inputArr = input.split(" ");
		switch (inputArr[0]) {
		case ParkingCommands.PARK: 
			parkVehicle(inputArr);
			break;
		case ParkingCommands.STATUS: // Check Vehicle Status
			checkParkingStatus();
			break;
		case ParkingCommands.LEAVE: // Leave Parking
			if (ParkingUtils.vaidateCommand(inputArr, 2) && ParkingUtils.integerInputValidation(inputArr[1])) {
				String leaveParkingStatus = parkingService.leaveVehicle(Integer.parseInt(inputArr[1]));
				System.out.println(leaveParkingStatus);
			}
			break;
		case ParkingCommands.REGIATRATION_NUMBER_FOR_CARS_WITH_COLOR: // Search Registration Number From Vechile Color
			if (ParkingUtils.vaidateCommand(inputArr, 2)) {
				String allDetails = parkingService.findAllRegistrationNumberForCarsWithColor(inputArr[1]);
				System.out.println(allDetails);
			}
			break;
		case ParkingCommands.SLOT_NUMBERS_FOR_CARS_WITH_COLOR: // Search Slot Number From Vechile Color
			if (ParkingUtils.vaidateCommand(inputArr, 2)) {
				String allSlotNumber = parkingService.findAllSlotNumberForCarsWithColor(inputArr[1]);
				System.out.println(allSlotNumber);
			}
			break;
		case ParkingCommands.SLOT_NUMBER_FOR_REGISTRATION_NUMBER: // Search Slot Number From Registration Number
			if (ParkingUtils.vaidateCommand(inputArr, 2)) {
				String slotNoStatusOfRegNo = parkingService.findSlotNumberFromRegistrationNo(inputArr[1]);
				System.out.println(slotNoStatusOfRegNo);
			}
			break;
		case ParkingCommands.HELP: // Help
			displayCommands();
			break;
		case ParkingCommands.EXIT: // Exit
			System.exit(0);
			break;

		default:
			System.out.println("Invalid Command! Please refer help for commands");
			break;
		}
	}

	private static void displayCommands() {
		System.out.println("\n Commands");
		System.out.println("--------------------------------------");
		System.out.println("park <RegistrationNo> <Color>");
		System.out.println("status");
		System.out.println("leave <Parking lot Number>");
		System.out.println("registration_numbers_for_cars_with_colour <color>");
		System.out.println("slot_numbers_for_cars_with_colour <color>");
		System.out.println("slot_number_for_registration_number <Registration Number>");
		System.out.println("exit\n");
	}

	public static void parkVehicle(String[] vehicleDetailsArr) {
		if (ParkingUtils.vaidateCommand(vehicleDetailsArr, 3)) {
			Vehicle vehicle = new Vehicle(vehicleDetailsArr[1], vehicleDetailsArr[2]);
			String parkVehicleStatus = parkingService.parkVehicle(vehicle);
			System.out.println(parkVehicleStatus);
		}
	}

	public static void checkParkingStatus() {
		Set<Entry<Integer, Vehicle>> parkingDetails = parkingService.parkingStatus();
		System.out.println("Slot No." + "  " + " Reg. No. " + "\t" + " Color");
		
		for (Entry<Integer, Vehicle> e : parkingDetails) {
			if (e.getValue() != null)
				System.out.println(e.getKey() + "\t    " 
									+ e.getValue().getRegistrationNo() 
									+ "\t\t  " + e.getValue().getColor());
		}
	}

}
