package org.tsui.easyweather.xml;

import org.tsui.easyweather.dto.City;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
/*
  Created by TsuiXh on 16/11/21.
  Hope it's a bug-free-program.O-O
 */

/**
 * XML数据解析工具类
 */
public class XMLParser {

    private DocumentBuilder builder;

    public XMLParser() throws ParserConfigurationException {
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    /**
     * 将指定的输入流解析为Java对象
     *
     * @param is 包含数据天气的xml流
     * @return 包含天气状况的城市对象list，如果不存在则返回一个长度为0的ArrayList.
     */
    public List<City> getCities(InputStream is) {

        List<City> cities = new ArrayList<>();
        Document document;
        try {
            document = builder.parse(is);
        } catch (SAXException e) {
            return cities;
        } catch (IOException e) {
            return cities;
        }
        //获取所有的city元素
        NodeList list = document.getElementsByTagName("city");
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            String quName = element.getAttribute("quName");
            String pyName = element.getAttribute("pyName");
            String cityName = element.getAttribute("cityname");
            String state1 = element.getAttribute("state1");
            String state2 = element.getAttribute("state2");
            String stateDetailed = element.getAttribute("stateDetailed");
            int tem1 = Integer.parseInt(element.getAttribute("tem1"));
            int tem2 = Integer.parseInt(element.getAttribute("tem2"));
            String windState = element.getAttribute("windState");
            String windDir = element.getAttribute("windDir");
            String windPower = element.getAttribute("windPower");
            String humidity = element.getAttribute("humidity");
            String updateTime = element.getAttribute("time");

            //省级别没有此项，如果没有则用-1000代表没有当前温度
            String tn = element.getAttribute("temNow");
            int temNow = (tn != null && !"".equals(tn) && !"暂无实况".equals(tn)) ? Integer.parseInt(tn) : -1000;

            City city = new City(quName, pyName, cityName, state1, state2, stateDetailed, tem1, tem2, windState, windDir, windPower, humidity, updateTime, temNow);
            cities.add(city);
        }
        return cities;


    }

}
