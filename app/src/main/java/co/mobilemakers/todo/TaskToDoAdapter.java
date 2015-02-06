package co.mobilemakers.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by David on 05/02/2015.
 */
public class TaskToDoAdapter extends BaseAdapter{

    private final Context mContext;
    private List<TaskToDo> mTaskToDo = Collections.emptyList();

    public TaskToDoAdapter(Context context, List<TaskToDo> taskToDos) {
        this.mContext = context;
        this.mTaskToDo = taskToDos;
    }

    public void updateTaskToDo(List<TaskToDo> mTaskToDo){
        this.mTaskToDo = mTaskToDo;
        notifyDataSetChanged();
    }

    private View reuseOrGenerateRowView(View convertView, ViewGroup parent) {
        View rowView;
        if (convertView != null) {
            rowView = convertView;
        } else {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item_entry, parent, false);
        }
        return rowView;
    }

    private void displayContentInView(int position, View rowView) {
        if (rowView != null) {

            TextView textViewDescription = (TextView)rowView.findViewById(R.id.text_view_task_name);
            textViewDescription.setText(mTaskToDo.get(position).getmDescription());

            TextView textViewDate =(TextView)rowView.findViewById(R.id.text_view_date);
            textViewDate.setText(mTaskToDo.get(position).getmCreation().toString());

            CheckBox textViewDone =(CheckBox)rowView.findViewById(R.id.checkBox_done);
            textViewDone.setChecked(mTaskToDo.get(position).getmDone());
        }
    }

    @Override
    public int getCount() {
        return mTaskToDo.size();
    }

    @Override
    public Object getItem(int position) {
        return mTaskToDo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = reuseOrGenerateRowView(convertView, parent);
        displayContentInView(position, rowView);
        return rowView;
    }
}
