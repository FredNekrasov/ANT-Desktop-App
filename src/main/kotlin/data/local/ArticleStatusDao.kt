package data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * ArticleStatusDao is used for working with the database
 * @param db is an ANTDatabase class
 */
class ArticleStatusDao(
    private val db: ANTDatabase
) {
    /**
     * Get filtered list of article status from the database
     * @return List of articles
     */
    suspend fun getArticleStatusBy(catalogId: Int, pageNumber: Int): ArticleStatusEntity? = withContext(Dispatchers.IO) {
        db.articleStatusQueries.getArticleStatusBy(catalogId.toLong(), pageNumber.toLong()).executeAsOneOrNull()
    }
    /**
     * Get count of all article status from the database
     * @return Long
     */
    suspend fun getCountAllArticleStatus(): Long = withContext(Dispatchers.IO) {
        db.articleStatusQueries.getCountAllArticleStatus().executeAsOne()
    }
    /**
     * Upsert article status in the database
     * @param articleStatus is an ArticleStatus class that will be inserted in the database if it doesn't exist or updated otherwise
     */
    suspend fun upsertArticleStatus(articleStatus: ArticleStatusEntity) = withContext(Dispatchers.IO) {
        db.articleStatusQueries.upsertArticleStatus(articleStatus)
    }
    /**
     * Delete article status from the database
     * @param id is an ID of the article that will be deleted
     */
    suspend fun deleteArticleStatus(id: Long) = withContext(Dispatchers.IO) {
        db.articleStatusQueries.deleteArticleStatus(id)
    }
}