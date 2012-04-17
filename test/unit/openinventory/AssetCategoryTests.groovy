package openinventory



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(AssetCategory)
class AssetCategoryTests {

    void testCreateAssetCategory() {
        AssetCategory assetCategory = new AssetCategory(category:'CPU', details:'This is the details',deleted:'false').save();
		assertNotNull assetCategory.id
    }
}
