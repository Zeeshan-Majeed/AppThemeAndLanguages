package com.brain.themes.applanguages.helper.brainthemesandapplanguages.utils

import com.brain.themes.applanguages.helper.brainthemesandapplanguages.models.Languages

val LANGUAGES_LIST = listOf(
    Languages("Arabic", "ar"),
    Languages("African", "af"),
    Languages("Greman", "de"),
    Languages("Persian", "fa"),
    Languages("Italian", "it"),
    Languages("Japanese", "ja"),
    Languages("Koren", "ko"),
    Languages("Malay", "ms"),
    Languages("Portuguese", "pt"),
    Languages("English", "en")
).sortedBy { it.languageName }
