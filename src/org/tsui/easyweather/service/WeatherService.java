package org.tsui.easyweather.service;

import org.tsui.easyweather.dto.City;
import org.tsui.easyweather.http.Constants;
import org.tsui.easyweather.http.HttpRequest;
import org.tsui.easyweather.xml.XMLParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TsuiXh on 16/11/21.
 */

/**
 * 业务逻辑层：处理天气数据的业务逻辑层
 */
public class WeatherService {

    public List<City> getCitiesData(String pyName) {
        InputStream is = null;
        List<City> cities = null;
        try {
            is = HttpRequest.read(Constants.BASE_URI + pyName + ".xml");
            XMLParser parser = new XMLParser();
            cities = parser.getCities(is);
        } catch (IOException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return cities;
    }

    /**
     * 获取制定城市天气情况
     *
     * @param pyName   城市拼音名
     * @param cityName 城市名
     * @return 城市天气情况
     */
    public City getCity(String pyName, String cityName) {
        InputStream is = null;
        List<City> cities;
        City city = null;
        try {
            //拼接获取请求地址
            String uri = Constants.BASE_URI + pyName + ".xml";
            System.out.println(uri);
            is = HttpRequest.read(uri);
            XMLParser parser = new XMLParser();
            cities = parser.getCities(is);
            if (cities.size() > 0) {
                for (City c : cities) {
                    //判断当前遍历到的城市名字是否是需要被查询的城市名字
                    if (c.getCityName().equals(cityName)) {
                        city = c;
                        break;
                    }
                }
            }
        } catch (IOException | ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return city;
    }
}
