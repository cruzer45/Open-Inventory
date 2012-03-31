package openinventory

class Employee {

    String firstName
    String lastName
    String title
    String extension
    String workPhone
    String officeLocation
    
    static constraints = {
        firstName(blank:false)
        lastName(blank:false)
        title(blank:false)
        extension()
        workPhone()
        officeLocation()
    }
    
    String toString(){
        return firstName + " " + lastName
    }
}
