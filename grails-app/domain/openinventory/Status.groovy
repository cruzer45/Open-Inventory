package openinventory

class Status {

    String status
    boolean deleted
    
    static constraints = {
        status(blank:false)
    }
    
    String toString(){
        return status
    }
}
