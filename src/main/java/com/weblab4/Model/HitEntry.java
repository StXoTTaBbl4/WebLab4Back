package com.weblab4.Model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "uhits")
public class HitEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "login")
    private String login;

    @Column(name = "xvalue")
    private double xvalue;
    @Column(name = "yvalue")
    private double yvalue;
    @Column(name = "rvalue")
    private double rvalue;
    @Column(name = "ishit")
    private boolean hit;

    public HitEntry() {
    }

    public HitEntry(String login, double xvalue, double yvalue, double rvalue, boolean hit) {
        this.login = login;
        this.xvalue = xvalue;
        this.yvalue = yvalue;
        this.rvalue = rvalue;
        this.hit = hit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public double getXvalue() {
        return xvalue;
    }

    public void setXvalue(double xvalue) {
        this.xvalue = xvalue;
    }

    public double getYvalue() {
        return yvalue;
    }

    public void setYvalue(double yvalue) {
        this.yvalue = yvalue;
    }

    public double getRvalue() {
        return rvalue;
    }

    public void setRvalue(double rvalue) {
        this.rvalue = rvalue;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean ishit) {
        this.hit = ishit;
    }

    private boolean checkTriangle() {
        return xvalue <=0 && yvalue <=0 && (Math.abs(yvalue + xvalue) < rvalue);
    }

    private boolean checkRectangle() {
        return xvalue <= 0 && yvalue >= 0 && Math.abs(xvalue) <= rvalue && Math.abs(yvalue) <= rvalue /2;
    }

    private boolean checkCircle() {
        return xvalue >= 0 && yvalue <= 0 && xvalue*xvalue + yvalue*yvalue <= rvalue*rvalue/4.0;
    }

    public void checkHit() {
        hit = checkTriangle() || checkRectangle() || checkCircle();
    }
}
