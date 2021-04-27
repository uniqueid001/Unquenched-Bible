package com.theunquenchedservant.granthornersbiblereadingsystem

import android.app.Application
import android.content.Context
import io.realm.Realm
import io.realm.mongodb.AppConfiguration
import io.realm.mongodb.App
import io.realm.mongodb.Credentials

class MainApp : Application () {
    init {
        instance = this
    }
    companion object{
        private var instance : MainApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}