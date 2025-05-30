package data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * ArticleDao is used for working with the database
 * @param db is an [ANTDatabase] class
 */
class ArticleDao(private val db: ANTDatabase) {
    /** Get count of all records from the database */
    suspend fun getCountAll() = withContext(Dispatchers.IO) {
        db.articleQueries.getCountAll().executeAsOne()
    }
    /**
     * Get articles filtered by catalog id and page number from the database
     * @param catalogId is an ID of the catalog
     * @param pageNumber is a number of the page
     * @return List of articles
     */
    suspend fun getListBy(catalogId: Long, pageNumber: Long) = withContext(Dispatchers.IO) {
        db.articleQueries.getArticlesBy(catalogId, pageNumber).executeAsList()
    }
    /**
     * Upsert article in the database
     * @param article is an Article class that will be inserted in the database if it doesn't exist or updated otherwise
     */
    suspend fun upsert(article: ArticleEntity) = withContext(Dispatchers.IO) {
        db.articleQueries.upsert(article)
    }
    /**
     * Delete articles filtered by catalog id and page number from the database
     * @param catalogId is an ID of the catalog
     * @param pageNumber is a number of the page
     */
    suspend fun deleteBy(catalogId: Long, pageNumber: Long) = withContext(Dispatchers.IO) {
        db.articleQueries.deleteBy(catalogId, pageNumber)
    }
}
/**
 * ArticleStatusDao is used for working with the database
 * @param db is an [ANTDatabase] class
 */
class ArticleStatusDao(private val db: ANTDatabase) {
    /**
     * Retrieves the article status from the database filtered by catalog id and page number.
     *
     * @param catalogId The ID of the catalog to filter the article status by.
     * @param pageNumber The number of the page to filter the article status by.
     * @return An instance of [ArticleStatusEntity] if found, or null if no matching record is found.
     */
    suspend fun getBy(catalogId: Long, pageNumber: Long) = withContext(Dispatchers.IO) {
        db.articleStatusQueries.getBy(catalogId, pageNumber).executeAsOneOrNull()
    }
    /** Get count of all article status from the database **/
    suspend fun getCountAll() = withContext(Dispatchers.IO) {
        db.articleStatusQueries.getCountAll().executeAsOne()
    }
    /**
     * Upsert article status in the database
     * @param articleStatus is an ArticleStatus class that will be inserted in the database if it doesn't exist or updated otherwise
     */
    suspend fun upsert(articleStatus: ArticleStatusEntity) = withContext(Dispatchers.IO) {
        db.articleStatusQueries.upsert(articleStatus)
    }
}