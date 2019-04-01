package com.fanwe.g419.model;

import com.fanwe.library.common.SDSelectManager;

/**
 * Created by Administrator on 2017/5/15.
 */

public class HomeTabTitleModel implements SDSelectManager.Selectable
{
    private int classified_id;
    private boolean selected;
    private String title;

    private String classified_url;

    public String getClassified_url() {
        return classified_url;
    }

    public void setClassified_url(String classified_url) {
        this.classified_url = classified_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getClassified_id() {
        return classified_id;
    }

    public void setClassified_id(int classified_id) {
        this.classified_id = classified_id;
    }


    @Override
    public boolean isSelected()
    {
        return selected;
    }

    @Override
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
}
