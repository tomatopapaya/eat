package com.ted;
import java.io.*;
import java.sql.*;
//��J�a�ϫK�i�d�߷�a���\�U  ���H���D��@�Ӥ���n�h���\�U
public class restaurant
{
    public static void main(String[] args)throws IOException
    {
        
        //�إߥi��J���y�k
        String area,rating,total_rate;
        BufferedReader keyin = new BufferedReader(
                                         new InputStreamReader(System.in));
        System.out.println("1�H�q�� 2����� 3�j�w�� 4�_��� 5�h�L�� 6���s��\n7�j�P�� 8�Q�s�� 9������10�U�ذ� 11��s�� 12�n���");
        System.out.println("\n");
        System.out.print("�п�J�e�����a�I:"); 
        area = keyin.readLine();
        System.out.print("�п�J�P�P��:"); 
        rating = keyin.readLine();
        System.out.print("�п�J�����H��:"); 
        total_rate = keyin.readLine();
        System.out.println("\n");
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //�X�ʵ{���Ѽ�
       
        String sDriver  = "com.mysql.jdbc.Driver";
        String user     = "root";
        String password = "1234";
        String url      = "jdbc:mysql://localhost/restaurant";
        String sql; 
        
        try {  
            
            Class.forName(sDriver);
        }
        catch(Exception e){
        
            System.out.println("�L�k���J�X�ʵ{��");
            return;
        }
        
        try {  
       
            conn = DriverManager.getConnection(url,user,password);
            stmt = conn.createStatement();
        }
        catch(SQLException e){
       
            System.out.println("�P��ƨӷ��s�����~: ");
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
            System.out.println("�a�Ͽﶵ:");
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
            System.out.println("�o����쪺�O:");
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