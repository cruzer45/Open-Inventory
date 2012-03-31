package openinventory

class Maintenance {

    Asset asset
    Date date
    String notes
    String performedBy
    double  cost
    
    static constraints = {
        asset(nullable:false)
        date(blank:false, nulable:false)
        notes()
        performedBy()
        cost()
    }
}
