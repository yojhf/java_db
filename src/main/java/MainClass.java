import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainClass
{

    public static void main(String[] args)
    {
        System.out.println("디비를 연결해보자");

        Scanner sc = new Scanner(System.in);
        System.out.println("아이템을 등록하세요");
        System.out.print("아이템 이름 입력 : ");
        String name = sc.next();
        System.out.print("속성 입력 : ");
        String att = sc.next();
        System.out.print("데미지 입력 : ");
        String dem = sc.next();
        System.out.print("효과 입력 : ");
        String hyo = sc.next();


        // 디비 생성
        DBClass dc = new DBClass();
        dc.insertItem(name, att, dem, hyo);
        dc.selectItem();

    }
}