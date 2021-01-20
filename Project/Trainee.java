package Project;

import java.util.ArrayList;
import java.util.Arrays;

public class Trainee implements Worker{
    private int id;
    private String name;
    private String surname;
    private int age;
    private String nameOfPosition;
    private String dateOfEmployment;
    private int salary;
    private String creditCardNumber;
    private int probationPeriod;
    private int progress;

    // Unexpected changes: added constructor
    public Trainee(ArrayList<String> data){
        id = Integer.parseInt(data.get(0));
        name = data.get(1);
        surname = data.get(2);
        age = Integer.parseInt(data.get(3));
        nameOfPosition = "Trainee";
        dateOfEmployment = data.get(4);
        salary = 1000;
        creditCardNumber = data.get(5);
        probationPeriod = Integer.parseInt(data.get(6));
        progress = Integer.parseInt(data.get(7));
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
        else if (attribute.equals("probationPeriod")){
            probationPeriod = Integer.parseInt(newValue);
        }
        else if (attribute.equals("progress")){
            progress = Integer.parseInt(newValue);
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
        else if (attribute.equals("probationPeriod")){
            data = String.valueOf(probationPeriod);
        }
        else if (attribute.equals("progress")){
            data = String.valueOf(progress);
        }
        else if (attribute.equals("all")){
            data = "id: " + String.valueOf(id) + " " + name + " " + surname +
                    "\r\n" + "name of position: " + String.valueOf(nameOfPosition) + 
                    "\r\n" + "age: " + String.valueOf(age) + 
                    "\r\n" + "date of employment: " + dateOfEmployment + 
                    "\r\n" + "salary: " + String.valueOf(salary) + 
                    "\r\n" + "credit card number: " + creditCardNumber +
                    "\r\n" + "probation period: " + String.valueOf(probationPeriod) +
                    "\r\n" + "progress: " + String.valueOf(progress);
        }

        return data;
    }

    public Worker promote(){
        ArrayList<String> data = new ArrayList<String>( 
            Arrays.asList(String.valueOf(id), name,surname, String.valueOf(age), 
                          dateOfEmployment, creditCardNumber, "0","0","0"));

        Consultant consultant = new Consultant(data);
        return new Consultant(data);
    }
    public Worker demote(){
        nameOfPosition = "Suspended employee";
        salary = 0;
        return this;
    }
}