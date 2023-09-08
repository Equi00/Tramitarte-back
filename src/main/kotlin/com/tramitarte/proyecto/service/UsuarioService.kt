package com.tramitarte.proyecto.service

import com.tramitarte.proyecto.dominio.*
import com.tramitarte.proyecto.repository.NotificacionRepository
import com.tramitarte.proyecto.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
class UsuarioService {
    @Autowired
    lateinit var usuarioRepository: UsuarioRepository
    @Autowired
    lateinit var notificacionRepository: NotificacionRepository

    fun buscarTraductores(): List<Usuario>{
        return usuarioRepository.findByRol(Rol.TRADUCTOR)
    }


    fun buscarPorRol(rol: Rol): List<Usuario> {
        return usuarioRepository.findAll()
    }

    fun buscarPorId(id: Long): Usuario? {
        return usuarioRepository.findById(id).getOrNull()
    }

    fun crear(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario)
    }

    fun buscarPorCorreoElectronico(correoElectonico: String): Usuario {
        validarFormatoCorreoElectronico(correoElectonico)
        try {
            return usuarioRepository.findByCorreoElectronico(correoElectonico)
        } catch (exception: EmptyResultDataAccessException) {
            throw IllegalArgumentException("No existe un usuario registrado con ese correo electrónico.", exception)
        }
    }

    fun buscarTraductorPorCorreo(correoElectronico: String): List<Usuario> {
        return usuarioRepository.findByRolAndCorreoElectronicoContaining(Rol.TRADUCTOR, correoElectronico)
    }


    fun buscarPorNombreYPrecio(nombre: Optional<String>, apellido: Optional<String>, precio: Optional<Float>): Usuario {
        return usuarioRepository.findByNombreAndAndApellidoAndPrecio(nombre, apellido, precio)
    }

    fun buscarNotificaciones(idUsuarioDestino: Long): List<NotificacionDTO> {
        try {
            val usuarioDestino = usuarioRepository.findById(idUsuarioDestino).get()

            val notificaciones = notificacionRepository.findAllByUsuarioDestino(usuarioDestino)

            return notificaciones.map { it -> NotificacionDTO(it.id, it.usuarioOrigen.id, it.usuarioDestino.id, it.descripcion) }
        }catch (e: Exception){
            throw IllegalArgumentException("No se pueden obtener notificaciones de este usuario",e)
        }

    }

    fun actualizar(id: Long?, update: UpdateUserDTO): Usuario {
        if (!usuarioRepository.existsById(id!!)) throw IllegalArgumentException("No existe un usuario con ese id")

        val usuario = usuarioRepository.findById(id).get()
        usuario.updateUser(update)

        return usuarioRepository.save(usuario)
    }

    private fun validarFormatoCorreoElectronico(correoElectonico: String) {
        if (!correoElectonico.matches(Regex("^[0-9A-Za-z.-]+@[A-Za-z]+.[a-zA-Z]+"))) throw IllegalArgumentException("El formato del correo no es válido. Debe cumplir la forma nombrecorreo@dominio.extensionDeDominio.")
    }
}