/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline.data;

import airline.employees.Pilot;
import airline.flights.Location;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class contains methods used to validate user input such as date, time, 
 * int, int with defined range, compare cities and check pilot availability and rating.
 * @author Gustavo Lessa
 * @author Rafael Barros
 */
public class DataValidation {
    
    /**
     * This method checks if the given pilot is allowed to fly a given airplane,
     * it checks the pilot availability and then its rating against the airplane capacicy.
     * @param p Pilot to be checked.
     * @param capacity capacity to be checked.
     * @return True if the pilot is Allowed to fly the airplane, false otherwise.
     */
    public boolean isAllowed(Pilot p, int capacity){
        if(p.isAssigned() || p.getRating().getPassengers() < capacity) return false;
        else return true;
    }
    
    /**
     * This method checks if the input is a integer.
     * @param input (boolean)
     * @return -1 if the input is not a integer
     */
    public int checkForInt(Scanner input){
        try{return input.nextInt();}
        catch(InputMismatchException e){
            input.next();
            System.out.println("\n*** Input is not a integer. Please try again. ***\n");
            return -1;
        }
    }
 
    /**
     * This method checks for a positive answer from user.
     * @param input (Scanner)
     * @return true if the input is 'Y' or 'y' and false otherwise
     */
    public boolean checkForYes(Scanner input){
        String answer = "";
        try{
            while(answer.isEmpty()){
                answer = input.nextLine();
            }
            if(answer.startsWith("y") || answer.startsWith("Y")){
                return true;
            } else {
                return false;
            }
            } catch(InputMismatchException e){
            System.out.println("\n*** Input is not a integer. Please try again. ***\n");
            return checkForYes(input);
        }
    }
    
    /**
     * This method checks for a integer input between a specific boundary.
     * @param input (Scanner)
     * @param lowerBoundary (int)
     * @param upperBoundary (int)
     * @return A validated integer between the specified boundary.
     */
    public int checkForInt(Scanner input, int lowerBoundary, int upperBoundary){
        int typedInt = 0;
        try{
            while(typedInt == 0){
                typedInt = input.nextInt();
            }
            if(typedInt>=lowerBoundary && typedInt<=upperBoundary){
                return typedInt;
            }else{
                System.out.println("\n*** The option should be and integer between "+
                        lowerBoundary+" and "+upperBoundary+". ***\n");
                System.out.println("Please try again:");
                return checkForInt(input, lowerBoundary,upperBoundary);
            }
        } catch(InputMismatchException e){
            System.out.println("\n*** Input is not an integer. ***\nPlease try again.\n");
            input.nextLine();
            return checkForInt(input, lowerBoundary,upperBoundary);
        }
    }
    
    /**
     * This method formats a raw string into a date format.
     * @param input (Scanner)
     * @return A formated date String
     */
    public Date checkForDate(Scanner input){ 
        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        fmt.setLenient(false);
        String typedDate = "";
        try {
            while(typedDate.isEmpty()){
                typedDate = input.nextLine();   
            }
            Date correctDate = fmt.parse(typedDate);
            return correctDate;
        } catch (ParseException e) {
            System.out.println("Wrong format! Date should be DD/MM/YYYY. Try again.");
            return checkForDate(input);
        }
    }
    
    /**
     * Checks input line for a date formatted as HH:mm.
     * If not correct, the method calls itself until the user input is accepted
     * @param input (Scanner)
     * @return A Date object correctDate
     */
    public Date checkForTime(Scanner input){ //maybe mudar esse método pra aceitar argumento (precisei quase da mesma coisa na classe FlightMenu)
        DateFormat fmt = new SimpleDateFormat("HH:mm");
        fmt.setLenient(false);
        String typedTime = "";
        
        try {
            while(typedTime.isEmpty()){
                typedTime = input.nextLine();
            }
            Date correctDate = fmt.parse(typedTime);
            return correctDate;
        } catch (ParseException e) {
            System.out.println("The time format should be (HH:mm).\nPlease try again:");
            return checkForTime(input);
        }
    }
    
    /**
     * This method checks the provided locations are differents.
     * @param origin (Location)
     * @param destination (Location)
     * @return True is the locations are different, false otherwise.
     */
    public boolean isDiferentLocation(Location origin, Location destination){
        if(origin.equals(destination)){
            return false;
        } else {
            return true;
        }
    }

}
