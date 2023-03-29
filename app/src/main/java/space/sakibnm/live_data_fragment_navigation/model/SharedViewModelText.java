package space.sakibnm.live_data_fragment_navigation.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModelText extends ViewModel {
    private MutableLiveData<String> sharedText;

    public SharedViewModelText(){
        sharedText = new MutableLiveData<String>();
    }

    public MutableLiveData<String> getSharedText() {
        return sharedText;
    }
}
