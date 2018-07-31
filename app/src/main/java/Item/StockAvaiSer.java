package Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dhruvildesai on 10/04/18.
 */

public class StockAvaiSer
{
    @SerializedName("stock_available")
    @Expose
    private StockAvailable stockAvailable;

    public StockAvailable getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(StockAvailable stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public class StockAvailable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("id_product")
        @Expose
        private String idProduct;
        @SerializedName("id_product_attribute")
        @Expose
        private String idProductAttribute;
        @SerializedName("id_shop")
        @Expose
        private String idShop;
        @SerializedName("id_shop_group")
        @Expose
        private String idShopGroup;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("depends_on_stock")
        @Expose
        private String dependsOnStock;
        @SerializedName("out_of_stock")
        @Expose
        private String outOfStock;

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

        public String getIdProductAttribute() {
            return idProductAttribute;
        }

        public void setIdProductAttribute(String idProductAttribute) {
            this.idProductAttribute = idProductAttribute;
        }

        public String getIdShop() {
            return idShop;
        }

        public void setIdShop(String idShop) {
            this.idShop = idShop;
        }

        public String getIdShopGroup() {
            return idShopGroup;
        }

        public void setIdShopGroup(String idShopGroup) {
            this.idShopGroup = idShopGroup;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getDependsOnStock() {
            return dependsOnStock;
        }

        public void setDependsOnStock(String dependsOnStock) {
            this.dependsOnStock = dependsOnStock;
        }

        public String getOutOfStock() {
            return outOfStock;
        }

        public void setOutOfStock(String outOfStock) {
            this.outOfStock = outOfStock;
        }

    }
}
