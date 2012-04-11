package openinventory

class Asset {
    
    AssetCategory assetCategory
    String description
    String make
    String model
    String serialNumber
    String barcodeNumber
    String assetTag
    Date aquired = new Date()
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
        description(blank:true, maxSixe:1500, widget:'textArea')
        make(blank:false)
        model(blank:false)
        serialNumber(blank:true)
        barcodeNumber(blank:true, nullable:true)
        assetTag(blank:true)
        aquired(nullable:true)
        status(nullable:false)
        department(nullable:false)
        employee(nullable:true)
        sold(nullable:true)
        purchasePrice(nullable:true, scale: 2)
        expectedLife(nulllable:true)
        currentValue(nullable:true , scale: 2)
        comments(maxSixe:1500, widget:'textArea')
    }
    String tostring(){
        return make + " Model " + model
    }
}
