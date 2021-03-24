package com.example.android.myweatherapp.view

fun aboutWeatherInfo(aboutWeather : String) : String {
    when (aboutWeather) {
        "cloudy" -> return "Сегодня облачно но с прояснениями, как как у тебя в жизни"
        "clear" -> return "Сегодня погода зашибись, ясная, бегом гулять"
        "partly-cloudy" -> return "Сегодня малооблачная погода, гулять пойти можно. Кого ждем?"
        "overcast" -> return "Сегодня пасмурно. Сиди ка ты лучше дома..\n хотя в магазин напротив, за чипсиками сбегать можно."
        "drizzle" -> return "Сегодня морось, прям как в фильмах ужасов. Сиди дома короче."
        "light-rain" -> return "Сегодня небольшой дождь, но нас этим не напугаешь"
        "rain" -> return "Сегодня дождь. Плачут небеса"
        "moderate-rain" -> return "Сегодня умеренно сильный дождь. Небеса рыдают"
        "heavy-rain" -> return "Сегодня сильный дождь. Самое то, чтобы сидеть дома и смотреть сериалы"
        "continuous-heavy-rain" -> return "Жопа там, целый день дождь будет."
        "showers" -> return "Ливень. Херачит не по детски"
        "wet-snow" -> return "Снег идет, очень очень мокрый...сиди дома"
        "light-snow" -> return "Сегодня небольшой снег. Ты в сказке"
        "snow" -> return "Сегодня снег, самый обычнейший, ничего особенного"
        "snow-showers" -> return "Лавину заказывали? Распишитесь"
        "hail" -> return "Сегодня град и ты этому точно не рад"
        "thunderstorm" -> return "Сегодня гроза, надеюсь пронесет. Лезь под одеяло"
        "thunderstorm-with-rain" -> return "Сегодня дождь с грозой, это короче жесть. Сиди дома"
        "thunderstorm-with-hail" -> return "Апокалипсис, мать его!!!"
        else -> return "Какая то непонятная херня с погодой"
        }
    }