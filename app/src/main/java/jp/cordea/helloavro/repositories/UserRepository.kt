package jp.cordea.helloavro.repositories

import com.github.avrokotlin.avro4k.Avro
import com.github.avrokotlin.avro4k.io.AvroDecodeFormat
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes
import jp.cordea.objects.Schemas
import jp.cordea.objects.User

class UserRepository(private val client: HttpClient) {
    suspend fun find(id: Int): User {
        val response = client.get("users/${id}")
        return Avro.default.openInputStream(User.serializer()) {
            decodeFormat = AvroDecodeFormat.Binary(Schemas.user)
        }.from(response.readBytes()).use { it.nextOrThrow() }
    }
}
