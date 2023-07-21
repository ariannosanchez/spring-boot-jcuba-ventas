package edu.pe.idat.app.models.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ventas")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	@Column(nullable = false)
	private String descripcion;
	private String observacion;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechaCreacion;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clientes_id", nullable = false)
	private Cliente cliente;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "venta_id", nullable = false)
	private List<VentaProducto> items;

	@PrePersist
	public void prePersist() {
		fechaCreacion = new Date();
	}
	
	public Venta() {
		items = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<VentaProducto> getItems() {
		return items;
	}

	public void setItems(List<VentaProducto> items) {
		this.items = items;
	}
	
	public void setItem(VentaProducto item) {
		items.add(item);
	}
	
	public String calcularTotal() {
		DecimalFormat decimalFormat = new DecimalFormat(".##");

		Double total = 0.0;
		for (VentaProducto item : items) {
			total += item.calcularSubtotal();
		}
		return decimalFormat.format(total);
	}
	
}
