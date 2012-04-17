package openinventory

class AssetService {

	def saveAsset(def params) {
		Asset asset = params.id ? Asset.get(params.id.toLong()) : new Asset()

		asset.assetCategory =  params.assetCategory
		asset.details = params.details 
		asset.make = params.make 
		asset.model = params.model
		asset.serialNumber = params.serialNumber 
		asset.assetTag = params.assetTag 
		asset.aquired = params.aquired 
		asset.status = params.status 
		asset.department = params.department 
		asset.purchasePrice = params.purchasePrice 
		asset.expectedLife =  params.expectedLife 
		asset.salvageValue = params.salvageValue
		asset.currentValue = params.currentValue
		asset.comments = params.comments 
		asset.nextScheduledMaintenance = params.nextScheduledMaintenance 
		
		asset.save()
	}
}
