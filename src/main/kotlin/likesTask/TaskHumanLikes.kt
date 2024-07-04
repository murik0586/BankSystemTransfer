package likesTask

const val NAME_LIKES_VERSION = "человеку"
const val NAME_LIKES_VERSION_TWO = "людям"


fun main() {
    val countLikes = 441

    val result:String = if (countLikes % 10 == 1 && countLikes % 100 != 11) NAME_LIKES_VERSION else NAME_LIKES_VERSION_TWO
    println("Понравилось $countLikes $result")

}