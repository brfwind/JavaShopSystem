import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Shop {
      static Scanner sc = new Scanner(System.in);

      //用户列表
      static List<User> userList = new ArrayList<>();
      //商品列表
      static List<Good> goodList = new ArrayList<>();
      //当前用户对象
      static User currentUser = null;

      public static void main(String[] args) {
            Shop shop = new Shop();

            //初始化商城商品
            shop.initGoodList();

            while (true) {
                  int choice = shop.showMainMenu();
                  shop.chooseMenu(choice);
            }
      }

      private int showMainMenu() {
            System.out.println("*****欢迎进入杨侦瑞的电子商城*****");
            System.out.println("\t1.注册");
            System.out.println("\t2.登录");
            System.out.println("\t3.查看商城");
            System.out.println("\t4.查看已购买的商品");
            System.out.println("\t5.管理员登录");
            System.out.println("\t6.退出系统");
            System.out.println("****************************");
            System.out.print("请选择菜单：");
            return sc.nextInt();
      }

      private void chooseMenu(int choice) {
            switch (choice) {
                  case 1:
                        System.out.println("您选择的菜单是：注册");
                        User.register();
                        break;
                  case 2:
                        System.out.println("您选择的菜单是：登录");
                        currentUser = User.login();
                        break;
                  case 3:
                        System.out.println("您选择的菜单是：查看商城");
                        showGoodList();
                        if (currentUser != null) {
                              System.out.print("是否要购买商品？(yes/no)：");
                              String ans = sc.next().toLowerCase();
                              if (ans.equals("yes")) currentUser.buy();
                        }
                        break;
                  case 4:
                        System.out.println("您选择的菜单是：查看我购买的商品");
                        if (currentUser == null) System.out.println("您还未登录！");
                        else currentUser.showMyGoodList();
                        break;
                  case 5:
                        Admin admin = new Admin();
                        admin.adminLogin();
                        break;
                  case 6:
                        System.out.println("谢谢使用，期待下次再见！");
                        System.exit(0);
                        break;
                  default:
                        System.out.println("输入有误！");
            }
      }

      //显示商城里的商品列表
      public static void showGoodList() {
            System.out.println("****************商城列表****************");
            if (goodList.isEmpty()) { System.out.println("当前没有商品！"); return; }
            Collections.sort(goodList);
            System.out.printf("%-8s %-10s %-8s %-6s%n", "编号", "名称", "价格", "库存");
            for (Good g : goodList) System.out.println(g);
      }

      void initGoodList() {
            goodList.add(new Good(1, "苹果", BigDecimal.valueOf(5), 5));
            goodList.add(new Good(2, "桃子", BigDecimal.valueOf(10), 10));
      }

      public static Good findGoodByID(int id) {
            for (Good g : goodList) if (g.getId() == id) return g;
            return null;
      }
}
