package com.xiao.utils;

import com.xiao.entity.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: learning
 * @description: 爬取网页常用工具
 * @author: lonely xiao
 * @create: 2020-10-08 15:25
 */
public class HtmlParserUtils {

    public static void main(String[] args) throws Exception {

    }


    public static List<Goods> getGoods(String keyword) throws Exception {
        String url = "https://search.jd.com/Search?keyword="+keyword;
        Document document = Jsoup.parse(new URL(url), 30000);
        //获取商品列表
        Element goodsList = document.getElementById("J_goodsList");
        Elements elements = goodsList.getElementsByTag("li");

        List<Goods> goods = elements.stream().map(element -> {
            String img = element.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = element.getElementsByClass("p-price").eq(0).text();
            String name = element.getElementsByClass("p-name").eq(0).text();
            return new Goods(price, img, name);
        }).collect(Collectors.toList());
        return goods;
    }
}