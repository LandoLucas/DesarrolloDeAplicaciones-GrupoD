package ar.edu.unq.desapp.grupoD.model.providers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "provider")
@Entity
@Table(name = "Provider")
public class Provider {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique=true)
	private Integer providerId;
	
	@Column
	private String name;
	
	@Column(unique=true)
	private String tradeName;
	
	@Column
	private String direction;
	
	@Column(unique=true)
	private Integer cuit;
	
	@Column
	private Integer telephone;
	
	
	public Provider(Integer providerId, String name, String dir, String tradeName, int cuit, int telephone){
		this.setProviderId(providerId);
		this.setName(name);
		this.setCuit(cuit);
		this.setDirection(dir);
		this.setTelephon(telephone);
		this.setTradeName(tradeName);

	}
	
	public Provider(Integer providerId) {
		this.providerId = providerId;
	}
	
	public void setProviderId(Integer providerId){
		this.providerId = providerId;
	}
	
	public Integer getProviderId(){
		return providerId;
	}
	
	public void setTelephon(Integer telephone){
		this.telephone = telephone;
	}
	
	public Integer getTelephone(){
		return telephone;
	}
	
	public void setCuit(Integer cuit){
		this.cuit = cuit;
	}
	
	public Integer getCuit(){
		return cuit;
	}
	
	public void setDirection(String dir){
		this.direction = dir;
	}
	
	public String getDirection(){
		return direction;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setTradeName(String tradeName){
		this.tradeName = tradeName;
	}
	
	public String getTradeName(){
		return tradeName;
	}
}