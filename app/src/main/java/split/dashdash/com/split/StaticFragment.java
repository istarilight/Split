package split.dashdash.com.split;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StaticFragment extends Fragment {
    public static StaticFragment newInstance() {
        return new StaticFragment();
    }

    public static StaticFragment newInstance(int layoutId) {

        Bundle arguments = new Bundle();
        populateArguments(arguments, layoutId);

        StaticFragment fragment = new StaticFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int layoutId = getLayoutIdArgument();

        return (0 != layoutId) ?
                inflater.inflate(layoutId, container, false) :
                super.onCreateView(inflater, container, savedInstanceState);
    }

    protected static Bundle createArguments(int layoutId) {
        return populateArguments(new Bundle(), layoutId);
    }

    protected static Bundle populateArguments(Bundle arguments, int layoutId) {
        if (null != arguments) {
            arguments.putInt(EXTRA_LAYOUT_ID, layoutId);
        }
        return arguments;
    }

    protected final int getLayoutIdArgument() {

        Bundle arguments = getArguments();

        if (null != arguments) {
            return arguments.getInt(EXTRA_LAYOUT_ID, DEFAULT_LAYOUT_ID);
        }

        return DEFAULT_LAYOUT_ID;
    }

    private static final String EXTRA_LAYOUT_ID = "EXTRA_LAYOUT_ID";
    private static final int DEFAULT_LAYOUT_ID = 0;
}
