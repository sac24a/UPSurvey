package com.candlestickschart.upsurvey;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pollfirst")
public class PollFirstData {
    @PrimaryKey(autoGenerate = true)
    public int ids;
    @ColumnInfo(name = "user_id")
    public String user_id;
    @ColumnInfo(name = "user_mobile_no")
    public String user_mobile_no;
    @ColumnInfo(name = "LOC_ID")
    public String LOC_ID;
    @ColumnInfo(name = "VS_No")
    public String VS_No;
    @ColumnInfo(name = "Locality_name")
    public String Locality_name;
    @ColumnInfo(name = "Locality_type")
    public String Locality_type;
    @ColumnInfo(name = "Economic")
    public String Economic;
    @ColumnInfo(name = "Party_leading")
    public String Party_leading;
    @ColumnInfo(name = "Reason_leading")
    public String Reason_leading;
    @ColumnInfo(name = "Local_issue")
    public String Local_issue;
    @ColumnInfo(name = "Panchayat_analysis")
    public String Panchayat_analysis;
    @ColumnInfo(name = "Cond_SP")
    public String Cond_SP;
    @ColumnInfo(name = "Cond_BJP")
    public String Cond_BJP;
    @ColumnInfo(name = "Cond_BSP")
    public String Cond_BSP;
    @ColumnInfo(name = "Cond_INC")
    public String Cond_INC;
    @ColumnInfo(name = "Cond_OTH")
    public String Cond_OTH;
    @ColumnInfo(name = "MLA_visits")
    public String MLA_visits;
    @ColumnInfo(name = "MLA_image")
    public String MLA_image;
    @ColumnInfo(name = "MLA_Dev_Work")
    public String MLA_Dev_Work;
    @ColumnInfo(name = "Elec_hrs")
    public String Elec_hrs;
    @ColumnInfo(name = "Roads")
    public String Roads;
    @ColumnInfo(name = "Water")
    public String Water;
    @ColumnInfo(name = "Hospital")
    public String Hospital;
    @ColumnInfo(name = "School")
    public String School;
    @ColumnInfo(name = "Emp_opps")
    public String Emp_opps;
    @ColumnInfo(name = "Main_probs")
    public String Main_probs;
    @ColumnInfo(name = "Dev_needs")
    public String Dev_needs;
    public PollFirstData(int ids,
                         String user_id,
                         String user_mobile_no,
                         String LOC_ID,
                         String VS_No,
                         String Locality_name,
                         String Locality_type,
                         String Economic,
                         String Party_leading,
                         String Reason_leading,
                         String Local_issue,
                         String Cond_SP,
                         String Cond_BJP,
                         String Cond_BSP,
                         String Cond_INC,
                         String Cond_OTH,
                         String Panchayat_analysis,

                         String MLA_visits,
                         String MLA_image,
                         String MLA_Dev_Work,
                         String Elec_hrs,
                         String Roads,
                         String Water,
                         String Hospital,
                         String School,
                         String Emp_opps,
                         String Main_probs,
                         String Dev_needs){
        this.ids = ids;
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Dev_needs = Dev_needs;
        this.Main_probs = Main_probs;
        this.Emp_opps = Emp_opps;
        this.School = School;
        this.Hospital = Hospital;
        this.Water = Water;
        this.Roads = Roads;
        this.Elec_hrs = Elec_hrs;
        this.VS_No = VS_No;
        this.Locality_name = Locality_name;
        this.Locality_type = Locality_type;
        this.Economic = Economic;
        this.Party_leading = Party_leading;
        this.Reason_leading = Reason_leading;
        this.Local_issue = Local_issue;
        this.Panchayat_analysis = Panchayat_analysis;
        this.Cond_BJP= Cond_BJP;
        this.Cond_BSP = Cond_BSP;
        this.Cond_INC = Cond_INC;
        this.Cond_OTH = Cond_OTH;
        this.Cond_SP = Cond_SP;
        this.MLA_visits = MLA_visits;
        this.MLA_Dev_Work = MLA_Dev_Work;
        this.MLA_image = MLA_image;


    }

    @Ignore
    public PollFirstData(String user_id,
                         String user_mobile_no,
                         String LOC_ID,
                         String VS_No,
                         String Locality_name,
                         String Locality_type,
                         String Economic,
                         String Party_leading,
                         String Reason_leading,
                         String Local_issue,
                         String Panchayat_analysis,
                         String Cond_SP,
                         String Cond_BJP,
                         String Cond_BSP,
                         String Cond_INC,
                         String Cond_OTH,
                         String MLA_visits,
                         String MLA_image,
                         String MLA_Dev_Work,
                         String Elec_hrs,
                         String Roads,
                         String Water,
                         String Hospital,
                         String School,
                         String Emp_opps,
                         String Main_probs,
                         String Dev_needs){
        this.user_id = user_id;
        this.user_mobile_no = user_mobile_no;
        this.LOC_ID = LOC_ID;
        this.Dev_needs = Dev_needs;
        this.Main_probs = Main_probs;
        this.Emp_opps = Emp_opps;
        this.School = School;
        this.Hospital = Hospital;
        this.Water = Water;
        this.Roads = Roads;
        this.Elec_hrs = Elec_hrs;
        this.VS_No = VS_No;
        this.Locality_name = Locality_name;
        this.Locality_type = Locality_type;
        this.Economic = Economic;
        this.Party_leading = Party_leading;
        this.Reason_leading = Reason_leading;
        this.Local_issue = Local_issue;
        this.Panchayat_analysis = Panchayat_analysis;
        this.Cond_BJP= Cond_BJP;
        this.Cond_BSP = Cond_BSP;
        this.Cond_INC = Cond_INC;
        this.Cond_OTH = Cond_OTH;
        this.Cond_SP = Cond_SP;
        this.MLA_visits = MLA_visits;
        this.MLA_Dev_Work = MLA_Dev_Work;
        this.MLA_image = MLA_image;

    }
}
