package com.lucas.tutorialapp;

/**
 * Created by Lucas on 09/10/2015.
 */
public class tutoSteps {

    private int _id;
    private String _layout;
    private int _step;

    public tutoSteps() {

    }

    public tutoSteps(int id, String layout, int step) {
        this._id = id;
        this._layout = layout;
        this._step = step;
    }

    public tutoSteps(String layout, int step) {
        this._layout = layout;
        this._step = step;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setLayout(String layout) {
        this._layout = layout;
    }

    public String getLayout() {
        return this._layout;
    }

    public void setStep(int step) {
        this._step = step;
    }

    public int getStep() {
        return this._step;
    }

}
