import openinventory.AssetCategory;
import openinventory.Department;
import openinventory.Status;

class BootStrap {

    def init = { servletContext ->
        if (AssetCategory.count() == 0)
        {
            new AssetCategory(category: 'Phone' ).save(flush:true)
            new AssetCategory(category: 'Computer' ).save(flush:true)
            new AssetCategory(category: 'Printer' ).save(flush:true)
            new AssetCategory(category: 'Fax' ).save(flush:true)
            new AssetCategory(category: 'UPS' ).save(flush:true)
            new AssetCategory(category: 'Monitor' ).save(flush:true)
            new AssetCategory(category: 'Digital Camera' ).save(flush:true)
            new AssetCategory(category: 'Scanner' ).save(flush:true)
            new AssetCategory(category: 'Speakers' ).save(flush:true)
        }
		
        if (Department.count() == 0)
        {
            new Department(department: 'Accounts' ).save(flush:true)
            new Department(department: 'Claims' ).save(flush:true)
            new Department(department: 'Customer Service' ).save(flush:true)
            new Department(department: 'Human Resources' ).save(flush:true)
            new Department(department: 'Reinsurance' ).save(flush:true)
            new Department(department: 'Management' ).save(flush:true)
            new Department(department: 'IT' ).save(flush:true)
            new Department(department: 'NCE' ).save(flush:true)
            new Department(department: 'Corozal Town' ).save(flush:true)
            new Department(department: 'Corozal Border' ).save(flush:true)
            new Department(department: 'Orange Walk Town' ).save(flush:true)
            new Department(department: 'Orance Walk Central' ).save(flush:true)
            new Department(department: 'Head Office' ).save(flush:true)
            new Department(department: 'South Side' ).save(flush:true)
            new Department(department: 'Sales Team' ).save(flush:true)
            new Department(department: 'Belmopan' ).save(flush:true)
            new Department(department: 'Santa Elena' ).save(flush:true)
            new Department(department: 'San Ignacio' ).save(flush:true)
            new Department(department: 'Benque Viejo' ).save(flush:true)
            new Department(department: 'Dangriga' ).save(flush:true)
            new Department(department: 'Seine Bight' ).save(flush:true)
            new Department(department: 'Independence' ).save(flush:true)
            new Department(department: 'Punta Gorda' ).save(flush:true)
            new Department(department: 'San Pedro' ).save(flush:true)
            new Department(department: 'Cashier' ).save(flush:true)
        }
		
        if (Status.count() == 0 )
        {
            new Status(status: 'Working - In Use' ).save(flush:true)
            new Status(status: 'Working - Not In Use' ).save(flush:true)
            new Status(status: 'Not Working' ).save(flush:true)
            new Status(status: 'Purged' ).save(flush:true)
            new Status(status: 'No Longer Used' ).save(flush:true)
        } 
    }
    def destroy = {
    }
}
