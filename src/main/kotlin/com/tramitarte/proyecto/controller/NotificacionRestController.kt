package com.tramitarte.proyecto.controller

import com.tramitarte.proyecto.dominio.Alerta
import com.tramitarte.proyecto.dominio.Mensaje
import com.tramitarte.proyecto.dominio.Notificacion
import com.tramitarte.proyecto.dominio.Usuario
import com.tramitarte.proyecto.repository.NotificacionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.Optional

@RestController
@CrossOrigin
@RequestMapping("/api")
class NotificacionRestController {
    @Autowired
    lateinit var notificacionRepository: NotificacionRepository

    @GetMapping("/notificacion")
    fun buscarPorUsuaruioDestino(
        @RequestParam usuario: Optional<Usuario>
    ): List<Notificacion> =
        notificacionRepository.findAllByUsuarioDestino(usuario.get())

    @PostMapping("/notificacion/mensaje")
    fun crearMensaje(
        @RequestParam origen: Optional<Usuario>,
        @RequestParam destino: Optional<Usuario>,
        @RequestParam mensaje: String
    ) {
        var mensajeNuevo = Mensaje(origen.get(), destino.get(), mensaje)
        notificacionRepository.save(mensajeNuevo)
    }

    @PostMapping("/notificacion/alerta")
    fun crearAlerta(
        @RequestParam origen: Optional<Usuario>,
        @RequestParam destino: Optional<Usuario>,
        @RequestParam descripcion: String
    ) {
        var alertaNueva = Alerta(origen.get(), destino.get(), descripcion)
        notificacionRepository.save(alertaNueva)
    }

    @DeleteMapping("/notificacion/mensaje")
    fun borrarMensaje(
        @RequestParam mensaje: Optional<Mensaje>
    ) =
        notificacionRepository.delete(mensaje.get())

    @DeleteMapping("/notificacion/alerta")
    fun borrarAlerta(
        @RequestParam mensaje: Optional<Alerta>
    ) =
        notificacionRepository.delete(mensaje.get())
}
