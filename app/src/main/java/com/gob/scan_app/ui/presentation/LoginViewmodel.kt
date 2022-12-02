package com.gob.scan_app.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.gob.scan_app.core.Resource
import com.gob.scan_app.repository.LoginRepository
import kotlinx.coroutines.Dispatchers

class LoginViewmodel (private val repo: LoginRepository) : ViewModel() {

    fun getLogin(user:String,passwd:String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getLogin(user,passwd)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

}

class LoginViewModelFactory(private val repo: LoginRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(LoginRepository::class.java).newInstance(repo)
    }
}