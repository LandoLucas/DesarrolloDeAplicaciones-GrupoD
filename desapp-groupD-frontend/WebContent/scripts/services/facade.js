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

// Empresas
function invokeRegistrarEmpresa(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'registrarEmpresa', handlerOnSuccess, handlerOnError);
}

function invokeBuscarEmpresa(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion', 'buscarEmpresa',
			handlerOnSuccess, handlerOnError);
}

function invokeActualizarEmpresa(cnxHttp, data, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'actualizarEmpresa', handlerOnSuccess, handlerOnError);
}

// Buscar Empresas
function invokeBuscarEmpresas(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'buscarEmpresas', handlerOnSuccess, handlerOnError);
}

function invokeBorrarEmpresa(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'borrarEmpresa', handlerOnSuccess, handlerOnError);
}

function invokeRegistrarSucursal(cnxHttp, data, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'registrarSucursal', handlerOnSuccess, handlerOnError);
}

// Buscar Sucursales
function invokeBuscarSucursales(cnxHttp, filtro, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'buscarSucursales', handlerOnSuccess, handlerOnError);
}

function invokeBorrarSucursal(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'borrarSucursal', handlerOnSuccess, handlerOnError);
}

function invokeBuscarSucursal(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'buscarSucursal', handlerOnSuccess, handlerOnError);
}

function invokeActualizarSucursal(cnxHttp, data, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'actualizarSucursal', handlerOnSuccess, handlerOnError);
}

function invokeRegistrarSector(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'registrarSector', handlerOnSuccess, handlerOnError);
}

// Buscar Sectores
function invokeBuscarSectores(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'buscarSectores', handlerOnSuccess, handlerOnError);
}

function invokeBorrarSector(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion', 'borrarSector',
			handlerOnSuccess, handlerOnError);
}

function invokeDesvincularSector(cnxHttp, filtro, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'desvincularSector', handlerOnSuccess, handlerOnError);
}

function invokeBuscarSector(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion', 'buscarSector',
			handlerOnSuccess, handlerOnError);
}

function invokeActualizarSector(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'actualizarSector', handlerOnSuccess, handlerOnError);
}

function invokeAsociarSector(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'asociarSectores', handlerOnSuccess, handlerOnError);
}

// Buscar Terminales
function invokeBuscarTerminales(cnxHttp, filtro, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'buscarTerminales', handlerOnSuccess, handlerOnError);
}

function invokeBorrarTerminal(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'borrarTerminal', handlerOnSuccess, handlerOnError);
}

function invokeRegistrarTerminal(cnxHttp, data, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'registrarTerminal', handlerOnSuccess, handlerOnError);
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

// Programacion
function invokeObtenerTerminalesSegunFiltro(cnxHttp, filtro, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'programacion',
			'obtenerTerminalesSegunFiltro', handlerOnSuccess, handlerOnError);
}
function invokeBuscarTerminal(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'buscarTerminal', handlerOnSuccess, handlerOnError);
}

function invokeActualizarTerminal(cnxHttp, data, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'actualizarTerminal', handlerOnSuccess, handlerOnError);
}

// Contenidos
function invokeBuscarContenidos(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'media', 'buscarContenidos',
			handlerOnSuccess, handlerOnError);
}

// Niveles de permiso
function invokeRegistrarNivelDePermiso(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'registrarNivelDePermiso', handlerOnSuccess, handlerOnError);
}

function invokeBuscarNivelDePermiso(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'buscarNivelDePermiso', handlerOnSuccess, handlerOnError);
}


function invokeBuscarNivelesDePermiso(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'buscarNivelesDePermiso', handlerOnSuccess, handlerOnError);
}

function invokeActualizarNivelDePermiso(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'configuracion',
			'actualizarNivelDePermiso', handlerOnSuccess, handlerOnError);
}

 
function invokeBuscarAccionesDeNivelDePermiso(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'buscarAccionesDeNivelDePermiso', handlerOnSuccess, handlerOnError);
}

function invokeBuscarAccionesDeAccesoDeNivelDePermiso(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'buscarAccionesDeAccesoDeNivelDePermiso', handlerOnSuccess, handlerOnError);
}

function invokeAsociarAcciones(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'asociarAcciones', handlerOnSuccess, handlerOnError);
}

function invokeDesvincularAccion(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'desvincularAccion', handlerOnSuccess, handlerOnError);
}

function invokeBorrarNivelDePermiso(cnxHttp, filtro, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, filtro, 'configuracion',
			'borrarNivelDePermiso', handlerOnSuccess, handlerOnError);
}


function invokeRegistrarProgramacion(cnxHttp, data, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'programacion',
			'registrarProgramacion', handlerOnSuccess, handlerOnError);
}
function invokeBorrarProgramacion(cnxHttp, data, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'programacion',
			'borrarProgramacion', handlerOnSuccess, handlerOnError);
}



function invokeBorrarContenido(cnxHttp, data, handlerOnSuccess, handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'media', 'borrarContenido',
			handlerOnSuccess, handlerOnError);
}

function invokeBuscarProgramacion(cnxHttp, data, handlerOnSuccess,
		handlerOnError) {
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'programacion',
			'buscarProgramacion', handlerOnSuccess, handlerOnError);
}

function invokeSincronizarTerminales(cnxHttp, data, handlerOnSuccess, handlerOnError)
{
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'programacion',
			'sincronizarTerminales', handlerOnSuccess, handlerOnError);
}

function invokeUpload(cnxHttp, data, handlerOnSuccess, handlerOnError)
{
	var header = defaultHeader();
	invokeRestService(cnxHttp, header, data, 'media',
			'upload', handlerOnSuccess, handlerOnError);
}