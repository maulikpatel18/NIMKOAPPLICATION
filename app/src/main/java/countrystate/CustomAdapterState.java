package countrystate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nimako.R;

import java.util.List;

/**
 * Created by dhruvildesai on 21/03/18.
 */

public class CustomAdapterState extends BaseAdapter {
    Context context;
    List<String> stateNames;
    LayoutInflater inflter;

    public CustomAdapterState(Context applicationContext, List<String> stateNames) {
        this.context = applicationContext;

        this.stateNames = stateNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return stateNames.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinnerdesign, null);
        TextView names = (TextView) view.findViewById(R.id.textView);
        names.setText(stateNames.get(i));
        return view;
    }
}