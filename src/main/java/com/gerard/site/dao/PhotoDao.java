package com.gerard.site.dao;

import com.gerard.site.connection.ConnectionException;
import com.gerard.site.connection.ConnectionPool;
import com.gerard.site.entity.DogEntity;
import com.gerard.site.entity.PhotoAndDog;
import com.gerard.site.entity.PhotoEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhotoDao extends AbstractDao<PhotoEntity> {
    private static PhotoDao instance;
    static final String TABLE_NAME = "gerard.photo";
    static final String COLUMN_LABEL_1 = "photo_id";
    static final String COLUMN_LABEL_2 = "photo_path";
    static final String COLUMN_LABEL_3 = "name";
    static final String COLUMN_LABEL_4 = "dog_id";
    static final String COLUMN_LABEL_5 = "photo_date";

    private static final String SELECT_ALL_PHOTOS=
            "select photo_path, photo_date, photo_id,name, photo_path, dog_id from photo";
    private static final String SELECT_ALL_PHOTOS_AND_DOGS =
            "select photo_path, dog.nickname, photo_date "
                    + "from photo left join dog "
                    + "on photo.dog_id = dog.dog_id order by photo_date desc";
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

    public List<PhotoAndDog> selectAllPhotosAndDogs() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().giveOutConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_PHOTOS_AND_DOGS);
                if (resultSet.isBeforeFirst()) {
                    List<PhotoAndDog> selectedPhotos = new ArrayList<>();
                    while (resultSet.next()) {
                        String photoPath = resultSet.getString(COLUMN_LABEL_2);
                        Date photoDate = resultSet.getDate(COLUMN_LABEL_5);
                        String nickname = resultSet.getString(DogDao.COLUMN_LABEL_3);
                        PhotoAndDog photoAndDog = new PhotoAndDog(photoPath, photoDate, nickname);
                        selectedPhotos.add(photoAndDog);
                    }
                    return selectedPhotos;
                } else {
                    LOGGER.info("No records were found in table: " + TABLE_NAME + ". ");
                    LOGGER.warn("Null will be returned");
                    return null;
                }
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: " + TABLE_NAME
                    + " ! " + exception.getMessage(), exception);
        }
    }

    @Override
    public Optional<PhotoEntity> find(PhotoEntity entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<PhotoEntity> selectAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().giveOutConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_PHOTOS);
                List<PhotoEntity> selectedUsers = new ArrayList<>();
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {
                        selectedUsers.add(parseResultSet(resultSet));
                    }
                }
                return selectedUsers;
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + "Reason: " + exception.getMessage(), exception);
        }
    }

    @Override
    public boolean update(PhotoEntity entity, PhotoEntity newEntityVersion)
            throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<PhotoEntity> create(PhotoEntity entity) throws DaoException {
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
