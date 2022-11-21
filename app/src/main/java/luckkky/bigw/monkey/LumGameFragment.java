package luckkky.bigw.monkey;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

import io.michaelrocks.paranoid.Obfuscate;
import luckkky.bigw.monkey.databinding.FragmentLumGameBinding;
@Obfuscate
public class LumGameFragment extends Fragment {

    private FragmentLumGameBinding binding;

    private static final int START_BET = 100;

    private int bet = START_BET, balance;

    private boolean gameRunning = false;

    private ImageView[] wheelItems;

    private int betCount = 0;

    private SharedPreferences shar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        binding = FragmentLumGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shar = Objects.requireNonNull(getActivity()).getSharedPreferences("shar", Context.MODE_PRIVATE);

        balance = shar.getInt("shar", 15000);

        binding.tvScore.setText(String.valueOf(balance));
        binding.tvBetValue.setText(String.valueOf(bet));

        wheelItems = new ImageView[]{binding.ivSlot1, binding.ivSlot2, binding.ivSlot3, binding.ivSlot4, binding.ivSlot5, binding.ivSlot6,
                binding.ivSlot7, binding.ivSlot8, binding.ivSlot9, binding.ivSlot10, binding.ivSlot11,
                binding.ivSlot12, binding.ivSlot13, binding.ivSlot14, binding.ivSlot15};


        binding.bSpin.setOnClickListener(v -> {
            if (!gameRunning) {
                startGame();
            }
        });

        binding.ivUp.setOnClickListener(v -> upgradeBetValue(true));
        binding.ivDown.setOnClickListener(v -> upgradeBetValue(false));

    }

    private void upgradeBetValue(boolean isChangeButtonPressed){

        int[] smbBetValue = new int[] {100, 200, 300, 400, 500};

        try{
            if (isChangeButtonPressed && (betCount + 1) > smbBetValue.length) {
                betCount = 0;
                return;
            }
            if (!isChangeButtonPressed && betCount - 1 < 0) {
                betCount = 4;
                return;
            }

            if (isChangeButtonPressed) {
                betCount++;
            } else {
                betCount--;
            }

            binding.tvBetValue.setText(String.valueOf(smbBetValue[betCount]));
            bet = smbBetValue[betCount];

        } catch (Exception ignored){}

    }

    public void changeScore(int value){

        if(balance < bet){
            Toast.makeText(getContext(), "You loose too many points. But i will help you!", Toast.LENGTH_SHORT).show();
            balance += 5000;
        } else {
            balance += value;
        }

        shar.edit()
                .putInt("shar", balance)
                .apply();

        binding.tvScore.setText(String.valueOf(balance));

    }



    public void startGame() {
        if (!gameRunning) {
            gameRunning = true;
            binding.bSpin.setEnabled(false);
            new CountDownTimer(2500, 100) {
                @Override
                public void onTick(long l) {
                    int[] imageSlot = {R.drawable.lum_1, R.drawable.lum_2, R.drawable.lum_3,
                            R.drawable.lum_4, R.drawable.lum_5, R.drawable.lum_6, R.drawable.lum_7, R.drawable.lum_8,
                            R.drawable.lum_9};
                    for (ImageView item : wheelItems) {
                        int random = (int) (Math.random() * 9);
                        item.setImageResource(imageSlot[random]);
                        item.setTag(imageSlot[random]);
                    }
                }

                @Override
                public void onFinish() {
                    gameRunning = false;
                    binding.bSpin.setEnabled(true);
                    checkSlotWinner();
                }
            }.start();
        }
    }

    private void checkSlotWinner() {

        int value;
        int lum1 = 0;
        int lum2 = 0;
        int lum3 = 0;
        int lum4 = 0;
        int lum5 = 0;
        int lum6 = 0;
        int lum7 = 0;
        int lum8 = 0;
        int lum9 = 0;

        for (ImageView wheelItem : wheelItems){

            if (wheelItem.getTag().equals(R.drawable.lum_1)) {
                lum1++;
            } else if (wheelItem.getTag().equals(R.drawable.lum_2)) {
                lum2++;
            } else if (wheelItem.getTag().equals(R.drawable.lum_3)) {
                lum3++;
            } else if (wheelItem.getTag().equals(R.drawable.lum_4)) {
                lum4++;
            } else if (wheelItem.getTag().equals(R.drawable.lum_5)) {
                lum5++;
            } else if (wheelItem.getTag().equals(R.drawable.lum_6)) {
                lum6++;
            } else if (wheelItem.getTag().equals(R.drawable.lum_7)) {
                lum7++;
            } else if (wheelItem.getTag().equals(R.drawable.lum_8)) {
                lum8++;
            } else if (wheelItem.getTag().equals(R.drawable.lum_9)) {
                lum9++;
            }

        }

        int winner;
        if (lum1 > 5 && lum1 > lum2 && lum1 > lum3 && lum1 > lum4 && lum1 > lum5 && lum1 > lum6
                && lum1 > lum7 && lum1 > lum8 && lum1 > lum9) {
            winner = 1;
        } else if (lum2 > 5 && lum2 > lum1 && lum2 > lum3 && lum2 > lum4 && lum2 > lum5 && lum2  > lum6
                && lum2  > lum7 && lum2  > lum8 && lum2  > lum9) {
            winner = 2;
        } else if (lum3 > 5 && lum3 > lum2 && lum3 > lum4 && lum3 > lum5 && lum3  > lum6
                && lum3  > lum7 && lum3  > lum8 && lum3  > lum9) {
            winner = 3;
        } else if (lum4 > 5 && lum4 > lum2 && lum4 > lum3 && lum4 > lum5 && lum4   > lum6
                && lum4   > lum7 && lum4   > lum8 && lum4   > lum9) {
            winner = 4;
        } else if (lum5 > 5 && lum5 > lum2 && lum5 > lum3 && lum5 > lum4 && lum5  > lum6
                && lum5  > lum7 && lum5  > lum8 && lum5  > lum9) {
            winner = 5;
        } else if (lum6 > 5 && lum6 > lum2 && lum6 > lum3 && lum6 > lum4 && lum6  > lum1
                && lum6  > lum7 && lum6  > lum8 && lum6  > lum9) {
            winner = 6;
        } else if (lum7 > 5 && lum7 > lum2 && lum7 > lum3 && lum7 > lum4 && lum7  > lum6
                && lum7  > lum1 && lum7  > lum8 && lum7  > lum9) {
            winner = 7;
        } else if (lum8 > 5 && lum8 > lum2 && lum8 > lum3 && lum8 > lum4 && lum8  > lum6
                && lum8  > lum7 && lum8  > lum1 && lum8  > lum9) {
            winner = 8;
        } else if (lum9 > 5 && lum9 > lum2 && lum9 > lum3 && lum9 > lum4 && lum9  > lum6
                && lum9  > lum7 && lum9  > lum8 && lum9  > lum1) {
            winner = 9;
        } else {
            winner = 0;
        }

        switch (winner) {
            case 0:
                value = -bet;
                break;
            case 1:
                value = 2;
                break;
            case 2:
                value = 3;
                break;
            case 3:
                value = 4;
                break;
            case 4:
                value = 5;
                break;
            case 5:
                value = 6;
                break;
            case 6:
                value = 7;
                break;
            case 7:
                value = 8;
                break;
            case 8:
                value = 9;
                break;
            case 9:
                value = 10;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + winner);
        }

        changeScore(value);
    }





}