package com.lucas.tutorialapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

/**
 * Created by Lucas on 05/10/2015.
 */
public class menu1_Fragment extends Fragment {
    View rootview;
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    CheckBox cb4;
    CheckBox cb5;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.menu1_layout,container,false);

        cb1 = (CheckBox) rootview.findViewById(R.id.checkBox1);
        cb2 = (CheckBox) rootview.findViewById(R.id.checkBox2);
        cb3 = (CheckBox) rootview.findViewById(R.id.checkBox3);
        cb4 = (CheckBox) rootview.findViewById(R.id.checkBox4);
        cb5 = (CheckBox) rootview.findViewById(R.id.checkBox5);

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                efetuaAcao(cb1,isChecked);
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                efetuaAcao(cb2,isChecked);
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                efetuaAcao(cb3,isChecked);
            }
        });

        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                efetuaAcao(cb4,isChecked);
            }
        });

        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                efetuaAcao(cb5,isChecked);
            }
        });

        lookupStep(rootview);
        return rootview;
    }

    private void efetuaAcao(CheckBox cb, boolean isChecked){

        switch (cb.getId()) {
            case R.id.checkBox1:
                if (isChecked) {
                    newStep(rootview,1);
                    cb2.setEnabled(true);
                } else {
                    newStep(rootview,0);
                    cb2.setEnabled(false);
                    cb2.setChecked(false);
                }
                break;
            case R.id.checkBox2:
                if (isChecked) {
                    newStep(rootview,2);
                    cb3.setEnabled(true);
                } else {
                    newStep(rootview,1);
                    cb3.setEnabled(false);
                    cb3.setChecked(false);
                }
                break;
            case R.id.checkBox3:
                if (isChecked) {
                    newStep(rootview,3);
                    cb4.setEnabled(true);
                } else {
                    newStep(rootview,2);
                    cb4.setEnabled(false);
                    cb4.setChecked(false);
                }
                break;
            case R.id.checkBox4:
                if (isChecked) {
                    newStep(rootview,4);
                    cb5.setEnabled(true);
                } else {
                    newStep(rootview,3);
                    cb5.setEnabled(false);
                    cb5.setChecked(false);
                }
                break;
            case R.id.checkBox5:
                if (isChecked) {
                    newStep(rootview,5);
                    Toast.makeText(rootview.getContext(), "Fim do Tutorial!", Toast.LENGTH_SHORT).show();
                } else{
                    newStep(rootview,4);
                }
                break;
        }
    }

    public void newStep (View view, Integer num) {
        MyDBHandler dbHandler = new MyDBHandler(rootview.getContext(), null, null, 1);

        int step = num;

        tutoSteps tuto =
                new tutoSteps("menu1", step);

        Toast.makeText(rootview.getContext(), String.valueOf(step), Toast.LENGTH_SHORT).show();
        dbHandler.addStep(tuto);
    }

    public void lookupStep (View view) {
        MyDBHandler dbHandler = new MyDBHandler(rootview.getContext(), null, null, 1);

        tutoSteps tuto = dbHandler.findProduct("menu1");

        if (tuto != null) {
            switch (tuto.getStep()) {
                case 0:
                    cb1.setChecked(false);
                    break;
                case 1:
                    cb1.setChecked(true);
                    break;
                case 2:
                    cb1.setChecked(true);
                    cb2.setChecked(true);
                    break;
                case 3:
                    cb1.setChecked(true);
                    cb2.setChecked(true);
                    cb3.setChecked(true);
                    break;
                case 4:
                    cb1.setChecked(true);
                    cb2.setChecked(true);
                    cb3.setChecked(true);
                    cb4.setChecked(true);
                    break;
                case 5:
                    cb1.setChecked(true);
                    cb2.setChecked(true);
                    cb3.setChecked(true);
                    cb4.setChecked(true);
                    cb5.setChecked(true);
                    break;
            }
        } else {
            Toast.makeText(rootview.getContext(), "Não há tutorial em andamento.", Toast.LENGTH_SHORT).show();
        }
    }

}