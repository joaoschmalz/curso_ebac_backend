package org.example.exercises.m17_generics.second;

import org.example.exercises.m17_generics.second.dao.HondaDAO;
import org.example.exercises.m17_generics.second.dao.IHondaDAO;
import org.example.exercises.m17_generics.second.dao.IPeugeoutDAO;
import org.example.exercises.m17_generics.second.dao.PeugeoutDAO;
import org.example.exercises.m17_generics.second.dao.generic.IGenericDAO;
import org.example.exercises.m17_generics.second.domain.Car;
import org.example.exercises.m17_generics.second.enums.BreakType;
import org.example.exercises.m17_generics.second.enums.DirectionType;
import org.example.exercises.m17_generics.second.enums.FuelType;
import org.example.exercises.m17_generics.second.factory.CarFactory;
import org.example.exercises.m17_generics.second.factory.Factory;
import org.example.exercises.m17_generics.second.factory.IFactory;

import java.util.Scanner;

public class App {

  private final static IHondaDAO iHondaDAO = new HondaDAO();
  private final static IPeugeoutDAO iPeugeoutDAO = new PeugeoutDAO();

  public static void main(String[] args) {

    Scanner s = new Scanner(System.in);
    boolean isInvalid = true;

    String car = "";
    int option;

    do {
      System.out.println("Enter 1 to save an Honda | 2 to save a Civic");
      option = s.nextInt();

      if (option == 1) {
        car = requestProperties(option);
        isInvalid = checkEnumProperties(car);
      }
      if (option == 2) {
        car = requestProperties(option);
        isInvalid = checkEnumProperties(car);
      }
      else {
        System.out.println("Invalid option");
      }
    } while (isInvalid);

    save(option, car.split(","));
  }

  private static String requestProperties(final int option) {
    Scanner s = new Scanner(System.in);

    initialInstructions();
    baseCar();

    if(option == 1) {
      System.out.println("VTEC \n"
          + "Magic Seat \n");
    } else {
      System.out.println("Icokpit");
    }

    return s.nextLine();
  }

  private static boolean checkEnumProperties(final String car) {

    final String[] properties = car.split(",");
    final boolean isValidDirection =  EnumUtils.isValidEnum(DirectionType.class, properties[2]);
    final boolean isValidBreak = EnumUtils.isValidEnum(BreakType.class, properties[3]);
    final boolean isValidFuel = EnumUtils.isValidEnum(FuelType.class, properties[4]);

    if(isValidDirection && isValidBreak && isValidFuel) {
      return false;
    }

    if (!isValidDirection) System.out.println("Invalid direction sent");
    if (!isValidBreak) System.out.println("Invalid break sent");
    if (!isValidFuel) System.out.println("Invalid fuel sent");

    return true;
  }

  private static void save(final int option, final String[] properties) {
    final Car entity = buildEntity(option, properties);
    final Boolean saved = getDAO(option).save(entity);

    if (saved) {
      System.out.println("Car successfully save");
    } else {
      System.out.println("There was an error saving the car");
    }

  }

  private static Car buildEntity(final int option, final String[] properties) {
    final IFactory factory = new Factory();
    final CarFactory carFactory = factory.createFactory(option);
    return carFactory.buildEntity(properties);
  }

  public static IGenericDAO getDAO(final int option) {
    if(option == 1) return iHondaDAO;
    return iPeugeoutDAO;
  }

  private static void initialInstructions() {
    System.out.println("Enter properties separated by commas to save: ");
  }

  private static void baseCar() {
    System.out.println("Code \n"
                     + "Model \n"
                     + "Direction Type: Manual, Electric, Hydraulic \n"
                     + "Break: Standard, ABS \n"
                     + "Fuel: Gas, Ethanol, Electric, Hybrid \n"
                     + "Fabrication Year \n"
                     + "Horse Power \n"
                     + "Fuel Capacity \n"
                     + "Max Speed");
  }
}
