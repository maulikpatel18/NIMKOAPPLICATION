package user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhruvildesai on 17/03/18.
 */

public class CustSerialize {
    @SerializedName("customer")
    @Expose
    private Group.Customer customer;

    public Group.Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Group.Customer customer) {
        this.customer = customer;
    }

    public class Group {

        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public class Customer {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("id_default_group")
            @Expose
            private String idDefaultGroup;
            @SerializedName("id_lang")
            @Expose
            private String idLang;
            @SerializedName("newsletter_date_add")
            @Expose
            private String newsletterDateAdd;
            @SerializedName("ip_registration_newsletter")
            @Expose
            private String ipRegistrationNewsletter;
            @SerializedName("last_passwd_gen")
            @Expose
            private String lastPasswdGen;
            @SerializedName("secure_key")
            @Expose
            private String secureKey;
            @SerializedName("deleted")
            @Expose
            private String deleted;
            @SerializedName("passwd")
            @Expose
            private String passwd;
            @SerializedName("lastname")
            @Expose
            private String lastname;
            @SerializedName("firstname")
            @Expose
            private String firstname;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("id_gender")
            @Expose
            private String idGender;
            @SerializedName("birthday")
            @Expose
            private String birthday;
            @SerializedName("newsletter")
            @Expose
            private String newsletter;
            @SerializedName("optin")
            @Expose
            private String optin;
            @SerializedName("website")
            @Expose
            private Object website;
            @SerializedName("company")
            @Expose
            private Object company;
            @SerializedName("siret")
            @Expose
            private Object siret;
            @SerializedName("ape")
            @Expose
            private Object ape;
            @SerializedName("outstanding_allow_amount")
            @Expose
            private String outstandingAllowAmount;
            @SerializedName("show_public_prices")
            @Expose
            private String showPublicPrices;
            @SerializedName("id_risk")
            @Expose
            private String idRisk;
            @SerializedName("max_payment_days")
            @Expose
            private String maxPaymentDays;
            @SerializedName("active")
            @Expose
            private String active;
            @SerializedName("note")
            @Expose
            private Object note;
            @SerializedName("is_guest")
            @Expose
            private String isGuest;
            @SerializedName("id_shop")
            @Expose
            private String idShop;
            @SerializedName("id_shop_group")
            @Expose
            private String idShopGroup;
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

            public String getIdDefaultGroup() {
                return idDefaultGroup;
            }

            public void setIdDefaultGroup(String idDefaultGroup) {
                this.idDefaultGroup = idDefaultGroup;
            }

            public String getIdLang() {
                return idLang;
            }

            public void setIdLang(String idLang) {
                this.idLang = idLang;
            }

            public String getNewsletterDateAdd() {
                return newsletterDateAdd;
            }

            public void setNewsletterDateAdd(String newsletterDateAdd) {
                this.newsletterDateAdd = newsletterDateAdd;
            }

            public String getIpRegistrationNewsletter() {
                return ipRegistrationNewsletter;
            }

            public void setIpRegistrationNewsletter(String ipRegistrationNewsletter) {
                this.ipRegistrationNewsletter = ipRegistrationNewsletter;
            }

            public String getLastPasswdGen() {
                return lastPasswdGen;
            }

            public void setLastPasswdGen(String lastPasswdGen) {
                this.lastPasswdGen = lastPasswdGen;
            }

            public String getSecureKey() {
                return secureKey;
            }

            public void setSecureKey(String secureKey) {
                this.secureKey = secureKey;
            }

            public String getDeleted() {
                return deleted;
            }

            public void setDeleted(String deleted) {
                this.deleted = deleted;
            }

            public String getPasswd() {
                return passwd;
            }

            public void setPasswd(String passwd) {
                this.passwd = passwd;
            }

            public String getLastname() {
                return lastname;
            }

            public void setLastname(String lastname) {
                this.lastname = lastname;
            }

            public String getFirstname() {
                return firstname;
            }

            public void setFirstname(String firstname) {
                this.firstname = firstname;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getIdGender() {
                return idGender;
            }

            public void setIdGender(String idGender) {
                this.idGender = idGender;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getNewsletter() {
                return newsletter;
            }

            public void setNewsletter(String newsletter) {
                this.newsletter = newsletter;
            }

            public String getOptin() {
                return optin;
            }

            public void setOptin(String optin) {
                this.optin = optin;
            }

            public Object getWebsite() {
                return website;
            }

            public void setWebsite(Object website) {
                this.website = website;
            }

            public Object getCompany() {
                return company;
            }

            public void setCompany(Object company) {
                this.company = company;
            }

            public Object getSiret() {
                return siret;
            }

            public void setSiret(Object siret) {
                this.siret = siret;
            }

            public Object getApe() {
                return ape;
            }

            public void setApe(Object ape) {
                this.ape = ape;
            }

            public String getOutstandingAllowAmount() {
                return outstandingAllowAmount;
            }

            public void setOutstandingAllowAmount(String outstandingAllowAmount) {
                this.outstandingAllowAmount = outstandingAllowAmount;
            }

            public String getShowPublicPrices() {
                return showPublicPrices;
            }

            public void setShowPublicPrices(String showPublicPrices) {
                this.showPublicPrices = showPublicPrices;
            }

            public String getIdRisk() {
                return idRisk;
            }

            public void setIdRisk(String idRisk) {
                this.idRisk = idRisk;
            }

            public String getMaxPaymentDays() {
                return maxPaymentDays;
            }

            public void setMaxPaymentDays(String maxPaymentDays) {
                this.maxPaymentDays = maxPaymentDays;
            }

            public String getActive() {
                return active;
            }

            public void setActive(String active) {
                this.active = active;
            }

            public Object getNote() {
                return note;
            }

            public void setNote(Object note) {
                this.note = note;
            }

            public String getIsGuest() {
                return isGuest;
            }

            public void setIsGuest(String isGuest) {
                this.isGuest = isGuest;
            }

            public String getIdShop() {
                return idShop;
            }

            public void setIdShop(String idShop) {
                this.idShop = idShop;
            }

            public String getIdShopGroup() {
                return idShopGroup;
            }

            public void setIdShopGroup(String idShopGroup) {
                this.idShopGroup = idShopGroup;
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

            @SerializedName("groups")
            @Expose
            private List<Group> groups = null;

            public List<Group> getGroups() {
                return groups;
            }

            public void setGroups(List<Group> groups) {
                this.groups = groups;
            }

        }
    }
}
