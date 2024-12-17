package di

import domain.repositories.IArticleRepository
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import presentation.screens.viewModels.*

val presentationModule get() = module {
    factory(qualifier<MainVM>()) {
        MainVM(get(qualifier<IArticleRepository>()))
    }
    factory(qualifier<ParishLifeVM>()) {
        ParishLifeVM(get(qualifier<IArticleRepository>()))
    }
    factory(qualifier<YouthClubVM>()) {
        YouthClubVM(get(qualifier<IArticleRepository>()))
    }
    factory(qualifier<AdvicesVM>()) {
        AdvicesVM(get(qualifier<IArticleRepository>()))
    }
    factory(qualifier<HistoryVM>()) {
        HistoryVM(get(qualifier<IArticleRepository>()))
    }
    factory(qualifier<StoriesVM>()) {
        StoriesVM(get(qualifier<IArticleRepository>()))
    }
}