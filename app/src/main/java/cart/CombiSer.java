package cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhruvildesai on 09/04/18.
 */

public class CombiSer {
    @SerializedName("combination")
    @Expose
    private Combination combination;

    public Combination getCombination() {
        return combination;
    }

    public void setCombination(Combination combination) {
        this.combination = combination;
    }
    public class Associations {

        @SerializedName("product_option_values")
        @Expose
        private List<ProductOptionValue> productOptionValues = null;

        public List<ProductOptionValue> getProductOptionValues() {
            return productOptionValues;
        }

        public void setProductOptionValues(List<ProductOptionValue> productOptionValues) {
            this.productOptionValues = productOptionValues;
        }

    }
    public class Combination {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("id_product")
        @Expose
        private String idProduct;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("ean13")
        @Expose
        private String ean13;
        @SerializedName("upc")
        @Expose
        private String upc;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("reference")
        @Expose
        private String reference;
        @SerializedName("supplier_reference")
        @Expose
        private String supplierReference;
        @SerializedName("wholesale_price")
        @Expose
        private String wholesalePrice;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("ecotax")
        @Expose
        private String ecotax;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("unit_price_impact")
        @Expose
        private String unitPriceImpact;
        @SerializedName("minimal_quantity")
        @Expose
        private String minimalQuantity;
        @SerializedName("default_on")
        @Expose
        private Object defaultOn;
        @SerializedName("available_date")
        @Expose
        private String availableDate;
        @SerializedName("associations")
        @Expose
        private Associations associations;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getIdProduct() {
            return idProduct;
        }

        public void setIdProduct(String idProduct) {
            this.idProduct = idProduct;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getEan13() {
            return ean13;
        }

        public void setEan13(String ean13) {
            this.ean13 = ean13;
        }

        public String getUpc() {
            return upc;
        }

        public void setUpc(String upc) {
            this.upc = upc;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getSupplierReference() {
            return supplierReference;
        }

        public void setSupplierReference(String supplierReference) {
            this.supplierReference = supplierReference;
        }

        public String getWholesalePrice() {
            return wholesalePrice;
        }

        public void setWholesalePrice(String wholesalePrice) {
            this.wholesalePrice = wholesalePrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getEcotax() {
            return ecotax;
        }

        public void setEcotax(String ecotax) {
            this.ecotax = ecotax;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getUnitPriceImpact() {
            return unitPriceImpact;
        }

        public void setUnitPriceImpact(String unitPriceImpact) {
            this.unitPriceImpact = unitPriceImpact;
        }

        public String getMinimalQuantity() {
            return minimalQuantity;
        }

        public void setMinimalQuantity(String minimalQuantity) {
            this.minimalQuantity = minimalQuantity;
        }

        public Object getDefaultOn() {
            return defaultOn;
        }

        public void setDefaultOn(Object defaultOn) {
            this.defaultOn = defaultOn;
        }

        public String getAvailableDate() {
            return availableDate;
        }

        public void setAvailableDate(String availableDate) {
            this.availableDate = availableDate;
        }

        public Associations getAssociations() {
            return associations;
        }

        public void setAssociations(Associations associations) {
            this.associations = associations;
        }

    }
    public class ProductOptionValue {

        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

}
