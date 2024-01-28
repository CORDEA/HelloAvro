package jp.cordea.objects

import com.github.avrokotlin.avro4k.Avro

object Schemas {
    val user = Avro.default.schema(User.serializer())
    val userDetails = Avro.default.schema(UserDetails.serializer())
}
