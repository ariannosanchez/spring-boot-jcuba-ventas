$(document).ready(() => {
	$("#producto").autocomplete({
		source: (request, response) => {
			$.ajax({
				url: "/venta/cargar-productos/" + request.term,
				dataType: "json",
				data: {
					term: request.term
				},
				success: (data) => {
					response($.map(data, (item) => {
						return {
							value: item.id,
							label: item.nombre,
							precio: item.precio,
						};
					}));
				},
			});
		},
		select: (event, ui) => {
			if(existeProducto(ui.item.value)){
				incrementarCantidad(ui.item.value, ui.item.precio);
				return false;
			}
			
			let fila = $("#productosPlantilla").html();

			fila = fila.replace(/{ID}/g, ui.item.value);
			fila = fila.replace(/{NOMBRE}/g, ui.item.label);
			fila = fila.replace(/{PRECIO}/g, ui.item.precio);

			$("#productosSeleccionados tbody").append(fila);

			calcularSubtotal(ui.item.value, ui.item.precio, 1);
			
			return false;
		}
	});
	$("form").submit(function(){
		$("#productosPlantilla").remove();
		return;
	});
});

function calcularSubtotal(id, precio, cantidad) {
	$("#subtotal_" + id).html("S/ " + redondear(parseFloat(precio) * parseInt(cantidad)));
	cacularTotal();
}

function existeProducto(id){
	let existe = false;
	$("input[name='producto_id[]']").each(function(){
		if(id == $(this).val()){
			existe = true;
		}
	});
	return existe;
}

function incrementarCantidad(id, precio){
	let cantidad = $("#cantidad_" + id).val();
	$("#cantidad_" + id).val(++cantidad);
	calcularSubtotal(id, precio, cantidad);
}

function eliminarProducto(id){
	$("#fila_" + id).remove();
	cacularTotal();
}

function cacularTotal(){
	let total = 0.0;
	$("td[id^='subtotal_']").each(function(){
		total += parseFloat($(this).html().slice(3));	
	});
	$("#total").html("S/ " + redondear(total));
}

function redondear(numero) {
	return +(Math.round(numero + "e+2") + "e-2");
}