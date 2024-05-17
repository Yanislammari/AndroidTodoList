package com.example.testsetup

import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.Assert.*

class MainActivityTest{
    @Test
    fun `Check Valid Password`(){
        // Setup
        val password = "Pass0rd123!"
        // Test
        val errors = verifyPassword(password)
        // Resultat / Check / Assertion
        assertTrue(errors.isEmpty())
    }

    @Test
    fun `Check Length Password`(){
        val password = "Pass0rd123!"
        val errors = verifyPassword(password)
        assertTrue(!errors.contains("Le mot de passe doit contenir au moins 6 caractères."))
    }

    @Test
    fun `Check Uppercase Password`(){
        val password = "Pass0rd123!"
        val errors = verifyPassword(password)
        assertTrue(!errors.contains("Le mot de passe doit contenir au moins une lettre majuscule."))
    }

    @Test
    fun `Check Lowercase Password`(){
        val password = "AAZZZaZ"
        val errors = verifyPassword(password)
        assertTrue(!errors.contains("Le mot de passe doit contenir au moins une lettre minuscule."))
    }

    @Test
    fun `Check Number Password`(){
        val password = "Pass0rd123"
        val errors = verifyPassword(password)
        assertTrue(!errors.contains("Le mot de passe doit contenir au moins un chiffre."))
    }

    @Test
    fun `Check Specials Char Password`(){
        val password = "Pass0rd123"
        val caracteresSpeciaux = "~`!@#\\$%\\^&*\\(\\)-_+=<>?/\\[]\\{}|"
        val errors = verifyPassword(password)
        assertTrue(!errors.contains("Le mot de passe doit contenir au moins un caractère spécial parmi $caracteresSpeciaux."))
    }

}