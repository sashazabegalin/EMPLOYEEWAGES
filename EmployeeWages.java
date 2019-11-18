import java.util.*;
import java.text.NumberFormat;

abstract class Employee {
    String empName;
    double empWage;
    
    Employee(String empName, double empWage)
    {
      this.empName = empName;
      this.empWage = empWage;
     }
    void increaseEmpWage(double increasePerc)
     { empWage = empWage + (empWage *increasePerc);  }
    abstract double computePay();
}
class HourlyEmployee extends Employee {
    double hours;
    static NumberFormat fmt = NumberFormat.getCurrencyInstance();

    HourlyEmployee(String empName, double empWage) {super(empName,empWage);   }
    double computePay() { //compute both regular time and 
      //overtime based on hours 
      double overtime;
      
      if(hours > 40){
         overtime = hours - 40;
         
         return (empWage * 40) + ((empWage * 1.5) * overtime); 
      }
      else
         return empWage * hours; 
      } 
    public String toString()   { //return empname and wage 
      return(empName + "   " + fmt.format(empWage));
    }   
 }
class SalariedEmployee extends Employee { 
   static NumberFormat fmt = NumberFormat.getCurrencyInstance();
     
    SalariedEmployee(String empName, double annualSalary) {  //set name and wage divide by 52, divide by 
    super(empName, (annualSalary / 52) / 40);}
    double computePay() { //Multiply wage by 40 
       empWage = empWage * 40;
       return empWage; 
    }
   public String toString()   { //return empname and annual salary 
            return(empName + "   " + fmt.format((empWage * 52) * 40));
    }
 }
 
class EmployeeDriver {
    //declare array
    static Employee[] list = new Employee[1];
    static int empCount = 0; 
       
    public static void main(String[] args) { 
//display menu in a do/while loop  (call menu and selectOptions methods)
   do{
   }while(!selectOptions(employeeMenu()));
    
 }
    public static String employeeMenu() {
    //display menu and return user’s selection
    
      String input;
      Scanner reader = new Scanner(System.in);
      
      System.out.println("N: New employee\nP: Compute paychecks\nR: Raise wages\nL: List all employees\nQ: Quit\n");
      System.out.println("Enter command: ");
      
      input = reader.next();
      
      return input;
         }

      
    public static boolean selectOptions (String user) {
        boolean quit = false;

       switch (user){
       
          case "n":
          case "N":    newEmployee();
                       break;
          /**
          case "P":    computePaycheck();
          case "R":    raiseWages();
          **/
          case "l":
          case "L":    listEmployees();
                       break;
          
          case "Q": quit = true;
          
          
              }
          return quit;

 }
 

    public static void newEmployee() {
     //grab input from user such as name, whether the employee is hourly or salaried,
//hourly wage or salary
     //create employee object based on the input
     //expand array as needed and assign new object to the proper index of the array
     String name;
     String salaryType;
     double wage;
     
     HourlyEmployee hourlyWorker;
     SalariedEmployee salaryWorker;
     
     Scanner reader = new Scanner(System.in);
     
     System.out.println("Enter name of new employee: ");
     name = reader.nextLine();
     
     System.out.println("Hourly (h) or salaried (s): ");
     salaryType = reader.next();
     
     
     if(salaryType.equals("h")){
        System.out.println("Enter hourly wage: ");
        wage = reader.nextDouble();
        if(empCount >= list.length)
            list = resize();

         hourlyWorker = new HourlyEmployee(name,wage);
         list[empCount] = hourlyWorker;
         empCount++;
      }
     else{
     System.out.println("Enter annual salary: ");
     wage = reader.nextDouble();
     if(empCount >= list.length)
            list = resize();

      salaryWorker = new SalariedEmployee(name,wage);
      list[empCount] = salaryWorker;
      empCount++;
     }
     
     
     
     
    }
 
 
 static Employee[] resize(){
	//resize array. Double the size
   Employee[] temp = new Employee[list.length * 2];
   System.arraycopy(list, 0, temp, 0, list.length);
   return temp;

  }

   
    public static void computeWeeklyPaycheck() {
      //display weekly pay for all employees using a loop. 
 //For hourly employees first grab hours, set hours to instance variable 
 //then call computePay which will call the 
 //appropriate overridden method for either hourly or salaried employee
     
    } 
    /**   
    public static void raiseWages() {
      //grab percentage from user and raise empWage for all employees using loop
    }
    **/
    public static void listEmployees() {
       //display information for all employees using loop and toString method
       System.out.println("Name             " + "hourly wages");
       System.out.println("----             " + "------------");
       for(int i = 0; i < empCount; i++){
         if(list[i] instanceof HourlyEmployee) 
            System.out.println(((HourlyEmployee)list[i]).toString());
         if(list[i] instanceof SalariedEmployee)
            System.out.println(((SalariedEmployee)list[i]).toString());

       }
    }
 }
