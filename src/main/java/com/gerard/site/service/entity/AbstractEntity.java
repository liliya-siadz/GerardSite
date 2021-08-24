package com.gerard.site.service.entity;

/**
 * Represents any single record from any table in database <i>gerard</i> .
 * Represents record's id by parametrized instance field,
 * which could be used by all subclasses
 *
 * @param <K> record's id type {@link AbstractEntity#id}
 *            (java type, that subjectively mapped to database type)
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class AbstractEntity<K> {
    /**
     * Record's id, is used for representing table's primary key .
     */
    protected K id;

    public AbstractEntity() {
    }

    public final K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = hash + 31 * (id == null ? 0 : id.hashCode());
        return hashcode;
    }

    @Override
    public String toString() {
        return "AbstractEntity{"
                + "id=" + id
                + '}';
    }
}
