@file:Suppress("UNUSED_PARAMETER")

package lesson7

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */

/**
 * Быстродействие: O(n * m)
 * Ресурсоёмкость: O(n * m)
 */
fun longestCommonSubSequence(first: String, second: String): String {
    var m = first.length
    var n = second.length
    val res = Array(m + 1) { IntArray(n + 1) }
    for (i in 1 until m + 1) {
        for (j in 1 until n + 1) {
            if (first[i - 1] == second[j - 1]) {
                res[i][j] = res[i - 1][j - 1] + 1
            } else {
                res[i][j] = res[i - 1][j].coerceAtLeast(res[i][j - 1])
            }
        }
    }
    val output = StringBuilder()
    while (m > 0 && n > 0) {
        when {
            first[m - 1] == second[n - 1] -> {
                output.append(first[m - 1])
                m--
                n--
            }
            res[m][n - 1] == res[m][n] -> {
                n--
            }
            else -> {
                m--
            }
        }
    }
    return output.reverse().toString()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */

/**
 * Быстродействие: O(N^2)
 * Ресурсоёмкость: O(N)
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    val size = list.size
    if (size <= 1) return list
    val previous = IntArray(size)
    val value = IntArray(size)

    for (i in 0 until size) {
        value[i] = 1
        previous[i] = -1
        for (j in 0 until i) {
            val logic1 = list[j] < list[i]
            val logic2 = value[j] + 1 > value[i]
            if (logic1 && logic2) {
                value[i] = value[j] + 1
                previous[i] = j
            }
        }
    }

    var index = 0
    var length = 0
    for (i in 0 until size) {
        if (value[i] > length) {
            index = i
            length = value[i]
        }
    }

    val result: MutableList<Int> = ArrayList()
    while (index != -1) {
        result.add(0, list[index])
        index = previous[index]
    }
    return result
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}
