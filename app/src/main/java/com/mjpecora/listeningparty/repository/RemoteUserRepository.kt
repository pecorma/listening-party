package com.mjpecora.listeningparty.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.mjpecora.listeningparty.model.cache.User
import javax.inject.Inject

class RemoteUserRepositoryImpl @Inject constructor(
    private val fbDatabaseReference: DatabaseReference
) : RemoteUserRepository {

    override suspend fun getUser(email: String) =
        fbDatabaseReference.child("users").child(email).get()

    override suspend fun writeNewUser(user: User): Task<Void> {
        return fbDatabaseReference.child("users")
            .child(user.id)
            .setValue(user)
    }


    private fun DataSnapshot.isUserNameTaken(user: User): Boolean {
        return this.children.map {
            it.getValue(User::class.java)
        }.any {
            user.userName == it?.userName
        }
    }

}

interface RemoteUserRepository {
    suspend fun getUser(email: String): Task<DataSnapshot>
    suspend fun writeNewUser(user: User): Task<Void>
}

