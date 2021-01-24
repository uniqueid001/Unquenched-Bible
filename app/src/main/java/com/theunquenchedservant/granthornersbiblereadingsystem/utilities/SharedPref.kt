package com.theunquenchedservant.granthornersbiblereadingsystem.utilities

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.preference.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.theunquenchedservant.granthornersbiblereadingsystem.App
import com.theunquenchedservant.granthornersbiblereadingsystem.MainActivity
import com.theunquenchedservant.granthornersbiblereadingsystem.data.Books.bookChapters
import com.theunquenchedservant.granthornersbiblereadingsystem.data.Books.ntBooks
import com.theunquenchedservant.granthornersbiblereadingsystem.data.Books.otBooks
import com.theunquenchedservant.granthornersbiblereadingsystem.utilities.Dates.checkDate
import java.io.File


object SharedPref {

    fun setStreak(){
        if(!checkDate("both", false)){
            setIntPref("currentStreak", 0)
        }
    }
    private fun getPref(): SharedPreferences{
        val context = App.applicationContext()
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
    fun updateFS(name: String, value: Any) {
        val db = FirebaseFirestore.getInstance()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null)
            db.collection("main").document(user.uid).update(name, value)
    }

    fun setIntPref(name: String, value: Int, updateFS:Boolean=false): Int{
        getPref().edit().putInt(name, value).apply()
        if(updateFS) {
            updateFS(name, value)
        }
        return value
    }
    private fun deletePref(name:String){
        getPref().edit().remove(name).apply()
    }
    fun increaseIntPref(name: String, value: Int, updateFS:Boolean=false): Int{
        val start = getIntPref(name)
        setIntPref(name, start+value)
        if(updateFS) {
            updateFS(name, start + value)
        }
        return start+value
    }
    fun getIntPref(name: String, defaultValue: Int = 0): Int {
        return getPref().getInt(name, defaultValue)
    }

    fun setStringPref(name:String, value: String, updateFS: Boolean = false):String {
        getPref().edit().putString(name, value).apply()
        if(updateFS) {
            updateFS(name, value)
        }
        return value
    }
    fun getStringPref(name:String, defaultValue: String = "itsdeadjim"): String{
        return getPref().getString(name, defaultValue)!!
    }

    fun setBoolPref(name: String, value: Boolean, updateFS:Boolean=false):Boolean{
        getPref().edit().putBoolean(name, value).apply()
        if(updateFS) {
            updateFS(name, value)
        }
        return value
    }
    fun getBoolPref(name: String, defaultValue: Boolean=false): Boolean{
        return getPref().getBoolean(name, defaultValue)
    }

    private val user = FirebaseAuth.getInstance().currentUser

    fun preferenceToFireStone(){
        val db = FirebaseFirestore.getInstance()
        val user2 = FirebaseAuth.getInstance().currentUser
        val results = mutableMapOf<String?, Any?>()
        for(i in 1..10){
            results["list$i"] = getIntPref(name="list$i")
            results["list${i}Done"] = getIntPref(name="list${i}Done")
            results["list${i}DoneDaily"] = getIntPref(name="list${i}DoneDaily")
        }
        for(i in 1..4){
            results["mcheyneList$i"] = getIntPref(name="mcheyneList$i")
            results["mcheyneList${i}Done"] = getIntPref(name="mcheyneList${i}Done")
            results["mcheyneList${i}DoneDaily"] = getIntPref(name="mcheyneList${i}DoneDaily")
        }
        results["listsDone"] = getIntPref(name="listsDone")
        results["currentStreak"] = getIntPref(name="currentStreak")
        results["dailyStreak"] = getIntPref(name="dailyStreak")
        results["maxStreak"] = getIntPref(name="maxStreak")
        results["notifications"] = getBoolPref(name="notifications")
        results["psalms"] = getBoolPref(name="psalms")
        results["holdPlan"] = getBoolPref(name="holdPlan")
        results["graceTime"] = getIntPref(name="graceTime")
        results["isGrace"] = getBoolPref(name="isGrace")
        results["currentDayIndex"] = getIntPref(name="currentDayIndex")
        results["mcheyneCurrentDayIndex"] = getIntPref(name="mcheyneCurrentDayIndex")
        results["grantHorner"] = getBoolPref(name="grantHorner", defaultValue=true)
        results["numericalDay"] = getBoolPref(name="numericalDay", defaultValue=false)
        results["calendarDay"] = getBoolPref(name="calendarDay", defaultValue=false)
        results["vacationMode"] = getBoolPref(name="vacationMode")
        results["allowPartial"] = getBoolPref(name="allowPartial")
        results["dailyNotif"] = getIntPref( name="dailyNotif")
        results["remindNotif"] = getIntPref(name="remindNotif")
        results["dateChecked"] = getStringPref( name="dateChecked")
        results["versionNumber"] = getIntPref(name="versionNumber")
        results["darkMode"] = getBoolPref(name="darkMode", defaultValue=true)
        results["planType"] = getStringPref(name="planType", defaultValue="horner")
        results["bibleVersion"] = getStringPref(name="bibleVersion", defaultValue="esv")
        results["oldChaptersRead"] = getIntPref(name="oldChaptersRead")
        results["newChaptersRead"] = getIntPref(name="newChaptersRead")
        results["oldAmountRead"] = getIntPref(name="oldAmountRead")
        results["newAmountRead"] = getIntPref(name="newAmountRead")
        results["bibleAmountRead"] = getIntPref(name="bibleAmountRead")
        results["totalChaptersRead"] = getIntPref(name="totalChaptersRead")
        results["planSystem"] = getStringPref(name="planSystem")
        results["pghSystem"] = getBoolPref(name="pghSystem")
        results["mcheyneSystem"] = getBoolPref(name="mcheyneSystem")
        results["hasCompletedOnboarding"] = getBoolPref(name="hasCompletedOnboarding")
        for(book in otBooks){
            results["${book}AmountRead"] = getIntPref(name="${book}AmountRead")
            results["${book}ChaptersRead"] = getIntPref(name="${book}ChaptersRead")
            results["${book}DoneTestament"] = getBoolPref(name="${book}DoneTestament")
            results["${book}DoneWhole"] = getBoolPref(name="${book}DoneWhole")
            for(chapter in 1..(bookChapters[book] ?: error(""))){
                results["${book}${chapter}Read"] = getBoolPref(name="${book}${chapter}Read")
                results["${book}${chapter}AmountRead"] = getIntPref(name="${book}${chapter}AmountRead")
            }
        }
        for(book in ntBooks){
            results["${book}AmountRead"] = getIntPref(name="${book}AmountRead")
            results["${book}ChaptersRead"] = getIntPref(name="${book}ChaptersRead")
            results["${book}DoneTestament"] = getBoolPref(name="${book}DoneTestament")
            results["${book}DoneWhole"] = getBoolPref(name="${book}DoneWhole")
            for(chapter in 1..(bookChapters[book] ?: error(""))){
                results["${book}${chapter}Read"] = getBoolPref(name="${book}${chapter}Read")
                results["${book}${chapter}AmountRead"] = getIntPref(name="${book}${chapter}AmountRead")
            }
        }
        db.collection("main").document(user2!!.uid).set(results)
                .addOnSuccessListener { MainActivity.log("Data transferred to firestore") }
                .addOnFailureListener {e -> Log.w("PROFGRANT", "Error writing to firestore", e) }
    }
    private fun updateIntPref(data: MutableMap<String, Any>, key:String, secondKey: String=""){
        val prefKey = if(secondKey == ""){
            key
        }else{
            secondKey
        }
        if(data[key] != null){
            setIntPref(prefKey, (data[key] as Long).toInt())
        }
    }
    private fun updateBoolPref(data: MutableMap<String, Any>, key:String, secondKey:String = ""){
        val prefKey = if (secondKey == ""){
            key
        }else{
            secondKey
        }
        if(data[key] != null){
            setBoolPref(prefKey, data[key] as Boolean)
        }
    }
    private fun updateStringPref(data: MutableMap<String, Any>, key:String, secondKey:String=""){
        val prefKey = if(secondKey == ""){
            key
        }else{
            secondKey
        }
        if(data[key] != null){
            setStringPref(prefKey, data[key] as String)
        }
    }
    fun firestoneToPreference(database: DocumentSnapshot){
        val data = database.data
        if(data != null) {
            MainActivity.log("User document exists")
            for (i in 1..10) {
                updateIntPref(data, key="list${i}")
                updateIntPref(data, key="list${i}Done")
                updateIntPref(data, key="list${i}DoneDaily")
            }
            for (i in 1..4){
                updateIntPref(data, key="mcheyneList$i")
                updateIntPref(data, key="mcheyneList${i}Done")
                updateIntPref(data, key="mcheyneList${i}DoneDaily")
            }
            updateIntPref(data, key="dailyStreak")
            updateIntPref(data, key="currentStreak")
            updateIntPref(data, key="maxStreak")
            updateBoolPref(data, key="psalms")
            updateBoolPref(data, key="allowPartial")
            updateBoolPref(data, key="vacationMode")
            updateBoolPref(data, key="notifications")
            updateIntPref(data, key="dailyNotif")
            updateIntPref(data, key="remindNotif")
            updateStringPref(data, key="dateChecked")
            updateBoolPref(data, key="holdPlan")
            updateIntPref(data, key="versionNumber")
            updateBoolPref(data, key="darkMode")
            updateIntPref(data, key="listsDone")
            updateStringPref(data, key="planType")
            updateStringPref(data, key="bibleVersion")
            updateIntPref(data, key="oldChaptersRead")
            updateIntPref(data, key="newChaptersRead")
            updateIntPref(data, key="oldAmountRead")
            updateIntPref(data, key="newAmountRead")
            updateIntPref(data, key="bibleAmountRead")
            updateIntPref(data, key="totalChaptersRead")
            updateIntPref(data, key="mcheyneCurrentDayIndex")
            updateIntPref(data, key="currentDayIndex")
            updateBoolPref(data, key="grantHorner")
            updateBoolPref(data, key="numericalDay")
            updateBoolPref(data, key="calendarDay")
            updateIntPref(data, key="graceTime")
            updateBoolPref(data, key="isGrace")
            updateStringPref(data, key="planSystem")
            updateBoolPref(data, key="pghSystem")
            updateBoolPref(data, key="mcheyneSystem")
            updateBoolPref(data, key="hasCompletedOnboarding")
            for(book in otBooks){
                updateIntPref(data, key="${book}AmountRead")
                updateIntPref(data, key="${book}ChaptersRead")
                updateBoolPref(data, key="${book}DoneTestament")
                updateBoolPref(data, key="${book}DoneWhole")
                for(chapter in 1..(bookChapters[book] ?: error(""))){
                    updateBoolPref(data, key="${book}${chapter}Read")
                    updateIntPref(data, key="${book}${chapter}AmountRead")
                }
            }
            for(book in ntBooks){
                updateIntPref(data, key="${book}AmountRead")
                updateIntPref(data, key="${book}ChaptersRead")
                updateBoolPref(data, key="${book}DoneTestament")
                updateBoolPref(data, key="${book}DoneWhole")
                for(chapter in 1..(bookChapters[book] ?: error(""))){
                    updateBoolPref(data, key="${book}${chapter}Read")
                    updateIntPref(data, key="${book}${chapter}AmountRead")
                }
            }
        }
    }

    fun listNumbersReset() { App.applicationContext().getSharedPreferences("listNumbers", Context.MODE_PRIVATE).edit().clear().apply() }
    fun updatePrefNames(){
        for(i in 1..4){
            setIntPref(name="mcheyneList${i}", value=getIntPref(name="mcheyne_list${i}"))
            setIntPref(name="mcheyneList${i}Done", value=getIntPref(name="mcheyne_list${i}Done"))
            setIntPref(name="mcheyneList${i}DoneDaily", value=getIntPref(name="mcheyne_list${i}DoneDaily"))
            deletePref(name="mcheyne_list${i}")
            deletePref(name="mcheyne_list${i}Done")
            deletePref(name="mcheyne_list${i}DoneDaily")
        }
        setIntPref(name="mcheyneCurrentDayIndex", value=getIntPref(name="mcheyne_currentDayIndex"))
        setBoolPref(name="notifications", value= getBoolPref(name="notif_switch"))
        setBoolPref(name="vacationMode", value= getBoolPref(name="vacation_mode"))
        setBoolPref(name="allowPartial", value=getBoolPref(name="allow_partial_switch"))
        setIntPref(name="dailyNotif", value=getIntPref(name="daily_time"))
        setIntPref(name="remindNotif", value=getIntPref(name="remind_time"))
        setIntPref(name="oldChaptersRead", value=getIntPref(name="old_chapters_read"))
        setIntPref(name="newChaptersRead", value=getIntPref(name="new_chapters_read"))
        setIntPref(name="oldAmountRead", value=getIntPref(name="old_amount_read"))
        setIntPref(name="newAmountRead", value=getIntPref(name="new_amount_read"))
        setIntPref(name="bibleAmountRead", value=getIntPref(name="bible_amount_read"))
        setIntPref(name="totalChaptersRead", value=getIntPref(name="total_chapters_read"))
        setBoolPref(name="pghSystem", value=getBoolPref(name="pgh_system"))
        setBoolPref(name="mcheyneSystem", value=getBoolPref(name="mcheyne_system"))
        deletePref(name="mcheyne_currentDayIndex")
        deletePref(name="notif_switch")
        deletePref(name="vacation_mode")
        deletePref(name="allow_partial_switch")
        deletePref(name="daily_time")
        deletePref(name="remind_time")
        deletePref(name="old_chapters_read")
        deletePref(name="new_chapters_read")
        deletePref(name="old_amount_read")
        deletePref(name="new_amount_read")
        deletePref(name="bible_amount_read")
        deletePref(name="total_chapters_read")
        deletePref(name="pgh_system")
        deletePref(name="mcheyne_system")
        for(book in otBooks){
            setIntPref(name="${book}AmountRead", value=getIntPref(name="${book}_amount_read"))
            setIntPref(name="${book}ChaptersRead", value=getIntPref(name="${book}_chapters_read"))
            setBoolPref(name="${book}DoneTestament", value=getBoolPref(name="${book}_done_testament"))
            setBoolPref(name="${book}DoneWhole", value= getBoolPref(name="${book}_done_whole"))
            deletePref(name="${book}_amount_read")
            deletePref(name="${book}_chapters_read")
            deletePref(name="${book}_done_testament")
            deletePref(name="${book}_done_whole")
            for(chapter in 1..(bookChapters[book] ?: error(""))){
                setBoolPref(name="${book}${chapter}Read", value=getBoolPref(name="${book}_${chapter}_read"))
                setIntPref(name="${book}${chapter}AmountRead", value=getIntPref(name="${book}_${chapter}_amount_read"))
                deletePref(name="${book}_${chapter}_read")
                deletePref(name="${book}_${chapter}_amount_read")
            }
        }
        for(book in ntBooks){
            setIntPref(name="${book}AmountRead", value=getIntPref(name="${book}_amount_read"))
            setIntPref(name="${book}ChaptersRead", value=getIntPref(name="${book}_chapters_read"))
            setBoolPref(name="${book}DoneTestament", value=getBoolPref(name="${book}_done_testament"))
            setBoolPref(name="${book}DoneWhole", value= getBoolPref(name="${book}_done_whole"))
            deletePref(name="${book}_amount_read")
            deletePref(name="${book}_chapters_read")
            deletePref(name="${book}_done_testament")
            deletePref(name="${book}_done_whole")
            for(chapter in 1..(bookChapters[book] ?: error(""))){
                setBoolPref(name="${book}${chapter}Read", value=getBoolPref(name="${book}_${chapter}_read"))
                setIntPref(name="${book}${chapter}AmountRead", value=getIntPref(name="${book}_${chapter}_amount_read"))
                deletePref(name="${book}_${chapter}_read")
                deletePref(name="${book}_${chapter}_amount_read")
            }
        }
    }
    fun mergePref(){
        val context = App.applicationContext()
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val listPref = context.getSharedPreferences("listNumbers", Context.MODE_PRIVATE)
        val statPref = context.getSharedPreferences("statistics", Context.MODE_PRIVATE)
        pref.edit().putInt("list1", listPref.getInt("List 1", 0)).apply()
        pref.edit().putInt("list2", listPref.getInt("List 2", 0)).apply()
        pref.edit().putInt("list3", listPref.getInt("List 3", 0)).apply()
        pref.edit().putInt("list4", listPref.getInt("List 4", 0)).apply()
        pref.edit().putInt("list5", listPref.getInt("List 5", 0)).apply()
        pref.edit().putInt("list6", listPref.getInt("List 6", 0)).apply()
        pref.edit().putInt("list7", listPref.getInt("List 7", 0)).apply()
        pref.edit().putInt("list8", listPref.getInt("List 8", 0)).apply()
        pref.edit().putInt("list9", listPref.getInt("List 9", 0)).apply()
        pref.edit().putInt("list10", listPref.getInt("List 10", 0)).apply()
        pref.edit().putInt("list1Done", listPref.getInt("list1Done", 0)).apply()
        pref.edit().putInt("list2Done", listPref.getInt("list2Done", 0)).apply()
        pref.edit().putInt("list3Done", listPref.getInt("list3Done", 0)).apply()
        pref.edit().putInt("list4Done", listPref.getInt("list4Done", 0)).apply()
        pref.edit().putInt("list5Done", listPref.getInt("list5Done", 0)).apply()
        pref.edit().putInt("list6Done", listPref.getInt("list6Done", 0)).apply()
        pref.edit().putInt("list7Done", listPref.getInt("list7Done", 0)).apply()
        pref.edit().putInt("list8Done", listPref.getInt("list8Done", 0)).apply()
        pref.edit().putInt("list9Done", listPref.getInt("list9Done", 0)).apply()
        pref.edit().putInt("list10Done", listPref.getInt("list10Done", 0)).apply()
        pref.edit().putInt("listsDone", listPref.getInt("listsDone", 0)).apply()
        pref.edit().putInt("currentStreak", statPref.getInt("currentStreak", 0)).apply()
        pref.edit().putInt("maxStreak", statPref.getInt("maxStreak", 0)).apply()
        pref.edit().putInt("dailyStreak", statPref.getInt("dailyStreak", 0)).apply()
        pref.edit().putString("dateChecked", listPref.getString("dateChecked", "Jan 01")).apply()
        pref.edit().putBoolean("has_merged", true).apply()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.deleteSharedPreferences("statistics")
            context.deleteSharedPreferences("listNumbers")
        }else {
            val dir = File(context.filesDir?.parent + "/shared_prefs/")
            File(dir, "statistics.xml").delete()
            File(dir, "listNumber.xml").delete()
        }
    }
}