package com.example.semesterproject

import android.app.Application

class GachaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GachaRepository.initialize(this)
    }
}