import java.util.Scanner;

public class Shop {
      Scanner sc = new Scanner(System.in);

      public static void main(String[] args) {
            Shop shop = new Shop();
            shop.showMainMenu();
      }

      private void showMainMenu() {
            System.out.println("*****欢迎进入brf电子商城******");
            System.out.println("\t\t1.注册");
            System.out.println("\t\t2.登录");
            System.out.println("\t\t3.查看商城");
            System.out.println("\t\t4.查看我购买的商品");
            System.out.println("\t\t5.管理员登陆");
            System.out.println("\t\t6.退出系统");
            System.out.println("****************************");
      }
}
