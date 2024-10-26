package org.example

import UserList
import UserNameList
import com.google.gson.Gson
import man1Audios
import man1Blacklisted
import man1Followers
import man1Photos
import man1Subscriptions
import man1Videos
import man2Audios
import man2Blacklisted
import man2Followers
import man2Photos
import man2Subscriptions
import man3Videos
import man3Audios
import man3Blacklisted
import man3Followers
import man3Photos
import man3Subscriptions
import women1Audios
import women1Blacklisted
import women1Followers
import women1Photos
import women1Subscriptions
import women1Videos
import women2Audios
import women2Blacklisted
import women2Followers
import women2Photos
import women2Subscriptions
import women3Videos
import women3Audios
import women3Blacklisted
import women3Followers
import women3Photos
import women3Subscriptions
import man1Rel
import man2Rel
import man2Videos
import man3Rel
import women1Rel
import women2Rel
import women2Videos
import women3Rel
import java.io.File
import java.io.FileReader
import java.io.Writer
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.math.roundToInt
import kotlin.math.sqrt


fun main() {
    val users = FileReader("src/main/resources/users.json").readLines()[0].let{"{users: $it}"}.run jsonInfo@{
        Gson().fromJson(this, UserList::class.java).users!!
    }
    val userNames = FileReader("src/main/resources/user_names.json").readLines()[0].let{"{usernames: $it}"}.run jsonInfo@{
        Gson().fromJson(this, UserNameList::class.java).usernames!!
    }
    val result = userNames.mapNotNull { un ->
        users.firstOrNull { u -> u.id == un.id }?.run {
            un.copy(sex = this.sex, bdate = this.bdate, universities = this.universities)
        }
    }
    (Gson().toJson(result)).run res@{
        File("E:\\sexy_pudge.alpaca_yest").apply { createNewFile() }.writer().use { writer ->
            writer.write(this)
        }
    }
}