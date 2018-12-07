package oscar.com.eater.pojo

/**
 * Created by oscmenji on 2018-04-08.
 */
class QueRicoError(errCode : Int, errMsg : String) : Throwable() {
    var errorCode : Int = errCode
    var errorMessage : String = errMsg
}