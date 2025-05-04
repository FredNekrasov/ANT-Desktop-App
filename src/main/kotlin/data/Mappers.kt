package data

import kotlinx.serialization.json.Json

/**  Mapper for mapping [ArticleDto] to [ArticleEntity] and vice versa */
fun ArticleDto.toEntity(catalogId: Long, pageNumber: Long) = ArticleEntity(
    id = id,
    title = title,
    description = description,
    date = dateOrBanner,
    articleType = catalog.name,
    pageNumber = pageNumber,
    catalogId = catalogId,
    content = Json.encodeToString(content)
)
/**  Mapper for mapping [ArticleEntity] to [ArticleDto] and vice versa */
fun ArticleEntity.toDto() = ArticleDto(
    id = id,
    title = title,
    description = description,
    dateOrBanner = date,
    catalog = CatalogDto(catalogId, articleType),
    content = Json.decodeFromString<List<String>>(content)
)