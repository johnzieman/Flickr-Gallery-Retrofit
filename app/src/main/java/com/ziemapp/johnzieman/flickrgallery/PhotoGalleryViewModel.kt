package com.ziemapp.johnzieman.flickrgallery

import android.app.Application
import android.app.DownloadManager
import androidx.lifecycle.*

class PhotoGalleryViewModel(private val app: Application): AndroidViewModel(app) {
    val galleryItemLiveData: LiveData<List<GalleryItem>>

    private val flickrFetchr = FlickrFetchr()
    private val mutableSearch = MutableLiveData<String>()
    init {
        mutableSearch.value = com.ziemapp.johnzieman.flickrgallery.QueryPreferences.getStoredQuery(app)
        galleryItemLiveData = Transformations.switchMap(mutableSearch) {
            if (it.isBlank()) {
                flickrFetchr.fetchPhotos()
            } else {
                flickrFetchr.searchPhotos(it)
            }

        }
    }

    fun fetchPhotos(query: String = ""){
        QueryPreferences.setStoredQuery(app, query)
        mutableSearch.value = query
    }
}