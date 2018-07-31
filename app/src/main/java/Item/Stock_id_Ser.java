package Item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhruvildesai on 10/04/18.
 */

public class Stock_id_Ser {
    @SerializedName("stock_availables")
    @Expose
    private List<StockAvailable> stockAvailables = null;

    public List<StockAvailable> getStockAvailables() {
        return stockAvailables;
    }

    public void setStockAvailables(List<StockAvailable> stockAvailables) {
        this.stockAvailables = stockAvailables;
    }
    public class StockAvailable {

        @SerializedName("id")
        @Expose
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
