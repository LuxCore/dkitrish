package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестирование фильтрации вложений.
 */
public class FilterAttachmentTest {

    /**
     * Тест универсального фильтра вложений.
     * Фильтрация вложений по имени, содержащему подстроку Test.
     */
    @Test
    public void testFilterByNameContainedSubstring() {
        List<Attachment> attachments = Arrays.asList(
                new Attachment("Attachment 1. Work", 11),
                new Attachment("Attachment 2. Test", 22),
                new Attachment("Attachment 3. Work", 33),
                new Attachment("Attachment 4. Test", 44),
                new Attachment("Attachment 5. Test", 55)
        );
        List<Attachment> expectedAttachments = Arrays.asList(
                new Attachment("Attachment 2. Test", 22),
                new Attachment("Attachment 4. Test", 44),
                new Attachment("Attachment 5. Test", 55)
        );
        List<Attachment> filteredAttachments = FilterAttachment.filter(
                attachments, (attachment) -> attachment.getName().contains("Test")
        );
        assertThat(filteredAttachments, is(expectedAttachments));
    }

    /**
     * Тест универсального фильтра вложений.
     * Фильтрация вложений по размеру, превышающему 100.
     */
    @Test
    public void testFilterBySizeGreaterThan100() {
        List<Attachment> attachments = Arrays.asList(
                new Attachment("Attachment 1. Work", 101),
                new Attachment("Attachment 2. Test", 22),
                new Attachment("Attachment 3. Work", 33),
                new Attachment("Attachment 4. Test", 44),
                new Attachment("Attachment 5. Test", 121)
        );
        List<Attachment> expectedAttachments = Arrays.asList(
                new Attachment("Attachment 1. Work", 101),
                new Attachment("Attachment 5. Test", 121)
        );
        List<Attachment> filteredAttachments = FilterAttachment.filter(
                attachments, (attachment) -> attachment.getSize() > 100
        );
        assertThat(filteredAttachments, is(expectedAttachments));
    }
}