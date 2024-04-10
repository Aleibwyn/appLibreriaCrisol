package pe.edu.crisol.libreria.model

data class VolumeInfo (
    var title: String,
    var authors: List<String>,
    var publisher: String,
    var publishedDate: String,
    var description: String,
    var readingModes: ReadingModes,
    var maturityRating: String,
    var allowAnonLogging: Boolean,
    var contentVersion: String,
    var panelizationSummary: PanelizationSummary,
    var imageLinks: ImageLink,
    var previewLink: String,
    var infoLink: String,
    var canonicalVolumeLink: String
)
