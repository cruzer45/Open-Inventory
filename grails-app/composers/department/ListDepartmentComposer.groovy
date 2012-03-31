package department

import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.Component
import org.zkoss.zul.*
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zk.ui.select.annotation.Listen
import org.zkoss.zk.ui.Executions
import openinventory.Department


class ListDepartmentComposer extends GrailsComposer {

    Window departmentWindow
    Grid departmentGrid
    ListModelList listModel = new ListModelList()
    Paging paging
    Button cmdAdd
    Button cmdRefresh
    def appArea
    
    
    def afterCompose = { window ->
        // initialize components here
        paging = departmentGrid.getPagingChild() 
        departmentGrid.setRowRenderer(rowRenderer as RowRenderer)
        departmentGrid.setModel(listModel)
        redraw()
    }
    
     
    void reloadTable()
    {
        appArea.getChildren().clear()
        Executions.createComponents("department/listDepartment.zul", appArea, null)
    }
    
    void onClick_cmdAdd()
    {
        //        Window win = (Window) Executions.createComponents("status/statusCRUD.zul", listStatusWindow,null)
        //        win.setClosable(true)
        //        win.doModal()
    }
    
    void onClick_cmdRefresh()
    {
        //reload the table
        reloadTable()
    }
    
    
    void onPaging_paging(Event e)
    {
        def event = e.origin
        redraw(event.activePage)
    }
    
    private redraw(int activePage = 0) {
        int offset = activePage * paging.pageSize
        int max = paging.pageSize
        def deptInstanceList = Department.createCriteria().list(offset: offset, max: max) {
            //eq("deleted", false)
            order('department','asc')
        }
        paging.totalSize = deptInstanceList.totalCount
        listModel.clear()
        listModel.addAll(deptInstanceList.id)
    }
    
    private rowRenderer = {Row row, Object id, int index ->
        def deptInstance = Department.get(id)
        
        Cell labelCell = new Cell()
        labelCell.setValign('center')
        labelCell.appendChild(new Label(deptInstance.department))
        row.appendChild(labelCell)
        
        Cell enabledCell = new Cell()
        enabledCell.setValign('center')
        
        Label lblenabled = new Label()
        if (deptInstance.deleted)
        {
            lblenabled.setValue("Disabled")
        }
        
        else
        {
            lblenabled.setValue("Enabled")
        }
        
        enabledCell.appendChild(lblenabled)
        row.appendChild(enabledCell)
        
        
        Hbox actionsBox = new Hbox()
        
        Button cmdView = new Button(label:'View', image:'/images/heart.png')
        cmdView.addEventListener("onClick", new EventListener(){
                public void onEvent(Event event) throws Exception{
                    HashMap map = new HashMap<String, String>()
                    String deptID = id
                    map.put("deptID", statusID)
                    //                    Window win = (Window) Executions.createComponents("status/statusCRUD.zul", listStatusWindow,map)
                    //                    win.setClosable(true)
                    //                    win.doModal()
                    reloadTable()
                }
            })
        actionsBox.appendChild(cmdView)
       
        Cell actionCell = new Cell()
        actionCell.setValign('center')
        actionCell.appendChild(actionsBox)
        row.appendChild(actionCell)
    }
    
}
