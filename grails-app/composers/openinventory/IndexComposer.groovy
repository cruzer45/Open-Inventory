package openinventory

import org.zkoss.zk.grails.composer.*

import org.zkoss.zk.ui.select.annotation.Wire
import org.zkoss.zk.ui.select.annotation.Listen
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import grails.plugins.springsecurity.SpringSecurityService

class IndexComposer extends GrailsComposer {

    def springSecurityService
    
    def afterCompose = { window ->
        // initialize components here
        
        if(!springSecurityService.isLoggedIn()){
            execution.sendRedirect('/login')
        }
    }
    
    def beforeCompose = { window ->
        // initialize components here
        
        if(!springSecurityService.isLoggedIn()){
            execution.sendRedirect('/login')
        }
    }
}
