/**
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 03 - 23
 */

package shop;

//import shop.person.VIPClient;
import javax.swing.SpringLayout;
import shop.person.Client;
import shop.exceptions.AddException;
import shop.exceptions.SetterException;
import shop.gui.MainWindow;

public class Main {
    public static void main(String[] args) throws SetterException, AddException, CloneNotSupportedException {
      /*  try {
            Shop shop = new Shop();
    	//menu = new Menu();      	
            Item i1 = new Item("tarara", 2.58);
            Client c1 = new Client("pirmas", "pirmauskas");
            Order o1 = new Order();
            Order o2 = new Order();
            Order o3 = new Order();

            System.out.println(i1.toString());
            System.out.println(c1.toString());
            System.out.println(o1.toString());

            i1.setPrice(100);
            i1.setName("pakeiciauPavadinima");

            c1.setName("pakeiciauVarda");
            c1.setSurname("pakeiciauPavarde");

            o1.setOwner(c1);
            o1.addItem(i1);
            o1.addItem(i1, 3); // method overload
            o3.setOwner(c1);
            o3.addItem(i1);
            
            System.out.println(i1.toString());
            System.out.println(c1.toString());
            System.out.println(o1.toString());

            // 2 uzduot 

            System.out.println("---------------");

            Shop s = new Shop();

            s.add(i1);
            s.add(i1);
            s.add(o1);
            //s.addOrder(o2);
            s.add(o3);

            System.out.println(s.getClientAt(0));
            System.out.println(s.getOrderAt(0));
            System.out.println(s.getItemAt(0));

            // 3 uzduot

            System.out.println("\n trecia uzduotis\n");
            shop.person.Client VClient = new shop.person.VIPClient("vardas", "pavade", 10);
            Client client = new Client("vardas", "pavarde");

            o1.setOwner(VClient);
            o1.setOwner(client);

            VClient.getDiscount();         
            client.getDiscount();      

            System.out.println(o1);

            Order o4 = new Order();
            o4.addItem(i1);
            o4.setOwner(VClient);
            s.add(o4);      // sioj funkcijoj panaudotas CAST
         
            // 4 uzduotis
            
            System.out.println("\n 4 uzduotis \n");
            
            Item original = new Item("original", 100);
            Item cloned = new Item("cloned", 10);

            original = (Item) cloned.clone();
            cloned.setName("pakeistas");

            System.out.println(original);
            System.out.println(cloned);

            Order clonedOrder = new Order();

            clonedOrder = (Order) o1.clone();
            
            System.out.println(o1.getOwner());
            System.out.println(clonedOrder.getOwner());
            Client c2 = new Client("aa", "bb");
            clonedOrder.setOwner(c2);
            System.out.println(o1.getOwner());
            System.out.println(clonedOrder.getOwner());
            MainWindow gui = new MainWindow(shop);
            gui.createAndShowGui();
        }
        catch(SetterException e)
        {
            System.out.println(e.getMessage());
        }
        catch(AddException e)
        {
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
        
            System.out.println(e.getMessage());
        }
        * */
        Shop shop = new Shop();
        MainWindow gui = new MainWindow(shop);
        gui.createAndShowGui();
    }
}