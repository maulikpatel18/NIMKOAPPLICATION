package cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dhruvildesai on 09/04/18.
 */

public class OptValser {
    @SerializedName("product_option_value")
    @Expose
    private ProductOptionValue productOptionValue;

    public ProductOptionValue getProductOptionValue() {
        return productOptionValue;
    }

    public void setProductOptionValue(ProductOptionValue productOptionValue) {
        this.productOptionValue = productOptionValue;
    }

    public class ProductOptionValue {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("id_attribute_group")
        @Expose
        private String idAttributeGroup;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("position")
        @Expose
        private String position;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getIdAttributeGroup() {
            return idAttributeGroup;
        }

        public void setIdAttributeGroup(String idAttributeGroup) {
            this.idAttributeGroup = idAttributeGroup;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
