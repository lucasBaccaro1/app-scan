package com.gob.scan_app.repository

import com.gob.scan_app.data.model.LoginResponse
import com.gob.scan_app.data.remote.LoginDataSource

class LoginRepositoryImpl (private val loginDataSource: LoginDataSource):LoginRepository{

    override suspend fun getLogin(user: String, passwd: String): LoginResponse {
        return loginDataSource.getLogin(user,passwd)
    }

}