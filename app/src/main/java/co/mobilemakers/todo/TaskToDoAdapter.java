package co.mobilemakers.todo;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class TaskToDoAdapter extends BaseAdapter{

    private final Context mContext;
    private List<TaskToDo> mTaskToDo = Collections.emptyList();

    public TaskToDoAdapter(Context context, List<TaskToDo> taskToDos) {
        this.mContext = context;
        this.mTaskToDo = taskToDos;
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

    public void RefreshData(){
        this.notifyDataSetChanged();
    }
    private void displayContentInView(final int position, View rowView) {
        if (rowView != null) {

            TextView textViewDescription = (TextView)rowView.findViewById(R.id.text_view_task_name);
            textViewDescription.setText(mTaskToDo.get(position).getmDescription());

            TextView textViewDate =(TextView)rowView.findViewById(R.id.text_view_date);
            textViewDate.setText(mTaskToDo.get(position).getmCreationDate());

            CheckBox checkboxDone =(CheckBox)rowView.findViewById(R.id.checkBox_done);
            checkboxDone.setChecked(mTaskToDo.get(position).getmDone());
            checkboxDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        SimpleDateFormat sdf = new SimpleDateFormat(TaskToDo.DATE_FORMAT);
                        mTaskToDo.get(position).setmCreationDate(sdf.format(new Date()));

                    }else{
                        Toast.makeText(buttonView.getContext(), String.format(mContext.getString(R.string.task_uncompleted),mTaskToDo.get(position).getmDescription()), Toast.LENGTH_SHORT).show();
                        mTaskToDo.get(position).setmCreationDate("");
                    }
                    mTaskToDo.get(position).setmDone(isChecked);
                    RefreshData();

                }
            });

        }
    }
}
