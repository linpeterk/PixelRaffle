package com.revature.pixelraffle.ui.screens.adama

fun UserValidations(regFirstName: String, regLastName: String, regEmail: String, regPassword: String): String{
    var validationStatus: String = ""

    if(regFirstName.isNullOrEmpty()||regLastName.isNullOrEmpty()||regEmail.isNullOrEmpty()||regPassword.isNullOrEmpty()){
        validationStatus = "Please, field cannot be blank"
    }else if(regFirstName.isNullOrEmpty()){
        validationStatus = "First Name is required"
    }else if(regLastName.isNullOrEmpty()){
        validationStatus = "Last Name is required"
    }else if(regEmail.isNullOrEmpty()){
        validationStatus = "Email is required"
    }else if(regPassword.isNullOrEmpty()){
        validationStatus = "Password is required"
    }else{
        validationStatus = "Welcome!"
    }
    return validationStatus
}