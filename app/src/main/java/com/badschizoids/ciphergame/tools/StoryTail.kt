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
                        "на тестирование и развитие ваших навыков."
//                        + "\n" +
//                "Высылаю задания."
                ),StoryMessage( "Удачи")
        )
        private val stringsUserGetHelps = arrayOf(
                StoryMessage("Не терпится приступить к работе"),
                StoryMessage("Понял, учту")
        )
        private val stringsCompanyFirstWork = arrayOf(
                StoryMessage(" Выслал тебе пару зашифрованных сообщений из" +
                        " тайного чата работников," +
                        " найдешь что-нибудь интересное, высылай", true, Level.FIRST),
                StoryMessage("Это же хорошо, мемы - это круто," +
                        " я уезжаю в командировку на пару дней. Справишься без меня?"),
                StoryMessage("Удачи")
        )
        private val stringsUserFirstWork = arrayOf(
                StoryMessage("Ничего интересного, там лишь мемы :("),
                StoryMessage("Будто бы у меня есть выбор :)")
        )
        private val stringsCompanySecondWork = arrayOf(
            StoryMessage("Есть задание от которого очень " +
                    " сильно зависит цена акций нашей компании." +
                    " Т.к. Александер в командировке, задание" +
                    " придется отдать стажеру." +
                    " Файл будет выслан через несколько секунд."),
            StoryMessage("Руководство не довольно вашим результатом." +
                    " Вы отстраняетесь от работы на 2 ближайших месяца" +
                    " также будет поднят вопрос о вашем увольнении.")
        )
        private val stringsUserSecondWork = arrayOf(
            StoryMessage("К сожалению у меня не получилось дешифровать информацию.")
        )
        private val stringsMentorCameBack = arrayOf(
            StoryMessage("Только вернулся с командировки, как дела стажер?"),
            StoryMessage("Они там что, совсем с ума посходили?!")
        )
        private val stringsUserAboutDismissal = arrayOf(
            StoryMessage("Все плохо, меня отстранили от работы." +
                    " Сегодня узнаю уволят или нет ;(")
        )
        private val stringsMentorCompany = arrayOf(
//            StoryMessage("Что за новости?!"),
            StoryMessage("Назовите хотя бы 1 причину увольнения моего стажера."),
            StoryMessage("Перешлите мне файл с этим шифром."),
            StoryMessage("Конечно, парень не смог бы дешифровать это," +
                    " там присутствуют шифры о которых он даже не знает." +
                    " Вообщем, я требую восстановления парня в должности" +
                    " или мне придется пойти на крайние меры и перейти в “Стела”," +
                    " как раз слышал, что они набирают в свою" +
                    " команду криптографов с большим опытом.")
        )
        private val stringsCompanyMentor = arrayOf(
            StoryMessage("Александр, что случилось?"),
            StoryMessage("Он не смог выполнить важную задачу," +
                    " из-за его провала цена акций обрушилась на 5%."),
            StoryMessage("Хоть это и шантаж чистой воды, но у нас нет выбора," +
                    " вы слишком ценный актив. Стажер будет восстановлен в должности." +
                    " Можете продолжать работу." +
                    " И да, Александр как только освободитесь нам нужно будет побеседовать.")
        )
        private val stringsMentorThirdLevel = arrayOf(
            StoryMessage("Лови еще информации о шифрах." +
                    " Скинул бы я тебе эту информацию раньше," +
                    " ты бы просто убил тот шифр. Также высылаю тебе еще заданий.")
        )
        private val stringsMentorFourthLevel = arrayOf(
            StoryMessage("Смог расшифровать те данные, которые я тебе скидывал?"),
            StoryMessage("Представитель скинул мне файл с информацией," +
                    " которая поможет нам выдавить “Стеллу” с рынка," +
                    " на ней сразу все шифры." +
                    " Не могу с ней справиться, скину тебе, вдруг придет вдохновение."),
            StoryMessage("Отправляю специальную утилиту для XOR," +
                    " с её помощью ты сможешь дешифровать строку."),
            StoryMessage("Решил?"),
            StoryMessage("Это или шаг к шифру Цезаря или" +
                    " слово к Виженеру, ты понял к чему?"),
            StoryMessage("Прекрасно, приступай к дешифровке.")
        )
        private val stringsUserFourthLevel = arrayOf(
            StoryMessage("Да, дешифровка прошла успешно."),
            StoryMessage("Там XOR, не могу дешифровать."),
            StoryMessage("Александр, я дешифровал сообщение, там какая-то загадка."),
            StoryMessage("Ну, типа.")
        )
        val startComapny = "startCompany"
        val getHelpsCompany = "getHelpsCompany"
        val firstWorkCompany = "firstWorkCompany"
        val secondWorkCompany = "secondWorkCompany"
        val thirdWorkCompany = "thirdWorkCompany"
        val fourthWorkCompany = "fourthWorkCompany"
        val fifthWorkCompany = "fifthWorkCompany"
        val lastWorkCompany = "lastWorkCompany"

        val startUser = "startUser"
        val getHelpsUser = "getHelpsUser"
        val firstWorkUser = "firstWorkUser"
        val secondWorkUser = "secondWorkUser"
        val thirdWorkUser = "thirdWorkUser"
        val fourthWorkUser = "fourthWorkUser"
        val fifthWorkUser = "fifthWorkUser"
        val lastWorkUser = "lastWorkUser"


        val nameCompanyStart = "Представитель компании"
        val nameCompanyGetHelpsAndFirstWork = "Александр"


        val stringsCompany = mapOf<String, Array<StoryMessage>>(
                startComapny to stringsCompanyStart,
                getHelpsCompany to stringsCompanyGetHelps,
                firstWorkCompany to stringsCompanyFirstWork,
                secondWorkCompany to stringsCompanySecondWork,
                thirdWorkCompany to stringsMentorCameBack,
                fourthWorkCompany to stringsCompanyMentor,
                fifthWorkCompany to stringsMentorThirdLevel,
                lastWorkCompany to stringsMentorFourthLevel
        )

        val stringsUser = mapOf<String, Array<StoryMessage>>(
                startUser to stringsUserStart,
                getHelpsUser to stringsUserGetHelps,
                firstWorkUser to stringsUserFirstWork,
                secondWorkUser to stringsUserSecondWork,
                thirdWorkUser to stringsUserAboutDismissal,
                fourthWorkUser to stringsMentorCompany,
                fifthWorkUser to arrayOf(),
                lastWorkUser to stringsUserFourthLevel
        )
    }
}