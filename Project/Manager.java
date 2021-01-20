package Project;

import java.lang.reflect.Field;
import java.security.CryptoPrimitive;
import java.util.ArrayList;
import java.util.Arrays;

public class Manager implements Worker {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String nameOfPosition;
    private String dateOfEmployment;
    private int salary;
    private String creditCardNumber;
    private int finishedProjects;
    private int subordinates;
    private String currentProject;

    // Unexpected changes: added constructor
    public Manager(ArrayList<String> data){
        id = Integer.parseInt(data.get(0));
        name = data.get(1);
        surname = data.get(2);
        age = Integer.parseInt(data.get(3));
        nameOfPosition = "Manager";
        dateOfEmployment = data.get(4);
        salary = 3500;
        creditCardNumber = data.get(5);
        finishedProjects = Integer.parseInt(data.get(6));
        subordinates = Integer.parseInt(data.get(7));
        currentProject = data.get(8);
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
            finishedProjects = Integer.parseInt(newValue);
        }
        else if (attribute.equals("subordinates")){
            subordinates = Integer.parseInt(newValue);
        }
        else if (attribute.equals("currentProject")){
            currentProject = newValue;
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
        else if (attribute.equals("finishedProjects")){
            data = String.valueOf(finishedProjects);
        }
        else if (attribute.equals("subordinates")){
            data = String.valueOf(subordinates);
        }
        else if (attribute.equals("currentProject")){
            data = currentProject;
        }
        else if (attribute.equals("all")){
            data = "id: " + String.valueOf(id) + " " + name + " " + surname +
                    "\r\n" + "name of position: " + String.valueOf(nameOfPosition) + 
                    "\r\n" + "age: " + String.valueOf(age) + 
                    "\r\n" + "date of employment: " + dateOfEmployment + 
                    "\r\n" + "salary: " + String.valueOf(salary) + 
                    "\r\n" + "credit card number: " + creditCardNumber +
                    "\r\n" + "finished projects: " + String.valueOf(finishedProjects) +
                    "\r\n" + "number of subordinates: " + String.valueOf(subordinates) +
                    "\r\n" + "current project: " + currentProject;
        }

        return data;
    }
    public Worker promote(){
        salary += 1000;
        return this;
    }
    public Worker demote(){
        ArrayList<String> data = new ArrayList<String>( 
            Arrays.asList(String.valueOf(id), name,surname, String.valueOf(age), 
                          dateOfEmployment, creditCardNumber, "0","0","0"));

        Consultant consultant = new Consultant(data);
        return new Consultant(data);
    }
}