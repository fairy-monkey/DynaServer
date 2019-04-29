package com.geeboo.dyna.server.configuration.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*", initParams = {})
public class DruidStatViewServlet extends StatViewServlet {
    private static final long serialVersionUID = 1L;
}
