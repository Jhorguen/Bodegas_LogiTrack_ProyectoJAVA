const API_URL = 'http://localhost:8080';

function getToken() {
    return localStorage.getItem('token');
}

function getUsuario() {
    return localStorage.getItem('usuario');
}

function getRol() {
    return localStorage.getItem('rol');
}

function logout() {
    localStorage.clear();
    window.location.href = 'index.html';
}

function verificarAuth() {
    if (!getToken()) {
        window.location.href = 'index.html';
    }
}

async function fetchApi(endpoint, options = {}) {
    const config = {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + getToken()
        },
        ...options
    };

    const response = await fetch(API_URL + endpoint, config);

    if (response.status === 401) {
        logout();
        return;
    }

    return response;
}

// ===== Auth =====
async function login(username, password) {
    const response = await fetch(API_URL + '/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
    });
    return response;
}

async function register(username, password, nombreCompleto) {
    const response = await fetch(API_URL + '/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password, nombreCompleto })
    });
    return response;
}

// ===== Bodegas =====
async function obtenerBodegas() {
    const res = await fetchApi('/bodegas');
    return res.json();
}

async function obtenerBodega(id) {
    const res = await fetchApi('/bodegas/' + id);
    return res.json();
}

async function crearBodega(data) {
    return fetchApi('/bodegas', { method: 'POST', body: JSON.stringify(data) });
}

async function actualizarBodega(id, data) {
    return fetchApi('/bodegas/' + id, { method: 'PUT', body: JSON.stringify(data) });
}

async function eliminarBodega(id) {
    return fetchApi('/bodegas/' + id, { method: 'DELETE' });
}

// ===== Productos =====
async function obtenerProductos() {
    const res = await fetchApi('/productos');
    return res.json();
}

async function obtenerProducto(id) {
    const res = await fetchApi('/productos/' + id);
    return res.json();
}

async function crearProducto(data) {
    return fetchApi('/productos', { method: 'POST', body: JSON.stringify(data) });
}

async function actualizarProducto(id, data) {
    return fetchApi('/productos/' + id, { method: 'PUT', body: JSON.stringify(data) });
}

async function eliminarProducto(id) {
    return fetchApi('/productos/' + id, { method: 'DELETE' });
}

async function obtenerStockBajo() {
    const res = await fetchApi('/productos/stock-bajo');
    return res.json();
}

// ===== Movimientos =====
async function obtenerMovimientos() {
    const res = await fetchApi('/movimientos');
    return res.json();
}

async function crearMovimiento(data) {
    return fetchApi('/movimientos', { method: 'POST', body: JSON.stringify(data) });
}

async function obtenerMovimientosPorFecha(fechaInicio, fechaFin) {
    const res = await fetchApi('/movimientos/por-fecha?fechaInicio=' + fechaInicio + '&fechaFin=' + fechaFin);
    return res.json();
}

// ===== Auditorias =====
async function obtenerAuditorias() {
    const res = await fetchApi('/auditorias');
    return res.json();
}

async function obtenerAuditoriasPorUsuario(usuario) {
    const res = await fetchApi('/auditorias/por-usuario?usuario=' + usuario);
    return res.json();
}

async function obtenerAuditoriasPorTipo(tipo) {
    const res = await fetchApi('/auditorias/por-tipo?tipo=' + tipo);
    return res.json();
}
