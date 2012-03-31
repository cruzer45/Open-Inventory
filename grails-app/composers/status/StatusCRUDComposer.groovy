package status

import org.zkoss.zk.grails.composer.*

import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zk.ui.select.annotation.Listen
import org.zkoss.zul.*
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.Executions
import openinventory.Status

class StatusCRUDComposer extends GrailsComposer {

    Status status
    Textbox txtStatus
    Checkbox chkDeleted
    def appArea
    
    def afterCompose = { window ->
        // initialize components here
        if (arg.containsKey("statusID")) {
            String id =  (String) arg.get("statusID")
            status = Status.get(id)
            populateFields()
        }
    }
    void populateFields()
    {
        txtStatus.setValue(status.status)
        chkDeleted.setChecked(status.deleted)
    }
    
    void onClick_cmdCancel()
    {
        Event closeEvent = new Event( "onClose", this.self, null )
        Events.postEvent( closeEvent )
    }
    
    void onClick_cmdSave()
    {
        if (!status)
        {
            status = new Status()
        }
        status.status = txtStatus.getValue()?.trim()
        status.deleted = chkDeleted.isChecked()
        status = status.merge(flush:true)
        status.save(flush:true)
        //TODO Enable the messagebox when that bug us fixed
        Messagebox.show("Status Saved!", "Status", Messagebox.OK, Messagebox.INFORMATION)
        //reload the table
        appArea.getChildren().clear()
        Executions.createComponents("status/listStatus.zul", appArea, null)
        onClick_cmdCancel()
    }
    
}
