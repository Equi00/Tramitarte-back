package com.tramitarte.proyecto.controller

import com.tramitarte.proyecto.dominio.Alerta
import com.tramitarte.proyecto.dominio.Notificacion
import com.tramitarte.proyecto.dominio.Usuario
import com.tramitarte.proyecto.repository.NotificacionRepository
import com.tramitarte.proyecto.repository.UsuarioRepository
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

    @Autowired
    lateinit var usuarioRepository: UsuarioRepository

    @GetMapping("/notificacion")
    fun buscarNotificaciones(
        @RequestParam usuario: Optional<Usuario>
    ) {
        try {
            notificacionRepository.findAllByUsuarioDestino(usuario.get())
        } catch (exception: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    @PostMapping("/notificacion/alerta/{idOrigen}/{idDestino}")
    fun crearAlerta(
        @PathVariable idOrigen: Long,
        @PathVariable idDestino: Long,
        @RequestBody descripcion: String
    ) {
        try {
            val usuarioOrigen = usuarioRepository.findById(idOrigen).get()
            val usuarioDestino = usuarioRepository.findById(idDestino).get()
            var alertaNueva = Alerta(usuarioOrigen, usuarioDestino, descripcion)
            notificacionRepository.save(alertaNueva)
        } catch (exception: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
        }
    }

    @DeleteMapping("/notificacion/alerta/{id}")
    fun borrarAlerta(
        @PathVariable id: Long
    ) =
        notificacionRepository.deleteById(id)
}
