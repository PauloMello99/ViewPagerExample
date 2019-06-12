package com.paulomello.avaliacaoviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuestionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        TextView textView = view.findViewById(R.id.text_test);
        Bundle bundle = getArguments();
        String text = null;
        if (bundle != null) text = bundle.getString("TEXT");
        textView.setText(text);
        return view;
    }
}
