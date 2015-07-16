package com.culturall.todolist;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by kyshynevskyi on 13.07.2015.
 */
public class NewItemFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_item_fragment, container, false);

        final EditText myEditText = (EditText) view.findViewById(R.id.myEditText);

        //create OnKeyListener for the TextEdit
        View.OnKeyListener editTextOnKeyListenter = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (    event.getAction() == KeyEvent.ACTION_DOWN
                    &&  (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String newItem = myEditText.getText().toString();
                    //trigger the method, which the containing activity will implement
                    onNewItemAddedListener.onNewItemAdded(newItem);
                    myEditText.setText("");
                    return true;
                }
                return false;
            }
        };

        //bind the OnKeyListener to myEditText
        myEditText.setOnKeyListener(editTextOnKeyListenter);
        return view;
    }

    //Listener to inform the parent Activity about the fact, that new item has been creted in this Fragment
    public interface OnNewItemAddedListener {
        public void onNewItemAdded(String newItem );
    }

    //a variable to store a reference to the paret ToDoListActivity
    private OnNewItemAddedListener onNewItemAddedListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onNewItemAddedListener = (OnNewItemAddedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                " must implement OnNewItemAddedListener");
        }
    }
}
