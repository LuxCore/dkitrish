package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Сортировка вложений.
 */
public class AttachmentSort {
    public static void main(String[] args) {
        List<Attachment> attachments = Arrays.asList(
                new Attachment("attachment 33", 22),
                new Attachment("attachment 11", 33),
                new Attachment("attachment 22", 11)
        );
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Attachment left = (Attachment) o1;
                Attachment right = (Attachment) o2;
                return left.getSize() - right.getSize();
            }
        };
        attachments.sort(comparator);
        System.out.println(attachments);

        Comparator<Attachment> attachmentNameComp = new Comparator<Attachment>() {
            @Override
            public int compare(Attachment left, Attachment right) {
                return left.getName().compareTo(right.getName());
            }
        };
        attachments.sort(attachmentNameComp);
        System.out.println(attachments);
    }
}
