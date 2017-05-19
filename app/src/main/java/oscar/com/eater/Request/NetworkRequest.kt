package oscar.com.eater.Request

import android.util.Base64
import okhttp3.HttpUrl
import oscar.com.eater.ApplicationContants
import java.net.URLEncoder
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.collections.HashMap

/**
 * Created by omenji on 5/18/17.
 */
open class NetworkRequest(method: String, queryParameters: HashMap<String,String>) {
    protected val mHttpBuilder: HttpUrl.Builder = HttpUrl.Builder()
    private var queryParamsMap : HashMap<String,String> = queryParameters
    private val httpMethodPostGet = method
    private var timeStamp : String = getTimeStamp()
    private var nonce : String = nonce()
    init {
        mHttpBuilder.scheme(ApplicationContants.requestType)
        mHttpBuilder.host(ApplicationContants.hostName)
        mHttpBuilder.addPathSegments(ApplicationContants.path)
        mHttpBuilder.addQueryParameter("oauth_signature", signBaseParams(httpMethodPostGet))
        setOAuthBodyParams()

    }


    fun signBaseParams(httpMethod : String) : String {
        val encodedBase = encode(ApplicationContants.hostUrl)
        val encodedParams = sortAndEncode((getParameters().toList() + getArguments().toList()).toTypedArray())
        return base64encode(arrayOf(httpMethod, encodedBase, encodedParams).joinToString("&"))

    }
    protected fun getParameters() : Array<String> {
        return getOAuthParams()
    }

    private fun getArguments() : Array<String>{
        var sb = ArrayList<String>()
        for((key,value) in queryParamsMap){
            sb.add(key + "=" + value)
        }
        return sb.toTypedArray()
    }
    private fun getOAuthParams() : Array<String> {
        return arrayOf(ApplicationContants.oauth_consumer_key + "=" + ApplicationContants.apiConsumerKey,
                       ApplicationContants.oauth_signature_method + "=" + ApplicationContants.signatureMethod,
                       ApplicationContants.oauth_timestamp + "=" + timeStamp,
                       ApplicationContants.oauth_nonce + "=" + nonce,
                       ApplicationContants.oauth_version + "=" + 1.0,
                       ApplicationContants.format + "=" + "json")
    }

    protected fun setOAuthBodyParams() {
        mHttpBuilder.addQueryParameter(ApplicationContants.oauth_consumer_key, ApplicationContants.apiConsumerKey)
        mHttpBuilder.addQueryParameter(ApplicationContants.oauth_signature_method, ApplicationContants.signatureMethod)
        mHttpBuilder.addQueryParameter(ApplicationContants.oauth_timestamp,timeStamp)
        mHttpBuilder.addQueryParameter(ApplicationContants.oauth_nonce,nonce)
        mHttpBuilder.addQueryParameter(ApplicationContants.oauth_version, "1.0")
        mHttpBuilder.addQueryParameter(ApplicationContants.format, "json")
    }

    fun getTimeStamp() : String {
        return (System.currentTimeMillis() / 1000).toString()
    }

    fun nonce() : String{
        val rand = Random()
        val buf = StringBuffer()
        for(i in 1..8){
            buf.append(rand.nextInt(26))
        }
        return buf.toString()
    }

    fun base64encode(toEncode : String) : String{
        var weirdEncryptionThing = Mac.getInstance("HmacSHA1")
        var KeyString = ApplicationContants.apiSharedSecret + "&"
        var key = SecretKeySpec(KeyString.toByteArray(), "HmacSHA1")
        weirdEncryptionThing.init(key)
        return String(Base64.encode(weirdEncryptionThing.doFinal(toEncode.toByteArray()), Base64.DEFAULT)).trim()
    }

    fun encode(url : String) : String {
        return URLEncoder.encode(url,"utf-8")
                .replace("+","%20")
                .replace("!","%21")
                .replace("*","%2A")
                .replace("\\","%27")
                .replace("(","%28")
                .replace(")","%29")
    }

    fun sortAndEncode(list :Array<String>) : String {
        list.sort()
        return encode(list.joinToString("&"))
    }
}