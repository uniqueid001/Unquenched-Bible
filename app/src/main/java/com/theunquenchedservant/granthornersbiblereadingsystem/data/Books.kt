package com.theunquenchedservant.granthornersbiblereadingsystem.data

object Books {
    val bookChapters : Map<String, Int> = mapOf(
            "genesis" to 50, "exodus" to 40, "leviticus" to 27, "numbers" to 36,
            "deuteronomy" to 34, "joshua" to 24, "judges" to 21, "ruth" to 4,
            "samuel1" to 31, "samuel2" to 24, "kings1" to 22, "kings2" to 25,
            "chronicles1" to 29, "chronicles2" to 36, "ezra" to 10, "nehemiah" to 13,
            "esther" to 10, "job" to 42, "psalm" to 150, "proverbs" to 31,
            "ecclesiastes" to 12, "song" to 8, "isaiah" to 66, "jeremiah" to 52,
            "lamentations" to 5, "ezekiel" to 48, "daniel" to 12, "hosea" to 14,
            "joel" to 3, "amos" to 9, "obadiah" to 1, "jonah" to 4,
            "micah" to 7, "nahum" to 3, "habakkuk" to 3, "zephaniah" to 3,
            "haggai" to 2, "zechariah" to 14, "malachi" to 4, "matthew" to 28,
            "mark" to 16, "luke" to 24, "john" to 21, "acts" to 28,
            "romans" to 16, "corinthians1" to 16, "corinthians2" to 13, "galatians" to 6,
            "ephesians" to 6, "philippians" to 4, "colossians" to 4, "thessalonians1" to 5,
            "thessalonians2" to 3, "timothy1" to 6, "timothy2" to 4, "titus" to 3,
            "philemon" to 1, "hebrews" to 13, "james" to 5, "peter1" to 5,
            "peter2" to 3, "john1" to 5, "john2" to 1, "john3" to 1,
            "jude" to 1, "revelation" to 1
    )
    val bookNames : Map<String, String> = mapOf(
            "genesis" to "Genesis", "exodus" to "Exodus", "leviticus" to "Leviticus", "numbers" to "Numbers",
            "deuteronomy" to "Deuteronomy", "joshua" to "Joshua", "judges" to "Judges", "ruth" to "Ruth",
            "samuel1" to "1 Samuel", "samuel2" to "2 Samuel", "kings1" to "1 Kings", "kings2" to "2 Kings",
            "chronicles1" to "1 Chronicles", "chronicles2" to "2 Chronicles", "ezra" to "Ezra", "nehemiah" to "Nehemiah",
            "esther" to "Esther", "job" to "Job", "psalm" to "Psalm", "proverbs" to "Proverbs",
            "ecclesiastes" to "Ecclesiastes", "song" to "Song of Solomon", "isaiah" to "Isaiah", "jeremiah" to "Jeremiah",
            "lamentations" to "Lamentations", "ezekiel" to "Ezekiel", "daniel" to "Daniel", "hosea" to "Hosea",
            "joel" to "Joel", "amos" to "Amos", "obadiah" to "Obadiah", "jonah" to "Jonah",
            "micah" to "Micah", "nahum" to "Nahum", "habakkuk" to "Habakkuk", "zephaniah" to "Zephaniah",
            "haggai" to "Haggai", "zechariah" to "Zechariah", "malachi" to "Malachi", "matthew" to "Matthew",
            "mark" to "Mark", "luke" to "Luke", "john" to "John", "acts" to "Acts",
            "romans" to "Romans", "corinthians1" to "1 Corinthians", "corinthians2" to "2 Corinthians", "galatians" to "Galatians",
            "ephesians" to "Ephesians", "philippians" to "Philippians", "colossians" to "Colossians", "thessalonians1" to "1 Thessalonians",
            "thessalonians2" to "2 Thessalonians", "timothy1" to "1 Timothy", "timothy2" to "2 Timothy", "titus" to "Titus",
            "philemon" to "Philemon", "hebrews" to "Hebrews", "james" to "James", "peter1" to "1 Peter",
            "peter2" to "2 Peter", "john1" to "1 John",  "john2" to "2 John", "john3" to "3 John",
            "jude" to "Jude", "revelation" to "Revelation"
    )
    val otBooks = arrayOf("genesis", "exodus", "leviticus", "numbers", "deuteronomy",
            "joshua", "judges", "ruth", "samuel1", "samuel2", "kings1", "kings2",
            "chronicles1", "chronicles2", "ezra", "nehemiah", "esther", "job",
            "psalm", "proverbs", "ecclesiastes", "song", "isaiah", "jeremiah",
            "lamentations", "ezekiel", "daniel", "hosea", "joel", "amos", "obadiah",
            "jonah", "micah", "nahum", "habakkuk", "zephaniah", "haggai", "zechariah", "malachi")
    val ntBooks = arrayOf("matthew", "mark", "luke", "john", "acts", "romans", "corinthians1",
            "corinthians2", "galatians", "ephesians", "philippians", "colossians", "thessalonians1",
            "thessalonians2", "timothy1", "timothy2", "titus", "philemon", "hebrews", "james",
            "peter2", "peter2", "john1", "john2", "john3", "jude", "revelation")

    fun getBooks(testament: String) : Array<String>?{
        return when(testament){
            "old"-> otBooks
            "new"-> ntBooks
            else-> null
        }
    }
}