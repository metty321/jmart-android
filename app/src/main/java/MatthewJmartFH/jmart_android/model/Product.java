package MatthewJmartFH.jmart_android.model;

public class Product extends Serializable {
    public  int accountId;
    public  ProductCategory category;
    public  boolean conditionUsed;
    public double discount;
    public String name ;
    public  int weight;
    public double price;
    public byte shipmentPlans;

    public String toString()
    {
        return this.name;
    }

}
