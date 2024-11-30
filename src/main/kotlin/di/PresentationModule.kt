package di

import domain.repositories.IArticleRepository
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import presentation.screens.viewModels.ArticleVM

val presentationModule get() = module {
    factory(qualifier<ArticleVM>()) { ArticleVM(get<IArticleRepository>(qualifier<IArticleRepository>())) }
}