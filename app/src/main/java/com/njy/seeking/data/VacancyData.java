package com.njy.seeking.data;

import com.njy.seeking.model.Vacancy;

import java.util.ArrayList;

/**
 * Created by naufalprakoso on 12/05/18.
 */

public class VacancyData {

    public static String[][] data = new String[][]{
            {"Software Engineer", "Fulltime", "Tokopedia Tower", "5", "Rp 10.000.000",
                    "Minimal 3 years in Java", "Java Android", "-", "", "Improve Tokopedia app"},
            {"UX/UI", "Fulltime", "Tokopedia Tower", "2", "Rp 12.000.000",
                    "Build 10 awesome UI/UX projects", "Design", "-", "", "Improve Tokopedia app"}
    };

    public static ArrayList<Vacancy> getList(){
        Vacancy vacancy = null;
        ArrayList<Vacancy> list = new ArrayList<>();

        for (int i = 0; i < data.length; i++){
            vacancy = new Vacancy();
            vacancy.setPosition(data[i][0]);
            vacancy.setType(data[i][1]);
            vacancy.setLocation(data[i][2]);
            vacancy.setSeat(data[i][3]);
            vacancy.setSalary(data[i][4]);
            vacancy.setExperience(data[i][5]);
            vacancy.setLanguage(data[i][6]);
            vacancy.setCertification(data[i][7]);
            vacancy.setAdditional(data[i][8]);
            vacancy.setDescription(data[i][9]);

            list.add(vacancy);
        }

        return list;
    }
}
