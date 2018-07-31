package user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dhruvildesai on 12/03/18.
 */

public class UpdateSerializeProfile {
    @SerializedName("Update Profile")
    @Expose
    private UpdateProfile updateProfile;

    public UpdateProfile getUpdateProfile() {
        return updateProfile;
    }

    public void setUpdateProfile(UpdateProfile updateProfile) {
        this.updateProfile = updateProfile;
    }
    public class UpdateProfile {

        @SerializedName("Status")
        @Expose
        private Integer status;
        @SerializedName("Message")
        @Expose
        private String message;

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
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

