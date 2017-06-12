package com.como.comolake.dto;

import android.app.Fragment;

public class FragmentStack {
    private String id;
    private String title;
    private boolean showMenu, showBack, showBackCalendar, showSearch, showSearchBox;

    public FragmentStack() {

    }

    public FragmentStack(String title, boolean showMenu, boolean showBack, boolean showBackCalendar, boolean showSearch) {
        this.title = title;
        this.showMenu = showMenu;
        this.showBack = showBack;
        this.showBackCalendar = showBackCalendar;
        this.showSearch = showSearch;
        this.showSearchBox = false;
    }

    public FragmentStack(String title, boolean showMenu, boolean showBack, boolean showBackCalendar, boolean showSearch, boolean showSearchBox) {
        this.title = title;
        this.showMenu = showMenu;
        this.showBack = showBack;
        this.showBackCalendar = showBackCalendar;
        this.showSearch = showSearch;
        this.showSearchBox = showSearchBox;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShowMenu() {
        return showMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public boolean isShowBack() {
        return showBack;
    }

    public void setShowBack(boolean showBack) {
        this.showBack = showBack;
    }

    public boolean isShowBackCalendar() {
        return showBackCalendar;
    }

    public void setShowBackCalendar(boolean showBackCalendar) {
        this.showBackCalendar = showBackCalendar;
    }

    public boolean isShowSearch() {
        return showSearch;
    }

    public void setShowSearch(boolean showSearch) {
        this.showSearch = showSearch;
    }

    public boolean isShowSearchBox() {
        return showSearchBox;
    }

    public void setShowSearchBox(boolean showSearchBox) {
        this.showSearchBox = showSearchBox;
    }
}
