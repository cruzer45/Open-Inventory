package openinventory

class AssetCategory {
  
    String category
    String details
    boolean deleted
    
    static constraints = {
        category(blank:false)
        details(nullable:true)
        deleted()
    }
    
    String toString(){
        return category
    }
}
