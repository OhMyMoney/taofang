package com.taofang.webapi.bean;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-08
 */
public class LiangfangBean {
    private Integer p_id;
    private String p_name;
    private Double p_Convenience;
    private Double p_EffectiveDegree;
    private Double p_EffectiveSpeed;
    private Double p_Security;
    private Double p_SaveMoney;
    private Double p_Score;
    private String p_Material;
    private Integer d_id;
    private String d_name;
    private String d_aliasname;
    private String s_name;

    @Override
    public String toString() {
        return "LiangfangBean{" +
                "p_id=" + p_id +
                ", p_name='" + p_name + '\'' +
                ", p_Convenience=" + p_Convenience +
                ", p_EffectiveDegree=" + p_EffectiveDegree +
                ", p_EffectiveSpeed=" + p_EffectiveSpeed +
                ", p_Security=" + p_Security +
                ", p_SaveMoney=" + p_SaveMoney +
                ", p_Score=" + p_Score +
                ", p_Material='" + p_Material + '\'' +
                ", d_id=" + d_id +
                ", d_name='" + d_name + '\'' +
                ", d_aliasname='" + d_aliasname + '\'' +
                ", s_name='" + s_name + '\'' +
                '}';
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public Double getP_Convenience() {
        return p_Convenience;
    }

    public void setP_Convenience(Double p_Convenience) {
        this.p_Convenience = p_Convenience;
    }

    public Double getP_EffectiveDegree() {
        return p_EffectiveDegree;
    }

    public void setP_EffectiveDegree(Double p_EffectiveDegree) {
        this.p_EffectiveDegree = p_EffectiveDegree;
    }

    public Double getP_EffectiveSpeed() {
        return p_EffectiveSpeed;
    }

    public void setP_EffectiveSpeed(Double p_EffectiveSpeed) {
        this.p_EffectiveSpeed = p_EffectiveSpeed;
    }

    public Double getP_Security() {
        return p_Security;
    }

    public void setP_Security(Double p_Security) {
        this.p_Security = p_Security;
    }

    public Double getP_SaveMoney() {
        return p_SaveMoney;
    }

    public void setP_SaveMoney(Double p_SaveMoney) {
        this.p_SaveMoney = p_SaveMoney;
    }

    public Double getP_Score() {
        return p_Score;
    }

    public void setP_Score(Double p_Score) {
        this.p_Score = p_Score;
    }

    public String getP_Material() {
        return p_Material;
    }

    public void setP_Material(String p_Material) {
        this.p_Material = p_Material;
    }

    public Integer getD_id() {
        return d_id;
    }

    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_aliasname() {
        return d_aliasname;
    }

    public void setD_aliasname(String d_aliasname) {
        this.d_aliasname = d_aliasname;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }
}