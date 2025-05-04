package data

import kotlinx.serialization.Serializable

/**
 * The data transfer object for catalogs received from the server
 * @param id is a unique id of the catalog
 * @param name is a name of the catalog
 */
@Serializable
data class CatalogDto(
    val id: Long,
    val name: String
)
/**
 *  The data transfer object for articles received from the server
 *  @param id is a unique id of the article
 *  @param catalog is a type of the article (see [CatalogDto])
 *  @param title is a title of the article
 *  @param description is a description of the article
 *  @param dateOrBanner is a date or a banner of the article
 *  @param content is a list of strings representing the content of the article
 */
@Serializable
data class ArticleDto(
    val id: Long,
    val catalog: CatalogDto,
    val title: String,
    val description: String,
    val dateOrBanner: String,
    val content: List<String>
)
/**
 * ArticleResponse would be received from the server
 * @param pageNumber is a number of the page
 * @param pageSize is a size of the page
 * @param totalRecords is a total number of records in the remote database
 * @param data is a list of articles
 */
@Serializable
data class ArticleResponse(
    val pageNumber: Long,
    val pageSize: Long,
    val totalRecords: Long,
    val data: List<ArticleDto>
)