import DAO.DBClass;
import DAO.EnemyDao;
import DAO.TbCharDao;
import DAO.ItemDao;
import DTO.CharacterClass;
import DTO.EnemyClass;
import DTO.ItemClass;

import java.util.ArrayList;
import java.util.Random;
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
            System.out.print("원하는 번호를 입력하세요 : ");
            int ch_01 = sc.nextInt();
            if (ch_01 == 1)
            {

                System.out.println("캐릭터 정보를 입력하세요");
                System.out.print("캐릭터 이름을 설정해 주세요 : ");
                String c_name = sc.next();
                System.out.print("캐릭터 체력을 설정해 주세요 : ");
                int c_hp = sc.nextInt();
                System.out.print("캐릭터 직업을 설정해 주세요 : ");
                String c_job = sc.next();

                // 데이터 설정
                CharacterClass C_dto = new CharacterClass();
                C_dto.setC_name(c_name);
                C_dto.setC_hp(c_hp);
                C_dto.setC_job(c_job);
                TbCharDao C_dao = new TbCharDao();
                C_dao.insertCharacter(C_dto);

                System.out.println("몬스터 정보를 입력하세요");
                System.out.print("몬스터 이름을 설정해 주세요 : ");
                String e_name = sc.next();
                System.out.print("몬스터 체력을 설정해 주세요 : ");
                int e_hp = sc.nextInt();

                EnemyClass E_dto = new EnemyClass();
                E_dto.setE_name(e_name);
                E_dto.setE_hp(e_hp);
                EnemyDao E_Dao = new EnemyDao();
                E_Dao.insertEnemy(E_dto);


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
                ItemDao I_Dao = new ItemDao();
                I_Dao.insertItem(I_dto);

                // 디비 생성
                C_dao.selectCharacter();
                E_Dao.selectEnemy();
                I_Dao.selectItem();
            }
            else if (ch_01 == 2)
            {
                System.out.println("게임을 시작하겠습니다~~~~~");

                // 만약에 저장된 데이터가 없으면 데이터 설정하라고 하는 메세지
                TbCharDao C_dao = new TbCharDao();
                ArrayList<CharacterClass> C_list = C_dao.selectCharacter(); // 여기서 데이터를 가지고 와서

                if (C_list.isEmpty())
                {
                    System.out.println("캐릭터 정보가 없습니다. 게임 설정을 하세요");
                }
                else
                {
                    for(CharacterClass c_tb : C_list)
                    {
                        //EnemyDao call_en = new EnemyDao();
                        //EnemyDao.callEnemy = cn;

                        System.out.println("정보가 있습니다.");
                        System.out.print("사용하고 싶은 캐릭터를 입력하세요 : ");
                        String c_name = sc.next();
                        CharacterClass C_dto = C_dao.selectCharacter(c_name);
                        System.out.println("선택하신 플레이어 정보입니다.");
                        System.out.println("이름 : " + C_dto.getC_name());
                        System.out.println("HP : " + C_dto.getC_hp());
                        System.out.println("직업 : " + C_dto.getC_job());
                        System.out.println("게임을 시작합니다");
                        System.out.println(C_dto.getC_name() + "이 여행을 떠난다.");
                        System.out.println(""); // 랜덤 적 출현
                        System.out.println("퉁퉁이가 공격을 했습니다"); // 랜덤적이 공격

                        int C_hp = C_dto.getC_hp();
                        Random rnd = new Random();
                        int r = rnd.nextInt(100);

                        C_hp = C_hp - r;
                        System.out.println("나의 HP가 " + C_hp + "가 남았습니다"); //
                        System.out.println("게임을 중단 할까요?");
                        System.out.println("1. 중단 2. 계속");
                        System.out.println("데이터를 저장합니다.");
                        C_dao.updateCharacter(C_hp, C_dto.getId());
                    }
                }

                //System.out.println(p_name + "이 길을 가다" + dc.callEnemy() + "를 만났다.");
//                System.out.println("");
//
//                break;

            }
            else if (ch_01 == 3)
            {
                System.out.println("정보 보기");
                TbCharDao C_dao = new TbCharDao();
                ArrayList<CharacterClass> c_list = C_dao.selectCharacter();
                EnemyDao E_dao = new EnemyDao();
                ArrayList<EnemyClass> e_list = E_dao.selectEnemy();
                ItemDao I_dao = new ItemDao();
                ArrayList<ItemClass> i_list = I_dao.selectItem();// 여기서 데이터를 가지고 와서

                // 여기에 출력 하고 싶다

                for (CharacterClass c_tb : c_list)
                {

                }

                for (EnemyClass e_tb : e_list)
                {

                }

                for (ItemClass i_tb : i_list)
                {

                }

            }
            else
            {
                System.out.println("게임을 종료 합니다.");
                System.out.println("재시작을 원하시면 다시 실행해 주세요.");
                break;
            }
        }


    }
}