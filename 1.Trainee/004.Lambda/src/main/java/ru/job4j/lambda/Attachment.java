package ru.job4j.lambda;

/**
 * Вложение.
 */
public class Attachment {
    /**
     * Наименование вложения.
     */
    private String name;

    /**
     * Размер вложения.
     */
    private int size;

    /**
     * Конструктор вложения.
     * @param name Наименование вложения.
     * @param size Размер вложения.
     */
    public Attachment(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Attachment{"
                + "name='" + name + '\''
                + ", size=" + size
                + '}';
    }
}
