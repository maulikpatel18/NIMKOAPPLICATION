package tempagain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import category.Categories_Ser;

/**
 * Created by dhruvildesai on 19/01/18.
 */

public class ItemObject {

    @SerializedName("categories")
    @Expose
    private List<Categories_Ser> categories = null;

    public List<Categories_Ser> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories_Ser> categories) {
        this.categories = categories;
    }

}

//    @SerializedName("song_name")
//    private String name;
//
//    public ItemObject(String name){
//        this.name=name;
//
//    }



//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
