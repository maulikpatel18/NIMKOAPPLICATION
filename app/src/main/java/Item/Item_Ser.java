package Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhruvildesai on 02/01/18.
 */

public class Item_Ser {


    @SerializedName("products")
    @Expose
    private List<Product> products = null;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public class Product {

        String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("id_default_image")
        @Expose
        private String idDefaultImage;
        @SerializedName("on_sale")
        @Expose
        private String onSale;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("wholesale_price")
        @Expose
        private String wholesalePrice;
        @SerializedName("link_rewrite")
        @Expose
        private String linkRewrite;
        @SerializedName("unit_price_ratio")
        @Expose
        private String unitPriceRatio;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getIdDefaultImage() {
            return idDefaultImage;
        }

        public void setIdDefaultImage(String idDefaultImage) {
            this.idDefaultImage = idDefaultImage;
        }

        public String getOnSale() {
            return onSale;
        }

        public void setOnSale(String onSale) {
            this.onSale = onSale;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getWholesalePrice() {
            return wholesalePrice;
        }

        public void setWholesalePrice(String wholesalePrice) {
            this.wholesalePrice = wholesalePrice;
        }
        public String getUnitPriceRatio() {
            return unitPriceRatio;
        }

        public void setUnitPriceRatio(String unitPriceRatio) {
            this.unitPriceRatio = unitPriceRatio;
        }
        public String getLinkRewrite() {
            return linkRewrite;
        }

        public void setLinkRewrite(String linkRewrite) {
            this.linkRewrite = linkRewrite;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }


//    private String name;
//
//    private int thumbnail,aprice,price,disoff;
//
//    public Item_Ser() {
//    }
//
//    public Item_Ser(String name, int price,int aprice,int disoff,int thumbnail) {
//        this.name = name;
//        this.price = price;
//        this.aprice = aprice;
//        this.disoff = disoff;
//        this.thumbnail = thumbnail;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public int getThumbnail() {
//        return thumbnail;
//    }
//
//    public void setThumbnail(int thumbnail) {
//        this.thumbnail = thumbnail;
//    }
//
//    public int getAprice() {
//        return aprice;
//    }
//
//    public void setAprice(int aprice) {
//        this.aprice = aprice;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public int getDisoff() {
//        return disoff;
//    }
//
//    public void setDisoff(int disoff) {
//        this.disoff = disoff;
//    }


    }
}
