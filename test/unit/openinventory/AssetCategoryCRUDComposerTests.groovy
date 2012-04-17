package openinventory

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class AssetCategoryCRUDComposerTests {

    void setUp() {
        // Setup logic here
    }

    void tearDown() {
        // Tear down logic here
    }

    void testCreateAssetCategory() {
        AssetCategory assetCategory = new AssetCategory(category:'CPU', details:'This is the details');
		assertNotNull assetCategory.id
    }
}
