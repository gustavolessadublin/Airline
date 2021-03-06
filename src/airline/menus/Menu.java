package airline.menus;

import airline.data.Data;
import airline.data.DataValidation;
import java.util.Scanner;

/**
 * @author rbsrafa
 * @author gustavolessadublin
 */
public abstract class Menu {
    protected Data data;
    protected Scanner input;
    protected boolean exit;
    protected DataValidation validate;
    
    /**
     * Initializes a new Menu
     * @param data (Data)
     */
    public Menu(Data data){
        this.data = data;
        input = new Scanner(System.in);
        exit = false;
        validate = new DataValidation();
    }
    
    /**
     * This method will implement the menu options logic on children classes.
     */
    public abstract void optionSelector();
    
    /**
     * This method displays the menu on CLI.
     * @param menu (String)
     */
    public void displayMenu(Menu menu){
        System.out.println(menu);
    }
    
    /**
     * This method exits the running program.
     */
    public void exitProgram(){
        System.exit(0);
    }
    
    /**
     * This method creates a new instance of MainMenu class.
     */
    public void returnToMainMenu(){
        new MainMenu(data);
    }
    
}
