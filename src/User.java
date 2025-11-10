public class User {
      //创建用户名、密码变量
      private String username;
      private String password;

      //方法：获取用户名
      public String getUsername() {
            return username;
      }

      //方法：设置用户名
      public void setUsername(String username) {
            this.username = username;
      }

      //方法：获取密码
      public String getPassword() {
            return password;
      }

      //方法：设置密码
      public void setPassword(String password) {
            this.password = password;
      }


      //方法：用户注册（包括用户名和密码）
      public void register() {
            //初始化用户名、密码
            String username = "";
            String password = "";

            //用户名创建
            while (true) {
                  System.out.println("请输入用户名(长度不小于3位)：");
                  username = Shop.sc.next();

                  //判断用户名是否已存在
                  boolean flag = true;
                  for (User user : Shop.userList) {
                        if (username.equals(user.getUsername())) {
                              System.out.println("用户名已存在");
                              flag = false;
                              break;
                        }
                  }

                  if (flag == false) continue;

                  //若用户名不存在，继续执行用户名可用性检测
                  if (username.length() < 3) {
                        System.out.println("用户名不得小于三位！");
                  } else break;
            }

            //密码创建
            while (true) {
                  System.out.println("请输入密码(长度不小于6位，必须由字符和数字组成)：");
                  password = Shop.sc.next();

                  //密码可行性检测
                  if (password.length() < 6) {
                        System.out.println("密码长度不能小于6位，请重新输入！");
                        continue;
                  } else if (!password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*")) {
                        System.out.println("密码必须同时包含字母与数字，请重新输入！");
                        continue;
                  }

                  System.out.println("请再次输入密码:");
                  String repassword = Shop.sc.next();

                  if (password.equals(repassword)) {
                        User user = new User();
                        user.setUsername(username);
                        user.setPassword(password);
                        Shop.userList.add(user);
                        break;
                  } else System.out.println("两次密码不一致，请重新输入");
            }
            System.out.println("注册成功！\n");
      }


      //方法：用户登录
      public void login() {
            String username;
            String password;
            boolean loginSuccess = false;

            while (!loginSuccess) {
                  System.out.println("请输入用户名：");
                  username = Shop.sc.next();

                  System.out.println("请输入登录密码：");
                  password = Shop.sc.next();

                  boolean foundUser = false;

                  for (User user : Shop.userList) {
                        if (username.equals(user.getUsername())) {
                              foundUser = true;

                              if (password.equals(user.getPassword())) {
                                    System.out.println("登录成功!");
                                    loginSuccess = true;
                              } else
                                    System.out.println("密码错误，请重新输入");

                              break;
                        }
                  }

                  if (!foundUser)
                        System.out.println("用户名不存在，请重新输入");
            }
      }

      public void ShowGoodList() {

      }

      public Good findGoodByID(int id){
            Good returnGood = null;
            for (Good good :Shop.goodList){
                  if (good.getId() == id){
                        returnGood =good;
                        break;
                  }
            }
            return returnGood;
      }

}