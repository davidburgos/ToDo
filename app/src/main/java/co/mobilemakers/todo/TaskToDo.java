package co.mobilemakers.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by David on 05/02/2015.
 */
public class TaskToDo {

    String mDescription;
    Date mCreation;
    Boolean mDone;

    public TaskToDo(String mDescription) {
        this.mDescription = mDescription;
        this.mDone = false;
        this.mCreation = new Date();
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Date getmCreation() {
        return mCreation;
    }

    public void setmCreation(Date mCreation) {
        this.mCreation = mCreation;
    }

    public Boolean getmDone() {
        return mDone;
    }

    public void setmDone(Boolean mDone) {
        this.mDone = mDone;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateAndTime = sdf.format(new Date());
        return mDescription + " " + (mDone?"(Done)":"") +" Created:" + currentDateAndTime;
    }
}
