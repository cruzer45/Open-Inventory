package assetCategory

import org.zkoss.zk.grails.composer.*
import org.zkoss.zk.ui.Component
import org.zkoss.zul.*
import org.zkoss.zk.ui.event.*
import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zk.ui.select.annotation.Listen
import org.zkoss.zk.ui.Executions
import openinventory.AssetCategory

class ListAssetCategoriesComposer extends GrailsComposer {

 	Window assetCategoryWindow
    Grid assetCategoryGrid
    ListModelList listModel = new ListModelList()
    Paging paging
    Button cmdAdd
    Button cmdRefresh
    def appArea

    def afterCompose = { window ->
        // initialize components here
        paging = assetCategoryGrid.getPagingChild() 
        assetCategoryGrid.setRowRenderer(rowRenderer as RowRenderer)
        assetCategoryGrid.setModel(listModel)
        redraw()
    }

        void reloadTable()
    {
        appArea.getChildren().clear()
        Executions.createComponents("assetCategory/listAssetCategories.zul", appArea, null)
    }
    
    void onClick_cmdAdd()
    {
        Window win = (Window) Executions.createComponents("assetCategory/assetCategoryCRUD.zul", assetCategoryWindow,null)
        win.setClosable(true)
        win.doModal()
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
        def assetCategoryInstanceList = AssetCategory.createCriteria().list(offset: offset, max: max) {
            //eq("deleted", false)
            order('category','asc')
        }
        paging.totalSize = assetCategoryInstanceList.totalCount
        listModel.clear()
        listModel.addAll(assetCategoryInstanceList.id)
    }
    
    private rowRenderer = {Row row, Object id, int index ->
        def assetCategoryInstance = AssetCategory.get(id)
        
        Cell labelCell = new Cell()
        labelCell.setValign('center')
        labelCell.appendChild(new Label(assetCategoryInstance.category))
        row.appendChild(labelCell)
        
        Cell enabledCell = new Cell()
        enabledCell.setValign('center')
        
        Label lblenabled = new Label()
        if (assetCategoryInstance.deleted)
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
        
        Button cmdView = new Button(label:'View', image:'/images/computer.png')
        cmdView.addEventListener("onClick", new EventListener(){
                public void onEvent(Event event) throws Exception{
                    HashMap map = new HashMap<String, String>()
                    String assetCategoryID = id
                    map.put("assetCategoryID", assetCategoryID)
                    Window win = (Window) Executions.createComponents("assetCategory/assetCategoryCRUD.zul", assetCategoryWindow,map)
                    win.setClosable(true)
                    win.doModal()
                }
            })
        actionsBox.appendChild(cmdView)
       
        Cell actionCell = new Cell()
        actionCell.setValign('center')
        actionCell.appendChild(actionsBox)
        row.appendChild(actionCell)
    }
}
