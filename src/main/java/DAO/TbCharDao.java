package DAO;
import DTO.CharacterClass;

import java.sql.*;
import java.util.ArrayList;


public class TbCharDao
{
    DBClass dc = new DBClass();
    // 데이터 넣기 메소드
    public void insertCharacter(CharacterClass character)
    {
        //쿼리문 준비
        String sql = "INSERT INTO `tb_character` (`c_name`, `c_hp`, `c_job`) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = dc.dbConn();   // db 연결 메소드
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, character.getC_name());
            pstmt.setInt(2, character.getC_hp());
            pstmt.setString(3, character.getC_job());

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

    public void updateCharacter(int c_hp, int c_id) {
        //쿼리문 준비
        String sql = "UPDATE `tb_character` SET `c_hp`= ? WHERE  `id`= ?";
        PreparedStatement pstmt = null;
        Connection conn = dc.dbConn();   // db 연결 메소드
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, c_hp);
            pstmt.setInt(2, c_id);

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

    // 데이터 보기 메소드드
    public ArrayList<CharacterClass> selectCharacter()
    {
        ArrayList<CharacterClass> c_list = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dc.dbConn();   // db 연결 메소드
        try
        {
            String sql = "select * from tb_character";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                CharacterClass c_dto = new CharacterClass();
                c_dto.setC_name(rs.getString("c_name"));
                c_dto.setC_hp(rs.getInt("c_hp"));
                c_dto.setC_job(rs.getString("c_job"));
                c_list.add(c_dto);
                System.out.println("이름 : " + c_dto.getC_name() + " / 체력 : " + c_dto.getC_hp() + " / 직업 : " + c_dto.getC_job());

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
        return c_list;

    }
    public CharacterClass selectCharacter(String c_name)
    {
        CharacterClass c_dto = new CharacterClass();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = dc.dbConn();   // db 연결 메소드
        try
        {
            String sql = "select * from tb_character where c_name = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, c_name);

            rs = pstmt.executeQuery();

            if (rs.next())
            {
                c_dto.setId(rs.getInt("id"));
                c_dto.setC_name(rs.getString("c_name"));
                c_dto.setC_hp(rs.getInt("c_hp"));
                c_dto.setC_job(rs.getString("c_job"));


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
        return c_dto;

    }
}

