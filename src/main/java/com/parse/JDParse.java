package com.parse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.model.JdModel;
import com.util.TimeUtils;

public class JDParse {
	public static List<JdModel> getData (String html,String keyword) throws Exception{
		//获取的数据，存放在集合中
		List<JdModel> data = new ArrayList<JdModel>();
		//采用Jsoup解析
		Document doc = Jsoup.parse(html);
		//获取html标签中的内容
		Elements elements = doc.select("li[class=gl-item]");
		for (Element ele:elements) {
			String itemId = ele.attr("data-sku");
			String itemPrice = ele.select("div[class=p-price]").select("strong").select("i").text();
			String itemName = ele.select("div[class~=p-name?]").select("em").text();  //书籍类产品
			String commentNumber = ele.select("div[class=p-commit]").text();
			String itemurl = "https://item.jd.com/"+itemId+".html";
			String shopname = "";
			String shopurl = "";
			if (ele.select("div[class=p-shop]").select("a[class=curr-shop]").text().length()!=0) {
				shopname = ele.select("div[class=p-shop]").select("a[class=curr-shop]").text();
				shopurl = "https:"+ele.select("div[class=p-shop]").select("a[class=curr-shop]").attr("href");
			}else {
				shopname = "京东自营";
				shopurl = "https://mall.jd.com/index-"+ele.select("div[class=p-shop]").attr("data-shopid")+".html";
			}
			String crawl_time = TimeUtils.GetNowDate("yyyy-MM-dd HH:mm:ss");
			//创建一个对象，这里可以看出，使用Model的优势，直接进行封装
			JdModel jdModel=new JdModel();
			//对象的值
			jdModel.setItemId(itemId);
			jdModel.setItemName(itemName);;
			jdModel.setItemPrice(itemPrice);
			jdModel.setCommentNumber(commentNumber);
			jdModel.setItemurl(itemurl);
			jdModel.setShopname(shopname);
			jdModel.setShopurl(shopurl);
			jdModel.setCrawl_time(crawl_time);
			//将每一个对象的值，保存到List集合中
			data.add(jdModel);
		}
		//返回数据
		return data;
	}
}
