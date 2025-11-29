import java.math.BigDecimal;

public class Admin extends User{

      //管理员登录
      public void adminLogin() {
            System.out.print("请输入管理员账户：");
            String adminName = Shop.sc.next();
            System.out.print("请输入管理员密码：");
            String adminPass = Shop.sc.next();
            if (!adminName.equals("YZR") || !adminPass.equals("YZR1234")) {
                  System.out.println("管理员账号或密码错误！");
                  return;
            }
            System.out.println("管理员登陆成功！");

            boolean go_on = true;

            while (go_on) {
                  int choice = showAdminMenu();
                  chooseAdminMenu(choice);
                  if (choice == 5) go_on = false;
            }
      }

      //显示管理员菜单
      private int showAdminMenu() {
            System.out.println("*****管理员菜单*****");
            System.out.println("\t1.添加商品");
            System.out.println("\t2.修改商品");
            System.out.println("\t3.删除商品");
            System.out.println("\t4.查看商品列表");
            System.out.println("\t5.退出");
            System.out.println("*******************");
            System.out.println("请选择菜单：");
            int choice = Shop.sc.nextInt();
            return choice;
      }

      private void chooseAdminMenu(int choice) {
            switch (choice) {
                  case 1:
                        System.out.println("您选择的菜单是：添加商品");
                        addGood();
                        break;
                  case 2:
                        System.out.println("您选择的菜单是：修改商品");
                        updateGood();
                        break;
                  case 3:
                        System.out.println("您选择的菜单是：删除商品");
                        deleteGood();
                        break;
                  case 4:
                        System.out.println("您选择的菜单是：查看商品列表");
                        listGoods();
                        break;
                  case 5:
                        System.out.println("已退出管理员模式!");
                        break;
                  default:
                        System.out.println("您的输入有误！");
                        break;
            }
      }

      //添加商品
      private void addGood() {
            System.out.println("*********添加商品********");
            while (true) {
                  System.out.print("请输入商品编号：");
                  int id = Shop.sc.nextInt();

                  boolean exist = false;
                  for (Good good : Shop.goodList) {
                        if (good.getId() == id) {
                              System.out.println("商品编号已存在，添加失败！请重新添加！");
                              exist = true;
                              break;
                        }
                  }
                  if (exist) continue;

                  System.out.print("请输入商品名称：");
                  String name = Shop.sc.next();

                  BigDecimal price;
                  while (true) {
                        System.out.print("请输入商品价格：");
                        price = Shop.sc.nextBigDecimal();
                        if (price.compareTo(BigDecimal.ZERO) <= 0) {
                              System.out.println("价格必须大于 0，请重新输入！");
                        } else {
                              break;
                        }
                  }

                  int num;
                  while (true) {
                        System.out.print("请输入商品数量：");
                        num = Shop.sc.nextInt();
                        if (num <= 0) {
                              System.out.println("数量必须大于 0，请重新输入！");
                        } else {
                              break;
                        }
                  }

                  Shop.goodList.add(new Good(id, name, price, num));
                  System.out.println("*****添加商品成功*****");
                  System.out.println("是否继续添加商品？");
                  String choice = Shop.sc.next();

                  if (!choice.equals("是")) {
                        break;
                  }
            }
      }

      //修改商品
      private void updateGood() {
            System.out.println("*******修改商品*******");

            while (true){
                  System.out.println("请输入要修改的商品编号：");
                  int id = Shop.sc.nextInt();
                  Good good = Shop.findGoodByID(id);
                  if (good == null){
                        System.out.println("该商品不存在，请重新输入编号！");
                        continue;
                  }
                  System.out.println("找到该商品，商品信息如下：");
                  System.out.println("商品编号\t"+ "名称\t"+ "价格\t"+ "数量\t");
                  System.out.println(good.getId()+"\t"+good.getName()+"\t"+good.getPrice()+"\t"+good.getNum());
                  System.out.println("请输入新的商品名称：");
                  String name = Shop.sc.next();
                  System.out.println("请输入新的商品价格：");
                  BigDecimal price = Shop.sc.nextBigDecimal();
                  System.out.println("请输入新的商品数量：");
                  int num = Shop.sc.nextInt();
                  good.setName(name);
                  good.setPrice(price);
                  good.setNum(num);
                  System.out.println("*****修改商品成功*****");
                  System.out.println("是否继续修改商品？");
                  String choice = Shop.sc.next();

                  if (!choice.equals("是")) {
                        break;
                  }
            }
      }

      //删除商品
      private void deleteGood() {
            System.out.println("*******删除商品*******");
            Shop.showGoodList();

            while (true) {
                  System.out.print("请输入要删除的商品编号：");
                  int id = Shop.sc.nextInt();
                  Good good = Shop.findGoodByID(id);

                  if (good == null) {
                        System.out.println("该商品不存在，请重新输入编号！");
                        continue;
                  }

                  System.out.println("找到该商品，商品信息如下：");
                  System.out.println("商品编号\t"+ "名称\t"+ "价格\t"+ "数量\t");
                  System.out.println(good);

                  System.out.println("确认删除该商品？");
                  String choice = Shop.sc.next();
                  if (choice.equals("确认")) {
                        Shop.goodList.remove(good);
                        System.out.println("*****删除商品成功*****");
                  } else {
                        System.out.println("已取消删除。");
                  }

                  System.out.println("是否继续删除商品？");
                  String next = Shop.sc.next();
                  if (!next.equals("确认")) {
                        System.out.println("已返回管理员菜单。");
                        break;
                  }
            }
      }

      //打印商品列表
      private void listGoods() {
            if (Shop.goodList.isEmpty()) {
                  System.out.println("当前没有商品，请先添加商品！");
                  return;
            }
            System.out.println("商品编号\t"+ "名称\t"+ "价格\t"+ "数量\t");
            for (Good good : Shop.goodList) {
                  System.out.println(good);
            }
      }
}