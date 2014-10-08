function invokeRestService(cnxHttp, header, data, modulo, servicio,
		handlerOnSuccess, handlerOnError) {
	var respuesta = [];

	cnxHttp(
			{
				method : 'POST',
				url : 'http://' + restHost + '/' + restContext + '/rest/'
						+ modulo + '/' + servicio,
				data : data,
				headers : header,
				transformRequest : function(obj) {
					var str = [];
					for ( var p in obj)
						str.push(encodeURIComponent(p) + "="
								+ encodeURIComponent(obj[p]));
					return str.join("&");
				}
			}).success(function(response) {
		handlerOnSuccess(response);

	}).error(function(response) {
		console.log(response)
		handlerOnError(response);
	});
}


function invokeGetRestService(cnxHttp, header, data, modulo, servicio,
		handlerOnSuccess, handlerOnError) {
	var respuesta = [];

	cnxHttp(
			{
				method : 'GET',
				url : 'http://' + restHost + '/' + restContext + '/rest/'
						+ modulo + '/' + servicio,
				data : data,
				headers : header,
				transformRequest : function(obj) {
					var str = [];
					for ( var p in obj)
						str.push(encodeURIComponent(p) + "="
								+ encodeURIComponent(obj[p]));
					return str.join("&");
				}
			}).success(function(response) {
		handlerOnSuccess(response);

	}).error(function(response) {
		console.log(response)
		handlerOnError(response);
	});
}

function defaultHandlerOnError(response) {
	console.log(response);
}

function defaultHeader() {
	return {
		'Content-Type' : 'application/x-www-form-urlencoded; charset=utf-8'
	};
}

function jsonHeader() {
	return {
		'Content-Type' : 'application/json'
	};
}

function autenticar(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'seguridad', 'login',
			handlerOnSuccess, handlerOnError);

}


function invokeNewOperationTest(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'operation',
			'newTest', handlerOnSuccess, handlerOnError);
}


function invokeNewCategory(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'category',
			'add', handlerOnSuccess, handlerOnError);
}



// Usuarios
function invokeRegistrarUsuario(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'registrarUsuario', handlerOnSuccess, handlerOnError);
}

function invokeBuscarUsuario(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion', 'buscarUsuario',
			handlerOnSuccess, handlerOnError);
}

function invokeActualizarUsuario(cnxHttp, data, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'actualizarUsuario', handlerOnSuccess, handlerOnError);
}

// Buscar Usuarios
function invokeBuscarUsuarios(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'buscarUsuarios', handlerOnSuccess, handlerOnError);
}

function invokeBorrarUsuario(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'borrarUsuario', handlerOnSuccess, handlerOnError);
}



