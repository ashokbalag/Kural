package com.abc.`in`.kural.navigation

import androidx.annotation.StringRes
import com.abc.`in`.kural.R

object NavigationInfo {

    enum class NavigationScreen(@StringRes val title: Int) {
        //Start(title = R.string.screen_home),
        Home(title = R.string.screen_name),
        Aathisudi(title = R.string.screen_name_2),
        Thirukkural(title = R.string.screen_name_1),
        Iyal(title = R.string.screen_name_1),
        Paal(title = R.string.screen_name_1),
        Kuralkal(title = R.string.screen_name_1),
        Kural(title = R.string.screen_name_1)
    }

}

//Navigation
//Home -> Thirukkural | Aathisudi
//Thirukkural -> Adhikaaram -> TenKuralkal-> Kural
