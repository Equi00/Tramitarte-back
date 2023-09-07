package com.tramitarte.proyecto.controller

import com.tramitarte.proyecto.dominio.Alerta
import com.tramitarte.proyecto.dominio.Mensaje
import com.tramitarte.proyecto.dominio.Notificacion
import com.tramitarte.proyecto.dominio.Usuario
import com.tramitarte.proyecto.repository.NotificacionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.Optional

@RestController
@CrossOrigin
@RequestMapping("/api")
class NotificacionRestController {
    @Autowired
    lateinit var notificacionRepository: NotificacionRepository

    @GetMapping("/notificacion/mensaje")
    fun buscarNotificaciones(
        @RequestParam usuario: Optional<Usuario>
    ) {
        try {
            notificacionRepository.findAllByUsuarioDestino(usuario.get())
        } catch (exception: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    @PostMapping("/notificacion/mensaje")
    fun crearMensaje(
        @RequestParam origen: Optional<Usuario>,
        @RequestParam destino: Optional<Usuario>,
        @RequestParam mensaje: String
    ) {
        try {
            var mensajeNuevo = Mensaje(origen.get(), destino.get(), mensaje)
            notificacionRepository.save(mensajeNuevo)
        } catch (exception: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    @PostMapping("/notificacion/alerta")
    fun crearAlerta(
        @RequestParam origen: Optional<Usuario>,
        @RequestParam destino: Optional<Usuario>,
        @RequestParam descripcion: String
    ) {
        try {
            var alertaNueva = Alerta(origen.get(), destino.get(), descripcion)
            notificacionRepository.save(alertaNueva)
        } catch (exception: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
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
