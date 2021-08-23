<c:set var="avatar" value="${chosenPuppy.avatarPhotoPath}"/>
<img class="img"
     src="${applicationPath}/${avatar}"
     alt="?"
     width="250"/>
<c:set var="pedigree" value="${chosenPuppy.pedigreePhotoPath}"/>
<img class="img"
     src="${applicationPath}/${pedigree}"
     alt="?"
     width="250"/>
<div>
    <div style="display:block;">
        <h3><b><fmt:message key="page.dogs.view.title.sex"/> </b>
            <c:out value="${chosenPuppy.dogSex}"/>
        </h3>
    </div>
    <div style="display:block;">
        <h3><b><fmt:message key="page.dogs.view.title.nickname"/> </b>
            <c:out value="${chosenPuppy.nickname}"/>
        </h3>
    </div>
    <div style="display:block;">
        <h3><b><fmt:message key="page.dogs.view.title.fullname"/> </b>
            <c:out value="${chosenPuppy.fullname}"/>
        </h3>
    </div>
    <div style="display:block;">
        <h3><b><fmt:message key="page.dogs.view.title.birthday"/> </b>
            <c:out value="${chosenPuppy.birthday}"/>
        </h3>
    </div>
    <div style="display:block;">
        <h3><b><fmt:message key="page.dogs.view.title.description"/> </b>
            <c:out value="${chosenPuppy.description}"/><c:if test="${empty chosenPuppy.description}">&#10006;</c:if>
        </h3>
    </div>
</div>