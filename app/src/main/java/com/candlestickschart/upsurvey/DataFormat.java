package com.candlestickschart.upsurvey;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

class DataFormat {
    @PrimaryKey(autoGenerate = true)
    int ids;

    String user_id;

    String user_mobile_no;

    String LOC_ID;

    String VS_No;

    String Locality_name;

    String Locality_type;

    String Economic;

    String Party_leading;

    String Reason_leading;

    String Local_issue;

    String Panchayat_analysis;

    String Cond_SP;

    String Cond_BJP;

    String Cond_BSP;

    String Cond_INC;

    String Cond_OTH;

    String MLA_visits;

    String MLA_image;

    String MLA_Dev_Work;

    String Elec_hrs;

    String Roads;

    String Water;
    @ColumnInfo(name = "Hospital")
    String Hospital;

    String School;

    String Emp_opps;

    String Main_probs;

    String Dev_needs;

    String Brahmin_Votes;

    String Brahmin_Imp_per;

    String Brahmin_Gen;

    String Brahmin_Age;

    String Brahmin_Mobile;

    String Brahmin_Leaning;
}
