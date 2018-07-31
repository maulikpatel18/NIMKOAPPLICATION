package shipping_option;

/**
 * Created by dhruvildesai on 29/03/18.
 */

public class ShipoptionSer {
    String optiontitle,optiontxt,tax;
    int id1;

    public ShipoptionSer() {
    }

    public ShipoptionSer(int id1,String title, String opttxt, String tax) {
        this.optiontitle = title;
        this.id1=id1;
        this.optiontxt = opttxt;
        this.tax = tax;

    }
    public String getOptiontitle() {
        return optiontitle;
    }

    public void setOptiontitle(String optiontitle) {
        this.optiontitle = optiontitle;
    }

    public String getOptiontxt() {
        return optiontxt;
    }

    public void setOptiontxt(String optiontxt) {
        this.optiontxt = optiontxt;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }
}
