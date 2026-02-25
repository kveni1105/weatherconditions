package com.kce.wether.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datetime_utc", unique = true)
    private LocalDateTime datetimeUtc;

    private String conds;

    private Double dewptm;
    private Integer fog;
    private Integer hail;
    private Double heatindexm;

    private Double hum;
    private Double precipm;
    private Double pressurem;

    private Integer rain;
    private Integer snow;

    private Double tempm;

    private Integer thunder;
    private Integer tornado;

    private Double vism;
    private Double wdird;
    private String wdire;

    private Double wgustm;
    private Double windchillm;
    private Double wspdm;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getDatetimeUtc() {
		return datetimeUtc;
	}
	public void setDatetimeUtc(LocalDateTime datetimeUtc) {
		this.datetimeUtc = datetimeUtc;
	}
	public String getConds() {
		return conds;
	}
	public void setConds(String conds) {
		this.conds = conds;
	}
	public Double getDewptm() {
		return dewptm;
	}
	public void setDewptm(Double dewptm) {
		this.dewptm = dewptm;
	}
	public Integer getFog() {
		return fog;
	}
	public void setFog(Integer fog) {
		this.fog = fog;
	}
	public Integer getHail() {
		return hail;
	}
	public void setHail(Integer hail) {
		this.hail = hail;
	}
	public Double getHeatindexm() {
		return heatindexm;
	}
	public void setHeatindexm(Double heatindexm) {
		this.heatindexm = heatindexm;
	}
	public Double getHum() {
		return hum;
	}
	public void setHum(Double hum) {
		this.hum = hum;
	}
	public Double getPrecipm() {
		return precipm;
	}
	public void setPrecipm(Double precipm) {
		this.precipm = precipm;
	}
	public Double getPressurem() {
		return pressurem;
	}
	public void setPressurem(Double pressurem) {
		this.pressurem = pressurem;
	}
	public Integer getRain() {
		return rain;
	}
	public void setRain(Integer rain) {
		this.rain = rain;
	}
	public Integer getSnow() {
		return snow;
	}
	public void setSnow(Integer snow) {
		this.snow = snow;
	}
	public Double getTempm() {
		return tempm;
	}
	public void setTempm(Double tempm) {
		this.tempm = tempm;
	}
	public Integer getThunder() {
		return thunder;
	}
	public void setThunder(Integer thunder) {
		this.thunder = thunder;
	}
	public Integer getTornado() {
		return tornado;
	}
	public void setTornado(Integer tornado) {
		this.tornado = tornado;
	}
	public Double getVism() {
		return vism;
	}
	public void setVism(Double vism) {
		this.vism = vism;
	}
	public Double getWdird() {
		return wdird;
	}
	public void setWdird(Double wdird) {
		this.wdird = wdird;
	}
	public String getWdire() {
		return wdire;
	}
	public void setWdire(String wdire) {
		this.wdire = wdire;
	}
	public Double getWgustm() {
		return wgustm;
	}
	public void setWgustm(Double wgustm) {
		this.wgustm = wgustm;
	}
	public Double getWindchillm() {
		return windchillm;
	}
	public void setWindchillm(Double windchillm) {
		this.windchillm = windchillm;
	}
	public Double getWspdm() {
		return wspdm;
	}
	public void setWspdm(Double wspdm) {
		this.wspdm = wspdm;
	}
   
    
}