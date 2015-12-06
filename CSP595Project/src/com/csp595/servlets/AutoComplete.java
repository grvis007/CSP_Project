package com.csp595.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csp595.model.CatalogItem;
import com.csp595.model.Utils;

import java.util.ArrayList;

public class AutoComplete extends HttpServlet {

    private ServletContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        String targetId = request.getParameter("id");
        StringBuffer sb = new StringBuffer();

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
        } else {
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {

            // check if user sent empty string
            if (!targetId.equals("")) {

                ArrayList<CatalogItem> items = Utils.getCatalogItems(null, null, null);

                for(CatalogItem item : items){
                    String name = item.getShortDescription();
                    String id = item.getItemID();

                    if ( // targetId matches first name
                            name.toLowerCase().startsWith(targetId)) {

                        sb.append("<products>");
                        sb.append("<id>" + id + "</id>");
                        sb.append("<productName>" + name + "</productName>");
                        sb.append("</products>");
                        namesAdded = true;
                    }
                }
            }

            if (namesAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<products>" + sb.toString() + "</products>");
            } else {
                //nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action.equals("lookup")) {

            // put the target product in the request scope to display 
            if (targetId != null) {
                context.getRequestDispatcher("/shopListPage.jsp?itemID="+targetId).forward(request, response);
            }
        }
    }
}
