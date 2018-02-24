package test.multy.io.btpay.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import test.multy.io.btpay.R;

/**
 * Created by anschutz1927@gmail.com on 24.02.18.
 */

public class ReceiveFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText inputAmount;
    private TextInputEditText inputAddress;
    private ToggleButton toggleButton;
    private ViewGroup groupInput;
    private ViewGroup groupComplete;
    private View progress;

    public static ReceiveFragment getInstance() {
        return new ReceiveFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_receive, container, false);
        init(v);
        return v;
    }

    @Override
    public void onClick(View view) {

    }

    private void init(View v) {
        inputAmount = v.findViewById(R.id.input_amount);
        inputAddress = v.findViewById(R.id.input_address);
        toggleButton = v.findViewById(R.id.toggle_switcher);
        groupInput = v.findViewById(R.id.group_input);
        groupComplete = v.findViewById(R.id.group_complete);
        progress = v.findViewById(R.id.progress);
        setListeners(v);
    }

    private void setListeners(View v) {
        ((ToggleButton) v.findViewById(R.id.toggle_switcher)).setOnCheckedChangeListener((compoundButton, b) -> {
            if (inputAddress.getText().toString().isEmpty() || inputAmount.getText().toString().isEmpty()) {
                Toast.makeText(compoundButton.getContext(), "Please, input data above!",
                        Toast.LENGTH_SHORT).show();
                compoundButton.setChecked(false);
                return;
            }
            if (b) {
                setInputEnabled(false);
                startReceiving();
            } else {
                setInputEnabled(true);
            }
        });
    }

    private void setInputEnabled(boolean isEnable) {
        if (isEnable) {
            progress.setVisibility(View.GONE);
            inputAmount.setEnabled(true);
            inputAddress.setEnabled(true);
        } else {
            progress.setVisibility(View.VISIBLE);
            inputAmount.setEnabled(false);
            inputAddress.setEnabled(false);
        }
    }

    private void startReceiving() {
        runTimer(5000, () -> {
            if (toggleButton.isChecked()) {
                groupComplete.setVisibility(View.VISIBLE);
                groupInput.setVisibility(View.GONE);
                stopReceiving();
            }
        });
    }

    private void stopReceiving() {
        runTimer(3000, () -> {
            groupInput.setVisibility(View.VISIBLE);
            groupComplete.setVisibility(View.GONE);
            setInputEnabled(true);
        });
    }

    private void runTimer(int millis, Runnable runnable) {
        Handler h = new Handler();
        h.postDelayed(runnable, millis);
    }
}
