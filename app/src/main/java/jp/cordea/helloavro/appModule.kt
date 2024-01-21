package jp.cordea.helloavro

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import jp.cordea.helloavro.repositories.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(CIO) {
            defaultRequest {
                url("http://localhost:8080")
            }
        }
    }
    singleOf(::UserRepository)
}
