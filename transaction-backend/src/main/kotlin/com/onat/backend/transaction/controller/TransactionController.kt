package com.onat.backend.transaction.controller

import com.onat.backend.transaction.model.AddUserRequest
import com.onat.backend.transaction.model.CreateTransactionRequest
import com.onat.backend.transaction.model.Transaction
import com.onat.backend.transaction.service.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = ["*"])
class TransactionController(
    private val transactionService: TransactionService
) {

    @GetMapping
    fun getAll(): List<Transaction> =
        transactionService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Transaction> {
        val tx = transactionService.getById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(tx)
    }

    @GetMapping("/group/{groupId}")
    fun getByGroup(@PathVariable groupId: Long): List<Transaction> =
        transactionService.getByGroupId(groupId)

    @GetMapping("/user/{username}")
    fun getByUser(@PathVariable username: String): List<Transaction> =
        transactionService.getByUser(username)

    @PostMapping
    fun create(@RequestBody request: CreateTransactionRequest): ResponseEntity<Transaction> {
        val created = transactionService.create(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @PostMapping("/{id}/users")
    fun addUser(
        @PathVariable id: Long,
        @RequestBody body: Map<String, String>
    ): ResponseEntity<Transaction> {
        val username = body["username"] ?: return ResponseEntity.badRequest().build()

        val updated = transactionService.addUser(id, AddUserRequest(username))
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long): ResponseEntity<Void> {
        val removed = transactionService.deleteById(id)
        return if (removed) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping
    fun deleteAll(): ResponseEntity<Void> {
        transactionService.clearAll()
        return ResponseEntity.noContent().build()
    }
}
