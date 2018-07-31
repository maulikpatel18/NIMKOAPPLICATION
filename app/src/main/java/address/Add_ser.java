package address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhruvildesai on 02/02/18.
 */

public class Add_ser {

//    private String txtadd,txttitle;
//
//
//    public Add_ser() {
//    }
//
//    public Add_ser(String txtadd, String txttitle ) {
//        this.txtadd = txtadd;
//        this.txttitle = txttitle;
//
//    }
//
//    public String getTxttitle() {
//        return txttitle;
//    }
//
//    public void setTxttitle(String txttitle) {
//        this.txttitle = txttitle;
//    }
//
//    public String getTxtadd() {
//
//        return txtadd;
//    }
//
//    public void setTxtadd(String txtadd) {
//        this.txtadd = txtadd;
//    }

    @SerializedName("Address")
    @Expose
    private List<Address> address = null;

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
    public class Address {

        @SerializedName("Customer id")
        @Expose
        private String customerId;
        @SerializedName("Addreess id")
        @Expose
        private String addreessId;
        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("lastname")
        @Expose
        private String lastname;
        @SerializedName("alias")
        @Expose
        private String alias;
        @SerializedName("address1")
        @Expose
        private String address1;
        @SerializedName("address2")
        @Expose
        private String address2;
        @SerializedName("postcode")
        @Expose
        private String postcode;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("phone mobile")
        @Expose
        private String phoneMobile;
        @SerializedName("CountryID")
        @Expose
        private String countryID;
        @SerializedName("Country")
        @Expose
        private String country;
        @SerializedName("Company")
        @Expose
        private String company;
        @SerializedName("other")
        @Expose
        private String other;
        @SerializedName("stateid")
        @Expose
        private String stateid;
        @SerializedName("StateName")
        @Expose
        private String stateName;
        @SerializedName("Email")
        @Expose
        private String email;

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getAddreessId() {
            return addreessId;
        }

        public void setAddreessId(String addreessId) {
            this.addreessId = addreessId;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhoneMobile() {
            return phoneMobile;
        }

        public void setPhoneMobile(String phoneMobile) {
            this.phoneMobile = phoneMobile;
        }

        public String getCountryID() {
            return countryID;
        }

        public void setCountryID(String countryID) {
            this.countryID = countryID;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getStateid() {
            return stateid;
        }

        public void setStateid(String stateid) {
            this.stateid = stateid;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
