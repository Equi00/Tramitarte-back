package com.tramitarte.proyecto.service

import com.tramitarte.proyecto.dominio.SolicitudDescarga
import com.tramitarte.proyecto.repository.SolicitudDescargaRepository
import com.tramitarte.proyecto.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SolicitudDescargaService {
    @Autowired
    lateinit var solicitudDescargaRepository: SolicitudDescargaRepository
    @Autowired
    lateinit var usuarioRepository: UsuarioRepository

    @Transactional
    fun crearSolicitudDescarga(idSolicitante: Long, idTraductor: Long) {
        val solicitante = usuarioRepository.findById(idSolicitante).get()
        val traductor = usuarioRepository.findById(idTraductor).get()
        val solicitudDescarga = SolicitudDescarga(solicitante, traductor)
        solicitudDescargaRepository.save(solicitudDescarga)
    }

    @Transactional
    fun buscarSolicitudDescargaPorSolicitante(idSolicitante: Long): List<SolicitudDescarga?>{
        val solicitante = usuarioRepository.findById(idSolicitante).get()
        return solicitudDescargaRepository.findBySolicitante(solicitante)
    }
}