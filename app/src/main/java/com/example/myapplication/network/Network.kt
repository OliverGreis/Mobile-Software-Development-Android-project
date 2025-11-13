package com.example.myapplication
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*

// Source - https://stackoverflow.com/questions/66059143/how-to-make-a-http-post-request-in-kotlin-android-to-simple-server
// Posted by Stefan Zhelyazkov, modified by community. See post 'Timeline' for change history
// Retrieved 2025-11-13, License - CC BY-SA 4.0

implementation "io.ktor:ktor-client-android:$ktor_version"



class Network() {

    fun sendPostRequest(urlInput:String) {
        val client = HttpClient(Android){}

        val response: HttpResponse = client.request("$urlInput") {
            method = HttpMethod.Post
        }

        return response

        client.close()
    }

    fun sendGetRequest(urlInput:String) {
        val client = HttpClient(Android){}

        val response: HttpResponse = client.request("$urlInput") {
            method = HttpMethod.Get
        }

        return response

        client.close()
    }

    fun sendPutRequest(urlInput:String) {
        val client = HttpClient(Android){}

        val response: HttpResponse = client.request("$urlInput") {
            method = HttpMethod.Put
        }

        return response

        client.close()
    }

}