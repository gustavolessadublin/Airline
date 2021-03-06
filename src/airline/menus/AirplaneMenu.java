/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airline.menus;

import airline.aircrafts.Airplane;
import airline.employees.Pilot;
import airline.data.Data;

/**
 *
 * @author lucivalsn
 */
public class AirplaneMenu extends Menu{
    
    /**
     * Build a Menu with options to manage Airplane objects
     * @param data (Data)
     */
    public AirplaneMenu(Data data){
        super(data);
        while(!exit){
            this.displayMenu(this);
            this.optionSelector();
        }
    }
    
    /**
     * This method uses a Scanner variable to receive a int value,
     * this variable is then used to select on of the menu options.
     */
    @Override
    public void optionSelector() {
        int option = this.validate.checkForInt(input);
        switch (option) {
            case 1:
                this.displayAirplanes();
                break;
            case 2:
                Airplane aId = this.searchAirplane();
                System.out.println(aId);
                break;            
            case 3:
                this.addAirplane();
                break;
            case 4:
                this.assignPilot();
                break;
            case 5:
                this.returnToMainMenu();
                break;
            case 6:
                this.exitProgram();
                break;
            default:
                System.out.println("\n*** Invalid option. Please try again ***\n");
                break;
        }
    }
    
    /**
     * Display an option Menu to manage Airplane object.
     * @return a String in a text box format 
     * representing the menu options for the user.
     */
    @Override
    public String toString(){
        return
            "\n+--------------------------+\n" +
            "| Airplanes Menu           |\n" + 
            "+--------------------------+\n" +
            "| 1 - Show Airplane list   |\n" +
            "| 2 - Find Airplane by ID  |\n" +
            "| 3 - Add New Airplane     |\n" +
            "| 4 - Assign Pilot by ID   |\n" +
            "| 5 - Return to Main Menu  |\n" + 
            "| 6 - Exit Program         |\n" + 
            "+--------------------------+\n" +
            "Please select an option: ";
    }

    /**
     * Display all the Airplane objects stored in the list
     */
    private void displayAirplanes() {
        for(Airplane a: super.data.getAirplanes()){
            System.out.println(a);
        }
    }

    /**
     * Search an Airplane object using the id reference,
     * a valid id must be input by the user.
     * @return Returns Airplane object details
     */
    private Airplane searchAirplane() {
        Airplane searchAirplaneId = null;
        System.out.println("Please type the airplane Id:");
        int id = this.validate.checkForInt(input);
        
        for(Airplane a: super.data.getAirplanes()){
            if(id == a.getId()) searchAirplaneId = a;
        }
        if(searchAirplaneId == null) System.out.println("\n*** Airplane not found! ***\n"); 
        return searchAirplaneId;
    }
    
    /**
     * This method collect users entry to add a new Airplane object,
     * a valid Pilot object must be assigned.
     */
    public void addAirplane(){
        input.nextLine();        
        System.out.println("Please type the airplane make:");
        String make = input.nextLine();        
        System.out.println("Please type the airplane model:");
        String model = input.nextLine();        
        System.out.println("Please type the airplane capacity:");
        int capacity = this.validate.checkForInt(input);        
        System.out.println("Please select the pilot id:");
        int option = this.validate.checkForInt(input);
        Pilot pilot = super.data.getPilots().get(option);
        super.data.getAirplanes().add(new Airplane(make, model, capacity, pilot));
        System.out.println("\n*** Airplane id: " 
                + super.data.getAirplanes().size() + " has been added ***\n");
    }
    
    /**
     * This method assigns a Pilot object to the designated Airplane object,
     * any previous Pilot data assigned will be lost.
     */
    private void assignPilot() {       
        Pilot pilotToAssign = null;
        Airplane airplaneToAssign = null;
        System.out.println("Please type the pilot Id:");
        int pilotId = this.validate.checkForInt(input);
        
        for(Pilot p: super.data.getPilots()){
            if(pilotId == p.getId()) pilotToAssign = p;
        }
        if(pilotToAssign == null) {
            System.out.println("\n*** Pilot not found! ***");
            this.assignPilot();
        }else{
            airplaneToAssign = this.searchAirplane();       
        
            if(this.validate.isAllowed(pilotToAssign, airplaneToAssign.capacity())){
                airplaneToAssign.getPilot().setAssigned(false);
                airplaneToAssign.assignPilot(pilotToAssign);
                pilotToAssign.setAssigned(true);
                System.out.println(airplaneToAssign);
            }else{
                System.out.println("\n*** The selected pilot is not allowed to fly the selected airplane ***\n");
                System.out.println("*** Please check the pilot's rating and/or availability. ***");
            }
        }
           
    }
    
    
}
