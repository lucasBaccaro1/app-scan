package com.gob.scan_app.data.remote

import com.gob.scan_app.data.model.LoginResponse
import com.gob.scan_app.repository.WebService

class LoginDataSource (private val webService: WebService){

    suspend fun getLogin(user:String, passwd:String) : LoginResponse = webService.getLogin(user,passwd)
}