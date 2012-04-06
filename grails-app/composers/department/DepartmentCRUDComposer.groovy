package department

import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zk.ui.select.annotation.Listen
import org.zkoss.zul.*
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.Executions
import openinventory.Department

class DepartmentCRUDComposer extends GrailsComposer {

    Department department
    Textbox txtDepartment
    Checkbox chkDeleted
    def appArea
    
    def afterCompose = { window ->
        // initialize components here
        if (arg.containsKey("deptID")) {
            String id =  (String) arg.get("deptID")
            department = Department.get(id)
            populateFields()
        }
    }
    void populateFields()
    {
        txtDepartment.setValue(department.department)
        chkDeleted.setChecked(department.deleted)
    }
    
    void onClick_cmdCancel()
    {
        Event closeEvent = new Event( "onClose", this.self, null )
        Events.postEvent( closeEvent )
    }
    
    void onClick_cmdSave()
    {
        if (!department)
        {
            department = new Department()
        }
        department.department = txtDepartment.getValue()?.trim()
        department.deleted = chkDeleted.isChecked()
        department = department.merge(flush:true)
        department.save(flush:true)
        //TODO Enable the messagebox when that bug us fixed
        Messagebox.show("Department Saved!", "Department", Messagebox.OK, Messagebox.INFORMATION)
        //reload the table
        appArea.getChildren().clear()
        Executions.createComponents("department/listDepartment.zul", appArea, null)
        onClick_cmdCancel()
    }
}
