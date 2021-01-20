package Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Terminal {

    // Unexpected changes added field databaseRef, sc
    private Database databaseRef; // reference to database object
    private Scanner sc;

    public void printWorkerAttributes(Worker worker){
        System.out.println("------------------------");
        String attributesData = worker.getData("all");
        System.out.println(attributesData);
        System.out.println("------------------------");
    }
    public void printAllWorkersData(){
        System.out.println("------------------------");
        for (Worker worker : databaseRef.getWorkers()) {
            printWorkerAttributes(worker);
            System.out.println("------------------------");
        }
    }

    // Unexpected changes printWorkersData() -> printWorkersData(ArrayList<Worker> workers)
    public void printWorkersData(ArrayList<Worker> workers){
        for (Worker worker : workers) {
            System.out.println("id: " + worker.getData("id") + " " + worker.getData("name") + " " +
                               worker.getData("surname") + " " + worker.getData("nameOfPosition"));
        }
    }
    public String readUserInput(){
        String inputMsg = sc.nextLine();
        return inputMsg;
    }
    public void chooseOption(){
        String answer = "none";
        String[] workerTypes = {"manager", "consultant", "trainee"};
        String[] basicAttributes = {"name","surname","age", "nameOfPosition", "dateOfEmployment", "salary", "creditCardNumber"};
        String[] managerAttributes = {"finishedProjects", "subordinates", "currentProject"};
        String[] consultantAttributes = {"clients", "successfulDeals", "unsuccessfulDeals"};
        String[] traineeAttributes = {"probationPeriod", "progress"};

        //Warning
        String emptyDatabaseWR = "Warning: employee database is empty...";

        while(answer!="exit"){
            System.out.print("$> ");
            answer = sc.nextLine();
            String[] splited = answer.split("\\s+");

            String command = splited[0];

            //Reading arguments
            String[] args = {"none"};
            if (splited.length - 1 >= 1){
                args = new String[splited.length - 1];
                for (int i = 1; i < splited.length; i++){
                    args[i-1] = splited[i];
                }
            }

            if (command.equals("add")){
                String type = args[0];
                if (Arrays.asList(workerTypes).contains(type)){
                    databaseRef.addNewWorker(this, type);
                }
                else{
                    System.out.println("Unknown type: " + type);
                    System.out.println("Use types: " + Arrays.toString(workerTypes));
                }
            }
            else if (command.equals("find")){
                if (databaseRef.getSize() == 0){
                    System.out.println(emptyDatabaseWR);
                }
                else if (args[0].equals("-all") || args[0].equals("none")){
                    printAllWorkersData();
                }
                else{
                    boolean isApproximateSearch = false;
                    for (String idStr : args){
                        try {
                            if (!isApproximateSearch){
                                int id = Integer.parseInt(idStr);
                                Worker worker = databaseRef.findWorker(id);
                                if (worker != null){
                                    printWorkerAttributes(worker);
                                }
                            }
                        } catch (NumberFormatException nfe) {
                            isApproximateSearch = true;
                            String surname = idStr;
                            try{
                                int n = Integer.parseInt(args[1]);
                                if (n > databaseRef.getSize()){
                                    n = databaseRef.getSize();
                                }
                                Worker[] workers = new Worker[n];
                                workers = databaseRef.findWorker(surname, n);
                                for (Worker worker : workers){
                                    printWorkerAttributes(worker);
                                }
                            }catch (ArrayIndexOutOfBoundsException aiofbe) {
                                int defaultValue = 5;
                                if (databaseRef.getSize() < 5){
                                    defaultValue = databaseRef.getSize();
                                }
                                Worker[] workers = new Worker[defaultValue];
                                workers = databaseRef.findWorker(surname, defaultValue);
                                for (Worker worker : workers){
                                    printWorkerAttributes(worker);
                                }
                            }
                        }
                    }
                }
            }
            else if (command.equals("change")){
                if (databaseRef.getSize() == 0){
                    System.out.println(emptyDatabaseWR);
                }
                else if (args.length > 2){
                    String idStr = args[0];
                    String attribute = args[1];
                    String newValue = args[2];
                    try {
                        int id = Integer.parseInt(idStr);
                        Worker worker = databaseRef.findWorker(id);
                        if (worker != null){
                            databaseRef.changeWorkerData(worker, attribute, newValue);
                        }
                    } catch (NumberFormatException nfe) {}
                }
            }
            else if (command.equals("display")){
                if (databaseRef.getSize() == 0){
                    System.out.println(emptyDatabaseWR);
                }
                else if (args[0].equals("-all") || args[0].equals("none")){
                    printWorkersData(databaseRef.getWorkers());
                }
                else{
                    ArrayList<Worker> workers = new ArrayList<Worker>();
                    for (String idStr : args){
                        try {
                            int id = Integer.parseInt(idStr);
                            Worker worker = databaseRef.findWorker(id);
                            if (worker != null){
                                workers.add(worker);
                            }
                        } catch (NumberFormatException nfe) {}
                    }
                    printWorkersData(workers);
                }
            }
            else if (command.equals("remove")){
                if (databaseRef.getSize() == 0){
                    System.out.println(emptyDatabaseWR);
                }
                else if (args[0].equals("-all")){
                    ArrayList<Worker> workers;
                    int sizeTMP = databaseRef.getSize();
                    for (int i = 0; i < sizeTMP; i++){
                        workers = databaseRef.getWorkers();
                        databaseRef.deleteWorker(workers.get(0));
                    }
                }
                else if (args.length == 0){
                    System.out.println(emptyDatabaseWR);
                }
                else{
                    for (String idStr : args){
                        try {
                            int id = Integer.parseInt(idStr);
                            Worker worker = databaseRef.findWorker(id);
                            if (worker != null){
                                databaseRef.deleteWorker(worker);
                            }
                        } catch (NumberFormatException nfe) {}
                    }
                }
            }
            else if (command.equals("help")){
                help();
            }
            else if (command.equals("exit")){
                break;
            }
            else{
                System.out.println("Unknown command: " + command);
            }
            
        }
    }
    public void startInteract(Database database){
        databaseRef = database;
        sc = new Scanner(System.in);  

        System.out.println("Welcome to the Employee Database Management System");
        System.out.println("--------------------------------------------------");
        System.out.println("Available commands: (add, find, display, change, remove, help, exit),");
        System.out.println("-> add (enum {manager, consultant, trainee})");
        System.out.println("-> find (surname) [n=5]");
        System.out.println("-> find (id)");
        System.out.println("-> change (id) (attribute) (newValue)");
        System.out.println("-> display (enum {-all, {id}, [{id1},{id2},...]})");
        System.out.println("-> remove (enum {-all, {id}, [{id1},{id2},...]})");
        System.out.println("-> help");
        System.out.println("-> exit");
        System.out.println("--------------------------------------------------");
    }

    public void help(){
        System.out.println("--------------------------------------------------");
        System.out.println("-> add - adds a new worker of the selected type (manager, consultant, trainee)");
        System.out.println("-> find - displays full information about the found employee (in format: {id} {name} {surname} \n {[otherAttributes]})");
        System.out.println("with the given id or surname in the case of a surname, also required number of displayed results (default value is 5),");
        System.out.println("because such a search is approximate");
        System.out.println("-> change - changes the given attribute of the person with the given id");
        System.out.println("Attribute list: name, surname, age, nameOfPosition, dateOfEmployment, salary, creditCardNumber");
        System.out.println("Additional attributes of employees of the Manager type: finishedProjects, subordinates, currentProject");
        System.out.println("Additional attributes of employees of the Consultant type: clients, successfulDeals, unsuccessfulDeals");
        System.out.println("Additional attributes of employees of the Trainee type: probationPeriod, progress");
        System.out.println("-> display - displays short information about the person with the given id (in format: {id} {name} {surname} {type})");
        System.out.println("-> remove - deletes an employee from the database");
        System.out.println("--------------------------------------------------");
    }
}