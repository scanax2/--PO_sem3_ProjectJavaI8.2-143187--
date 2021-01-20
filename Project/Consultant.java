package Project;

import java.util.ArrayList;
import java.util.Arrays;

public class Consultant implements Worker {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String nameOfPosition;
    private String dateOfEmployment;
    private int salary;
    private String creditCardNumber;
    private int clients;
    private int successfulDeals;
    private int unsuccessfulDeals;

    //Unexpected changes: added constructor
    public Consultant(ArrayList<String> data){
        id = Integer.parseInt(data.get(0));
        name = data.get(1);
        surname = data.get(2);
        age = Integer.parseInt(data.get(3));
        nameOfPosition = "Consultant";
        dateOfEmployment = data.get(4);
        salary = 2500;
        creditCardNumber = data.get(5);
        clients = Integer.parseInt(data.get(6));
        successfulDeals= Integer.parseInt(data.get(7));
        unsuccessfulDeals = Integer.parseInt(data.get(8));
    }

    public void changeAttribute(String attribute, String newValue){
        if (attribute.equals("name")){
            name = newValue;
        }
        else if (attribute.equals("surname")){
            surname = newValue;
        }
        else if (attribute.equals("age")){
            age = Integer.parseInt(newValue);
        }
        else if (attribute.equals("nameOfPosition")){
            nameOfPosition = newValue;
        }
        else if (attribute.equals("dateOfEmployment")){
            dateOfEmployment = newValue;
        }
        else if (attribute.equals("salary")){
            salary = Integer.parseInt(newValue);
        }
        else if (attribute.equals("creditCardNumber")){
            creditCardNumber = newValue;
        }
        else if (attribute.equals("finishedProjects")){
            clients = Integer.parseInt(newValue);
        }
        else if (attribute.equals("subordinates")){
            successfulDeals = Integer.parseInt(newValue);
        }
        else if (attribute.equals("currentProject")){
            unsuccessfulDeals = Integer.parseInt(newValue);
        }
    }
    public String getData(String attribute){
        String data = "None";
        if (attribute.equals("id")){
            data = String.valueOf(id);
        }
        else if (attribute.equals("name")){
            data = name;
        }
        else if (attribute.equals("surname")){
            data = surname;
        }
        else if (attribute.equals("age")){
            data = String.valueOf(age);
        }
        else if (attribute.equals("nameOfPosition")){
            data = nameOfPosition;
        }
        else if (attribute.equals("dateOfEmployment")){
            data = dateOfEmployment;
        }
        else if (attribute.equals("salary")){
            data = String.valueOf(salary);
        }
        else if (attribute.equals("creditCardNumber")){
            data = creditCardNumber;
        }
        else if (attribute.equals("clients")){
            data = String.valueOf(clients);
        }
        else if (attribute.equals("successfulDeals")){
            data = String.valueOf(successfulDeals);
        }
        else if (attribute.equals("unsuccessfulDeals")){
            data = String.valueOf(unsuccessfulDeals);
        }
        else if (attribute.equals("all")){
            data = "id: " + String.valueOf(id) + " " + name + " " + surname +
                    "\r\n" + "name of position: " + String.valueOf(nameOfPosition) + 
                    "\r\n" + "age: " + String.valueOf(age) + 
                    "\r\n" + "date of employment: " + dateOfEmployment + 
                    "\r\n" + "salary: " + String.valueOf(salary) + 
                    "\r\n" + "credit card number: " + creditCardNumber +
                    "\r\n" + "number of clients: " + String.valueOf(clients) +
                    "\r\n" + "number of successful deals: " + String.valueOf(successfulDeals) +
                    "\r\n" + "number of unsuccessful deals: " + String.valueOf(unsuccessfulDeals);
        }

        return data;
    }
    public Worker promote(){
        ArrayList<String> data = new ArrayList<String>( 
            Arrays.asList(String.valueOf(id), name,surname, String.valueOf(age), 
                          dateOfEmployment, creditCardNumber, "0","0","No project at the moment"));
        
        return new Manager(data);
    }
    public Worker demote(){
        ArrayList<String> data = new ArrayList<String>( 
            Arrays.asList(String.valueOf(id), name,surname, String.valueOf(age), 
                          dateOfEmployment, creditCardNumber, "0","0"));
        
        return new Trainee(data);
    }
}