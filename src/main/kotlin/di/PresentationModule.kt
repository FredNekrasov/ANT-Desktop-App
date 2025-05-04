package di

import data.ArticleRepository
import org.koin.core.qualifier.qualifier
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import presentation.screens.viewModels.*

val presentationModule = module {
    viewModel(qualifier<MainVM>()) {
        MainVM(get(qualifier<ArticleRepository>()))
    }
    viewModel(qualifier<ParishLifeVM>()) {
        ParishLifeVM(get(qualifier<ArticleRepository>()))
    }
    viewModel(qualifier<YouthClubVM>()) {
        YouthClubVM(get(qualifier<ArticleRepository>()))
    }
    viewModel(qualifier<AdvicesVM>()) {
        AdvicesVM(get(qualifier<ArticleRepository>()))
    }
    viewModel(qualifier<HistoryVM>()) {
        HistoryVM(get(qualifier<ArticleRepository>()))
    }
    viewModel(qualifier<StoriesVM>()) {
        StoriesVM(get(qualifier<ArticleRepository>()))
    }
}