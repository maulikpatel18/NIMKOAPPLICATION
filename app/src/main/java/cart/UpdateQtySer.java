package cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dhruvildesai on 18/04/18.
 */

public class UpdateQtySer {
    @SerializedName("Update Quantity")
    @Expose
    private UpdateQuantity updateQuantity;

    public UpdateQuantity getUpdateQuantity() {
        return updateQuantity;
    }

    public void setUpdateQuantity(UpdateQuantity updateQuantity) {
        this.updateQuantity = updateQuantity;
    }

    public class UpdateQuantity {

        @SerializedName("Status")
        @Expose
        private Boolean status;
        @SerializedName("Quantity")
        @Expose
        private String quantity;
        @SerializedName("Message")
        @Expose
        private String message;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
}
