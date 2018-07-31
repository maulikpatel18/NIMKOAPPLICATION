package cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Cart_ser {
    @SerializedName("cart")
    @Expose
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public class CartRow {

        String email;
        String custid;
        String pricetoitem,wpricetoitem,addshipping;

        public String getPricetoitem() {
            return pricetoitem;
        }

        public void setPricetoitem(String pricetoitem) {
            this.pricetoitem = pricetoitem;
        }

        public String getWpricetoitem() {
            return wpricetoitem;
        }

        public void setWpricetoitem(String wpricetoitem) {
            this.wpricetoitem = wpricetoitem;
        }

        public String getAddshipping() {
            return addshipping;
        }

        public void setAddshipping(String addshipping) {
            this.addshipping = addshipping;
        }

        public String getCustid() {
            return custid;
        }

        public void setCustid(String custid) {
            this.custid = custid;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @SerializedName("id_product")
        @Expose
        private String idProduct;
        @SerializedName("id_product_attribute")
        @Expose
        private String idProductAttribute;
        @SerializedName("id_address_delivery")
        @Expose
        private String idAddressDelivery;
        @SerializedName("quantity")
        @Expose
        private String quantity;

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

        public String getIdAddressDelivery() {
            return idAddressDelivery;
        }

        public void setIdAddressDelivery(String idAddressDelivery) {
            this.idAddressDelivery = idAddressDelivery;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

    }
    public class Cart {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("id_address_delivery")
        @Expose
        private String idAddressDelivery;
        @SerializedName("id_address_invoice")
        @Expose
        private String idAddressInvoice;
        @SerializedName("id_currency")
        @Expose
        private String idCurrency;
        @SerializedName("id_customer")
        @Expose
        private String idCustomer;
        @SerializedName("id_guest")
        @Expose
        private String idGuest;
        @SerializedName("id_lang")
        @Expose
        private String idLang;
        @SerializedName("id_shop_group")
        @Expose
        private String idShopGroup;
        @SerializedName("id_shop")
        @Expose
        private String idShop;
        @SerializedName("id_carrier")
        @Expose
        private String idCarrier;
        @SerializedName("recyclable")
        @Expose
        private String recyclable;
        @SerializedName("gift")
        @Expose
        private String gift;
        @SerializedName("gift_message")
        @Expose
        private String giftMessage;
        @SerializedName("mobile_theme")
        @Expose
        private String mobileTheme;
        @SerializedName("delivery_option")
        @Expose
        private String deliveryOption;
        @SerializedName("secure_key")
        @Expose
        private String secureKey;
        @SerializedName("allow_seperated_package")
        @Expose
        private String allowSeperatedPackage;
        @SerializedName("date_add")
        @Expose
        private String dateAdd;
        @SerializedName("date_upd")
        @Expose
        private String dateUpd;
        @SerializedName("associations")
        @Expose
        private Associations associations;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getIdAddressDelivery() {
            return idAddressDelivery;
        }

        public void setIdAddressDelivery(String idAddressDelivery) {
            this.idAddressDelivery = idAddressDelivery;
        }

        public String getIdAddressInvoice() {
            return idAddressInvoice;
        }

        public void setIdAddressInvoice(String idAddressInvoice) {
            this.idAddressInvoice = idAddressInvoice;
        }

        public String getIdCurrency() {
            return idCurrency;
        }

        public void setIdCurrency(String idCurrency) {
            this.idCurrency = idCurrency;
        }

        public String getIdCustomer() {
            return idCustomer;
        }

        public void setIdCustomer(String idCustomer) {
            this.idCustomer = idCustomer;
        }

        public String getIdGuest() {
            return idGuest;
        }

        public void setIdGuest(String idGuest) {
            this.idGuest = idGuest;
        }

        public String getIdLang() {
            return idLang;
        }

        public void setIdLang(String idLang) {
            this.idLang = idLang;
        }

        public String getIdShopGroup() {
            return idShopGroup;
        }

        public void setIdShopGroup(String idShopGroup) {
            this.idShopGroup = idShopGroup;
        }

        public String getIdShop() {
            return idShop;
        }

        public void setIdShop(String idShop) {
            this.idShop = idShop;
        }

        public String getIdCarrier() {
            return idCarrier;
        }

        public void setIdCarrier(String idCarrier) {
            this.idCarrier = idCarrier;
        }

        public String getRecyclable() {
            return recyclable;
        }

        public void setRecyclable(String recyclable) {
            this.recyclable = recyclable;
        }

        public String getGift() {
            return gift;
        }

        public void setGift(String gift) {
            this.gift = gift;
        }

        public String getGiftMessage() {
            return giftMessage;
        }

        public void setGiftMessage(String giftMessage) {
            this.giftMessage = giftMessage;
        }

        public String getMobileTheme() {
            return mobileTheme;
        }

        public void setMobileTheme(String mobileTheme) {
            this.mobileTheme = mobileTheme;
        }

        public String getDeliveryOption() {
            return deliveryOption;
        }

        public void setDeliveryOption(String deliveryOption) {
            this.deliveryOption = deliveryOption;
        }

        public String getSecureKey() {
            return secureKey;
        }

        public void setSecureKey(String secureKey) {
            this.secureKey = secureKey;
        }

        public String getAllowSeperatedPackage() {
            return allowSeperatedPackage;
        }

        public void setAllowSeperatedPackage(String allowSeperatedPackage) {
            this.allowSeperatedPackage = allowSeperatedPackage;
        }

        public String getDateAdd() {
            return dateAdd;
        }

        public void setDateAdd(String dateAdd) {
            this.dateAdd = dateAdd;
        }

        public String getDateUpd() {
            return dateUpd;
        }

        public void setDateUpd(String dateUpd) {
            this.dateUpd = dateUpd;
        }

        public Associations getAssociations() {
            return associations;
        }

        public void setAssociations(Associations associations) {
            this.associations = associations;
        }

    }
    public class Associations {

        @SerializedName("cart_rows")
        @Expose
        private List<CartRow> cartRows = null;

        public List<CartRow> getCartRows() {
            return cartRows;
        }

        public void setCartRows(List<CartRow> cartRows) {
            this.cartRows = cartRows;
        }

    }
}
