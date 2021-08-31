package com.gerard.site.dao;

import com.gerard.site.dao.impl.DaoException;
import com.gerard.site.service.entity.PhotoEntity;
import com.gerard.site.service.view.Photo;

import java.util.List;

public interface PhotoDao {
    List<Photo> selectAllPhotos() throws DaoException;

    List<Photo> provideDecimalPieceOfPhotos(int pieceValue) throws DaoException;

    boolean deleteByPhotoPath(String photoPath) throws DaoException;

    List<PhotoEntity> selectAll() throws DaoException;
}
