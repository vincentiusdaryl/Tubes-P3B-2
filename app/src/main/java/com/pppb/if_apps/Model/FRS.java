package com.pppb.if_apps.Model;

public class FRS {
    private String active_years;
    private int[] academic_years;

    public FRS(String active_years, int[] academic_years){
        this.active_years = active_years;
        this.academic_years = academic_years;
    }

    public String getActive_years() {
        return active_years;
    }

    public void setActive_years(String academic_years) {
        this.active_years = active_years;
    }

    public int[] getAcademic_years() {
        return academic_years;
    }

    public void setAcademic_years(int[] academic_years) {
        this.academic_years  = academic_years;
    }

}


