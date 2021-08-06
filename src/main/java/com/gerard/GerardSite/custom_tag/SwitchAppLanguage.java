package com.gerard.GerardSite.custom_tag;


import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.jsp.tagext.TagSupport;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;

@SuppressWarnings("serial")
public class SwitchAppLanguage extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        return SKIP_BODY;
    }
}
