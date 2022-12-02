package com.gob.scan_app.repository

import com.gob.scan_app.data.model.LoginResponse

interface LoginRepository {
    suspend fun getLogin(user:String, passwd:String):LoginResponse
}