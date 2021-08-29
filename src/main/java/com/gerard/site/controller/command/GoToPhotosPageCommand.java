package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.controller.PaginationItem;
import com.gerard.site.service.view.Photo;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.PhotoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public enum GoToPhotosPageCommand implements Command{
    INSTANCE;
    private final String viewAttributeName = "photosForView";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        int pageToDisplay = Command.getPage(request);
        List<Photo> photosForView =
                PhotoServiceImpl.getInstance()
                        .provideDecimalPieceOfPhotos(pageToDisplay);
        int photosQuantity = PhotoServiceImpl.getInstance().provideAllPhotosForView().size();

        session.setAttribute(PaginationItem.PAGE_NUMBER, pageToDisplay);
        session.setAttribute(viewAttributeName, photosForView);
        session.setAttribute(PaginationItem.PAGINATION_ITEM,
                new PaginationItem(photosQuantity, pageToDisplay, PaginationItem.PAGE_SIZE));
        return Page.PHOTOS.getPageUrl();
    }
}
