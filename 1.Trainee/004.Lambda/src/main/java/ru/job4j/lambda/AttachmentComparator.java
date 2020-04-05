package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Сравнение вложений.
 */
public class AttachmentComparator {
    /**
     * Точка входа.
     * @param args Входные аргументы.
     */
    public static void main(String[] args) {
        Attachment[] attachments = {
                new Attachment("image 1", 20),
                new Attachment("image 3", 120),
                new Attachment("image 2", 23)
        };
        Comparator<Attachment> comparator = (lower, upper) -> {
            System.out.println("upper : " + upper);
            System.out.println("lower : " + lower);
            return upper.getSize() - lower.getSize();
        };
        Arrays.sort(attachments, comparator);
    }
}
