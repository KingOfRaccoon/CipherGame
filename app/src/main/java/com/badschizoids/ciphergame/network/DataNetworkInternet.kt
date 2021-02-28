package com.badschizoids.ciphergame.network

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface DataNetworkInternet {
    fun getAllMemes(): Task<DocumentSnapshot>
}