package openinventory

class Asset {
    
    AssetCategory assetCategory
    String details
    String make
    String model
    String serialNumber
    String assetTag
    Date aquired
    Status status
    Employee employee
    Department department
    Date sold
    double purchasePrice
    int expectedLife
    double salvageValue
    double currentValue
    String comments
    Date nextScheduledMaintenance
    
    
    static constraints = {
        assetCategory(nullable:false, blank:false)
        make(blank:false)
        model(blank:false)
        serialNumber(blank:true)
        details(blank:true, maxSixe:1500)
        assetTag(blank:true)
        aquired(nullable:true)
        status(nullable:false)
        department(nullable:false)
        employee(nullable:true)
        sold(nullable:true)
        purchasePrice(nullable:true, scale: 2)
        expectedLife(nullable:false)
        currentValue(nullable:true , scale: 2)
        comments(blank:true,maxSixe:1500)
        nextScheduledMaintenance(nullable:true)
    }
    String tostring(){
        return make + " Model " + model
    }
}
