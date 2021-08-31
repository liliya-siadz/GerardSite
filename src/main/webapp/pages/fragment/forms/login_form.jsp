
<form method="POST"
      action="${applicationPath}${controllerUrl}">
  <p><fmt:message key="field.email.label"/>:(${emailValidationMessage})</p>
  <p><input
          type="email"
          name="email"
          required
          size="${emailMaxLength}"
          placeholder="${emailPlaceholder}"
<%--          pattern="${emailPattern}"--%>
          maxlength="${emailMaxLength}"
          minlength="${emailMinLength}"
          oninvalid="setCustomValidity('?')"/>
  </p>
  <p><fmt:message key="field.password.label"/>:(${passwordValidationMessage}) </p>
  <p><input
          type="password"
          size="${passwordMaxLength}"
          required
          name="password"
          placeholder="${passwordPlaceholder}"
          pattern="${passwordPattern}"
          maxlength="${passwordMaxLength}"
          minlength="${passwordMinLength}"
          oninvalid="setCustomValidity('?')"/>
  </p>
  <button type="submit"
          name="command"
          value="LOGIN"
          class="btn btn-xs btn-outline-danger navbar-btn">
    <fmt:message key="page.login.button.login.name"/>
  </button>
</form>