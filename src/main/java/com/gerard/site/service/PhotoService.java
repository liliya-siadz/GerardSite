package com.gerard.site.service;

import com.gerard.site.service.view.Photo;
import com.gerard.site.service.entity.PhotoEntity;

import java.util.List;

public interface PhotoService {
    List<Photo> provideAllPhotosForView() throws ServiceException;

    List<PhotoEntity> provideAllPhotosForAdmin() throws ServiceException;

    List<Photo> provideDecimalPieceOfPhotos(int pieceValue) throws ServiceException;

}
