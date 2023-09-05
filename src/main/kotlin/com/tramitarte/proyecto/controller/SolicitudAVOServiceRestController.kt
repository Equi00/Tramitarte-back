package com.tramitarte.proyecto.controller

import com.tramitarte.proyecto.dominio.SolicitudAVO
import com.tramitarte.proyecto.dominio.Tramite
import com.tramitarte.proyecto.service.SolicitudAVOService
import com.tramitarte.proyecto.service.TramiteService
import com.tramitarte.proyecto.service.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

class SolicitudAVOServiceRestController {

    @RestController
    @CrossOrigin("*")
    @RequestMapping("/api")
    class TramiteRestController {

        @Autowired
        lateinit var tramiteService: TramiteService

        @Autowired
        lateinit var solicitudAVOService: SolicitudAVOService

        @Autowired
        lateinit var usuarioService: UsuarioService

        @GetMapping("/SolicitudAVO/usuario/{idUsuario}")
        fun buscarAVOPorUsuario(@PathVariable idUsuario: Long): ResponseEntity<SolicitudAVO?> {
            try {
                val usuario = usuarioService.buscarPorId(idUsuario)
                return ResponseEntity(usuario?.let { solicitudAVOService.buscarAVOPorUsuario(it) }, HttpStatus.OK)
            } catch (exception: IllegalArgumentException) {
                throw ResponseStatusException(HttpStatus.BAD_REQUEST, exception.message)
            }
        }
    }
}