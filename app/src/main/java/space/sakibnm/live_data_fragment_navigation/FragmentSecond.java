package space.sakibnm.live_data_fragment_navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import space.sakibnm.live_data_fragment_navigation.model.SharedViewModelText;

public class FragmentSecond extends Fragment {
    private TextView textViewShared;
    private Button buttonBackToFirst;
    private SharedViewModelText sharedViewModelText;
    private Observer<String> textSharedObserver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModelText = new ViewModelProvider(getActivity())
                .get(SharedViewModelText.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_second, container, false);

        textViewShared = root.findViewById(R.id.textViewSharedText);
        buttonBackToFirst = root.findViewById(R.id.buttonBackToFirst);

//        Observe live data.....
        textSharedObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textViewShared.setText(s);
            }
        };
//        binding live data with the observer...
        sharedViewModelText.getSharedText().observe(getViewLifecycleOwner(), textSharedObserver);

        buttonBackToFirst.setOnClickListener(this::onBackButtonPressed);

        return root;
    }

    private void onBackButtonPressed(View view) {
        Navigation.findNavController(view)
                .navigate(R.id.action_fragmentSecond_to_fragmentFirst);
    }
}