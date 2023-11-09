package com.bluetechloop.mvvmtempleteproject.utlis

import com.google.gson.Gson

class SingletonGson{
    companion object
    {
        var gson=Gson()
    }
}