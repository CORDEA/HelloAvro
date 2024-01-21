package jp.cordea.helloavro.repositories

import com.github.avrokotlin.avro4k.Avro
import com.github.avrokotlin.avro4k.io.AvroDecodeFormat
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readBytes
import jp.cordea.objects.Schemas
import jp.cordea.objects.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserRepository(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun find(id: Int): User = withContext(dispatcher) {
        val response = client.get("users/${id}")
        Avro.default.openInputStream(User.serializer()) {
            decodeFormat = AvroDecodeFormat.Binary(Schemas.user)
        }.from(response.readBytes()).use { it.nextOrThrow() }
    }
}
