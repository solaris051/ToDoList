package com.culturall.todolist;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import java.util.ArrayList;


public class ToDoListActivity extends ActionBarActivity implements NewItemFragment.OnNewItemAddedListener {

    private ArrayAdapter<String> aa;
    private ArrayList<String> toDoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inflate the View
        setContentView(R.layout.activity_to_do_list);

        //get references to the fragments
        FragmentManager fm = getFragmentManager();
        ToDoListFragment toDoListFragment = (ToDoListFragment) fm.findFragmentById(R.id.TodoListFragment);

        //create the ArrayList of to do items
        final ArrayList<String> toDoItems = new ArrayList<String>();

        //create the ArrayAdapter to bind the Array to the ListView
//        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toDoItems);
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.todolist_item_custom, toDoItems);

        //bind the ArrayAdapter to the ListView
        toDoListFragment.setListAdapter(aa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_to_do_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNewItemAdded(String newItem) {
        toDoItems.add(newItem);
        aa.notifyDataSetChanged();
    }
}
