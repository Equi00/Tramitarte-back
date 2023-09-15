package com.tramitarte.proyecto.dominio

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class SolicitudAVO(
    nombre: String,
    apellido: String,
    fechaNacimiento: LocalDate,
    sexo: Sexo
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(length=50)
    var nombre: String = nombre
    @Column(length=50)
    var apellido: String = apellido
    @JsonFormat(pattern = "dd/MM/yyyy")
    var fechaNacimiento: LocalDate = fechaNacimiento
    @Column
    var sexo: Sexo = sexo

    fun validar(): Boolean = nombre.isNotBlank() && apellido.isNotBlank() && fechaNacimiento.isBefore(LocalDate.now())
}