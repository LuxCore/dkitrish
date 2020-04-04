package ru.job4j.lambda;

import java.util.Objects;

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

    /**
     * Получение имени вложения.
     * @return Имя вложения.
     */
    public String getName() {
        return name;
    }

    /**
     * Получение размера вложения.
     * @return Размер вложения.
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attachment that = (Attachment) o;
        return size == that.size && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
