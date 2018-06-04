package com.example.german.copaconfederaciones.models.get;

/**
 * Created by German on 3/6/2018.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("sections")
    @Expose
    private List<Section> sections = null;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
