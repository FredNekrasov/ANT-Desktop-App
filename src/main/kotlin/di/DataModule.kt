package di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

import data.*

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val dataModule = module {
    single<ANTDatabase>(createdAtStart = true, qualifier = qualifier<ANTDatabase>()) {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        ANTDatabase.Schema.create(driver)
        ANTDatabase.invoke(driver)
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
    single(qualifier<ArticleRepository>()) {
        ArticleRepository(
            get(qualifier = qualifier<ArticleDao>()),
            get(qualifier = qualifier<ArticleStatusDao>()),
            get(qualifier = qualifier<HttpClient>())
        )
    }
}