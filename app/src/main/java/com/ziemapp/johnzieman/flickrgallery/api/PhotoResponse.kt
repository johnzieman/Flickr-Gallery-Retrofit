package com.ziemapp.johnzieman.flickrgallery.api

import com.google.gson.annotations.SerializedName
import com.ziemapp.johnzieman.flickrgallery.GalleryItem

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}