package forgotpasswrod;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dhruvildesai on 31/03/18.
 */

public class Forgotpass_Ser{
        @SerializedName("Link")
        @Expose
        private String link;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
}
