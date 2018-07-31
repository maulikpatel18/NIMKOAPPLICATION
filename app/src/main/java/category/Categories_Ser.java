package category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhruvildesai on 02/01/18.
 */

public class Categories_Ser {

    @SerializedName("categories")
    @Expose
    private List<Categories_Ser.Category> categories = null;

    public List<Categories_Ser.Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories_Ser.Category> categories) {
        this.categories = categories;
    }
    public class Category {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
