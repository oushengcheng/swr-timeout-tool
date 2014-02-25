package com.aps.wicc.web;

import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

@WebServlet({ "/poll" })
public class PollServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.getSession();
    }
}
