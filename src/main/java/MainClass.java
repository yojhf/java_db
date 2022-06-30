import DTO.CharacterClass;
import DTO.EnemyClass;
import DTO.ItemClass;

import java.util.Scanner;

public class MainClass
{

    public static void main(String[] args)
    {
        DBClass dc = new DBClass();
        Scanner sc = new Scanner(System.in);



        System.out.println("GAME START!");
        System.out.print("플레이어의 이름을 입력세요 : ");
        String p_name = sc.next();
        System.out.println(p_name + "님 안녕하세요!!!");




        while(true)
        {
            System.out.println("1. 게임 설정" + " 2. 플레이 게임" + " 3. 저장된 정보 보기");
            int ch_01 = sc.nextInt();
            if (ch_01 == 1) {

                System.out.println("캐릭터 정보를 입력하세요");
                System.out.print("캐릭터 이름을 설정해 주세요 : ");
                String c_name = sc.next();
                System.out.print("캐릭터 체력을 설정해 주세요 : ");
                int c_hp = sc.nextInt();
                System.out.print("캐릭터 직업을 설정해 주세요 : ");
                String c_job = sc.next();

                CharacterClass C_dto = new CharacterClass();
                C_dto.setC_name(c_name);
                C_dto.setC_hp(c_hp);
                C_dto.setC_job(c_job);


                System.out.print("몬스터 이름을 설정해 주세요 : ");
                String e_name = sc.next();
                System.out.print("몬스터 체력을 설정해 주세요 : ");
                int e_hp = sc.nextInt();

                EnemyClass E_dto = new EnemyClass();
                E_dto.setE_name(e_name);
                E_dto.setE_hp(e_hp);


                System.out.println("아이템을 등록하세요");
                System.out.print("아이템 이름 입력 : ");
                String name = sc.next();
                System.out.print("속성 입력 : ");
                String att = sc.next();
                System.out.print("데미지 입력 : ");
                int dem = sc.nextInt();
                System.out.print("효과 입력 : ");
                String hyo = sc.next();
                ItemClass I_dto = new ItemClass();
                I_dto.setName(name);
                I_dto.setAtt(att);
                I_dto.setDem(dem);
                I_dto.setHyo(hyo);


                // 디비 생성

                dc.insertCharacter(C_dto);
                dc.selectCharacter();
                dc.insertEnemy(E_dto);
                dc.selectEnemy();
                dc.insertItem(I_dto);
                dc.selectItem();
            }
            else if (ch_01 == 2)
            {
                if (dc.checkDB())
                {
                    System.out.println("게임 진행");
                }
                else
                {
                    System.out.println("데이터를 생성하세요");
                }
                System.out.println("게임을 시작하겠습니다~~~~~");

                break;

            }
            else if (ch_01 == 3)
            {
                if (dc.checkDB())
                {
                    System.out.println("데이터 확인");

                }
                else
                {
                    System.out.println("데이터를 생성하세요");
                }

                dc.selectCharacter();
                dc.selectEnemy();
                dc.selectItem();
            }
            else
            {

            }
        }


    }
}