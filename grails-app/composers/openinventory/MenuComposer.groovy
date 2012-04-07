package openinventory

import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zk.ui.select.annotation.Listen
import org.zkoss.zk.ui.Component
import org.zkoss.zul.*
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.Executions

class MenuComposer extends GrailsComposer {

    def appArea
    Menuitem menuListStatus
    Menuitem menuAddStatus
    Menuitem menuListDepartments
    Menuitem menuAddDepartment
    Menuitem menuLogout
    Menuitem menuListAssetCategories
    Menuitem menuAddAssets
    
    def afterCompose = { window ->
        // initialize components here
    }
    
    void onClick_menuListStatus(){
        appArea.getChildren().clear()
        Executions.createComponents("status/listStatus.zul", appArea, null)
    }
    
    void onClick_menuAddStatus(){
        Window win = (Window) Executions.createComponents("status/statusCRUD.zul", appArea,null)
        win.setClosable(true)
        win.doModal()
    }
    
    void onClick_menuListDepartments(){
        appArea.getChildren().clear()
        Executions.createComponents("department/listDepartment.zul", appArea, null)
    }
    
    void onClick_menuLogout(){
       execution.sendRedirect('/logout')
    }

    void onClick_menuListAssetCategories(){
        appArea.getChildren().clear()
        Executions.createComponents("assetCategory/listAssetCategories.zul", appArea, null)
    }

    void onClick_menuAddAssets()    {
        appArea.getChildren().clear()
        Executions.createComponents("asset/assetCRUD.zul", appArea, null)
    }
}