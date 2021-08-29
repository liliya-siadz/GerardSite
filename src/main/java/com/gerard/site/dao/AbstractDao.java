package com.gerard.site.dao;

import com.gerard.site.controller.PaginationItem;
import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.entity.AbstractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<E extends AbstractEntity<?>> {
    protected AbstractDao() {
    }

    /**
     * @author Denis Kotov
     *
     * @param query current query to data base
     * @param pageNumber current page for displaying entities
     * @return query with current page
     */
    public String buildPaginatedQuery(String query, int pageNumber) {
        String LIMIT = " LIMIT ";
        String SEPARATOR = ", ";
        int limit = PaginationItem.PAGE_SIZE;
        int offset = (limit * pageNumber) - limit;
        StringBuilder queryBuilder = new StringBuilder(query);
        queryBuilder.append(LIMIT);
        if (offset > 0) {
            queryBuilder.append(offset).append(SEPARATOR);
        }
        queryBuilder.append(limit);
        return queryBuilder.toString();
    }

    public abstract Optional<E> find(E entity) throws DaoException;

    public abstract List<E> selectAll() throws DaoException;

    public abstract boolean remove(E entity) throws DaoException;

    public abstract boolean update(E entity, E newEntityVersion) throws DaoException;

    public abstract boolean create(E entity) throws DaoException;

    protected abstract E parseResultSet(ResultSet resultSet) throws SQLException;
}
