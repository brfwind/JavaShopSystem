import java.math.BigDecimal;

public class Good {
      private int id;
      private String name;   // 商品名
      private BigDecimal price;  // 商品价格
      private int num;

      public Good(int id, String name, BigDecimal price, int num) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.num = num;
      }

      public int getId() {
            return id;
      }

      public String getName() {
            return name;
      }

      public void setName(String name) {
            this.name = name;
      }

      public BigDecimal getPrice() {
            return price;
      }

      public void setPrice(BigDecimal price) {
            this.price = price;
      }

      public int getNum() {
            return num;
      }

      public void setNum(int num) {
            this.num = num;
      }

      @Override
      public String toString() {
            return id + "\t" + name + "\t" + price + "\t" + num;
      }
}