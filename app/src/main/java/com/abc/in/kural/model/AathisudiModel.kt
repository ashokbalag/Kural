package com.abc.`in`.kural.model

data class AathisudiList(
    val athisudi: List<Athisudi>,
    val lord_compliment: LordCompliment
)

data class Athisudi(
    val meaning: String,
    val number: Int,
    val paraphrase: String,
    val poem: String,
    val translation: String
)

data class LordCompliment(
    val line1: String,
    val line2: String,
    val meaning: String,
    val paraphase: String
)