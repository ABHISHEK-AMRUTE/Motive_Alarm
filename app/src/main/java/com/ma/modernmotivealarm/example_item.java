package com.ma.modernmotivealarm;

public class example_item {
    private String name_of_alarm;
    private String  path_offline,path_online;
    private int id;
    int hour ,minute;
    int state;////////0 off and 1 on
        int mode =1;///1 online  0 offline
    public example_item(int h, int m, String text1e, int state_mode,int id1,String text2re,String text2el,int state1)
    {
       hour= h;
       minute=m;
        name_of_alarm=text1e;
        mode=state_mode;
        id=id1;
        path_online =text2re;
        path_offline=text2el;
        state=state1;

    }
    public int getState()
    {return  state;}
    public void setState(int i)
    {state=i;}
    public void setid(int idset)
    {
        id=idset;
    }
    public int getId()
    {
        return  id;
    }
    public String getPath_offline()
    {
        return  path_offline;
    }
    public String getPath_online()
    {
        return  path_online;
    }
    public int gethour()
    {
        return  hour;
    }
    public int getminute()
    {
        return  minute;
    }
    public String gettext1()
    {
        return  name_of_alarm;
    }
    public int gettext2()
    {
        return  mode;
    }
}
