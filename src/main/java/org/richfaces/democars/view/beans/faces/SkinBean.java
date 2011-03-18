package org.richfaces.democars.view.beans.faces;

import org.richfaces.democars.application.PropertyManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

import static org.richfaces.democars.application.PropertyManager.getPropertyManager;

@ManagedBean(name = "skinBean")
@SessionScoped
public class SkinBean implements Serializable {
    private String skin;

    public String getSkin() {
        if (skin == null) {
            skin = getPropertyManager().getProperty("faces.default.skin");
        }
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }
}
