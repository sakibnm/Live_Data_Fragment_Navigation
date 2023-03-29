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
import android.widget.EditText;

import space.sakibnm.live_data_fragment_navigation.model.SharedViewModelText;

public class FragmentFirst extends Fragment {

    private EditText editTextShared;
    private Button buttonShare, buttonToFragment2, buttonToFragment3;
    private SharedViewModelText sharedViewModelText;
    private Observer<String> textSharedObserver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Initializing the ViewModel to set/update LiveData....
        sharedViewModelText = new ViewModelProvider(getActivity())
                .get(SharedViewModelText.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_first, container, false);

        editTextShared = root.findViewById(R.id.editTextSharedText);
        buttonShare = root.findViewById(R.id.button_Share);
        buttonToFragment2 = root.findViewById(R.id.button_toFragment2);
        buttonToFragment3 = root.findViewById(R.id.button_toFragment3);

        buttonShare.setOnClickListener(this::buttonShareClicked);
        buttonToFragment2.setOnClickListener(this::onButtonToFragment2Clicked);
        buttonToFragment3.setOnClickListener(this::onButtonToFragment3Clicked);

        //        Observe live data.....
        textSharedObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editTextShared.setText(s);
            }
        };
//        binding live data with the observer...
        sharedViewModelText.getSharedText().observe(getViewLifecycleOwner(), textSharedObserver);


        return root;
    }
    // Implementing fragment navigation to second fragment....
    private void onButtonToFragment2Clicked(View view) {
        Navigation.findNavController(view)
                .navigate(R.id.action_fragmentFirst_to_fragmentSecond);
    }
// Implementing fragment navigation to third fragment....

    private void onButtonToFragment3Clicked(View view) {
        Navigation.findNavController(view)
                .navigate(R.id.action_fragmentFirst_to_fragmentThird);
    }

//    Implementing shared live data.....
    private void buttonShareClicked(View view) {
        String sharedText = editTextShared.getText().toString();

        sharedViewModelText.getSharedText().setValue(sharedText);
    }
}