package jp.cordea

import com.github.avrokotlin.avro4k.Avro
import com.github.avrokotlin.avro4k.io.AvroEncodeFormat
import io.github.serpro69.kfaker.faker
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respond
import io.ktor.server.response.respondBytes
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import jp.cordea.objects.Schemas
import jp.cordea.objects.User
import jp.cordea.objects.UserDetails
import jp.cordea.objects.UserPet
import jp.cordea.objects.UserPetType
import java.io.ByteArrayOutputStream
import kotlin.random.Random

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val faker = faker { }
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/users") {
            val users = (0..100).map {
                User(it, faker.name.name())
            }
            call.respondBytes(
                ContentType(
                    "application",
                    "vnd.apache.avro+binary"
                )
            ) {
                ByteArrayOutputStream().use { stream ->
                    Avro.default.openOutputStream(User.serializer()) {
                        encodeFormat = AvroEncodeFormat.Binary
                        schema = Schemas.user
                    }.to(stream).use {
                        it.write(users)
                    }
                    stream.toByteArray()
                }
            }
        }
        get("/users/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }
            val user = UserDetails(
                id,
                faker.name.name(),
                faker.internet.safeEmail(),
                (0..Random.nextInt(1, 10)).map {
                    val isCat = Random.nextBoolean()
                    UserPet(
                        if (isCat) UserPetType.CAT else UserPetType.DOG,
                        if (isCat) faker.cat.name() else faker.dog.name(),
                        if (isCat) faker.cat.breed() else faker.dog.breed()
                    )
                }
            )
            call.respondBytes(
                ContentType(
                    "application",
                    "vnd.apache.avro+binary"
                )
            ) {
                ByteArrayOutputStream().use { stream ->
                    Avro.default.openOutputStream(UserDetails.serializer()) {
                        encodeFormat = AvroEncodeFormat.Binary
                        schema = Schemas.userDetails
                    }.to(stream).use {
                        it.write(user)
                    }
                    stream.toByteArray()
                }
            }
        }
    }
}
