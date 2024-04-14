package pe.edu.crisol.libreria.model

data class Book(
    var kind: String,
    var id: String,
    var etag: String,
    var selfLink: String,
    var volumeInfo: VolumeInfo,
    var saleInfo: SaleInfo,
    var accessInfo: AccessInfo
)