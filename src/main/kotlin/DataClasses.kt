data class University(
    val chair: Int?,
    val chair_name: String?,
    val city: Int?,
    val education_form: String?,
    val education_form_id: Int?,
    val education_status: String?,
    val education_status_id: Int?,
    val faculty: Int?,
    val faculty_name: String?,
    val graduation: Int?,
    val id: Int?,
    val name: String?
)

data class User(
    val id: String?,
    val sex: Int?,
    val bdate: String?,
    val universities: List<University>?
)

data class IdObject(
    val `$oid`: String?
)

data class DateObject(
    val `$date`: String?
)

data class UserName(
    val _id: IdObject?,
    val id: String?,
    val downloaded_at: DateObject?,
    val first_name: String?,
    val last_name: String?,
    val maiden_name: String?,
    val nickname: String?,
    val screen_name: String?,
    val status: String?,


    val sex: Int?,
    val bdate: String?,
    val universities: List<University>?
)

data class UserList(
    val users: List<User>?
)
data class UserNameList(
    val usernames: List<UserName>?
)