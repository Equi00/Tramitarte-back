package com.tramitarte.proyecto.dominio

class ServicioNotificaciones {

    fun generarAlerta(origen: Usuario, destino: Usuario, descripcion: String) =
        Alerta(origen, destino, descripcion)
}
