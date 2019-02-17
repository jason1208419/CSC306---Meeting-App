package com.example.jason.csc306.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jason.csc306.R;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    TextView textView11;
    EditText colour_code;
    Switch colour_switch, size_switch;
    Button colour_pre, maroon, olive, navy, red, green, blue, purple, fuchsia, teal, yellow, lime, aqua, size_pre;
    Spinner font_size;
    private static final String MAROON = "#800000";
    private static final String OLIVE = "#808000";
    private static final String NAVY = "#000080";
    private static final String RED = "#FF0000";
    private static final String GREEN = "#008000";
    private static final String BLUE = "#0000FF";
    private static final String PURPLE = "#800080";
    private static final String FUCHSIA = "#ff00ff";
    private static final String TEAL = "#008080";
    private static final String YELLOW = "#FFFF00";
    private static final String LIME = "#00ff00";
    private static final String AQUA = "#00FFFF";
    private static final String DEFAULT_COLOUR = "#000000";
    private static final int DEFAULT_SIZE = 22;
    private static final int SIZE_A = 18;
    private static final int SIZE_B = 20;
    private static final int SIZE_C = 24;
    private static final int SIZE_D = 26;
    private static final int SIZE_E = 28;
    private static final int SIZE_F = 30;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        colour_switch = view.findViewById(R.id.colour_switch);
        textView11 = view.findViewById(R.id.textView11);
        colour_code = view.findViewById(R.id.colour_code);
        colour_pre = view.findViewById(R.id.colour_pre);
        maroon = view.findViewById(R.id.maroon);
        olive = view.findViewById(R.id.olive);
        navy = view.findViewById(R.id.navy);
        red = view.findViewById(R.id.red);
        green = view.findViewById(R.id.green);
        blue = view.findViewById(R.id.blue);
        purple = view.findViewById(R.id.purple);
        fuchsia = view.findViewById(R.id.fuchsia);
        teal = view.findViewById(R.id.teal);
        yellow = view.findViewById(R.id.yellow);
        lime = view.findViewById(R.id.lime);
        aqua = view.findViewById(R.id.aqua);
        size_pre = view.findViewById(R.id.size_pre);
        size_switch = view.findViewById(R.id.size_switch);
        font_size = view.findViewById(R.id.font_size);

        colour_switch.setOnClickListener(this);
        colour_pre.setOnClickListener(this);
        maroon.setOnClickListener(this);
        olive.setOnClickListener(this);
        navy.setOnClickListener(this);
        red.setOnClickListener(this);
        green.setOnClickListener(this);
        blue.setOnClickListener(this);
        purple.setOnClickListener(this);
        fuchsia.setOnClickListener(this);
        teal.setOnClickListener(this);
        yellow.setOnClickListener(this);
        lime.setOnClickListener(this);
        aqua.setOnClickListener(this);
        size_switch.setOnClickListener(this);
        size_pre.setOnClickListener(this);

        String[] sizes = new String[] {"18", "20", "24", "26", "28", "30"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, sizes);
        font_size.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Settings");
    }

    @Override
    public void onClick(View v) {
        if (v == colour_switch) {
            if (!colour_switch.isChecked()){
                store(Color.parseColor(DEFAULT_COLOUR), getActivity());
                Intent intent = new Intent();
                getActivity().setResult(Activity.RESULT_OK, intent);
                Toast.makeText(getActivity(), "Text Colour Set To Default", Toast.LENGTH_LONG).show();
            }
        } else if (v == colour_pre) {
            String hex = "#" + colour_code.getText().toString();
            store(Color.parseColor(hex), getActivity());
            colour_pre.setTextColor(Color.parseColor(hex));
            colour_code.setText("");
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To " + hex, Toast.LENGTH_LONG).show();
        } else if (v == maroon) {
            store(Color.parseColor(MAROON), getActivity());
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To Maroon", Toast.LENGTH_LONG).show();
        } else if (v == olive) {
            store(Color.parseColor(OLIVE), getActivity());
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To Olive", Toast.LENGTH_LONG).show();
        } else if (v == navy) {
            store(Color.parseColor(NAVY), getActivity());
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To Navy", Toast.LENGTH_LONG).show();
        } else if (v == red) {
            store(Color.parseColor(RED), getActivity());
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To Red", Toast.LENGTH_LONG).show();
        } else if (v == green) {
            store(Color.parseColor(GREEN), getActivity());
            Intent intent = new Intent();
            Toast.makeText(getActivity(), "Text Colour Set To Green", Toast.LENGTH_LONG).show();
        } else if (v == blue) {
            store(Color.parseColor(BLUE), getActivity());
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To Blue", Toast.LENGTH_LONG).show();
        } else if (v == purple) {
            store(Color.parseColor(PURPLE), getActivity());
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To Purple", Toast.LENGTH_LONG).show();
        } else if (v == fuchsia) {
            store(Color.parseColor(FUCHSIA), getActivity());
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To Fuchsia", Toast.LENGTH_LONG).show();
        } else if (v == teal) {
            store(Color.parseColor(TEAL), getActivity());
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To Teal", Toast.LENGTH_LONG).show();
        } else if (v == yellow) {
            store(Color.parseColor(YELLOW), getActivity());
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To Yellow", Toast.LENGTH_LONG).show();
        } else if (v == lime) {
            store(Color.parseColor(LIME), getActivity());
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To Lime", Toast.LENGTH_LONG).show();
        } else if (v == aqua) {
            store(Color.parseColor(AQUA), getActivity());
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Text Colour Set To Aqua", Toast.LENGTH_LONG).show();
        } else if (v == size_switch) {
            if (!size_switch.isChecked()){
                storeSize(DEFAULT_SIZE, getActivity());
                Intent intent = new Intent();
                getActivity().setResult(Activity.RESULT_OK, intent);
                Toast.makeText(getActivity(), "Font Size Set To 22", Toast.LENGTH_LONG).show();
            } else {
                font_size.setVisibility(View.VISIBLE);
                size_pre.setVisibility(View.VISIBLE);
            }
        } else if (v == size_pre) {
            String selected = font_size.getSelectedItem().toString();
            int size = Integer.parseInt(selected);
            storeSize(size, getActivity());
            size_pre.setTextSize(size);
            Intent intent = new Intent();
            getActivity().setResult(Activity.RESULT_OK, intent);
            Toast.makeText(getActivity(), "Font Size Set To " + selected, Toast.LENGTH_LONG).show();
        }
    }

    public static void store(int color, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("color", color);
        editor.apply();
    }

    public static int getColor(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Setting", MODE_PRIVATE);
        return preferences.getInt("color", Color.parseColor(DEFAULT_COLOUR));
    }

    public static void storeSize(int fontSize, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Setting", MODE_PRIVATE);
        preferences.edit().putFloat("size", fontSize).apply();
    }

    public static float getSize(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("Setting", MODE_PRIVATE);
        // default as normal size
        float selector = preferences.getFloat("size", DEFAULT_SIZE);
        return selector;
    }
}
