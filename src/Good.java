import java.math.BigDecimal;

public class Good implements Comparable<Good>, Cloneable
{
      private int id;
      private String name;
      private BigDecimal price;
      private int num;

      public Good()
      {
      }

      public Good(int id, String name, BigDecimal price, int num)
      {
            this.id = id;
            this.name = name;
            this.price = price;
            this.num = num;
      }

      public int getId()
      {
            return id;
      }

      public String getName()
      {
            return name;
      }

      public BigDecimal getPrice()
      {
            return price;
      }

      public int getNum()
      {
            return num;
      }

      public void setName(String name)
      {
            this.name = name;
      }

      public void setPrice(BigDecimal price)
      {
            this.price = price;
      }

      public void setNum(int num)
      {
            this.num = num;
      }

      @Override
      public String toString() {
            return id + "          " + name + "          " + price + "          " + num;
      }

      @Override
      public int compareTo(Good other) {
            return Integer.compare(this.id, other.id); // 按id升序
      }

      @Override
      public Good clone() {
            try {
                  return (Good) super.clone();
            } catch (CloneNotSupportedException e) {
                  return new Good(this.id, this.name, this.price, this.num);
            }
      }
}