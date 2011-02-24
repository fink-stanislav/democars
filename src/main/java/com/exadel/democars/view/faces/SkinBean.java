package com.exadel.democars.view.faces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "skinBean")
@SessionScoped
public class SkinBean implements Serializable {
    private String skin;

    public String getSkin() {
        if (skin == null) {
            skin = "blueSky";
        }
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
