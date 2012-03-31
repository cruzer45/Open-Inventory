package openinventory

class Department {

    String department
    
    static constraints = {
        department(blank:false)
    }
    
    String toString(){
        return department
    }
}
