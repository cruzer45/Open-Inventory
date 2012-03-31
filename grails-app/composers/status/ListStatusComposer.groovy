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
            //order('firstName','desc')
        }
        paging.totalSize = statusInstanceList.totalCount
        listModel.clear()
        listModel.addAll(statusInstanceList.id)
    }
    
    private rowRenderer = {Row row, Object id, int index ->
        def statusInstance = Status.get(id)
        row.appendChild(new Label(statusInstance.status))
        Hbox actionsBox = new Hbox()
        
        Button cmdView = new Button(label:'View', image:'/images/heart.png')
        cmdView.addEventListener("onClick", new EventListener(){
                public void onEvent(Event event) throws Exception{
                    HashMap map = new HashMap<String, String>()
                    String statusID = id
                    map.put("statusID", statusID)
//                    Window win = (Window) Executions.createComponents("personCRUD.zul", personListWindow,map)
//                    win.setClosable(true)
//                    win.doModal()
                    reloadTable()
                }
            })
        actionsBox.appendChild(cmdView)
        
        Button cmdDelete = new Button(label:'Delete', image:'/images/heart_delete.png')
        cmdDelete.addEventListener("onClick", new EventListener(){
                public void onEvent(Event event) throws Exception{
                    listModel.remove(personInstance.id)
                    statusInstance.deleted = true
                    statusInstance.merge(flush:true)
                    reloadTable()
                    Messagebox.show("Status Deleted!", "Status", Messagebox.OK, Messagebox.INFORMATION)
                }
            })
        actionsBox.appendChild(cmdDelete)

        row.appendChild(actionsBox)
    }
    
}
