package yourorder.wishlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dhruvildesai on 11/04/18.
 */

public class StatusOrdSer {
    @SerializedName("order_state")
    @Expose
    private OrderState orderState;

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
    public class OrderState {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("unremovable")
        @Expose
        private String unremovable;
        @SerializedName("delivery")
        @Expose
        private String delivery;
        @SerializedName("hidden")
        @Expose
        private String hidden;
        @SerializedName("send_email")
        @Expose
        private String sendEmail;
        @SerializedName("module_name")
        @Expose
        private String moduleName;
        @SerializedName("invoice")
        @Expose
        private String invoice;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("logable")
        @Expose
        private String logable;
        @SerializedName("shipped")
        @Expose
        private String shipped;
        @SerializedName("paid")
        @Expose
        private String paid;
        @SerializedName("pdf_delivery")
        @Expose
        private String pdfDelivery;
        @SerializedName("pdf_invoice")
        @Expose
        private String pdfInvoice;
        @SerializedName("deleted")
        @Expose
        private String deleted;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("template")
        @Expose
        private String template;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUnremovable() {
            return unremovable;
        }

        public void setUnremovable(String unremovable) {
            this.unremovable = unremovable;
        }

        public String getDelivery() {
            return delivery;
        }

        public void setDelivery(String delivery) {
            this.delivery = delivery;
        }

        public String getHidden() {
            return hidden;
        }

        public void setHidden(String hidden) {
            this.hidden = hidden;
        }

        public String getSendEmail() {
            return sendEmail;
        }

        public void setSendEmail(String sendEmail) {
            this.sendEmail = sendEmail;
        }

        public String getModuleName() {
            return moduleName;
        }

        public void setModuleName(String moduleName) {
            this.moduleName = moduleName;
        }

        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getLogable() {
            return logable;
        }

        public void setLogable(String logable) {
            this.logable = logable;
        }

        public String getShipped() {
            return shipped;
        }

        public void setShipped(String shipped) {
            this.shipped = shipped;
        }

        public String getPaid() {
            return paid;
        }

        public void setPaid(String paid) {
            this.paid = paid;
        }

        public String getPdfDelivery() {
            return pdfDelivery;
        }

        public void setPdfDelivery(String pdfDelivery) {
            this.pdfDelivery = pdfDelivery;
        }

        public String getPdfInvoice() {
            return pdfInvoice;
        }

        public void setPdfInvoice(String pdfInvoice) {
            this.pdfInvoice = pdfInvoice;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

    }

}
