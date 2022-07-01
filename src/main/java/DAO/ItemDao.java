package DAO;
import DTO.ItemClass;

import java.sql.*;
import java.util.ArrayList;

public class ItemDao
{
    DBClass dc = new DBClass();

    public void insertItem(ItemClass item)
    {
        //쿼리문 준비
        String sql = "INSERT INTO `item` (`name`, `att`, `dem`, `hyo`) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = dc.dbConn();   // db 연결 메소드
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getAtt());
            pstmt.setInt(3, item.getDem());
            pstmt.setString(4, item.getHyo());

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

//    public void updateCharacter(int c_hp, int c_id) {
//        //쿼리문 준비
//        String sql = "UPDATE `tb_character` SET `c_hp`= ? WHERE  `id`= ?";
//        PreparedStatement pstmt = null;
//        Connection conn = dc.dbConn();   // db 연결 메소드
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, c_hp);
//            pstmt.setInt(2, c_id);
//
//            int result = pstmt.executeUpdate();
//
//            if (result == 1)
//            {
//                System.out.println("데이터 삽입 성공!");
//            }
//
//        }
//        catch (Exception e)
//        {
//            System.out.println("데이터 삽입 실패!");
//        }
//        finally
//        {
//            try
//            {
//                if (pstmt != null && !pstmt.isClosed())
//                {
//                    pstmt.close();
//                }
//            }
//            catch (Exception e2)
//            {
//                e2.printStackTrace();
//            }
//        }
//    }

    // 데이터 보기 메소드드
    public ArrayList<ItemClass> selectItem()
    {
        ArrayList<ItemClass> i_list = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dc.dbConn();   // db 연결 메소드
        try
        {
            String sql = "select * from item";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                ItemClass i_dto = new ItemClass();
                i_dto.setName(rs.getString("name"));
                i_dto.setAtt(rs.getString("att"));
                i_dto.setDem(rs.getInt("dem"));
                i_dto.setHyo(rs.getString("hyo"));
                i_list.add(i_dto);
                System.out.println(
                        "이름 : " + i_dto.getName() + " / 효과 : " + i_dto.getAtt() + " / 데미지 : " + i_dto.getDem() +
                        "효과 : " + i_dto.getHyo());

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
        return i_list;

    }
    public ItemClass selectCharacter(String i_name)
    {
        ItemClass i_dto = new ItemClass();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dc.dbConn();   // db 연결 메소드
        try
        {
            String sql = "select * from tb_character where c_name = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, i_name);

            rs = pstmt.executeQuery();

            if (rs.next())
            {
                i_dto.setId(rs.getInt("id"));
                i_dto.setName(rs.getString("name"));
                i_dto.setAtt(rs.getString("att"));
                i_dto.setDem(rs.getInt("dem"));
                i_dto.setHyo(rs.getString("hyo"));

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
        return i_dto;

    }
}
