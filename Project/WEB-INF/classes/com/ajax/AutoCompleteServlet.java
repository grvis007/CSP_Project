package com.ajax;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author nbuser
 */
public class AutoCompleteServlet extends HttpServlet {

    private ServletContext context;
    private ProductData prodData = new ProductData();
    private HashMap products = prodData.getProducts();

    
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
		
		//PrintWriter out = response.getWriter();
		
        String action = request.getParameter("action");
        String targetId = request.getParameter("id");
		//System.out.println("ID value:"+targetId);
		//out.println("Value:"+targetId);
        StringBuffer sb = new StringBuffer();
		//String prodType = "";

        if (targetId != null) {
            targetId = targetId.trim().toLowerCase();
			//System.out.println("ID value:"+targetId);
        } else {
            context.getRequestDispatcher("/error.html").forward(request, response);
        }

        boolean namesAdded = false;
        if (action.equals("complete")) {
			System.out.println("ID1 value:"+targetId);
            // check if user sent empty string
            if (!targetId.equals("")) {

                Iterator it = products.keySet().iterator();

                while (it.hasNext()) {
                    String itemId = (String) it.next();
                    Product product = (Product) products.get(itemId);

                    if (product.getItemName().toLowerCase().startsWith(targetId)) 
                       
					{
						System.out.println("ID2 value:"+targetId);  
                        sb.append("<product>");
                        sb.append("<itemId>" + product.getItemId() + "</itemId>");
                        sb.append("<itemName>" + product.getItemName() + "</itemName>");
                        sb.append("<itemPrice>" + product.getItemPrice() + "</itemPrice>");
						sb.append("<itemType>" + product.getItemType() + "</itemType>");
                        sb.append("</product>");
						//prodType = product.getItemType();
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
			//String prodId = request.getParameter("id");
			System.out.println("ID2 value:"+targetId);
			//System.out.println("ID3 value:"+prodId);
            // put the target composer in the request scope to display 
            if ((targetId != null) || products.containsKey(targetId.trim())) {
                request.setAttribute("product", products.get(targetId));
				System.out.println("ID3 value:"+targetId);
				if((targetId.substring(0,2)).equals("cm")){
					context.getRequestDispatcher("/classicmovies").forward(request, response);		
				}else if((targetId.substring(0,2)).equals("aa")){
					context.getRequestDispatcher("/AAmovies").forward(request, response);
				}else if((targetId.substring(0,2)).equals("mc")){
					context.getRequestDispatcher("/comedymovies").forward(request, response);
				}else if((targetId.substring(0,3)).equals("an")){
					context.getRequestDispatcher("/animatedmovies").forward(request, response);
				}else if((targetId.substring(0,3)).equals("pm")){
					context.getRequestDispatcher("/popmusic").forward(request, response);
				}else if((targetId.substring(0,2)).equals("fm")){
					context.getRequestDispatcher("/fwmusic").forward(request, response);
				}else if((targetId.substring(0,2)).equals("rm")){
					context.getRequestDispatcher("/rhiphopmusic").forward(request, response);
				}else if((targetId.substring(0,3)).equals("cmo")){
					context.getRequestDispatcher("/countrymusic").forward(request, response);
				}else if((targetId.substring(0,2)).equals("sa")){
					context.getRequestDispatcher("/actionshows").forward(request, response);
				}else if((targetId.substring(0,2)).equals("hs")){
					context.getRequestDispatcher("/horrorshows").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ds")){
					context.getRequestDispatcher("/dramashows").forward(request, response);
				}else if((targetId.substring(0,2)).equals("cs")){
					context.getRequestDispatcher("/cartoonshows").forward(request, response);
				}else if((targetId.substring(0,2)).equals("fo")){
					context.getRequestDispatcher("/football").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ba")){
					context.getRequestDispatcher("/basketball").forward(request, response);
				}else if((targetId.substring(0,2)).equals("so")){
					context.getRequestDispatcher("/soccer").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ws")){
					context.getRequestDispatcher("/mwoodsigns").forward(request, response);
				}else if((targetId.substring(0,2)).equals("mw")){
					context.getRequestDispatcher("/mwallstickers").forward(request, response);
				}else if((targetId.substring(0,2)).equals("gp")){
					context.getRequestDispatcher("/mgiantposters").forward(request, response);
				}else if((targetId.substring(0,2)).equals("us")){
					context.getRequestDispatcher("/usa").forward(request, response);
				}else if((targetId.substring(0,2)).equals("za")){
					context.getRequestDispatcher("/canada").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ch")){
					context.getRequestDispatcher("/china").forward(request, response);
				}else if((targetId.substring(0,2)).equals("in")){
					context.getRequestDispatcher("/india").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ja")){
					context.getRequestDispatcher("/japan").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ra")){
					context.getRequestDispatcher("/iran").forward(request, response);
				}else if((targetId.substring(0,2)).equals("uk")){
					context.getRequestDispatcher("/uk").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ge")){
					context.getRequestDispatcher("/germany").forward(request, response);
				}else if((targetId.substring(0,2)).equals("sp")){
					context.getRequestDispatcher("/spain").forward(request, response);
				}else if((targetId.substring(0,2)).equals("fr")){
					context.getRequestDispatcher("/france").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ph")){
					context.getRequestDispatcher("/photograohy").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ab")){
					context.getRequestDispatcher("/angrybird").forward(request, response);
				}else if((targetId.substring(0,2)).equals("b3")){
					context.getRequestDispatcher("/battlefield3").forward(request, response);
				}else if((targetId.substring(0,2)).equals("b4")){
					context.getRequestDispatcher("/battlefield4").forward(request, response);
				}else if((targetId.substring(0,2)).equals("cd")){
					context.getRequestDispatcher("/callofduty").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ic")){
					context.getRequestDispatcher("/indiancelebraty").forward(request, response);
				}else if((targetId.substring(0,2)).equals("fc")){
					context.getRequestDispatcher("/frenchcelebraty").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ac")){
					context.getRequestDispatcher("/americancelebraty").forward(request, response);
				}else if((targetId.substring(0,2)).equals("mc")){
					context.getRequestDispatcher("/marvelcomics").forward(request, response);
				}else if((targetId.substring(0,2)).equals("dc")){
					context.getRequestDispatcher("/dccomics").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ah")){
					context.getRequestDispatcher("/animalhumor").forward(request, response);
				}else if((targetId.substring(0,2)).equals("bh")){
					context.getRequestDispatcher("/bathroomhumor").forward(request, response);
				}else if((targetId.substring(0,2)).equals("ot")){
					context.getRequestDispatcher("/threedcomics").forward(request, response);
				}else if((targetId.substring(0,2)).equals("cb")){
					context.getRequestDispatcher("/cricket").forward(request, response);
				}else if((targetId.substring(0,2)).equals("it")){
					context.getRequestDispatcher("/italy").forward(request, response);
				}
				
				
				
                //context.getRequestDispatcher("/composer.jsp").forward(request, response);
            }
        }
    }
}
