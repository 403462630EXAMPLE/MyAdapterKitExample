package com.example.rjhy.modul;

import com.example.rjhy.adapter_kit_text.R;
import com.mobsandgeeks.adapters.InstantText;

/**
 * Created by rjhy on 14-8-15.
 */
public class Body {
    private String name;
    private Boolean isChecked;

    @InstantText(viewId = R.id.text1)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
//    非TextView控件建议不要用注解
//    @InstantText(viewId = R.id.checkbox) //不建议
    public Boolean getIsChecked() {
        return isChecked;
    }
    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

}
