package com.gerard.site.dao;

import com.gerard.site.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<E extends AbstractEntity<?>> {

    //todo make this package singleton
    protected AbstractDao() {
    }

    public abstract E find(E entity) throws DaoException;
    public abstract List<E> selectAll() throws DaoException;
    public abstract boolean update(E entity, E newEntityVersion) throws DaoException;
    public abstract boolean create(E entity) throws DaoException;
    public abstract E parseResultSet(ResultSet resultSet) throws SQLException;

    @Override
    public String toString() {
        return "AbstractDao{}";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return (this == object);
    }
}
