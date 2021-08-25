package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<E extends AbstractEntity<?>> {
    protected AbstractDao() {
    }

    public abstract Optional<E> find(E entity) throws DaoException;
    public abstract List<E> selectAll() throws DaoException;
    public abstract boolean remove(E entity) throws DaoException;
    public abstract boolean update(E entity, E newEntityVersion) throws DaoException;
    public abstract boolean create(E entity) throws DaoException;
    protected abstract E parseResultSet(ResultSet resultSet) throws SQLException;
}
