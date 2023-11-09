package com.bluetechloop.mvvmtempleteproject.utlis

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import com.bluetechloop.mvvmtempleteproject.MainApplication


object ArtiPreferenceManager {

    private const val PREF_NAME_1 = Constants.APP_GLOBAL;
    private const val PREF_NAME_2 = Constants.MOVE;

    private var preferences: SharedPreferences =
        MainApplication.instance.getSharedPreferences(PREF_NAME_1, MODE_PRIVATE)
    private var preferencesMove: SharedPreferences =
        MainApplication.instance.getSharedPreferences(PREF_NAME_2, MODE_PRIVATE)
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var editorMove: SharedPreferences.Editor



//    fun setCookies(cookies: HashSet<String>) {
//        editor = preferences.edit()
//        editor.putStringSet(Constants.COOKIES, cookies)
//        editor.apply()
//    }
//
//    fun getCookies(): HashSet<String> {
//        return preferences.getStringSet(Constants.COOKIES, HashSet<String>()) as HashSet<String>
//    }

//    fun setUser(user: User?) {
//        editor = preferences.edit()
//        editor.putBoolean(Constants.USER_LOGED_IN, true)
//        editor.putString(Constants.USER, SingletonGson.gson.toJson(user))
//        editor.apply()
//    }
//
//    fun getUser(): String? {
//        return preferences.getString(Constants.USER, "")
//    }
//    public fun clearUser() {
//        editor = preferences.edit()
//        editor.remove(Constants.USER)
//        editor.apply()
//    }

//    fun getLocale(): String {
//        return preferences.getString(Constants.LOCALE, "en") + ""
//    }

//    fun setLocale(locale: String) {
//        editor = preferences.edit()
//        editor.putString(Constants.LOCALE, locale)
//        editor.apply()
//    }
//
//    fun getLocaleEmoji(): String? {
//        return preferences.getString(Constants.LOCALE_EMOJI, "")
//    }



    fun getToken(): String? {
        return preferences.getString(Constants.TOKEN, "")
    }

    fun setToken(token: String?) {
        Log.v("TOKENOUTH", "$token<-----")
        editor = preferences.edit()
        editor.putString(Constants.TOKEN, token)
        editor.apply()
    }

    public fun removeToken() {
        editor = preferences.edit()
        editor.remove(Constants.TOKEN)
        editor.apply()
    }

    fun getId(): String? {
        return preferences.getString(Constants.USERID, "")
    }

    fun setId(token: String?) {
        Log.v("USERID", "$token<-----")
        editor = preferences.edit()
        editor.putString(Constants.USERID, token)
        editor.apply()
    }

    fun writeString(prefName: String, prefKey: String?, prefValue: String?) {
        when (prefName) {
            Constants.APP_GLOBAL -> {
                editor = preferences.edit()
                editor.putString(prefKey, prefValue)
                editor.apply()
            }
            Constants.MOVE -> {
                editor = preferencesMove.edit()
                editor.putString(prefKey, prefValue)
                editor.apply()
            }

            else->{
                editor = preferences.edit()
                editor.putString(prefKey, prefValue)
                editor.apply()
            }
        }
    }
    fun clearString(prefName: String, prefKey: String?, prefValue: String?) {
        when (prefName) {
            Constants.APP_GLOBAL -> {
                editor = preferences.edit()
                editor.putString(prefKey, prefValue)
                editor.apply()
            }
            Constants.MOVE -> {
                editor = preferencesMove.edit()
                editor.putString(prefKey, prefValue)
                editor.apply()
            }

            else->{
                editor = preferences.edit()
                editor.putString(prefKey, "")
                editor.apply()
            }
        }
    }

    fun readString(prefName: String, prefKey: String?): String {
        when (prefName) {
            Constants.APP_GLOBAL ->{
                return preferences.getString(prefKey, "")!!}
            Constants.MOVE ->{
                return preferencesMove.getString(prefKey, "")!!}

            else->{  return preferences.getString(prefKey, "")!!}
        }
    }



    fun writeStringSet(prefName: String, prefKey: String?, values: HashSet<String>) {
        when (prefName) {
            Constants.APP_GLOBAL -> {
                editor = preferences.edit()
                editor.putStringSet(prefKey, values)
                editor.apply()
            }
            Constants.MOVE -> {
                editor = preferencesMove.edit()
                editor.putStringSet(prefKey, values)
                editor.apply()
            }
        }

    }

    fun readStringSet(prefName: String, prefKey: String?): HashSet<String> {
        when (prefName) {
            Constants.APP_GLOBAL ->
                return preferences.getStringSet(prefKey, HashSet<String>()) as HashSet
            Constants.MOVE ->
                return preferencesMove.getStringSet(prefKey, HashSet<String>()) as HashSet
        }
        return HashSet()
    }

    fun writeInt(prefKey: String?, prefValue: Int) {
        editor = preferences.edit()
        editor.putInt(prefKey, prefValue)
        editor.apply()
    }

    fun readInt(prefKey: String?): Int {
        return preferences.getInt(prefKey, -1)
    }
//
//    fun logout() {
//        editor = preferences.edit()
//        editor.putBoolean(Constants.USER_LOGED_IN,false)
//        editor.apply()
//        removeUser()
//        removeToken()
//        SocketManager.closeSocketConnection()
//    }


    private fun removeUser() {
        editor = preferences.edit()
        editor.remove(Constants.USER)
        editor.apply()
    }

//    fun setCountryCode(code: String?) {
//        editor = preferences.edit()
//        editor.putString(Constants.COUNTRY_CODE, code)
//        editor.apply()
//    }
//
//    fun getCountryCode(): String? {
//        return preferences.getString(Constants.COUNTRY_CODE, "");
//    }

    fun clearPreferences(prefName: String) {
        when (prefName) {
            Constants.MOVE -> {
                editor = preferencesMove.edit()
                editor.clear()
                editor.apply()
            }
        }

    }

    fun writeBoolean(prefName: String, prefKey: String, value: Boolean) {
        when (prefName) {
            Constants.APP_GLOBAL -> {
                editor = preferences.edit()
                editor.putBoolean(prefKey, value)
                editor.apply()
            }
            Constants.MOVE -> {
                editor = preferencesMove.edit()
                editor.putBoolean(prefKey, value)
                editor.apply()
            }
        }
    }

    fun readBoolean(prefName: String, prefKey: String): Boolean {
        when (prefName) {
            Constants.APP_GLOBAL ->
                return preferences.getBoolean(prefKey, false)!!
            Constants.MOVE ->
                return preferencesMove.getBoolean(prefKey, false)!!
        }
        return false
    }

//    fun setLatLong(latitude: String, longitude: String) {
//        editor = preferences.edit()
//        editor.putString(Constants.LATITUDE, latitude)
//        editor.putString(Constants.LONGITUDE, longitude)
//        editor.apply()
//    }
//
//    fun getLatLong(): HashMap<String, String> {
//        val list = HashMap<String, String>()
//        list.put(Constants.LATITUDE, preferences.getString(Constants.LATITUDE, "")!!)
//        list.put(Constants.LONGITUDE, preferences.getString(Constants.LONGITUDE, "")!!)
//        return list
//    }

//    fun setFCMToken(token: String?) {
//        editor = preferences.edit()
//        editor.putString(Constants.FCM_TOKEN, token)
//        editor.apply()
//    }
//
//    fun getFCMToken()=preferences.getString(Constants.FCM_TOKEN, "")

//    fun setAreaId(areaId: String?) {
//        editor = preferences.edit()
//        editor.putString(Constants.AREA_ID, areaId)
//        editor.apply()
//    }
//
//
//    fun getAreaId()= preferences.getString(Constants.AREA_ID,"")
//    fun setFavGarage(garageId: String,check:Boolean) {
//        editor = preferences.edit()
//        editor.putBoolean(garageId, check)
//        editor.apply()
//    }
//    fun getFavGarage(garageId: String): Boolean = preferences.getBoolean(garageId,false)

}