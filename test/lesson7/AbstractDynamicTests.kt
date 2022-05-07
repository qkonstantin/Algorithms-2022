package lesson7

import kotlin.test.assertEquals

abstract class AbstractDynamicTests {
    fun longestCommonSubSequence(longestCommonSubSequence: (String, String) -> String) {
        assertEquals("дворовый", longestCommonSubSequence("дворцовый", "дворовый"))
        assertEquals("пдорожн", longestCommonSubSequence("подорожник", "придорожный"))
        assertEquals("игрй", longestCommonSubSequence("игрушечный", "играющий"))
        assertEquals("расии", longestCommonSubSequence("красильщик", "раскрашивание"))
        assertEquals("листоный", longestCommonSubSequence("листовидный", "листообразный"))
        assertEquals("", longestCommonSubSequence("мой мир", "я"))
        assertEquals("1", longestCommonSubSequence("1", "1"))
        assertEquals("13", longestCommonSubSequence("123", "13"))
        assertEquals("здс", longestCommonSubSequence("здравствуй мир", "мы здесь"))
        assertEquals("emt ole", longestCommonSubSequence("nematode knowledge", "empty bottle"))
        val expectedLength = "e kerwelkkd r".length
        assertEquals(
            expectedLength, longestCommonSubSequence(
                "oiweijgw kejrhwejelkrw kjhdkfjs hrk",
                "perhkhk lerkerorwetp lkjklvvd durltr"
            ).length, "Answer must have length of $expectedLength, e.g. 'e kerwelkkd r' or 'erhlkrw kjk r'"
        )
        val expectedLength2 = """ дд саы чтых,
евшнео ваа се сви дн.
        """.trimIndent().length
        assertEquals(
            expectedLength2, longestCommonSubSequence(
                """
Мой дядя самых честных правил,
Когда не в шутку занемог,
Он уважать себя заставил
И лучше выдумать не мог.
                """.trimIndent(),
                """
Так думал молодой повеса,
Летя в пыли на почтовых,
Всевышней волею Зевеса
Наследник всех своих родных.
                """.trimIndent()
            ).length, "Answer must have length of $expectedLength2"
        )
        val expectedLength3 = """NevergonnaNevergonnaNevergonnaNevergonnaNevergonnaNevergonna""".length
        assertEquals(
            expectedLength3, longestCommonSubSequence(
                """
Never gonna give you up
Never gonna let you down
Never gonna run around and desert you
                """.trimIndent(),
                """
Never gonna make you cry
Never gonna say goodbye
Never gonna tell a lie and hurt you
                """.trimIndent()
            ).length, "Answer must have length of $expectedLength3"
        )
    }

    fun longestIncreasingSubSequence(longestIncreasingSubSequence: (List<Int>) -> List<Int>) {
        assertEquals(listOf(-1), longestIncreasingSubSequence(listOf(-1, -2, -3, -4, -5, -6, -7, -8, -9)))
        assertEquals(
            listOf(-9, -8, -7, -6, -5, -4, -3, -2, -1),
            longestIncreasingSubSequence(listOf(-9, -8, -7, -6, -5, -4, -3, -2, -1))
        )
        assertEquals(listOf(0), longestIncreasingSubSequence(listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)))
        assertEquals(listOf(9), longestIncreasingSubSequence(listOf(9, 8, 7, 6, 5, 4, 3, 2, 1)))
        assertEquals(listOf(4, 5, 6, 7), longestIncreasingSubSequence(listOf(9, 8, 4, 5, 2, 3, 6, 7)))
        assertEquals(listOf(9, 99, 999, 9999), longestIncreasingSubSequence(listOf(9, 1, 99, 1, 999, 1, 9999)))
        assertEquals(listOf(22, 65, 66, 89), longestIncreasingSubSequence(listOf(22, 94, 12, 65, 66, 89, 78, 12, 20)))
        assertEquals(
            listOf(100, 125, 150, 175, 185, 195, 500, 600, 650, 900),
            longestIncreasingSubSequence(listOf(100, 200, 125, 300, 150, 400, 175, 185, 195, 500, 600, 550, 650, 900))
        )
        assertEquals(listOf(), longestIncreasingSubSequence(listOf()))
        assertEquals(listOf(1), longestIncreasingSubSequence(listOf(1)))
        assertEquals(listOf(1, 2), longestIncreasingSubSequence(listOf(1, 2)))
        assertEquals(listOf(2), longestIncreasingSubSequence(listOf(2, 1)))
        assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            longestIncreasingSubSequence(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        )
        assertEquals(listOf(2, 8, 9, 12), longestIncreasingSubSequence(listOf(2, 8, 5, 9, 12, 6)))
        assertEquals(
            listOf(23, 34, 56, 87, 91, 98, 140, 349), longestIncreasingSubSequence(
                listOf(
                    23, 76, 34, 93, 123, 21, 56, 87, 91, 12, 45, 98, 140, 12, 5, 38, 349, 65, 94,
                    45, 76, 15, 99, 100, 88, 84, 35, 88
                )
            )
        )
    }

    fun shortestPathOnField(shortestPathOnField: (String) -> Int) {
        assertEquals(1, shortestPathOnField("input/field_in2.txt"))
        assertEquals(12, shortestPathOnField("input/field_in1.txt"))
        assertEquals(43, shortestPathOnField("input/field_in3.txt"))
        assertEquals(28, shortestPathOnField("input/field_in4.txt"))
        assertEquals(222, shortestPathOnField("input/field_in5.txt"))
        assertEquals(15, shortestPathOnField("input/field_in6.txt"))
    }

}