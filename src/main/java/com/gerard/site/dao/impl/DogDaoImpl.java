package com.gerard.site.dao.impl;

import com.gerard.site.dao.AbstractDao;
import com.gerard.site.dao.DogDao;
import com.gerard.site.dao.connection.ConnectionException;
import com.gerard.site.dao.connection.ConnectionPool;
import com.gerard.site.service.entity.DogEntity;
import com.gerard.site.service.view.Dog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DogDaoImpl extends AbstractDao<DogEntity> implements DogDao {
    static final String TABLE_NAME = "dog";
    static final String COLUMN_LABEL_1 = "dog_id";
    static final String COLUMN_LABEL_2 = "dog_sex";
    static final String COLUMN_LABEL_3 = "nickname";
    static final String COLUMN_LABEL_4 = "fullname";
    static final String COLUMN_LABEL_5 = "birthday";
    static final String COLUMN_LABEL_6 = "description";
    static final String COLUMN_LABEL_7 = "active";
    static final String COLUMN_LABEL_8 = "avatar_photo_id";
    static final String COLUMN_LABEL_9 = "pedigree_photo_id";
    private static DogDaoImpl instance;

    private static final String SELECT_DOG_BY_ID =
            "select dog_id, dog_sex, nickname,"
                    + "fullname, birthday,avatar_photo_id, pedigree_photo_id,"
                    + "description, active from dog where dog_id=?";
    private static final String SELECT_ALL_DOGS_WITH_PHOTOS =
            """
                    SELECT avatars.avatar, pedigrees.pedigree,
                    avatars.dog_id, avatars.nickname, avatars.fullname,
                    avatars.dog_sex, avatars.birthday, avatars.active, avatars.description
                    from
                    (select photo.photo_path as avatar, dog.dog_id, dog.nickname, dog.fullname,
                    dog.dog_sex, dog.birthday, dog.active, dog.description
                    from photo
                    inner join dog
                    on photo.photo_id = dog.avatar_photo_id
                    where photo.photo_type='avatar')
                    as
                    avatars
                    inner join
                    (select photo.photo_path as pedigree, photo.dog_id
                    from photo
                    inner join dog
                    on photo.photo_id = dog.pedigree_photo_id
                    where photo.photo_type='pedigree')
                    as
                    pedigrees
                    on avatars.dog_id = pedigrees.dog_id;
                    """;

    private static final Logger LOGGER = LogManager.getLogger(DogDaoImpl.class);

    private DogDaoImpl() {
        super();
    }

    public static DogDaoImpl getInstance() {
        if (instance == null) {
            instance = new DogDaoImpl();
        }
        return instance;
    }

    @Override
    public Optional<DogEntity> find(DogEntity entity) throws DaoException {
        if (entity == null) {
            throw new DaoException("Parameter 'entity' is null");
        }
        int id = entity.getId();
        try (Connection connection =
                     ConnectionPool.getInstance().giveOutConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(SELECT_DOG_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            DogEntity dogEntity = null;
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    dogEntity = parseResultSetEntity(resultSet);
                }
            }
            return Optional.ofNullable(dogEntity);
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to select data from table: "
                    + TABLE_NAME + " ! "
                    + exception.getMessage(), exception);
        }
    }

    @Override
    public List<Dog> selectAllDogsWithPhotos() throws DaoException {
        try (Connection connection =
                     ConnectionPool.getInstance().giveOutConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet =
                    statement.executeQuery(SELECT_ALL_DOGS_WITH_PHOTOS);
            if (resultSet.isBeforeFirst()) {
                List<Dog> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(parseResultSetDog(resultSet));
                }
                return users;
            } else {
                LOGGER.info("No records were found in table: "
                        + TABLE_NAME + ". ");
                return Collections.emptyList();
            }
        } catch (ConnectionException | SQLException exception) {
            throw new DaoException("Unable to select data from table: "
                    + TABLE_NAME + " ! "
                    + "Reason: " + exception.getMessage(), exception);
        }
    }

    @Override
    public Dog parseResultSetDog(ResultSet resultSet) throws SQLException {
        int dogId = resultSet.getInt(COLUMN_LABEL_1);
        DogEntity.DogSex dogSex =
                DogEntity.DogSex.valueOf(
                        resultSet.getString(COLUMN_LABEL_2).toUpperCase());
        String nickname = resultSet.getString(COLUMN_LABEL_3);
        String fullname = resultSet.getString(COLUMN_LABEL_4);
        Date birthday = resultSet.getDate(COLUMN_LABEL_5);
        final String avatarPhotoPathColumnAlias = "avatar";
        final String pedigreePhotoPathColumnAlias = "pedigree";
        String avatarPhotoPath
                = resultSet.getString(avatarPhotoPathColumnAlias);
        String pedigreePhotoPath
                = resultSet.getString(pedigreePhotoPathColumnAlias);
        String description = resultSet.getString(COLUMN_LABEL_6);
        boolean active = resultSet.getBoolean(COLUMN_LABEL_7);
        Dog dog = new Dog();
        dog.setId(dogId);
        dog.setDogSex(dogSex);
        dog.setNickname(nickname);
        dog.setFullname(fullname);
        dog.setBirthday(birthday);
        dog.setAvatarPhotoPath(avatarPhotoPath);
        dog.setPedigreePhotoPath(pedigreePhotoPath);
        dog.setDescription(description);
        dog.setActive(active);
        return dog;
    }

    @Override
    public DogEntity parseResultSetEntity(ResultSet resultSet) throws SQLException {
        int dogId = resultSet.getInt(COLUMN_LABEL_1);
        DogEntity.DogSex dogSex =
                DogEntity.DogSex.valueOf(
                        resultSet.getString(COLUMN_LABEL_2).toUpperCase());
        String nickname = resultSet.getString(COLUMN_LABEL_3);
        String fullname = resultSet.getString(COLUMN_LABEL_4);
        Date birthday = resultSet.getDate(COLUMN_LABEL_5);
        int avatarPhotoId = resultSet.getInt(COLUMN_LABEL_8);
        int pedigreePhotoId = resultSet.getInt(COLUMN_LABEL_9);
        String description = resultSet.getString(COLUMN_LABEL_6);
        boolean active = resultSet.getBoolean(COLUMN_LABEL_6);
        DogEntity dogEntity = new DogEntity.Builder()
                .id(dogId)
                .dogSex(dogSex)
                .nickname(nickname)
                .birthday(birthday)
                .fullname(fullname)
                .avatarPhotoId(avatarPhotoId)
                .pedigreePhotoId(pedigreePhotoId)
                .description(description)
                .active(active)
                .build();
        return dogEntity;
    }
}
