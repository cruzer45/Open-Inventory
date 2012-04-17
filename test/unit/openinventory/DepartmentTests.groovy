package openinventory



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Department)
class DepartmentTests {

    void testCreateDepartment() {
       def dept = new Department(department: 'Accounts',deleted: false ).save()
	   assertNotNull(dept)
    }
}
