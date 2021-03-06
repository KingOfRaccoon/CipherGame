package com.badschizoids.ciphergame.tools

class StoryTail {
    companion object{

        private val stringsCompanyStart = arrayOf(
                StoryMessage("Добро пожаловать в рабочий чат компании." +
                        " Это ваш первый день, верно?"),
                StoryMessage("Хорошо, тогда перенаправляем к вашему ментору.")
        )
        private val stringsUserStart = arrayOf(StoryMessage("Да, верно, я только устроился"))

        private val stringsCompanyGetHelps = arrayOf(
                StoryMessage("Здравствуйте, поздравляю вас с устройством в нашу компанию." +
                        " Будут какие-то слова для старта?"),
                StoryMessage("Хороший настрой. У вас же есть навыки работы с шифрами?" +
                        " Конечно должны быть, " +
                        "не приняли же вас на работу просто так." +
                        " Но в любом случае освежите знания." + "\n" +
                "В первое время задания будут простые и направленные " +
                        "на тестирование и развитие ваших навыков." + "\n" +
                "Высылаю задания."),StoryMessage( "Удачи")
        )
        private val stringsUserGetHelps = arrayOf(
                StoryMessage("Не терпится приступить к работе"),
                StoryMessage("Понял, учту")
        )
        private val stringsCompanyFirstWork = arrayOf(
                StoryMessage(" Выслал тебе пару зашифрованных сообщений из" +
                        " тайного чата работников," +
                        " найдешь что-нибудь интересное, высылай", true),
                StoryMessage("Это же хорошо, мемы - это круто," +
                        " я уезжаю в командировку на пару дней. Справишься без меня?"),
                StoryMessage("Удачи")
        )
        private val stringsUserFirstWork = arrayOf(
                StoryMessage("Ничего интересного, там лишь мемы :("),
                StoryMessage("Будто бы у меня есть выбор :)")
        )
        val startComapny = "startCompany"
        val getHelpsCompany = "getHelpsCompany"
        val firstWorkCompany = "firstWorkCompany"

        val startUser = "startUser"
        val getHelpsUser = "getHelpsUser"
        val firstWorkUser = "firstWorkUser"

        val nameCompanyStart = "Представитель компании"
        val nameCompanyGetHelpsAndFirstWork = "Александр"


        val stringsCompany = mapOf<String, Array<StoryMessage>>(
                startComapny to stringsCompanyStart,
                getHelpsCompany to stringsCompanyGetHelps,
                firstWorkCompany to stringsCompanyFirstWork
        )

        val stringsUser = mapOf<String, Array<StoryMessage>>(
                startUser to stringsUserStart,
                getHelpsUser to stringsUserGetHelps,
                firstWorkUser to stringsUserFirstWork
        )
    }
}