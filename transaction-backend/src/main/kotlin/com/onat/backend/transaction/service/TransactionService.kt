package com.onat.backend.transaction.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.onat.backend.transaction.model.AddUserRequest
import com.onat.backend.transaction.model.CreateTransactionRequest
import com.onat.backend.transaction.model.Transaction
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.util.concurrent.atomic.AtomicLong

@Service
class TransactionService(
    private val objectMapper: ObjectMapper
) {

    private val transactions = mutableListOf<Transaction>()
    private val idSequence = AtomicLong(1)

    private val filePath: Path = Path.of("transactions.json")

    init {
        loadFromFile()
    }

    private fun loadFromFile() {
        try {
            if (Files.exists(filePath)) {
                val json = Files.readString(filePath)
                if (json.isNotBlank()) {
                    val list: List<Transaction> = objectMapper.readValue(
                        json,
                        object : TypeReference<List<Transaction>>() {}
                    )
                    transactions.clear()
                    transactions.addAll(list)

                    val maxId = transactions.maxOfOrNull { it.id } ?: 0L
                    idSequence.set(maxId + 1)
                }
            }
        } catch (e: Exception) {
            println("Failed to load transactions from file: ${e.message}")
        }
    }

    private fun saveToFile() {
        try {
            val json = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(transactions)
            Files.writeString(
                filePath,
                json,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            )
        } catch (e: Exception) {
            println("Failed to save transactions to file: ${e.message}")
        }
    }

    fun getAll(): List<Transaction> = transactions.toList()

    fun getById(id: Long): Transaction? =
        transactions.find { it.id == id }

    fun getByGroupId(groupId: Long): List<Transaction> =
        transactions.filter { it.groupId == groupId }

    fun getByUser(username: String): List<Transaction> =
        transactions.filter { it.participants.contains(username) }

    fun create(request: CreateTransactionRequest): Transaction {
        val id = idSequence.getAndIncrement()

        val participants = request.participants.toMutableList().ifEmpty {
            mutableListOf(request.createdBy)
        }

        val tx = Transaction(
            id = id,
            amount = request.amount,
            groupId = request.groupId,
            createdBy = request.createdBy,
            participants = participants,
            description = request.description
        )

        transactions.add(tx)
        saveToFile()
        return tx
    }

    fun addUser(id: Long, addUserRequest: AddUserRequest): Transaction? {
        val tx = transactions.find { it.id == id } ?: return null
        if (!tx.participants.contains(addUserRequest.username)) {
            tx.participants.add(addUserRequest.username)
            saveToFile()
        }
        return tx
    }

    fun deleteById(id: Long): Boolean {
        val removed = transactions.removeIf { it.id == id }
        if (removed) {
            saveToFile()
        }
        return removed
    }

    fun clearAll() {
        transactions.clear()
        idSequence.set(1)
        saveToFile()
    }
}
