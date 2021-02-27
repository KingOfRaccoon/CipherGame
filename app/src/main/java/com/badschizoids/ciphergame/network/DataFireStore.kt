package com.badschizoids.ciphergame.network

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*

class DataFireStore: DataNetworkInternet {
    val fireStore = FirebaseFirestore.getInstance()

    override fun getAllMemes(): Task<DocumentSnapshot> {
        return fireStore.collection(memesTag)
            .document("ZBDhwE8hTRPqwlV2oSKl")
            .get()
    }
    companion object{
        val memesTag = "memes"
    }
}