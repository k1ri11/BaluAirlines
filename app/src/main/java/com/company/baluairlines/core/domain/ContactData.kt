package com.company.baluairlines.core.domain


import com.google.gson.annotations.SerializedName

data class ContactData(
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String
)