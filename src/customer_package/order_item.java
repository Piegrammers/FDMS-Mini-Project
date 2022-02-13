/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
