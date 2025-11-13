package com.example.myapplication
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*

// Source - https://ktor.io/docs/client-create-and-configure.html#basic-config



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