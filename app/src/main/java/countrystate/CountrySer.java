package countrystate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhruvildesai on 06/03/18.
 */

public class CountrySer {



    @SerializedName("Country")
    @Expose
    private List<Country> country = null;

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

    public class Country {

        @SerializedName("idcountry")
        @Expose
        private String idcountry;
        @SerializedName("Countryname")
        @Expose
        private String countryname;

        public String getIdcountry() {
            return idcountry;
        }

        public void setIdcountry(String idcountry) {
            this.idcountry = idcountry;
        }

        public String getCountryname() {
            return countryname;
        }

        public void setCountryname(String countryname) {
            this.countryname = countryname;
        }
    }

}
