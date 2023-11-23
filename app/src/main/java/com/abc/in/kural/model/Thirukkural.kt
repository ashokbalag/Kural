package com.abc.`in`.kural.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// Step 1: Model Classes
data class Thirukkural(val adhikaram: List<Adhikaram>)
@Parcelize data class Adhikaram(var name: String, val iyal: List<Iyal>): Parcelable
@Parcelize data class Iyal(val name: String, val paal: List<Paal>): Parcelable
@Parcelize data class Paal(val desc: String, val name: String, val kuralkal: List<Kural>): Parcelable
@Parcelize data class Kural(val _id: String, val line1: String, val line2: String, val desc: String,var line_en: String? = null, var desc_en: String? = null): Parcelable

//
//
//data class Thirukkural(
//    val athikaram: List<Athikarankal>
//)
//
//@Parcelize
//data class Athikarankal(
//    val name: String,
//    val page: List<Athikaram>
//) : Parcelable
//
//@Parcelize
//data class Athikaram(
//    val name: String,
//    val subpage: List<AthikaramDetails>
//) : Parcelable
//
//@Parcelize
//data class AthikaramDetails(
//    val desc: String,
//    val kural: List<Kural>,
//    val name: String,
//    val subpage: List<AthikaramSubDetails>
//) : Parcelable
//
//@Parcelize
//data class AthikaramSubDetails(
//    val desc: String,
//    val kural: List<Kural>,
//    val name: String
//) : Parcelable
//
//@Parcelize
//data class Kural(
//    val _id: String,
//    val desc: String,
//    val line1: String,
//    val line2: String,
//    var line_en: String? = null,
//    var desc_en: String? = null
//) : Parcelable