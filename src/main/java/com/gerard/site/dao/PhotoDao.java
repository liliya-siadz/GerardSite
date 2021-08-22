package com.gerard.site.dao;

import com.gerard.site.entity.PhotoEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PhotoDao extends AbstractDao<PhotoEntity> {
    private static PhotoDao instance;
    private static final String TABLE_NAME = "gerard.photo";
    private static final String COLUMN_LABEL_1 = "photo_id";
    private static final String COLUMN_LABEL_2 = "photo_path";
    private static final String COLUMN_LABEL_3 = "name";
    private static final String COLUMN_LABEL_4 = "dog_id";
    private static final String COLUMN_LABEL_5 = "photo_date";
    private static final Logger LOGGER = LogManager.getLogger(PhotoDao.class);

    private PhotoDao() {
        super();
    }

    public static PhotoDao getInstance() {
        if (instance == null) {
            instance = new PhotoDao();
        }
        return instance;
    }

    @Override
    public PhotoEntity findRecord(PhotoEntity entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<PhotoEntity> selectAllRecords() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(PhotoEntity entity, PhotoEntity newEntityVersion) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(PhotoEntity entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PhotoEntity parseResultSet(ResultSet resultSet) throws SQLException {
        int photoId = resultSet.getInt(COLUMN_LABEL_1);
        String photoPath = resultSet.getString(COLUMN_LABEL_2);
        String name = resultSet.getString(COLUMN_LABEL_3);
        int dogId = resultSet.getInt(COLUMN_LABEL_4);
        Date photoDate = resultSet.getDate(COLUMN_LABEL_5);
        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setId(photoId);
        photoEntity.setPhotoPath(photoPath);
        photoEntity.setName(name);
        photoEntity.setDogId(dogId);
        photoEntity.setPhotoDate(photoDate);
        return photoEntity;
    }
}
