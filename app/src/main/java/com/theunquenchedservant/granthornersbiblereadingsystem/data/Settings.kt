package com.theunquenchedservant.granthornersbiblereadingsystem.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required
import org.bson.types.ObjectId

@RealmClass
open class Settings : RealmModel {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var userID: String="None"
    var psalms:Boolean = false
    var allowPartial:Boolean=false
    var holdPlan:Boolean=false
    var notifications:Boolean=false
    var dailyNotif:Int=300
    var remindNotif:Int=1200
    var vacationMode:Boolean=false
    var versionNumber:Int=89
    var darkMode:Boolean=true
    var planType:String="horner"
    var bibleVersion:String="esv"
    var planSystem:String="pgh"
    var hasCompletedOnboarding:Boolean=false

    constructor(userId:String, psalms:Boolean, allowPartial:Boolean,holdPlan:Boolean, notifications:Boolean, dailyNotif:Int, remindNotif:Int, vacationMode: Boolean,
                versionNumber:Int, darkMode:Boolean, planType:String, bibleVersion:String, planSystem:String, hasCompletedOnboarding: Boolean)  {
        this.userID = userId
        this.psalms = psalms
        this.allowPartial = allowPartial
        this.holdPlan = holdPlan
        this.notifications=notifications
        this.dailyNotif=dailyNotif
        this.remindNotif=remindNotif
        this.vacationMode=vacationMode
        this.versionNumber=versionNumber
        this.darkMode=darkMode
        this.planType=planType
        this.bibleVersion=bibleVersion
        this.planSystem=planSystem
        this.hasCompletedOnboarding=hasCompletedOnboarding
    }

    constructor() {}
}