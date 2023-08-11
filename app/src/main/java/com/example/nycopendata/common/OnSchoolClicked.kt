package com.example.nycopendata.common

import com.example.nycopendata.model.remote.SchoolListResponse
//Interface to click on a particular school to get details about the school
interface OnSchoolClicked {
    fun schoolClicked(school: SchoolListResponse)

}