package com.example.myapplication
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*

// Source - https://ktor.io/docs/client-create-and-configure.html#basic-config



class Network() {

    companion object {

        suspend fun sendPostRequest(urlInput: String): HttpResponse {
            val client = HttpClient(Android) {}

            val response: HttpResponse = client.request("$urlInput") {
                method = HttpMethod.Post
            }

            return response

            client.close()
        }

        suspend fun sendGetRequest(urlInput: String): HttpResponse {
            val client = HttpClient(Android) {}

            val response: HttpResponse = client.request("$urlInput") {
                method = HttpMethod.Get
            }

            return response

            client.close()
        }

        suspend fun sendPutRequest(urlInput: String): HttpResponse {
            val client = HttpClient(Android) {}

            val response: HttpResponse = client.request("$urlInput") {
                method = HttpMethod.Put
            }

            return response

            client.close()
        }
    }

}