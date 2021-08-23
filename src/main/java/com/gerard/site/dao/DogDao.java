package com.gerard.site.dao;

import com.gerard.site.connection.ConnectionException;
import com.gerard.site.connection.ConnectionPool;
import com.gerard.site.entity.DogEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DogDao extends AbstractDao<DogEntity> {
    private static DogDao instance;
    static final String TABLE_NAME = "dog";
    static final String COLUMN_LABEL_1 = "dog_id";
    static final String COLUMN_LABEL_2 = "dog_sex";
    static final String COLUMN_LABEL_3 = "nickname";
    static final String COLUMN_LABEL_4 = "fullname";
    static final String COLUMN_LABEL_5 = "birthday";
    static final String COLUMN_LABEL_6 = "avatar_photo_path";
    static final String COLUMN_LABEL_7 = "pedigree_photo_path";
    static final String COLUMN_LABEL_8 = "description";
    static final String COLUMN_LABEL_9 = "is_active";

    private static final String SELECT_DOG_BY_ID = "select dog_id, dog_sex, nickname," +
            "fullname, birthday,avatar_photo_path, pedigree_photo_path," +
            "description, is_active from dog where dog_id=?";

    private static final String SELECT_ALL_DOGS = "select dog_id, dog_sex, nickname," +
            "fullname, birthday,avatar_photo_path, pedigree_photo_path, is_active, description from dog";
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
    public Optional<DogEntity> find(DogEntity entity) throws DaoException {
        if (entity == null) {
            throw new DaoException("Parameter 'entity' is null");
        }
        int id = entity.getId();
        try (Connection connection
                     = ConnectionPool.getInstance().giveOutConnection()) {
            try (PreparedStatement preparedStatement
                         = connection.prepareStatement(SELECT_DOG_BY_ID)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                DogEntity selectedDog = null;
                if (resultSet.isBeforeFirst()) {
                    while (resultSet.next()) {
                        selectedDog = parseResultSet(resultSet);
                    }
                }
                return Optional.ofNullable(selectedDog);
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to get data from table: "
                    + TABLE_NAME + " ! "
                    + exception.getMessage(), exception);
        }
    }

    @Override
    public List<DogEntity> selectAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().giveOutConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SELECT_ALL_DOGS);
                List<DogEntity> selectedUsers = new ArrayList<>();
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
    public boolean update(DogEntity entity, DogEntity newEntityVersion) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<DogEntity> create(DogEntity entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public DogEntity parseResultSet(ResultSet resultSet) throws SQLException {
        int dogId = resultSet.getInt(COLUMN_LABEL_1);
        DogEntity.DogSex dogSex = DogEntity.DogSex.valueOf(resultSet.getString(COLUMN_LABEL_2).toUpperCase());
        String nickname = resultSet.getString(COLUMN_LABEL_3);
        String fullname = resultSet.getString(COLUMN_LABEL_4);
        Date birthday = resultSet.getDate(COLUMN_LABEL_5);
        String avatarPhotoPath = resultSet.getString(COLUMN_LABEL_6);
        String pedigreePhotoPath = resultSet.getString(COLUMN_LABEL_7);
        String description = resultSet.getString(COLUMN_LABEL_8);
        boolean isActive = resultSet.getBoolean(COLUMN_LABEL_9);
        DogEntity dog = new DogEntity.Builder()
                .id(dogId)
                .dogSex(dogSex)
                .nickname(nickname)
                .birthday(birthday)
                .fullname(fullname)
                .avatarPhotoPath(avatarPhotoPath)
                .pedigreePhotoPath(pedigreePhotoPath)
                .description(description)
                .isActive(isActive)
                .build();
        return dog;
    }
}
