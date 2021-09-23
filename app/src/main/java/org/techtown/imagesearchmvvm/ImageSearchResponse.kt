package org.techtown.imagesearchmvvm

data class ImageSearchResponse(
    val documents: ArrayList<Document>,
    val meta: Meta
)