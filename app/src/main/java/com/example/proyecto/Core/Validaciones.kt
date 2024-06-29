package com.example.proyecto.Core

class Validaciones {

    fun registroUsuario(
        nombres: String,
        correo: String,
        password: String,
        passwordConf: String
    ): String {
        val reg = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d\$@\$!%*?&]{8,15}"
        if(this.tempEmail(correo)){
            return "El Correo Electronico no es valido, intentelo mas tarde."
        }

        if (nombres.isEmpty() || correo.isEmpty() || password.isEmpty() || passwordConf.isEmpty()) {
            return "Por Favor ingrese todo los campos."
        }else{
            val rg = Regex(reg.toString()).matches(password.toString())
            if (rg == false) {
                return "La contraseña Tiene que tener 8 carateres como minimo entre Mayus., Minus. y numeros."
            }else if (!password.contains(passwordConf)) {
                return "Las Contraseñas no coensiden."
            }
        }
        return "Valido"
    }

    fun login(correo : String, password: String): String {
        if (correo.isEmpty()) {
            return "Por Favor ingrese Correo electronico."
        }else if(password.isEmpty()){
            return "Por Favor ingrese una contraseña."
        }else if (this.tempEmail(correo)) {
            return "El Correo Electronico no es valido, intentelo mas tarde."
        }
        return "Valido"
    }

    fun compras(ciudad : String, direccion : String, cantidad : String, total : String): String{
        if (ciudad.isEmpty()) {
            return "Por Favor ingrese una Ciudad."
        }

        if (direccion.isEmpty()) {
            return "Por Favor ingrese una Dirección."
        }

        if (cantidad.isEmpty()) {
            return "Por Favor ingrese una Cantidad."
        }

        if (total.isEmpty()) {
            return "Por Favor ingrese una Cantidad Mayor a 0."
        }

        return "Valido"
    }

    fun tempEmail(correo : String) : Boolean{
        val tempEmail = listOf(
            "10minutemail.com",
            "tempmail.com",
            "guerrillamail.com",
            "mailinator.com",
            "maildrop.cc",
            "yopmail.com",
            "trashmail.com",
            "getnada.com",
            "dispostable.com",
            "fakeinbox.com"
        )

        val tempEmailReg = Regex(tempEmail.joinToString(separator = "|", prefix = ".*@(", postfix = ")"))
        return tempEmailReg.containsMatchIn(correo.toString())
    }
}