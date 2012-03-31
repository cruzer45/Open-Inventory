package status

import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.Component
import org.zkoss.zul.*
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zk.ui.select.annotation.Listen
import org.zkoss.zk.ui.Executions
import openinventory.Status

class ListStatusComposer extends GrailsComposer {

   
    Window listStatusWindow
    Grid statusGrid
    ListModelList listModel = new ListModelList()
    Paging paging
    Button cmdAdd
    def appArea
    
    
    def afterCompose = { window ->
        // initialize components here
        paging = statusGrid.getPagingChild() 
        statusGrid.setRowRenderer(rowRenderer as RowRenderer)
        statusGrid.setModel(listModel)
        redraw()
    }
    
    void reloadTable()
    {
        statusGrid.setRowRenderer(rowRenderer as RowRenderer)
        listModel = new ListModelList()
        redraw(0)
        statusGrid.setModel(listModel)
        
    }
    
    void onClick_cmdAdd()
    {
        Window win = (Window) Executions.createComponents("status/statusCRUD.zul", listStatusWindow,null)
        win.setClosable(true)
        win.doModal()
    }
    
    void onClick_cmdRefresh()
    {
        //reload the table
        appArea.getChildren().clear()
        Executions.createComponents("status/listStatus.zul", appArea, null)
    }
    
    
    void onPaging_paging(Event e)
    {
        def event = e.origin
        redraw(event.activePage)
    }
    
    private redraw(int activePage = 0) {
        int offset = activePage * paging.pageSize
        int max = paging.pageSize
        def statusInstanceList = Status.createCriteria().list(offset: offset, max: max) {
            //eq("deleted", false)
            order('status','asc')
        }
        paging.totalSize = statusInstanceList.totalCount
        listModel.clear()
        listModel.addAll(statusInstanceList.id)
    }
    
    private rowRenderer = {Row row, Object id, int index ->
        def statusInstance = Status.get(id)
        
        Cell labelCell = new Cell()
        labelCell.setValign('center')
        labelCell.appendChild(new Label(statusInstance.status))
        row.appendChild(labelCell)
        
        Cell enabledCell = new Cell()
        enabledCell.setValign('center')
        
        Label lblenabled = new Label()
        if (statusInstance.deleted)
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
                    String statusID = id
                    map.put("statusID", statusID)
                    Window win = (Window) Executions.createComponents("status/statusCRUD.zul", listStatusWindow,map)
                    win.setClosable(true)
                    win.doModal()
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