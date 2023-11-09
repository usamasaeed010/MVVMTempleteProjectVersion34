package com.example.zameedar.viewmodel

import androidx.lifecycle.ViewModel
import com.example.zameedar.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


//    var resetLiveData: MutableLiveData<ResetModel>? = null

//    private val resource = Resource(Status.LOADING, null, null)
//    private var _loginDataModelList = MutableLiveData<Resource<LoginDataModel>>(resource)
//    val loginDataModelList: LiveData<Resource<LoginDataModel>>
//        get() = _loginDataModelList


//    fun getLogin(email: String, password: String) {
//        _loginDataModelList.postValue(resource)
//        viewModelScope.launch(Dispatchers.IO) {
//            async {
//                try {
//                    val response = repository.getLoginData(email, password)
////                     Log.e("usamaaa", Gson().toJson(response))
//                    if (response.isSuccessful) {
//                        _loginDataModelList.postValue(
//                            Resource(
//                                Status.SUCCESS,
//                                response.body(),
//                                response.message()
//                            )
//                        )
//                    }
//                    else
//                        _loginDataModelList.postValue(
//                            Resource(
//                                Status.ERROR,
//                                response.body(),
//                                response.message()
//                            )
//                        )
//                } catch (e: Exception) {
//                    _loginDataModelList.postValue(Resource(Status.ERROR, null, e.message))
//                }
//            }
//        }
//    }
}