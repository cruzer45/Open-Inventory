package assetCategory

import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zk.ui.select.annotation.Listen
import org.zkoss.zul.*
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.Executions
import openinventory.AssetCategory


class AssetCategoryCRUDComposer extends GrailsComposer {

	AssetCategory assetCategory
    Textbox txtAssetCategory
    Textbox txtAssetCategoryDetails
    Checkbox chkDeleted
    def appArea

    def afterCompose = { window ->
        // initialize components here
        if (arg.containsKey("assetCategoryID")) {
            String id =  (String) arg.get("assetCategoryID")
            assetCategory = AssetCategory.get(id)
            populateFields()
        }
    }
    void populateFields()
    {
        txtAssetCategory.setValue(assetCategory.category)
        txtAssetCategoryDetails.setValue(assetCategory.details)
        chkDeleted.setChecked(assetCategory.deleted)
    }
    
    void onClick_cmdCancel()
    {
        Event closeEvent = new Event( "onClose", this.self, null )
        Events.postEvent( closeEvent )
    }
    
    void onClick_cmdSave()
    {
        if (!assetCategory)
        {
            assetCategory = new AssetCategory()
        }
        assetCategory.category = txtAssetCategory.getValue()?.trim()
        assetCategory.details = txtAssetCategoryDetails.getValue()?.trim()
        assetCategory.deleted = chkDeleted.isChecked()
        assetCategory = assetCategory.merge(flush:true)
        assetCategory.save(flush:true)
        //TODO Enable the messagebox when that bug us fixed
        Messagebox.show("Asset Category Saved!", "Asset Category", Messagebox.OK, Messagebox.INFORMATION)
        //reload the table
        appArea.getChildren().clear()
        Executions.createComponents("assetCategory/listAssetCategories.zul", appArea, null)
        onClick_cmdCancel()
    }
}
