package com.example.sauexpert.model

data class AppointmentCardData(
    val name: String = "Замеры глюкозы",
    val lastSampleDate: String = "1 замер в 9:00, 14:00, 18:00",
    val period: String = "15 Октября 2021 — 20 декабря 2021",
    val frequency: String = "Каждый день"
)