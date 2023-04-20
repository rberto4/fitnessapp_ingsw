package com.ingsw.fitnessapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingsw.fitnessapp.R;


public class FragmentEsercizi extends Fragment {


    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_esercizi, container, false);

        recyclerView = v.findViewById(R.id.id_rv_esercizi);



        return v;
    }
}