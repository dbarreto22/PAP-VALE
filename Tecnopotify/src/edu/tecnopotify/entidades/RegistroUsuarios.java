/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tecnopotify.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author root
 */

@Entity
public class RegistroUsuarios implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    
    private String ip;
    private String url;
    private String browser;
  

    public RegistroUsuarios() {
    }

    public RegistroUsuarios( String ip, String url, String browser) {
        this.ip = ip;
        this.url = url;
        this.browser = browser;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }



    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + (this.ip != null ? this.ip.hashCode() : 0);
        hash = 83 * hash + (this.url != null ? this.url.hashCode() : 0);
        hash = 83 * hash + (this.browser != null ? this.browser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegistroUsuarios other = (RegistroUsuarios) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   
    
}
