package customer_package;

public class order_item {
    public String foodid;
    public int quantity;
    order_item(String foodid,int quantity)
    {
        this.foodid=foodid;
        this.quantity=quantity;
    }
    public void display()
    {
        System.out.println(foodid+' '+quantity+'\n');
    }
}
