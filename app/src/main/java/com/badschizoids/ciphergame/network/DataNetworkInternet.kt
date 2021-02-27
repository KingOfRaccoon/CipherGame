package com.badschizoids.ciphergame.network

import androidx.lifecycle.MutableLiveData
import com.badschizoids.ciphergame.Mem
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface DataNetworkInternet {
    fun getAllMemes(): Task<DocumentSnapshot>

}