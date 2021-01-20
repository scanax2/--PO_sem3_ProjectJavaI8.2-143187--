package Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.Collections;

public class Database{

    private final ArrayList<Worker> listOfWorkerks = new ArrayList<>();
    private DatabaseAlgorithms dbalgorithms = new DatabaseAlgorithms();
    private int size = 0;

    //Unexpected changes addNewWorker() -> addNewWorker(terminal, type)
    public Worker addNewWorker(Terminal terminal, String type){
        String id = String.valueOf(size);

        System.out.println("Enter name of new worker (string): ");
        String nameStr = terminal.readUserInput();
        if(nameStr.equals("")){
            nameStr = "None";
        }

        System.out.println("Enter surname of new worker (string): ");
        String surnameStr = terminal.readUserInput();
        if(surnameStr.equals("")){
            surnameStr = "None";
        }


        System.out.println("Enter age of new worker (int): ");
        String ageStr = terminal.readUserInput();
        try {
            int test = Integer.parseInt(ageStr);
        } catch (NumberFormatException nfe) {
            System.out.println("The entered data have incorrect type: " + ageStr);
            ageStr = "0";
        }
       
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
        String dateOfEmploymentStr = dateFormat.format(date);     

        System.out.println("Enter credit card number of new worker (int): ");
        String creditCardNumberStr = terminal.readUserInput();

        boolean isCorrect = dbalgorithms.luhnAlgorithm(creditCardNumberStr);
        if(isCorrect){
            creditCardNumberStr += " <- correct :)";
        }
        else{
            creditCardNumberStr += " <- wrong :<";
        }
        if(creditCardNumberStr.equals("")){
            creditCardNumberStr = "None";
        }


        ArrayList<String> data = new ArrayList<String>();
        data.add(id);
        data.add(nameStr);
        data.add(surnameStr);
        data.add(ageStr);
        data.add(dateOfEmploymentStr);
        data.add(creditCardNumberStr);

        if(type.equals("manager")){
            System.out.println("Enter finished projects (int): ");
            String finishedProjects = terminal.readUserInput();

            try {
                int test = Integer.parseInt(finishedProjects);
            } catch (NumberFormatException nfe) {
                System.out.println("The entered data have incorrect type: " + finishedProjects);
                finishedProjects = "0";
            }

            System.out.println("Enter number of subordinates (int): ");
            String subordinates = terminal.readUserInput();
            try {
                int test = Integer.parseInt( subordinates);
            } catch (NumberFormatException nfe) {
                System.out.println("The entered data have incorrect type: " +  subordinates);
                subordinates = "0";
            }

            System.out.println("Enter current project (string): ");
            String currentProject = terminal.readUserInput();
            if(currentProject.equals("")){
                currentProject = "None";
            }

            data.add(finishedProjects);
            data.add(subordinates);
            data.add(currentProject);

            Worker manager = new Manager(data);
            listOfWorkerks.add(manager);
            size++;
            return manager;
        }
        else if(type.equals("consultant")){
            System.out.println("Enter number of clients (int): ");
            String clients = terminal.readUserInput();

            try {
                int test = Integer.parseInt(clients);
            } catch (NumberFormatException nfe) {
                System.out.println("The entered data have incorrect type: " + clients);
                clients = "0";
            }

            System.out.println("Enter number of successful deals (int): ");
            String successfulDeals = terminal.readUserInput();

            try {
                int test = Integer.parseInt(successfulDeals);
            } catch (NumberFormatException nfe) {
                System.out.println("The entered data have incorrect type: " + successfulDeals);
                successfulDeals = "0";
            }

            System.out.println("Enter number of unsuccessful deals (int): ");
            String unsuccessfulDeals = terminal.readUserInput();

            try {
                int test = Integer.parseInt(unsuccessfulDeals);
            } catch (NumberFormatException nfe) {
                System.out.println("The entered data have incorrect type: " + unsuccessfulDeals);
                unsuccessfulDeals = "0";
            }

            data.add(clients);
            data.add(successfulDeals);
            data.add(unsuccessfulDeals);

            Consultant consultant = new Consultant(data);
            listOfWorkerks.add(consultant);
            size++;
            return consultant;
        }
        else if(type.equals("trainee")){
            System.out.println("Enter probation period (int): ");
            String probationPeriod = terminal.readUserInput();

            try {
                int test = Integer.parseInt(probationPeriod);
            } catch (NumberFormatException nfe) {
                System.out.println("The entered data have incorrect type: " + probationPeriod);
                probationPeriod = "0";
            }

            System.out.println("Enter current progress (int) <0,probationPeriod>: ");
            String progress = terminal.readUserInput();

            try {
                int test = Integer.parseInt(progress);
            } catch (NumberFormatException nfe) {
                System.out.println("The entered data have incorrect type: " + progress);
                progress = "0";
            }

            data.add(probationPeriod);
            data.add(progress);

            Trainee trainee = new Trainee(data);
            listOfWorkerks.add(trainee);
            size++;
            return trainee;
        }

        return null;
    }
    //Unexpected changes: changeWorkerData(Worker worker) -> changeWorkerData(Worker worker, String attribute, String newValue)
    public void changeWorkerData(Worker worker, String attribute, String newValue){
        if (attribute.equals("creditCardNumber")){
            if (dbalgorithms.luhnAlgorithm(newValue)){
                newValue += " <- correct :)";
            }
            else{
                newValue += " <- wrong :<";
            }
        }
        worker.changeAttribute(attribute, newValue);
    }
    
    public Worker[] findWorker(String surname, int n){
        
        Map<Worker, Integer> levensteinData = new HashMap<Worker, Integer>();
        for (Worker worker : listOfWorkerks){  
            levensteinData.put(worker, dbalgorithms.levensteinDistance(surname, worker.getData("surname")));
        }
        LinkedHashMap<Worker, Integer> sortedMap = new LinkedHashMap<Worker, Integer>();
        levensteinData.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        Worker[] workers = new Worker[n];
        //Selecting best suitable to surname n workers 
        for (int i = 0; i < n; i++){
            workers[i] = (Worker)sortedMap.keySet().toArray()[i];
        }
        return workers;
    }
    public Worker findWorker(int id){
        for (Worker worker : listOfWorkerks){
            if (Integer.parseInt(worker.getData("id")) == id){
                return worker;
            }
        }
        return null;
    }
    public void deleteWorker(Worker worker){
        for (int i = 0; i < size; i++){
            if (listOfWorkerks.get(i) == worker){
                listOfWorkerks.remove(worker);
                size--;
            }
        }
    }

    //Unexpected changes: added get function
    public int getSize(){
        return size;
    }
    //Unexpected changes: added get function
    public ArrayList<Worker> getWorkers(){
        return listOfWorkerks;
    }

    public static void main(final String[] args){
        Database database = new Database();
        Terminal terminal = new Terminal();

        terminal.startInteract(database);
        terminal.chooseOption();
    }
}