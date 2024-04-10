package pe.edu.crisol.libreria.retrofit.response

import pe.edu.crisol.libreria.model.AccessInfo
import pe.edu.crisol.libreria.model.SaleInfo
import pe.edu.crisol.libreria.model.VolumeInfo

data class DetailsResponse (
    val kind: String,
    val id: String,
    val etag: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo,
    val accessInfo: AccessInfo
)