import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
      static Scanner sc = new Scanner(System.in);
      //创建用户列表
      static List<User>userList = new ArrayList<User>();

      public static void main(String[] args) {
            Shop shop = new Shop();
            boolean go_on = true;

            while(go_on){
                  //展示/选择菜单
                  int choice = shop.showMainMenu();
                  //跳转相应菜单内容
                  shop.chooseMenu(choice, go_on);
            }
      }

      //展示菜单，并获得用户选择的菜单选项
      private int showMainMenu() {
            System.out.println("*****欢迎进入杨侦瑞的电子商城*****");
            System.out.println("\t1.注册");
            System.out.println("\t2.登录");
            System.out.println("\t3.查看商城");
            System.out.println("\t4.查看已购买的商品");
            System.out.println("\t5.管理员登陆");
            System.out.println("\t6.退出系统");
            System.out.println("****************************");
            System.out.println("请选择菜单：");
            int choice = sc.nextInt();
            return choice;
      }

      //创建用户对象，跳转子菜单
      private void chooseMenu(int choice, boolean go_on) {
            User user = new User();

            switch (choice) {
                  case 1:
                        System.out.println("您选择的菜单是：注册");
                        user.register();
                        break;
                  case 2:
                        System.out.println("您选择的菜单是：登录");
                        break;
                  case 3:
                        System.out.println("您选择的菜单是：查看商城");
                        break;
                  case 4:
                        System.out.println("您选择的菜单是：查看我购买的商品");
                        break;
                  case 5:
                        System.out.println("您选择的菜单是:管理员登录");
                        break;
                  case 6:
                        System.out.println("谢谢使用，期待与您下次再见^_^");
                        go_on = false;
                        //结束菜单，程序立即停止
                        System.exit(0);
                        break;
                  default:
                        System.out.println("您的输入有误！");
                        break;
            }
      }
}