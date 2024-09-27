package org.example

import com.google.gson.Gson
import java.io.FileReader
import java.math.BigDecimal
import java.math.RoundingMode

data class Personal(
    val langs: List<String>?,
    val life_main: Int?
)

data class User(
    val id: Int?,
    val sex : Int?,

    val home_town: String?,
    val schools: List<Any?>?,
    val universities: List<Any?>?,
    val personal: Personal?,
    val home_phone: String?,
    val relation: Int?,
    val relatives: List<Any?>?,
    val career: List<Any>?,
    val military: List<Any>?,
    val interests: String?,

)

data class UserList(
    val users: List<User>
)

fun Int.percentWithDigits(size: Int, digits: Int) : Double{
    return BigDecimal(this.toDouble() / size).setScale(digits, RoundingMode.HALF_EVEN).toDouble()
}

fun processDataCharacteristics(users: List<User>){
    val homeTown = users.count { it.home_town?.run{ isNotEmpty()} ?: false }
    val graduation = users.count{ (it.schools?.size ?: 0) > 0 || (it.universities?.size ?: 0) > 0 }
    val noLangs = users.count { it.personal?.langs?.size == null }
    val singleLang = users.count { it.personal?.langs?.size == 1}
    val manyLangs = users.count { (it.personal?.langs?.size ?: 0) > 1}
    val home_phone = users.count { it.home_phone?.run{ isNotEmpty() } ?: false } // or (it.home_phone ?: "").isNotEmpty
    val relation = users.count { it.relation != null }
    val relatives = users.count { (it.relatives?.size ?: 0) > 0 }
    val career = users.count { (it.career?.size ?: 0) > 0 }
    val military = users.count { (it.military?.size ?: 0) > 0 }
    val interests = users.count { (it.interests ?: "").isNotEmpty() }
    val life_main = users.count { it.personal?.life_main != null }


    listOf(
        homeTown,
        graduation,
        noLangs,
        singleLang,
        manyLangs,
        home_phone,
        relation,
        relatives,
        career,
        military,
        interests,
        life_main
    ).forEachIndexed { ind, p ->
        println("$ind: ${p.percentWithDigits(users.size, 2)}")
    }
}

fun main() {
    FileReader("src/main/resources/users.json").readLines()[0].let{"{users: $it}"}.run jsonInfo@{
        val allUsers = Gson().fromJson(this, UserList::class.java).users
        val girls = allUsers.filter { it.sex == 1 }
        val boys = allUsers.filter { it.sex == 2 }
        val studyingAtUniversity = allUsers.filter { (it.universities?.size ?: 0) > 0 }
        val notStudyingAtUniversity = allUsers.filter { (it.universities?.size ?: 0 == 0) }

        processDataCharacteristics(allUsers)
        println()
        processDataCharacteristics(boys)
        println()
        processDataCharacteristics(girls)
        println()
        processDataCharacteristics(studyingAtUniversity)
        println()
        processDataCharacteristics(notStudyingAtUniversity)
        println()
    }
}