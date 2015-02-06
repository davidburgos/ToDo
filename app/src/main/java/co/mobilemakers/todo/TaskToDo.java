package co.mobilemakers.todo;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TaskToDo {

    public final static String DATE_FORMAT ="MMMM dd yyyy - HH:mm";
    String mDescription;
    String mCreationDate;
    Boolean mDone;

    public TaskToDo(String mDescription) {
        this.mDescription = mDescription;
        this.mDone = false;
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        this.mCreationDate = "";
    }

    public void setmCreationDate(String mCreationDate) {
        this.mCreationDate = mCreationDate;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmCreationDate() {
        return mCreationDate;
    }

    public Boolean getmDone() {
        return mDone;
    }

    public void setmDone(Boolean mDone) {
        this.mDone = mDone;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String currentDateAndTime = sdf.format(new Date());
        return mDescription + " " + (mDone?"(Done)":"") +" Created:" + currentDateAndTime;
    }
}
