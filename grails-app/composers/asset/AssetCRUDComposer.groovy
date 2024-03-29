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
import openinventory.Asset

class AssetCRUDComposer extends GrailsComposer {

	def appArea
	def assetService
    Listbox cmbAssetCategory
    Listbox cmbCondition
    Listbox cmbDepartment
    Asset asset
    Textbox txtMake
    Textbox txtModel
    Textbox txtSerialNumber
    Textbox txtDetails
    Textbox txtAssetTag
    Decimalbox txtPurchasePrice
    Decimalbox txtCurrentValue
    Decimalbox txtSalvagePrice
    Datebox calDateAquired
    Intbox txtExpectedLife
    Textbox txtComments
    Datebox calNextMaintenance



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

     void onClick_cmdCancel()
    {
        Event closeEvent = new Event( "onClose", this.self, null )
        Events.postEvent( closeEvent )
    }
    
    void onClick_cmdSave()
    {
        if (!asset)
        {
            asset = new Asset()
        }

        def assetCategory = AssetCategory.get(cmbAssetCategory.getSelectedItem()?.getValue()) 
        def assetStatus = Status.get(cmbCondition.getSelectedItem()?.getValue()) 
        def assetDept = Department.get(cmbDepartment.getSelectedItem()?.getValue()) 

		def params = [
			
			assetCategory:  assetCategory,
			details: txtDetails.getValue()?.trim() ?: ' ',
			make: txtMake.getValue()?.trim()?.capitalize() ?: ' ',
			model: txtModel.getValue()?.trim()?.capitalize() ?: ' ',
			serialNumber: txtSerialNumber.getValue()?.trim()?.capitalize() ?: ' ',
			//asset.barcodeNumber = ''
			assetTag: txtAssetTag.getValue()?.trim()?.capitalize() ?: ' ',
			aquired: calDateAquired.getValue() ?: '',
			status: assetStatus ?: Status.findByStatus("Unknown"),
			//asset.employee
			department: assetDept,
			purchasePrice: txtPurchasePrice.doubleValue() ?: 0.0,
			expectedLife:  txtExpectedLife.intValue() ?: 0.0,
			salvageValue: txtSalvagePrice.doubleValue() ?: 0.0,
			currentValue: txtCurrentValue.doubleValue() ?: 0.0,
			comments: txtComments.getValue()?.trim() ?: 0.0,
			nextScheduledMaintenance: calNextMaintenance.getValue()  ?: defaultNextMaintenance()
		]
		
        
        assetService.saveAsset(params);
        
        //asset = asset.merge(flush:true)
        //asset.save(flush:true)
        Messagebox.show("Asset Saved!", "Asset", Messagebox.OK, Messagebox.INFORMATION)
        //reload the table
        appArea.getChildren().clear()
        //Executions.createComponents("assetCategory/listAssetCategories.zul", appArea, null)
        onClick_cmdCancel()
    }

    Date defaultNextMaintenance() {
        def c = new GregorianCalendar()
        c.setTime(new Date())
        c.add(6, java.util.Calendar.MONTH)
        return c.getTime()
    }
}
