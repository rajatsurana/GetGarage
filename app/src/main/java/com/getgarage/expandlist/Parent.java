package com.getgarage.expandlist;

/**
 * Created by Rajat on 18-10-2015.
 */
import java.util.ArrayList;

public class Parent
{
    private String name;
    private String text1;
    private String text2;
    private String checkedtype;
    private boolean checked;

    // ArrayList to store child objects
    private ArrayList<Child> children;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getText1()
    {
        return text1;
    }

    public void setText1(String text1)
    {
        this.text1 = text1;
    }

    public String getText2()
    {
        return text2;
    }

    public void setText2(String text2)
    {
        this.text2 = text2;
    }
    public String getCheckedType()
    {
        return checkedtype;
    }

    public void setCheckedType(String checkedtype)
    {
        this.checkedtype = checkedtype;
    }


    public boolean isChecked()
    {
        return checked;
    }
    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    // ArrayList to store child objects
    public ArrayList<Child> getChildren()
    {
        return children;
    }

    public void setChildren(ArrayList<Child> children)
    {
        this.children = children;
    }
}
