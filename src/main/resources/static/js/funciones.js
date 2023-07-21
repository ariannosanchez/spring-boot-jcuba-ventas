

function eliminar(id) {
	console.log(id);
	Swal.fire({
		title: 'Esta seguro de eliminar?',
		text: "¡No podrás revertir esto!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		cancelButtonText: 'Cancelar',
		confirmButtonText: '¡Sí, bórralo!'
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "/cliente/eliminar/" + id,
				success: function(res) {
					console.log(res);
				},
			});
			Swal.fire(
				'¡Eliminado!',
				'El cliente fue eliminado.',
				'success'
			).then((result) => {
				if (result) {
					location.href = "/cliente/listar";
				}
			});
		}
	})
}

function eliminarVenta(id) {
	console.log(id);
	Swal.fire({
		title: 'Esta seguro de eliminar?',
		text: "¡No podrás revertir esto!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		cancelButtonText: 'Cancelar',
		confirmButtonText: '¡Sí, bórralo!'
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "/venta/eliminar/" + id,
				success: function(res) {
					console.log(res);
				},
			});
			Swal.fire(
				'¡Eliminado!',
				'Venta Eliminada.',
				'success'
			).then((result) => {
				if (result) {
					location.href = "/cliente/listar";
				}
			});
		}
	})
}

function eliminarEmpleado(id) {
	console.log(id);
	Swal.fire({
		title: 'Esta seguro de eliminar?',
		text: "¡No podrás revertir esto!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		cancelButtonText: 'Cancelar',
		confirmButtonText: '¡Sí, bórralo!'
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "/empleado/eliminar/" + id,
				success: function(res) {
					console.log(res);
				},
			});
			Swal.fire(
				'¡Eliminado!',
				'El empleado fue eliminado.',
				'success'
			).then((result) => {
				if (result) {
					location.href = "/empleado/listar";
				}
			});
		}
	})
}

function eliminarProducto(id) {
	console.log(id);
	Swal.fire({
		title: 'Esta seguro de eliminar?',
		text: "¡No podrás revertir esto!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		cancelButtonText: 'Cancelar',
		confirmButtonText: '¡Sí, bórralo!'
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "/producto/eliminar/" + id,
				success: function(res) {
					console.log(res);
				},
			});
			Swal.fire(
				'¡Eliminado!',
				'El producto fue eliminado.',
				'success'
			).then((result) => {
				if (result) {
					location.href = "/producto/listar";
				}
			});
		}
	})
}

function limpiarCampos(){
	window.location='/cliente/listar';
}

function limpiarCamposP(){
	window.location='/producto/listar';
}

function limpiarCamposE(){
	window.location='/empleado/listar';
}


