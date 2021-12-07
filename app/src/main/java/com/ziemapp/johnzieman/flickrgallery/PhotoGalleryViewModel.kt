package com.ziemapp.johnzieman.flickrgallery

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PhotoGalleryViewModel: ViewModel() {
    val galleryItemLiveData: LiveData<List<GalleryItem>>

    private val flickrFetchr = FlickrFetchr()
    private val mutableSearch = MutableLiveData<String>()
    init {
        mutableSearch.value = "planets"
        galleryItemLiveData = Transformations.switchMap(mutableSearch) {
            flickrFetchr.searchPhotos(it)
        }
    }

    fun fetchPhotos(query: String = ""){
        mutableSearch.value = query
    }
}