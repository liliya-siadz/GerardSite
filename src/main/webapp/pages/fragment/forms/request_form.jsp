<form method="POST"
      action="${applicationPath}${controllerUrl}">
    <h3 style="color: maroon">
        <b><fmt:message key="page.make_request.request_form.label"/></b>
    </h3>
    <p><fmt:message key="field.content.label"/> (${contentValidationMessage})</p>
    <p><textarea name="content"
                 required
                 pattern="${contentPattern}"
                 maxlength="${contentMaxLength}"
                 minlength="${contentMinLength}"
                 oninvalid="setCustomValidity('?')"></textarea>
    </p>
    <p><fmt:message key="field.email.label"/>(${emailValidationMessage})</p>
    <p><input
            name="email"
            id="emailId"
            required
            size="${50}"
            type="text"
            placeholder="${emailPlaceholder}"
            pattern="${emailPattern}"
            maxlength="${emailMaxLength}"
            minlength="${emailMinLength}"
            oninvalid="setCustomValidity('?')"/>
    </p>
    <p><fmt:message key="field.name.label"/>(${nameValidationMessage})</p>
    <p><input
            name="name"
            type="text"
            required
            size="${50}"
            pattern="${namePattern}"
            maxlength="${nameMaxLength}"
            minlength="${nameMinLength}"
            oninvalid="setCustomValidity('?')"/>
    </p>
    <p><fmt:message key="field.surname.label"/>(${surnameValidationMessage})</p>
    <p><input
            name="surname"
            type="text"
            required
            size="${50}"
            pattern="${namePattern}"
            maxlength="${nameMaxLength}"
            minlength="${nameMinLength}"
            oninvalid="setCustomValidity('?')"/>
    </p>
    <p><fmt:message key="field.patronymic.label"/>(${patronymicValidationMessage})</p>
    <p><input
            name="patronymic"
            type="text"
            size="${50}"
            pattern="${namePattern}"
            maxlength="${nameMaxLength}"
            minlength="${nameMinLength}"
            oninvalid="setCustomValidity('?')"/>
    </p>

    <p><fmt:message key="field.phone.label"/>(${phoneValidationMessage})</p>
    <p> <input
            name="phone"
            type="text"
            required
            size="${strongLength}"
            placeholder="${phonePlaceholder}"
            pattern="${phonePattern}"
            maxlength="${strongLength}"
            minlength="${strongLength}"
            oninvalid="setCustomValidity('?')"/>
    </p>
    <button type="submit"
            name="command"
            class="btn btn-xs navbar-btn"
            style="background-color: red"
            value="MAKE_REQUEST">
        <fmt:message key="page.make_request.button.make_request.name"/>
    </button>
</form>