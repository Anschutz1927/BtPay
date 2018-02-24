package test.multy.io.btpay.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.multy.io.btpay.R;

/**
 * Created by anschutz1927@gmail.com on 24.02.18.
 */

public class MainFragment extends Fragment implements View.OnClickListener {

    public static MainFragment getInastance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        setClickListeners(v);
    }

    private void setClickListeners(View v) {
        v.findViewById(R.id.btn_send).setOnClickListener(this);
        v.findViewById(R.id.btn_receive).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                break;
            case R.id.btn_receive:
                getActivity().getSupportFragmentManager().
                        beginTransaction()
                        .replace(R.id.container, ReceiveFragment.getInstance(), ReceiveFragment.class.getSimpleName())
                        .addToBackStack(ReceiveFragment.class.getSimpleName())
                        .commit();
                break;
        }
    }
}
