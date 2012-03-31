package openinventory

class AssetCategory {
  
    String category
    
    static constraints = {
        category(blank:false)
    }
    
    String toString(){
        return category
    }
}
