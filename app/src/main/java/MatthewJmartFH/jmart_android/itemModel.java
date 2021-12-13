package MatthewJmartFH.jmart_android;

import MatthewJmartFH.jmart_android.model.ProductCategory;

public class itemModel {
    public String accountId;
    public String category;
    public String conditionUsed;
    public String discount;
    public String name ;
    public String weight;
    public String price;
    public String shipmentPlans;

    public String getId()
    {
        return accountId;
    }

    public void setId(String accountId)
    {
        this.accountId = accountId;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }
    public String getCondition()
    {
        return conditionUsed;
    }

    public void setCondition(String conditionUsed)
    {
        this.conditionUsed = conditionUsed;
    }
    public String getDiscount()
    {
        return discount;
    }

    public void setDiscount(String discount)
    {
        this.discount = discount;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getWeight()
    {
        return weight;
    }

    public void setWeight(String weight)
    {
        this.weight = weight;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price=price;
    }

    public String getShipmentPlans()
    {
        return shipmentPlans;
    }

    public void setShipmentPlans(String shipmentPlans)
    {
        this.shipmentPlans=shipmentPlans;
    }
}
