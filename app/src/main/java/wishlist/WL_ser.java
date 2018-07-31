package wishlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class WL_ser {
//    private String name;
//
//    private int thumbnail,aprice,price,disoff;
//
//    public Order_ser() {
//    }
//
//    public Order_ser(String name, int price, int aprice, int disoff, int thumbnail) {
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

    @SerializedName("Whishlist")
    @Expose
    private List<Whishlist> whishlist = null;

    public List<Whishlist> getWhishlist() {
        return whishlist;
    }

    public void setWhishlist(List<Whishlist> whishlist) {
        this.whishlist = whishlist;
    }


    public class Whishlist {
        String Custid;

        public String getCustid() {
            return Custid;
        }

        public void setCustid(String custid) {
            Custid = custid;
        }

        String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @SerializedName("id_whishlist")
        @Expose
        private String idWhishlist;
        @SerializedName("Product id")
        @Expose
        private String productId;
        @SerializedName("WhishList Name")
        @Expose
        private String whishListName;
        @SerializedName("Product date added")
        @Expose
        private Object productDateAdded;
        @SerializedName("Product date updated")
        @Expose
        private Object productDateUpdated;

        public String getIdWhishlist() {
            return idWhishlist;
        }

        public void setIdWhishlist(String idWhishlist) {
            this.idWhishlist = idWhishlist;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getWhishListName() {
            return whishListName;
        }

        public void setWhishListName(String whishListName) {
            this.whishListName = whishListName;
        }

        public Object getProductDateAdded() {
            return productDateAdded;
        }

        public void setProductDateAdded(Object productDateAdded) {
            this.productDateAdded = productDateAdded;
        }

        public Object getProductDateUpdated() {
            return productDateUpdated;
        }

        public void setProductDateUpdated(Object productDateUpdated) {
            this.productDateUpdated = productDateUpdated;
        }

    }

}
