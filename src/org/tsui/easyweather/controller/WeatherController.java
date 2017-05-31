package org.tsui.easyweather.controller;

import org.tsui.easyweather.dto.City;
import org.tsui.easyweather.http.Constants;
import org.tsui.easyweather.service.WeatherService;
import org.tsui.easyweather.xml.XMLParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by TsuiXh on 16/11/21.
 */
@WebServlet(name = "WeatherController",urlPatterns = "/weather")
public class WeatherController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pyName = request.getParameter("pyName");
        pyName = (pyName != null? pyName:"china");
        System.out.println(pyName);
        WeatherService weatherService = new WeatherService();
        List<City> cities = weatherService.getCitiesData(pyName);
        if (cities.size() > 0) {
            request.setAttribute("cities", cities);
            request.setAttribute("imgPath", Constants.BASE_IMG_URI);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } else {
            response.getOutputStream().println("<script>history.back()</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
