package com.ted;
import java.io.*;
import java.sql.*;
//輸入地區便可查詢當地的餐廳  並隨機挑選一個今日要去的餐廳
public class restaurant
{
    public static void main(String[] args)throws IOException
    {
        
        //建立可輸入的語法
        String area,rating,total_rate;
        BufferedReader keyin = new BufferedReader(
                                         new InputStreamReader(System.in));
        System.out.println("1信義區 2內湖區 3大安區 4北投區 5士林區 6中山區\n7大同區 8松山區 9中正區10萬華區 11文山區 12南港區");
        System.out.println("\n");
        System.out.print("請輸入前往的地點:"); 
        area = keyin.readLine();
        System.out.print("請輸入星星數:"); 
        rating = keyin.readLine();
        System.out.print("請輸入評分人數:"); 
        total_rate = keyin.readLine();
        System.out.println("\n");
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //驅動程式參數
       
        String sDriver  = "com.mysql.jdbc.Driver";
        String user     = "root";
        String password = "1234";
        String url      = "jdbc:mysql://localhost/restaurant";
        String sql; 
        
        try {  
            
            Class.forName(sDriver);
        }
        catch(Exception e){
        
            System.out.println("無法載入驅動程式");
            return;
        }
        
        try {  
       
            conn = DriverManager.getConnection(url,user,password);
            stmt = conn.createStatement();
        }
        catch(SQLException e){
       
            System.out.println("與資料來源連結錯誤: ");
            System.out.println(e.getMessage());
            if (conn != null)
            {
                try { conn.close(); }
                catch( SQLException e2 ) {}
            }
            return;
        }
        
        
        sql="SELECT * FROM search where area="+"'"+area+"'" +"&& rating>="+rating+"&& total_rate>="+total_rate;
        try {
            System.out.println("地區選項:");
            rs = stmt.executeQuery(sql);
            System.out.println("\n");
            System.out.println("area     name    rating   total_rate ");
            System.out.println("================================================"+
                              "==========================");                    
            while(rs.next())
            {
                System.out.print(rs.getString("area"));
                System.out.print("\t");
                System.out.print(rs.getString("name"));
                System.out.print("\t");
                System.out.print(rs.getString("rating"));
                System.out.print("\t");
                System.out.println(rs.getString("total_rate"));
                
                             
                
            }
        }
        catch(SQLException e){}
        sql="SELECT * FROM search where area="+"'"+area+"'" +"&& rating>="+rating+"&& total_rate>="+total_rate+"\norder by rand() limit 1";
        
        try {
        
            rs = stmt.executeQuery(sql);
            System.out.println("\n");
            System.out.println("這次選到的是:");
            System.out.println("\n");
            System.out.println("area     name    rating   total_rate ");
            System.out.println("================================================"+
                              "==========================");                    
            while(rs.next())
            {
                System.out.print(rs.getString("area"));
                System.out.print("\t");
                System.out.print(rs.getString("name"));
                System.out.print("\t");
                System.out.print(rs.getString("rating"));
                System.out.print("\t");
                System.out.print(rs.getString("total_rate"));
                
                             
                
            }
        }
        catch(SQLException e){}
       
        try {
        
            stmt.close(); 
            conn.close(); 
        }
        catch( SQLException e ) {}
    }
}