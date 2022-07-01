package DAO;

import DTO.CharacterClass;
import DTO.EnemyClass;
import DTO.ItemClass;

import java.sql.*;

public class DBClass
{
    // 생성자와
    // 변수
    // 메소드

    // 디비 연결부 메소드
    public Connection dbConn()
    {
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3307";
        final String DB_NAME = "mydb";
        final String DB_USER = "root";
        final String DB_PASS = "1234";
        final String DB_URL = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;


        Connection conn = null;

        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            if (conn != null)
            {
                System.out.println("DB 접속 성공");
            }

        }
        catch (ClassNotFoundException e)
        {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
        return conn; // conn을 다른 메소드에 사용하기 위해 리턴
    }


    // 데이터 넣기 메소드
    public void insertItem(ItemClass item)
    {
        //쿼리문 준비
        String sql = "INSERT INTO `item` (`name`, `att`, `dem`, `hyo`) VALUES (?, ?, ?, ?)";

        PreparedStatement pstmt = null;

        Connection conn = dbConn(); // db연결 메소드

        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getAtt());
            pstmt.setInt(3, item.getDem());
            pstmt.setString(4, item.getHyo());

            int result = pstmt.executeUpdate();

            if(result == 1)
            {
                System.out.println("데이터 삽입 성공!");

            }

        }
        catch (Exception e)
        {
            System.out.println("데이터 삽입 실패!");
        }
        finally
        {
            try
            {
                if(pstmt != null && !pstmt.isClosed())
                {
                    pstmt.close();
                }
            }
            catch (Exception e2)
            {
                e2.printStackTrace(); // 오류 출력 기능
            }
        }
    }


    // 데이터 보기 메소드
    public void selectItem()
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn(); // db연결 메소드

        try
        {
            String sql = "select * from item";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                String name = rs.getString("name");
                String att = rs.getString("att");
                String dem = rs.getString("dem");
                String hyo = rs.getString("hyo");
                System.out.println("이름 : " + name + " / 속성 : " + att + " / 데미지 : " + dem + " / 효과 : " + hyo);
            }

        }
        catch (SQLException e)
        {
            System.out.println("error: " + e);
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }
    public void insertCharacter(CharacterClass character)
    {
        //쿼리문 준비
        String sql = "INSERT INTO `tb_character` (`c_name`, `c_hp`, `c_job`) VALUES (?, ?, ?)";

        PreparedStatement pstmt = null;

        Connection conn = dbConn(); // db연결 메소드

        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, character.getC_name());
            pstmt.setInt(2, character.getC_hp());
            pstmt.setString(3, character.getC_job());

            int result = pstmt.executeUpdate();

            if(result == 1)
            {
                System.out.println("데이터 삽입 성공!");

            }

        }
        catch (Exception e)
        {
            System.out.println("데이터 삽입 실패!");
        }
        finally
        {
            try
            {
                if(pstmt != null && !pstmt.isClosed())
                {
                    pstmt.close();
                }
            }
            catch (Exception e2)
            {
                e2.printStackTrace(); // 오류 출력 기능
            }
        }
    }


    // 데이터 보기 메소드
    public void selectCharacter()
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn(); // db연결 메소드

        try
        {
            String sql = "select * from tb_character";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                String name = rs.getString("c_name");
                String hp = rs.getString("c_hp");
                String job = rs.getString("c_job");
                System.out.println("이름 : " + name + " / 체력 : " + hp + " / 직업 : " + job);
            }

        }
        catch (SQLException e)
        {
            System.out.println("error: " + e);
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }
    public void insertEnemy(EnemyClass enemy)
    {
        //쿼리문 준비
        String sql = "INSERT INTO `enemy` (`e_name`, `e_hp`) VALUES (?, ?)";

        PreparedStatement pstmt = null;

        Connection conn = dbConn(); // db연결 메소드

        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, enemy.getE_name());
            pstmt.setInt(2, enemy.getE_hp());

            int result = pstmt.executeUpdate();

            if(result == 1)
            {
                System.out.println("데이터 삽입 성공!");

            }

        }
        catch (Exception e)
        {
            System.out.println("데이터 삽입 실패!");
        }
        finally
        {
            try
            {
                if(pstmt != null && !pstmt.isClosed())
                {
                    pstmt.close();
                }
            }
            catch (Exception e2)
            {
                e2.printStackTrace(); // 오류 출력 기능
            }
        }
    }


    // 데이터 보기 메소드
    public void selectEnemy()
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn(); // db연결 메소드

        try
        {
            String sql = "select * from enemy ";
            //String sql1 = "select * from enemy order by rand() LIMIT 1";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                String name = rs.getString("e_name");
                String hp = rs.getString("e_hp");
                System.out.println("이름 : " + name + " / 체력 : " + hp);
            }

        }
        catch (SQLException e)
        {
            System.out.println("error: " + e);
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

    }
    public boolean checkDB() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn();   // db 연결 메소드
        String playerCheck = "select id from tb_character;";
        String monsterCheck = "select id from enemy;";
        String itemCheck = "select id from item;";
        boolean cc = false;
        boolean ic= false;
        boolean mc = false;
        boolean result;
        try
        {
            pstmt = conn.prepareStatement(playerCheck);

            rs = pstmt.executeQuery();
            if (rs.next())
            {
                int index = rs.getInt("id");
                System.out.println(index);
                cc = true;      //해당 테이블에 데이터가 있으면 true
            }
        }
        catch (SQLException e)
        {
            System.out.println("error: " + e);
        }
        try
        {
            pstmt = conn.prepareStatement(monsterCheck);

            rs = pstmt.executeQuery();

            if (rs.next())
            {
                int index = rs.getInt("id");
                System.out.println(index);
                ic = true;      //해당 테이블에 데이터가 있으면 true
            }
        }
        catch (SQLException e)
        {
            System.out.println("error: " + e);
        }
        try
        {
            pstmt = conn.prepareStatement(itemCheck);

            rs = pstmt.executeQuery();
            if (rs.next())
            {
                int index = rs.getInt("id");
                System.out.println(index);
                mc = true;      //해당 테이블에 데이터가 있으면 true
            }
        }
        catch (SQLException e)
        {
            System.out.println("error: " + e);
        }

        if (cc && ic && mc)
        {   //3개의 테이블에 데이터가 모두 있으면 true / 없으면 false
            result = true;
        }
        else
        {
            result = false;
        }
        return result;
    }
    public String callEnemy()
    {
        String cn = "";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dbConn(); // db연결 메소드

        try
        {
            String sql = "select * from enemy order by rand() LIMIT 1 ";
            //String sql1 = "select * from enemy order by rand() LIMIT 1";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next())
           {
                cn = rs.getString("e_name");
                String hp = rs.getString("e_hp");
                //System.out.println("이름 : " + cn + " / 체력 : " + hp);
           }

        }
        catch (SQLException e)
        {
            System.out.println("error : " + e);
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (pstmt != null)
                {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return cn;
    }
}
