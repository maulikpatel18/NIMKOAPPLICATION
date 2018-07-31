package wishlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhruvildesai on 20/03/18.
 */

public class Cate_Subcate_Ser {
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public class Category {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("id_parent")
        @Expose
        private String idParent;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getIdParent() {
            return idParent;
        }

        public void setIdParent(String idParent) {
            this.idParent = idParent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
