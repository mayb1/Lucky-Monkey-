package luckkky.bigw.monkey;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import io.michaelrocks.paranoid.Obfuscate;
import luckkky.bigw.monkey.databinding.FragmentLumPrivacyPolicyBinding;
@Obfuscate
public class LumPrivacyPolicyFragment extends Fragment {

    private FragmentLumPrivacyPolicyBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = FragmentLumPrivacyPolicyBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.bLumBack.setOnClickListener(v103 -> Navigation.findNavController(requireView()).navigate(R.id.navigation_lumMenu));
    }

}