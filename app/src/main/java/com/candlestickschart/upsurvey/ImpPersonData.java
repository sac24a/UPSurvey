package com.candlestickschart.upsurvey;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class ImpPersonData {
    @PrimaryKey(autoGenerate = true)
    public int ids;
    @ColumnInfo(name = "user_id")
    public String user_id;
    @ColumnInfo(name = "user_mobile_no")
    public String user_mobile_no;
    @ColumnInfo(name = "LOC_ID")
    public String LOC_ID;

    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "Gender")
    public String Gender;
    @ColumnInfo(name = "Age")
    public String Age;
    @ColumnInfo(name = "Social_Type")
    public String Social_Type;
    @ColumnInfo(name = "Mobile")
    public String Mobile;
    @ColumnInfo(name = "Reason")
    public String Reason;
    @ColumnInfo(name = "Party")
    public String Party;
    @ColumnInfo(name = "Impression")
    public String Impression;


    public ImpPersonData(int ids,
                               String user_id,
                               String user_mobile_no,
                               String LOC_ID,
                               String Name,
                               String Gender,
                               String Age,
                               String Social_Type,
                               String Mobile,
                               String Reason, String Party,String Impression){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Social_Type = Social_Type;
        this.Impression = Impression;
        this.Age = Age;
        this.Gender = Gender;
        this.Name = Name;
        this.Mobile = Mobile;
        this.Reason = Reason;
        this.Party = Party;

    }

    @Ignore
    public ImpPersonData(String user_id,
                         String user_mobile_no,
                         String LOC_ID,
                         String Name,
                         String Gender,
                         String Age,
                         String Social_Type,
                         String Mobile,
                         String Reason, String Party,String Impression){
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Social_Type = Social_Type;
        this.Impression = Impression;
        this.Age = Age;
        this.Gender = Gender;
        this.Name = Name;
        this.Mobile = Mobile;
        this.Reason = Reason;
        this.Party = Party;

    }
}