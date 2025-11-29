import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class User {
      //用户姓名、密码、购物车
      private String username;
      private String password;
      private List<Good> myGoodList = new ArrayList<>();

      public User() {}

      public User(String username, String password) {
            this.username = username;
            this.password = password;
      }

      public String getUsername() {
            return username;
      }
      public void setUsername(String username) {
            this.username = username;
      }

      public String getPassword() {
            return password;
      }
      public void setPassword(String password) {
            this.password = password;
      }

      public List<Good> getMyGoodList() {
            return myGoodList;
      }

      // 用户注册
      public static void register() {
            String username;
            String password;

            while (true) {
                  System.out.print("请输入用户名(长度不小于3位)：");
                  username = Shop.sc.next();

                  //记录注册的用户名是否已存在
                  boolean exists = false;

                  for (User user : Shop.userList) {
                        if (username.equals(user.getUsername())) {
                              System.out.println("用户名已存在");
                              exists = true;
                              break;
                        }
                  }
                  if (exists) continue;

                  if (username.length() < 3) {
                        System.out.println("用户名不得小于三位！");
                        continue;
                  }
                  break;
            }

            while (true) {
                  System.out.print("请输入密码(长度不小于6位，包含字母和数字)：");
                  password = Shop.sc.next();

                  boolean hasLetter = false;
                  boolean hasDigit = false;
                  for (int i = 0; i < password.length(); i++) {
                        char ch = password.charAt(i);
                        if (Character.isLetter(ch)) hasLetter = true;
                        if (Character.isDigit(ch)) hasDigit = true;
                  }

                  if (password.length() < 6 ||!hasLetter || !hasDigit) {
                        System.out.println("密码不符合要求，请重新输入！");
                        continue;
                  }

                  System.out.print("请再次输入密码：");
                  String repassword = Shop.sc.next();
                  if (!password.equals(repassword)) {
                        System.out.println("两次密码不一致，请重新输入！");
                        continue;
                  }

                  //注册成功，创建新的用户对象
                  User newUser = new User(username, password);
                  Shop.userList.add(newUser);
                  System.out.println("注册成功！");
                  break;
            }
      }

      // 用户登录
      public static User login() {
            while (true) {
                  System.out.print("请输入用户名：");
                  String username = Shop.sc.next();

                  System.out.print("请输入密码：");
                  String password = Shop.sc.next();

                  for (User user : Shop.userList) {
                        if (username.equals(user.getUsername())) {
                              if (password.equals(user.getPassword())) {
                                    System.out.println("登录成功!");
                                    return user;
                              } else {
                                    System.out.println("密码错误，请重新输入");
                                    break;
                              }
                        }
                  }
                  System.out.println("用户名不存在，请重新输入或注册");
            }
      }

      // 购买商品
      public void buy() {
            if (Shop.currentUser == null) {
                  System.out.println("您还未登录！");
                  return;
            }

            Shop.showGoodList();

            boolean go_on = true;

            while (go_on) {
                  //初始化
                  Good good = null;
                  while (true) {
                        System.out.print("请输入购买商品的 id：");
                        int id = Shop.sc.nextInt();
                        good = Shop.findGoodByID(id);

                        if (good == null) {
                              System.out.println("商品不存在，请重新输入！");
                              continue;
                        }

                        System.out.println("找到商品：");
                        System.out.println("编号    名称       价格    数量");
                        System.out.println(good);

                        System.out.print("确认购买此商品吗：");
                        String c = Shop.sc.next();

                        if (c.equals("确认")) {
                              break;  // 用户确认购买，跳出循环
                        } else if (c.equals("取消")) {
                              continue;  // 用户取消，重新输入id
                        } else {
                              System.out.println("输入有误，请输入“确认”或“取消”！");
                        }
                  }

                  int num;

                  while (true) {
                        System.out.print("请输入购买数量：");
                        num = Shop.sc.nextInt();

                        if (num <= 0) {
                              System.out.println("数量必须大于 0！");
                              continue;
                        }
                        if (num > good.getNum()) {
                              System.out.println("库存不足！");
                              continue;
                        }
                        break;
                  }

                  Good exist = null;

                  for (Good g : this.myGoodList) {
                        if (g.getId() == good.getId()) {
                              exist = g;
                              break; }
                  }

                  if (exist != null) exist.setNum(exist.getNum() + num);
                  else {
                        Good copy = good.clone();
                        copy.setNum(num);
                        this.myGoodList.add(copy);
                  }

                  good.setNum(good.getNum() - num);
                  System.out.println("购买成功！");

                  while (true) {
                        System.out.print("是否继续购买？：");
                        String c = Shop.sc.next().toLowerCase();
                        if (c.equals("是")) break;
                        if (c.equals("否")) { go_on = false; break; }
                        System.out.println("输入有误！");
                  }
            }
      }

      // 查看已购商品
      public void showMyGoodList() {
            if (Shop.currentUser == null) {
                  System.out.println("您还未登录！");
                  return;
            }

            if (myGoodList.isEmpty()) {
                  System.out.println("您还未购买任何商品！");
                  return;
            }

            System.out.println("****************购买列表****************");
            System.out.printf("%-8s %-10s %-8s %-6s%n", "编号", "名称", "价格", "数量");

            BigDecimal total = BigDecimal.ZERO;
            for (Good g : myGoodList) {
                  System.out.println(g);
                  total = total.add(g.getPrice().multiply(BigDecimal.valueOf(g.getNum())));
            }
            System.out.println("---------------------------------------");
            System.out.println("总价为：" + total);
      }


}
