package countrystate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dhruvildesai on 06/03/18.
 */

public class StateSer {

    @SerializedName("States")
    @Expose
    private List<State> states = null;

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public class State {

        @SerializedName("id_state")
        @Expose
        private String idState;
        @SerializedName("State name")
        @Expose
        private String stateName;

        public String getIdState() {
            return idState;
        }

        public void setIdState(String idState) {
            this.idState = idState;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

    }
}
