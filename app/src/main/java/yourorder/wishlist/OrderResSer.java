package yourorder.wishlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dhruvildesai on 18/03/18.
 */

public class OrderResSer {
    @SerializedName("Order State")
    @Expose
    private OrderState orderState;

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
    public class OrderState {

        @SerializedName("Status")
        @Expose
        private Boolean status;
        @SerializedName("Message")
        @Expose
        private String message;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }


}