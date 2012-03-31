package openinventory

class Department {

    String department
    boolean deleted
    
    static constraints = {
        department(blank:false)
    }
    
    String toString(){
        return department
    }
}
