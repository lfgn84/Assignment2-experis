package se.experis.assignment2experis.models;

public class ViewSearch {
    private String searchParam;

    public ViewSearch(String searchParam) {
        this.searchParam = searchParam;
    }

    public ViewSearch(){}

    public String getSearchParam() {
        return searchParam;
    }

    public void setSearchParam(String searchParam) {
        this.searchParam = searchParam;
    }
}
