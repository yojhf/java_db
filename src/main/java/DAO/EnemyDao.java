package DAO;
import DTO.CharacterClass;
import DTO.EnemyClass;

import java.sql.*;
import java.util.ArrayList;


public class EnemyDao
{
    DBClass dc = new DBClass();

    public void insertEnemy(EnemyClass enemy)
    {
        //쿼리문 준비
        String sql = "INSERT INTO `enemy` (`e_name`, `e_hp`) VALUES (?, ?)";

        PreparedStatement pstmt = null;

        Connection conn = dc.dbConn(); // db연결 메소드

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

    public void updateEnemy(int e_hp, int e_id)
    {
        //쿼리문 준비
        String sql = "UPDATE `enemy` SET `e_hp`= ? WHERE  `id`= ?";
        PreparedStatement pstmt = null;
        Connection conn = dc.dbConn();   // db 연결 메소드
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, e_hp);
            pstmt.setInt(2, e_id);

            int result = pstmt.executeUpdate();

            if (result == 1)
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
                if (pstmt != null && !pstmt.isClosed())
                {
                    pstmt.close();
                }
            }
            catch (Exception e2)
            {
                e2.printStackTrace();
            }
        }
    }
    public ArrayList<EnemyClass> selectEnemy()
    {
        ArrayList<EnemyClass> e_list = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dc.dbConn();   // db 연결 메소드
        try {
            String sql = "select * from enemy";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                EnemyClass e_dto = new EnemyClass();
                e_dto.setE_name(rs.getString("e_name"));
                e_dto.setE_hp(rs.getInt("e_hp"));
                e_list.add(e_dto);
                System.out.println("이름 : " + e_dto.getE_name() + " / 체력 : " + e_dto.getE_hp());

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
        return e_list;

    }

    public EnemyClass selectEnemy(String e_name)
    {
        EnemyClass e_dto = new EnemyClass();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dc.dbConn();   // db 연결 메소드
        try
        {
            String sql = "select * from enemy where e_name = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, e_name);

            rs = pstmt.executeQuery();

            if (rs.next())
            {
                e_dto.setId(rs.getInt("id"));
                e_dto.setE_name(rs.getString("c_name"));
                e_dto.setE_hp(rs.getInt("c_hp"));
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
        return e_dto;

    }
    public String callEnemy()
    {
        String cn = "";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dc.dbConn(); // db연결 메소드

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
