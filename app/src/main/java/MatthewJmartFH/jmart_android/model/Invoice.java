package MatthewJmartFH.jmart_android.model;

import java.util.Date;

/**
 * @author  Matthew Eucharist
 * This is the model class of Invoice class from Invoice Class in back-end
 */
public abstract class Invoice extends Serializable{

    public enum Status {WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY,
        COMPLAINT, FINISHED, FAILED, DELIVERED}

    public enum Rating {NONE, BAD, NEUTRAL, GOOD};

    public Date date ;
    public int buyerId;
    public int productId;
    public int complaintId = -1;
    public Rating rating = Rating.NONE;

    protected Invoice(int buyerId, int productId)
    {

        this.buyerId = buyerId;
        this.productId = productId;
        this.date = java.util.Calendar.getInstance().getTime();
    }

    public boolean read(String content)
    {
        // put your code here
        return false;
    }

    public abstract double getTotalPay(Product product);


}
