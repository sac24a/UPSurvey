package com.candlestickschart.upsurvey;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PollFirstDao {
    @Query("Select * from pollfirst")
    List<PollFirstData> getPollfirstData();
    @Query("Select Locality_type from pollfirst")
    List<String> getlocalityType();

    @Query("DELETE FROM pollfirst")
    void clearPollfirstTable();
    @Insert
    void insertPollFirst(PollFirstData pollFirstData);

    @Query("UPDATE pollfirst SET Locality_type=:locality_type, Economic=:economic WHERE LOC_ID = :locId")
    void updateScreen3(String locality_type,String economic,String locId);

    @Query("UPDATE pollfirst SET Party_leading=:leading, Reason_leading=:rason, Local_issue=:facts WHERE LOC_ID = :locId")
    void updateScreen4(String leading,String rason,String facts,String locId);

    @Query("UPDATE pollfirst SET Cond_SP=:sp, Cond_BJP=:bjp, Cond_BSP=:bsp, Cond_INC=:inc, Cond_OTH=:other WHERE LOC_ID = :locId")
    void updateScreen4_1(String sp,String bjp,String bsp,String inc,String other,String locId);

    @Query("UPDATE pollfirst SET Panchayat_analysis=:reason WHERE LOC_ID = :locId")
    void updateScreen5(String reason,String locId);

    @Query("UPDATE pollfirst SET MLA_visits=:visit, MLA_Dev_Work=:work, MLA_image=:rai WHERE LOC_ID = :locId")
    void updateScreen5_1(String visit,String rai,String work,String locId);

    @Query("UPDATE pollfirst SET Elec_hrs=:bijli, Roads=:road, Water=:water, Hospital=:hospital, School=:school WHERE LOC_ID = :locId")
    void updateScreen6(String bijli,String road,String water,String hospital,String school,String locId);

    @Query("UPDATE pollfirst SET Emp_opps=:emp_opps, Main_probs=:mainprob, Dev_needs=:devneed WHERE LOC_ID = :locId")
    void updateScreen6_1(String emp_opps,String mainprob,String devneed,String locId);


    /* Social Table*/
    @Insert
    void insertSocialImpPersion(SocialData pollFirstData);
    @Query("Select * From social")
    List<SocialData> getSocialType();
    @Query("Select Social_Type From social Where Social_Type Like:socialType")
    List<String> checkSocialData( String socialType);
    @Query("DELETE FROM social")
    void clearSocialTable();


    /* Political Worker Table*/
    @Insert
    void insertPoliticalWorker(PoliticalWorkerData pollFirstData);
    @Query("Select * From politicalworker")
    List<PoliticalWorkerData> getPoliticalWorker();
    @Query("Select Party_Name From politicalworker Where Party_Name Like:searchType")
    List<String> checkPoliticalWorker( String searchType);
    @Query("DELETE FROM politicalworker")
    void clearPoliticalWorkerTable();


    /* Imp PersonTable*/
    @Insert
    void insertImpPerson(ImpPersonData pollFirstData);
    @Query("Select Name From person")
    List<String> getImpPersonName();
    @Query("Select * From person")
    List<ImpPersonData> getImpPerson();
    @Query("DELETE FROM person")
    void clearPersonTable();

    /* Religion Table*/
    @Insert
    void insertReligion(ReligionData pollFirstData);
    @Query("Select Name From religion")
    List<String> getReligionName();
    @Query("Select * From religion")
    List<ReligionData> getReligion();
    @Query("DELETE FROM religion")
    void clearReligionTable();

    /* Social media Table*/
    @Insert
    void insertSocialMedia(SocialMediaData pollFirstData);
    @Query("Select Name From socialmedia")
    List<String> getSocialMediaName();
    @Query("Select * From socialmedia")
    List<SocialMediaData> getSocialMedia();
    @Query("DELETE FROM socialmedia")
    void clearSocialMediaTable();

    /* Booth Agent Table*/
    @Insert
    void insertBoothAgent(BoothAgent pollFirstData);
    @Query("Select Party From boothagent Where Party Like:searchType")
    List<String> checkBoothAgent( String searchType);
    @Query("Select * From boothagent")
    List<BoothAgent> getBoothAgent();
    @Query("DELETE FROM boothagent")
    void clearBoothAgentTable();

    /* Employee Table*/
    @Insert
    void insertEmployee(EmployeeData pollFirstData);
    @Query("Select Name From employee")
    List<String> getEmployeeName();
    @Query("Select * From employee")
    List<EmployeeData> getEmployee();
    @Query("DELETE FROM employee")
    void clearEmployeeTable();


    @Delete
    void deletePollFirst(PollFirstData pollFirstData);
}
