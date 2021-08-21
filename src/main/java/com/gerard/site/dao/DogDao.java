package com.gerard.site.dao;

import com.gerard.site.connection.ConnectionException;
import com.gerard.site.connection.ConnectionPool;
import com.gerard.site.entity.DogEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DogDao extends AbstractDao<DogEntity> {
    private static DogDao instance;
    private static final String TABLE_NAME = "gerard.dog";
    private static final String COLUMN_LABEL_1 = "dog_id";
    private static final String COLUMN_LABEL_2 = "dog_status";
    private static final String COLUMN_LABEL_3 = "dog_sex";
    private static final String COLUMN_LABEL_4 = "nickname";
    private static final String COLUMN_LABEL_5 = "fullname";
    private static final String COLUMN_LABEL_6 = "birthday";
    private static final String COLUMN_LABEL_7 = "mother_dog_id";
    private static final String COLUMN_LABEL_8 = "father_dog_id";
    private static final String COLUMN_LABEL_9 = "avatar_photo_path";
    private static final String COLUMN_LABEL_10 = "pedigree_photo_path";
    private static final String COLUMN_LABEL_11 = "description";
    private static final Logger LOGGER = LogManager.getLogger(DogDao.class);

    private DogDao() {
        super();
    }

    public static DogDao getInstance() {
        if (instance == null) {
            instance = new DogDao();
        }
        return instance;
    }

    @Override
    public DogEntity findRecord(DogEntity entity) throws DaoException {
        return null;
    }

    @Override
    public List<DogEntity> selectAllRecords() throws DaoException {
        final String selectAllDogs = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_LABEL_4;
        try (Connection connection = ConnectionPool.getInstance().giveOutConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(selectAllDogs);
                if (resultSet.isBeforeFirst()) {
                    List<DogEntity> selectedUsers = new ArrayList<>();
                    while (resultSet.next()) {
                        selectedUsers.add(parseResultSet(resultSet));
                    }
                    return selectedUsers;
                } else {
                    LOGGER.info("No records were found in table: "
                            + TABLE_NAME + ". ");
                    LOGGER.warn("Null will be returned");
                    return null;
                }
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + "Reason: " + exception.getMessage(), exception);
        }
    }

    @Override
    public boolean update(DogEntity entity, DogEntity newEntityVersion) throws DaoException {
        return false;
    }

    @Override
    public boolean create(DogEntity entity) throws DaoException {
        return false;
    }

    @Override
    public DogEntity parseResultSet(ResultSet resultSet) throws SQLException {
        int dogId = resultSet.getInt(COLUMN_LABEL_1);
        DogEntity.DogStatus dogStatus =
                DogEntity.DogStatus.valueOf(
                        resultSet.getString(COLUMN_LABEL_2).toUpperCase());
        DogEntity.DogSex dogSex =
                DogEntity.DogSex.valueOf(
                        resultSet.getString(COLUMN_LABEL_3).toUpperCase());
        String nickname = resultSet.getString(COLUMN_LABEL_4);
        String fullname = resultSet.getString(COLUMN_LABEL_5);
        Date birthday = resultSet.getDate(COLUMN_LABEL_6);
        int motherDogId = resultSet.getInt(COLUMN_LABEL_7);
        int fatherDogId = resultSet.getInt(COLUMN_LABEL_8);
        String avatarPhotoPath = resultSet.getString(COLUMN_LABEL_9);
        String pedigreePhotoPath = resultSet.getString(COLUMN_LABEL_10);
        String description = resultSet.getString(COLUMN_LABEL_11);
        DogEntity dog = new DogEntity.Builder()
                .id(dogId)
                .dogStatus(dogStatus)
                .birthday(birthday)
                .dogSex(dogSex)
                .nickname(nickname)
                .fullname(fullname)
                .motherDogId(motherDogId)
                .fatherDogId(fatherDogId)
                .avatarPhotoPath(avatarPhotoPath)
                .pedigreePhotoPath(pedigreePhotoPath)
                .description(description)
                .build();
        return dog;
    }
}
