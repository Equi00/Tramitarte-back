package com.tramitarte.proyecto.dominio

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Usuario(username: String, nombre: String, apellido: String, rol: Rol, precio: Float, correoElectronico: String, fechaDeNacimiento: LocalDate, fotoPerfil: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(length=150)
    var username: String = username
    @Column(length=150)
    var nombre: String = nombre
    @Column(length=150)
    var apellido: String = apellido
    @Column
    var rol: Rol = rol
    @Column
    var precio: Float = precio
    @Column(length=150)
    var correoElectronico: String = correoElectronico
    @Column
    var fechaDeNacimiento: LocalDate = fechaDeNacimiento
    @Column
    var nesecitaTraduccion: Boolean = false
    @Column(length=255)
    var fotoPerfil: String = fotoPerfil

    fun updateUser(update: UpdateUserDTO){
          username = update.username
          apellido = update.apellido
          nombre = update.nombre
    }
}