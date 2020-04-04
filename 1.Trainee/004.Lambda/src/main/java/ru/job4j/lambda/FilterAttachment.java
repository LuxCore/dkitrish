package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Фильтрация вложений по различным признакам.
 */
public class FilterAttachment {

    /**
     * Универсальный фильтр вложений.
     * @param list Список вложений.
     * @param predicate Условие, по которому будет производиться фильтрация вложений.
     * @return Список отфильтрованных вложений.
     */
    public static List<Attachment> filter(List<Attachment> list, Predicate<Attachment> predicate) {
        List<Attachment> result = new ArrayList<>();
        list.forEach((attachment) -> {
            if (predicate.test(attachment)) {
                result.add(attachment);
            }
        });
        return result;
    }
}
