package com.getgarage.expandlist;

/**
 * Created by Rajat on 18-10-2015.
 */
public class Child
{
    private String name;
    private String text1;
    private String text2;
    private String checkedtype;
    private boolean checked;
    private Parent parent;
    public String getName()
    {
        return name;
    }
public Parent getParent(){
    return parent;
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
    public String getChildCheckedType()
    {
        return checkedtype;
    }

    public void setChildCheckedType(String checkedtype)
    {
        this.checkedtype = checkedtype;
    }


    public boolean isChildChecked()
    {
        return checked;
    }
    public void setChildChecked(boolean checked)
    {
        this.checked = checked;
    }

}