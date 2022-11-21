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
import luckkky.bigw.monkey.databinding.FragmentLumMenuBinding;
@Obfuscate
public class LumMenuFragment extends Fragment {

    private FragmentLumMenuBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binding = FragmentLumMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.bLumExit.setOnClickListener(v101 -> requireActivity().finish());
        binding.bLumPriv.setOnClickListener(v102 -> Navigation.findNavController(requireView()).navigate(R.id.navigation_lumPrivacyPolicy));
        binding.bLumStart.setOnClickListener(v103 -> Navigation.findNavController(requireView()).navigate(R.id.navigation_lumGame));
    }

}