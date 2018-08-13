package com.db;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;

import com.model.JdModel;
/*
 * author qianyang 1563178220@qq.com
 * Mysql操作的QueryRunner方法
 * 一个数据库操作类，别的程序直接调用即可
 */
public class MYSQLControl {
    //根据自己的数据库地址修改
    static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/csdncourse?useUnicode=true&characterEncoding=UTF8");
    static QueryRunner qr = new QueryRunner(ds);
  //第二类数据库操作方法
    public static void executeInsert(List<JdModel> jingdongdata)  {
        /*
         * 定义一个Object数组，行列
         * 8表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[jingdongdata.size()][8];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = jingdongdata.get(i).getItemId();
            params[i][1] = jingdongdata.get(i).getItemName();
            params[i][2] = jingdongdata.get(i).getItemPrice();
            params[i][3] = jingdongdata.get(i).getCommentNumber();
            params[i][4] = jingdongdata.get(i).getItemurl();
            params[i][5] = jingdongdata.get(i).getShopname();
            params[i][6] = jingdongdata.get(i).getShopurl();
            params[i][7] = jingdongdata.get(i).getCrawl_time();
        }
        try {
			qr.batch("insert into jingdonginfo (item_id, item_name, item_price,comment_number,item_url,shop_name,shop_url,crawl_time)"
			        + "values (?,?,?,?,?,?,?,?)", params);
			System.out.println("执行数据库完毕！"+"成功插入数据："+jingdongdata.size()+"条");
		} catch (SQLException e) {
			e.printStackTrace();
		}
        

    }
}