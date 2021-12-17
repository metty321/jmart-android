package MatthewJmartFH.jmart_android.model;

import java.util.ArrayList;
import java.util.Date;

public class Payment extends Invoice {
    public ArrayList<Record> history;
    public int productCount;
    public Shipment shipment;

    public Payment(int buyerId,int productId,int productCount
            ,Shipment shipment)
    {
        super(buyerId,productId);
        this.shipment = shipment;
        this.productCount = productCount;

    }

    @Override
    public double getTotalPay(Product product)
    {
        return(product.price -((product.price*product.discount))/100);
    }


    public static class Record
    {
        public Status status;
        public Date date =java.util.Calendar.getInstance().getTime();
        public String message;

        public Record (Status status, String message)
        {
            this.status = status;
            this.message = message;

        }
    }

}
