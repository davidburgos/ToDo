package co.mobilemakers.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class toDoListFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {

    private final static int REQUEST_CODE = 0;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TaskToDoAdapter mAdapter;
    List<TaskToDo> mEntries;

    public toDoListFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareListView();
    }

    private void prepareListView() {
        mEntries = new ArrayList<>();
        mAdapter = new TaskToDoAdapter(getActivity(), mEntries);
        setListAdapter(mAdapter);
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"Edit task in next version!",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_todo_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Boolean handled = false;

        switch (id){
            case R.id.action_add:
                Intent createTaskActivity = new Intent(getActivity(), CreateTaskActivity.class);
                startActivityForResult(createTaskActivity, REQUEST_CODE);
                handled = true;
                break;
        }

        if(!handled){
            handled = super.onOptionsItemSelected(item);
        }
        return handled;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case Activity.RESULT_OK:
                createNewTask(data.getStringExtra(CreateTaskActivity.NEW_TASK_DESCRIPTION));
                break;
            case Activity.RESULT_CANCELED:
                Toast.makeText(getActivity(), R.string.canceled_message, Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void createNewTask(String title) {
        TaskToDo taskToDo = new TaskToDo(title);
        mEntries.add(taskToDo);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        return rootView;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        Toast.makeText(getActivity(),"loading...",Toast.LENGTH_SHORT).show();
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
