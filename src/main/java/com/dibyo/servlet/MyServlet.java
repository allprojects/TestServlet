package com.dibyo.servlet;

import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyServlet extends HttpServlet {
    private static final String utf8 = "UTF-8";
    private static final String CONTENT_TYPE = "application/json";
    private static final int bufferSize = 1024 * 32;

    private final Logger logger = LoggerFactory.getLogger(MyServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    protected void doPost( HttpServletRequest request, HttpServletResponse response) {
        reply(response);
    }
    protected void doGet( HttpServletRequest request, HttpServletResponse response) {
        reply(response);
    }

    private void reply(HttpServletResponse response ) {
        response.setBufferSize(bufferSize);
        //response.getSession(true);
        logger.info("Request received...");

        response.setStatus(200);
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(utf8);

        try{
            PrintWriter writer = response.getWriter();
            writer.write("Hello!");
            writer.flush();
        } catch(OutOfMemoryError e) {
            logger.error(e.getMessage(), e);
            response.setStatus(500);
        } catch(Throwable t) {
            logger.error(t.getMessage(), t);
            response.setStatus(500);
        }

    }
}
