package asset

import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zk.ui.select.annotation.Listen
import org.zkoss.zul.*
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.Executions
import openinventory.AssetCategory
import openinventory.Status
import openinventory.Department

class AssetCRUDComposer extends GrailsComposer {

	Listbox cmbAssetCategory
    Listbox cmbCondition
    Listbox cmbDepartment

    def afterCompose = { window ->
        // initialize components here
        populateLists()
    }

    void populateLists(){
    	//Asset Categories
    	def assetCategoriesList = AssetCategory.createCriteria().list() { eq("deleted", false) order('category','asc') }
        for (assetCategory in assetCategoriesList){	cmbAssetCategory.appendItem(assetCategory.category , assetCategory.id.toString()) }
        //load statuses
        def statusList = Status.createCriteria().list() { eq("deleted", false) order('status','asc') }
        for (status in statusList){ cmbCondition.appendItem(status.status , status.id.toString()) }  
        //load statuses
        def departmentList = Department.createCriteria().list() { eq("deleted", false) order('department','asc') }
        for (department in departmentList){ cmbDepartment.appendItem(department.department , department.id.toString()) }  
    }

    
}
