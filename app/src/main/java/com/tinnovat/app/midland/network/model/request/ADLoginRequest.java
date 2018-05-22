package com.tinnovat.app.midland.network.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by ADMIN on 5/11/2018.
 */

@Root(name = "_0:ADLoginRequest", strict = false)
public class ADLoginRequest {

    @Element(name = "_0:user", required = false)
    private String user;

    @Element(name = "_0:pass", required = false)
    private String pass;

    @Element(name = "_0:lang", required = false)
    private String lang;

    @Element(name = "_0:ClientID", required = false)
    private int ClientID;

    @Element(name = "_0:RoleID", required = false)
    private int RoleID;

    @Element(name = "_0:OrgID", required = false)
    private int OrgID;

    @Element(name = "_0:WarehouseID", required = false)
    private int WarehouseID;

    @Element(name = "_0:stage", required = false)
    private int stage;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public int getOrgID() {
        return OrgID;
    }

    public void setOrgID(int orgID) {
        OrgID = orgID;
    }

    public int getWarehouseID() {
        return WarehouseID;
    }

    public void setWarehouseID(int warehouseID) {
        WarehouseID = warehouseID;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
}
