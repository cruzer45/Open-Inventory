package openinventory

import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zk.ui.select.annotation.Listen
import org.zkoss.zk.ui.Component
import org.zkoss.zul.*
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.Executions

class MenuComposer extends GrailsComposer {

    Menuitem cmdListStatus
    def appArea
    
    def afterCompose = { window ->
        // initialize components here
    }
    
    void onClick_cmdListStatus(){
        appArea.getChildren().clear()
        Executions.createComponents("status/listStatus.zul", appArea, null)
    }
}
