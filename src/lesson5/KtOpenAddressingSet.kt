package lesson5

/**
 * Множество(таблица) с открытой адресацией на 2^bits элементов без возможности роста.
 */
class KtOpenAddressingSet<T : Any>(private val bits: Int) : AbstractMutableSet<T>() {
    private object removed

    init {
        require(bits in 2..31)
    }

    private val capacity = 1 shl bits

    private val storage = Array<Any?>(capacity) { null }

    override var size: Int = 0

    /**
     * Индекс в таблице, начиная с которого следует искать данный элемент
     */
    private fun T.startingIndex(): Int {
        return hashCode() and (0x7FFFFFFF shr (31 - bits))
    }

    /**
     * Проверка, входит ли данный элемент в таблицу
     */
    override fun contains(element: T): Boolean {
        val startingIndex = element.startingIndex()
        var index = startingIndex
        var current = storage[index]
        while (current != null) {
            if (current == element) {
                return true
            }
            index = (index + 1) % capacity
            if (startingIndex == index) return false
            current = storage[index]
        }
        return false
    }

    /**
     * Добавление элемента в таблицу.
     *
     * Не делает ничего и возвращает false, если такой же элемент уже есть в таблице.
     * В противном случае вставляет элемент в таблицу и возвращает true.
     *
     * Бросает исключение (IllegalStateException) в случае переполнения таблицы.
     * Обычно Set не предполагает ограничения на размер и подобных контрактов,
     * но в данном случае это было введено для упрощения кода.
     */
    override fun add(element: T): Boolean {
        val startingIndex = element.startingIndex()
        var index = startingIndex
        var current = storage[index]
        while (current != null && current != removed) {
            if (current == element) {
                return false
            }
            index = (index + 1) % capacity
            check(index != startingIndex) { "Table is full" }
            current = storage[index]
        }
        storage[index] = element
        size++
        return true
    }

    /**
     * Удаление элемента из таблицы
     *
     * Если элемент есть в таблице, функция удаляет его из дерева и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * Высота дерева не должна увеличиться в результате удаления.
     *
     * Спецификация: [java.util.Set.remove] (Ctrl+Click по remove)
     *
     * Средняя
     */

    /**
     * Быстродействие: O(1) - лучший случай
     *                 O(N) - худший случай
     * Ресурсоёмкость: O(1)
     */

    override fun remove(element: T): Boolean {
        val startingIndex = element.startingIndex()
        var i = startingIndex
        var current = storage[i]

        while (current != null) {
            if (current == element) {
                storage[i] = removed
                size--
                return true
            }
            i = (i + 1) % capacity
            if (startingIndex == i) continue
            current = storage[i]
        }
        return false
    }

    /**
     * Создание итератора для обхода таблицы
     *
     * Не забываем, что итератор должен поддерживать функции next(), hasNext(),
     * и опционально функцию remove()
     *
     * Спецификация: [java.util.Iterator] (Ctrl+Click по Iterator)
     *
     * Средняя (сложная, если поддержан и remove тоже)
     */
    override fun iterator(): MutableIterator<T> {
        return OpenAddressingSetIterator()
    }

    private inner class OpenAddressingSetIterator : MutableIterator<T> {
        var current: T? = null
        var i = 0
        var totalElements = 0

        /**
         * Быстродействие: O(N)
         * Ресурсоёмкость: O(1)
         */
        override fun next(): T {
            if (!hasNext()) throw NoSuchElementException()
            while (storage[i] == null || storage[i] == removed) {
                i++
            }
            current = storage[i] as T
            i++
            totalElements++
            return current as T
        }

        /**
         * Быстродействие: O(1)
         * Ресурсоёмкость: O(1)
         */
        override fun remove() {
            check(current != null)
            storage[i - 1] = removed
            current = null
            totalElements--
            size--
        }

        /**
         * Быстродействие: O(1)
         * Ресурсоёмкость: O(1)
         */
        override fun hasNext(): Boolean = totalElements < size
    }

}
