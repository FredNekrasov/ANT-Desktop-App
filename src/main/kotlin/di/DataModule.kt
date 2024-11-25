package di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

import data.local.*
import data.repositories.ArticleRepository
import domain.repositories.IArticleRepository

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

import kotlinx.serialization.json.Json
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val dataModule get() = module {
    factory<SqlDriver>(qualifier = qualifier<SqlDriver>()) {
        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    }
    single(qualifier = qualifier<ANTDatabase>()) {
        ANTDatabase.invoke(get(qualifier = qualifier<SqlDriver>()))
    }
    single(qualifier = qualifier<ArticleDao>()) {
        ArticleDao(get(qualifier = qualifier<ANTDatabase>()))
    }
    single(qualifier = qualifier<ArticleStatusDao>()) {
        ArticleStatusDao(get(qualifier = qualifier<ANTDatabase>()))
    }
    single<HttpClient>(createdAtStart = true, qualifier = qualifier<HttpClient>()) {
        HttpClient(OkHttp) {
            expectSuccess = true
            install(Logging) { level = LogLevel.ALL }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
        }
    }
    single<IArticleRepository>(qualifier<IArticleRepository>()) {
        ArticleRepository(
            get(qualifier = qualifier<ArticleDao>()),
            get(qualifier = qualifier<ArticleStatusDao>()),
            get(qualifier = qualifier<HttpClient>())
        )
    }
}