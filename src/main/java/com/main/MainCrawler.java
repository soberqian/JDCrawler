package com.main;

import java.util.List;
import com.db.MYSQLControl;
import com.model.JdModel;
import com.parse.JDParse;
import com.util.HttpRequest;
/*
 * author qianyang 1563178220@qq.com
 * Mysql操作的QueryRunner方法
 * 一个数据库操作类，别的程序直接调用即可
 */
public class MainCrawler {

  public static void main(String[] args) throws Exception{
	//设置关键词
    String keyword = "手机";
    //输入价格区间,页面上是整型，所以这里也要是整型
    int firstprice = 2000;
    int endprive = 5000;
    //输入爬取的总页数
    int sumpagenumber = 100;
    for (int i = 1; i < sumpagenumber; i++) {
    	String html = HttpRequest.getRawHtml(keyword, i, firstprice, endprive);
    	List<JdModel> dataslist = JDParse.getData(html,keyword);
		//循环输出抓取的数据
		for (JdModel jd:dataslist) {
			System.out.println("itemId:" + jd.getItemId() + "\t" + "itemName:" + jd.getItemName() + "\t" + "itemPrice:" + jd.getItemPrice() + "\tcommentnumber:" + jd.getCommentNumber() + "\titemurl:" + jd.getItemurl()+"\tshopname:" + jd.getShopname() + "\tshopurl:" + jd.getShopurl() + "\tcrawl_time:" + jd.getCrawl_time());
		}
		//将抓取的数据插入数据库,插入数据库前，需要建表
		MYSQLControl.executeInsert(dataslist);
	}
  }
}
