# Employee Database Management System
A simple database with the option to manage employee data. 
### Short description:
Available commands:
  * _add (enum {manager, consultant, trainee})_ - adds a new worker of the selected type (manager, consultant, trainee), also checks the bank card number (by [Luhn algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm)).
  
  * _find (id)_ - displays full information about employee of the given id (in format: {id} {name} {surname} \n {[otherAttributes]}).
  
  * _find (surname) [n=5]_ - similar, but using approximate search (by [Levenshtein Distance](https://en.wikipedia.org/wiki/Levenshtein_distance#:~:text=Informally%2C%20the%20Levenshtein%20distance%20between,considered%20this%20distance%20in%201965.)) to show best n (default value 5) suitable employees.
  
  * _change (id) (attribute) (newValue)_ - changes the given attribute of the person with the given id.
    * Attribute list: **name, surname, age, nameOfPosition, dateOfEmployment, salary, creditCardNumber**.
    * Additional attributes of employees of the Manager type: **finishedProjects, subordinates, currentProject**.
    * Additional attributes of employees of the Consultant type: **clients, successfulDeals, unsuccessfulDeals**.
    * Additional attributes of employees of the Trainee type: **probationPeriod, progress**.
    
  * _display (enum {-all, {id}, [{id1},{id2},...]})_ - displays short information about the person with the given id (in format: {id} {name} {surname} {type}).
  
  * _remove (enum {-all, {id}, [{id1},{id2},...]})_ - deletes an employee from the database
  
  * _help_ - shows the description of the commands

## Usage

Clone repo. Open in VSC repo folder. Install in Extensions: 'Java Extension Pack'. 
To compile - enter the following command in the VSC terminal: java -jar EmployeeDatabaseManagementSystem.jar
