package com.tramitarte.proyecto.controller

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

    @GetMapping("/notificacion/mensajes")
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
}
