package com.eleith.hashtagit


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BackgroundViewModel: ViewModel() {
    var backgroundColor = MutableLiveData<Boolean>()
}