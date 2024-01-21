package jp.cordea.helloavro

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import jp.cordea.helloavro.repositories.UserRepository
import jp.cordea.helloavro.ui.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    factory(qualifier = named("IO")) { Dispatchers.IO }
    single {
        HttpClient(CIO) {
            defaultRequest {
                url("http://10.0.2.2:8080")
            }
        }
    }
    single { UserRepository(get(), get(named("IO"))) }
    viewModelOf(::HomeViewModel)
}
