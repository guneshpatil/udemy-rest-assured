package utils;

import data.Constants;

public enum MapApiResources {

    AddPlaceUrl(Constants.URL_ADD_PLACE),
    GetPlaceUrl(Constants.URL_GET_PLACE),
    UpdatePlaceUrl(Constants.URL_UPDATE_PLACE);

    private String resource;

    MapApiResources(String url) {
        this.resource = url;
    }

    public String getResource(){
        return resource;
    }
}
