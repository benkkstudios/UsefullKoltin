fun LocalDate.toWeton() : String {
    val kabisat = getKabisat()
    val dayCount = year * 365 + DaySize.getDaySize(month) + dayOfMonth + kabisat
    val currentDay = when (dayCount % 7) {
        0 -> "Jumat"
        1 -> "Sabtu"
        2 -> "Minggu"
        3 -> "Senin"
        4 -> "Selasa"
        5 -> "Rabu"
        6 -> "Kamis"
        else -> "Unknown"
    }

    val weton =  when (dayCount % 5) {
        0 -> "Kliwon"
        1 -> "Legi"
        2 -> "Pahing"
        3 -> "Pon"
        4 -> "Wage"
        else -> "Unknown"
    }


    val result = "$currentDay $weton, $dayOfMonth $monthValue $year"
    logging(result)
    return weton
}

private fun LocalDate.getKabisat() : Int{
    var kabisat = 1 + (year - (year % 4)) / 4
    if(year > 100) kabisat -= (year - (year % 400)) / 100
    if(year > 399) kabisat -= (year - (year % 400)) / 400
    if((year % 4) < 1 && monthValue < 3) kabisat--
    return kabisat
}