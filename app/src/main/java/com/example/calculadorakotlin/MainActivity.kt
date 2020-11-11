package com.example.calculadorakotlin

import android.icu.number.IntegerWidth
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.Display
import android.view.Surface
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    lateinit var display : TextView
    var num1 = 0
    var numPro = 0
    var op : String = ""
    var opPro : String = ""

    var binario: Boolean = false
    var hexadecimal: Boolean = false
    var decimal: Boolean = false

    var rt: Int? = null

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ds: Display? = baseContext.display

        rt = ds?.rotation

        if ((rt) == Surface.ROTATION_0){
            display = findViewById(R.id.textView)
            deshabilitarBotones()
        }

        if ((rt) == Surface.ROTATION_90){

            display = findViewById(R.id.display)

            deshabilatarOperadoresPro()
            deshabilitarTodos()

        }


    }

    fun deshabilatarOperadoresPro(){

        bSumar.isEnabled = false
        bResta.isEnabled = false
        bMult.isEnabled = false
        bDivide.isEnabled = false

    }
    fun habilatarOperadoresPro(){

        bSumar.isEnabled = true
        bResta.isEnabled = true
        bMult.isEnabled = true
        bDivide.isEnabled = true

    }

    fun deshabilitarTodos(){

        b0.isEnabled= false
        b1.isEnabled= false
        b2.isEnabled= false
        b3.isEnabled= false
        b4.isEnabled= false
        b5.isEnabled= false
        b6.isEnabled= false
        b7.isEnabled= false
        b8.isEnabled= false
        b9.isEnabled= false
        bA.isEnabled= false
        bB.isEnabled= false
        bC.isEnabled= false
        bD.isEnabled= false
        bE.isEnabled= false
        bF.isEnabled= false

    }

    fun habilitarBIN(){


        b0.isEnabled= true
        b1.isEnabled= true
        b2.isEnabled= false
        b3.isEnabled= false
        b4.isEnabled= false
        b5.isEnabled= false
        b6.isEnabled= false
        b7.isEnabled= false
        b8.isEnabled= false
        b9.isEnabled= false
        bA.isEnabled= false
        bB.isEnabled= false
        bC.isEnabled= false
        bD.isEnabled= false
        bE.isEnabled= false
        bF.isEnabled= false


    }

    fun habilitarHEX(){

        b0.isEnabled= true
        b1.isEnabled= true
        b2.isEnabled= true
        b3.isEnabled= true
        b4.isEnabled= true
        b5.isEnabled= true
        b6.isEnabled= true
        b7.isEnabled= true
        b8.isEnabled= true
        b9.isEnabled= true
        bA.isEnabled= true
        bB.isEnabled= true
        bC.isEnabled= true
        bD.isEnabled= true
        bE.isEnabled= true
        bF.isEnabled= true

    }

    fun habilitarDEC(){

        b0.isEnabled= true
        b1.isEnabled= true
        b2.isEnabled= true
        b3.isEnabled= true
        b4.isEnabled= true
        b5.isEnabled= true
        b6.isEnabled= true
        b7.isEnabled= true
        b8.isEnabled= true
        b9.isEnabled= true

        bA.isEnabled= false
        bB.isEnabled= false
        bC.isEnabled= false
        bD.isEnabled= false
        bE.isEnabled= false
        bF.isEnabled= false

    }

    fun pulsaBIN(v: View){

        if (display.text.toString().equals("")){

            habilitarBIN()
            binario = true
            hexadecimal = false
            decimal = false

        }else{

            if (decimal){

                display.text = Integer.toBinaryString(Integer.parseInt(display.text.toString()))

            }
            if (hexadecimal){

                val num : Long = display.text.toString().toLong(radix = 16)
                display.text = Integer.toBinaryString(Integer.parseInt(num.toString()))
            }

            habilitarBIN()
            binario = true
            hexadecimal = false
            decimal = false

        }


    }
    fun pulsaHEX(v: View){

        if (display.text.toString().equals("")){

            habilitarHEX()
            binario = false
            hexadecimal = true
            decimal = false

        }else{

            if (decimal){

                display.text = Integer.parseInt(display.text.toString()).toString(16).toUpperCase()

            }

            if (binario){

                var num: Long = Integer.parseInt(display.text.toString()).toLong()
                var num2: Int = convertBinaryToDecimal(num)
                display.text = num2.toString(16).toUpperCase()

            }

            habilitarHEX()
            binario = false
            hexadecimal = true
            decimal = false

        }


    }
    fun pulsaDEC(v: View){

        if (display.text.toString().equals("")){

            habilitarDEC()
            binario = false
            hexadecimal = false
            decimal = true

        }else{


            if (binario){
                var num: Long = Integer.parseInt(display.text.toString()).toLong()
                display.text = convertBinaryToDecimal(num).toString()

            }

            if (hexadecimal){

                display.text = display.text.toString().toLong(radix = 16).toString()

            }

            habilitarDEC()
            binario = false
            hexadecimal = false
            decimal = true


        }


    }

    fun sumarPro(v: View){

        if(!display.text.equals("")){


            if (binario){
                if (opPro.equals("")){
                    numPro = convertBinaryToDecimal(Integer.parseInt(display.text.toString()).toLong())
                }else{
                    var aux =  convertBinaryToDecimal(Integer.parseInt(display.text.toString()).toLong())
                    if (opPro.equals("+")){
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")){
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")){
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")){
                        numPro = numPro / aux
                    }
                    display.text = Integer.toBinaryString(numPro)
                }
            }

            if (hexadecimal){
                if (opPro.equals("")){
                    numPro = Integer.parseInt(display.text.toString().toLong(radix = 16).toString())
                }else{
                    var aux = Integer.parseInt(display.text.toString().toLong(radix = 16).toString())
                    if (opPro.equals("+")){
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")){
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")){
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")){
                        numPro = numPro / aux
                    }

                    display.text = numPro.toString(16).toUpperCase()
                }


            }

            if (decimal){
                if (opPro.equals("")){
                    numPro = Integer.parseInt(display.text.toString())
                }else {
                    var aux = Integer.parseInt(display.text.toString())
                    if (opPro.equals("+")) {
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")) {
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")) {
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")) {
                        numPro = numPro / aux
                    }
                    display.text = numPro.toString()
                }
            }
            Log.d("key", "numPro = " + numPro.toString())

            opPro = "+"
            display.text = ""
        }


    }
    fun restarPro(v: View){

        if(!display.text.equals("")){


            if (binario){
                var num: Long = Integer.parseInt(display.text.toString()).toLong()
                if (opPro.equals("")){
                    numPro = convertBinaryToDecimal(num)
                }else{
                    var aux =  convertBinaryToDecimal(num)
                    if (opPro.equals("+")){
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")){
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")){
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")){
                        numPro = numPro / aux
                    }

                    display.text = Integer.toBinaryString(numPro)
                }
            }

            if (hexadecimal){
                if (opPro.equals("")){
                    numPro = display.text.toString().toLong(radix = 16).toInt()
                }else{
                    var aux = display.text.toString().toLong(radix = 16).toInt()
                    if (opPro.equals("+")){
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")){
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")){
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")){
                        numPro = numPro / aux
                    }

                    display.text = numPro.toString(16).toUpperCase()
                }


            }

            if (decimal){
                if (opPro.equals("")){
                    numPro = Integer.parseInt(display.text.toString())
                }else {
                    var aux = Integer.parseInt(display.text.toString())
                    if (opPro.equals("+")) {
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")) {
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")) {
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")) {
                        numPro = numPro / aux
                    }
                    display.text = numPro.toString()
                }
            }
            Log.d("key", "numPro = " + numPro.toString())

            opPro = "-"
            display.text = ""
        }


    }
    fun multPro(v: View){

        if(!display.text.equals("")){


            if (binario){
                var num: Long = Integer.parseInt(display.text.toString()).toLong()
                if (opPro.equals("")){
                    numPro = convertBinaryToDecimal(num)
                }else{
                    var aux =  convertBinaryToDecimal(num)
                    if (opPro.equals("+")){
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")){
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")){
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")){
                        numPro = numPro / aux
                    }

                    display.text = Integer.toBinaryString(numPro)
                }
            }

            if (hexadecimal){
                if (opPro.equals("")){
                    numPro = display.text.toString().toLong(radix = 16).toInt()
                }else{
                    var aux = display.text.toString().toLong(radix = 16).toInt()
                    if (opPro.equals("+")){
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")){
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")){
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")){
                        numPro = numPro / aux
                    }

                    display.text = numPro.toString(16).toUpperCase()
                }


            }

            if (decimal){
                if (opPro.equals("")){
                    numPro = Integer.parseInt(display.text.toString())
                }else {
                    var aux = Integer.parseInt(display.text.toString())
                    if (opPro.equals("+")) {
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")) {
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")) {
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")) {
                        numPro = numPro / aux
                    }
                    display.text = numPro.toString()
                }
            }
            Log.d("key", "numPro = " + numPro.toString())

            opPro = "x"
            display.text = ""
        }


    }
    fun divPro(v: View){

        if(!display.text.equals("")){


            if (binario){
                var num: Long = Integer.parseInt(display.text.toString()).toLong()
                if (opPro.equals("")){
                    numPro = convertBinaryToDecimal(num)
                }else{
                    var aux =  convertBinaryToDecimal(num)
                    if (opPro.equals("+")){
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")){
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")){
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")){
                        numPro = numPro / aux
                    }

                    display.text = Integer.toBinaryString(numPro)
                }
            }

            if (hexadecimal){
                if (opPro.equals("")){
                    numPro = display.text.toString().toLong(radix = 16).toInt()
                }else{
                    var aux = display.text.toString().toLong(radix = 16).toInt()
                    if (opPro.equals("+")){
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")){
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")){
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")){
                        numPro = numPro / aux
                    }

                    display.text = numPro.toString(16).toUpperCase()
                }


            }

            if (decimal){
                if (opPro.equals("")){
                    numPro = Integer.parseInt(display.text.toString())
                }else {
                    var aux = Integer.parseInt(display.text.toString())
                    if (opPro.equals("+")) {
                        numPro = numPro + aux
                    }
                    if (opPro.equals("-")) {
                        numPro = numPro - aux
                    }
                    if (opPro.equals("x")) {
                        numPro = numPro * aux
                    }
                    if (opPro.equals("/")) {
                        numPro = numPro / aux
                    }
                    display.text = numPro.toString()
                }
            }
            Log.d("key", "numPro = " + numPro.toString())

            opPro = "/"
            display.text = ""
        }


    }
    fun igualPro(v: View){
        if(!display.text.equals("")) {
            if (binario) {
                var num: Long = Integer.parseInt(display.text.toString()).toLong()

                var aux = convertBinaryToDecimal(num)
                if (opPro.equals("+")) {
                    numPro = numPro + aux
                }
                if (opPro.equals("-")) {
                    numPro = numPro - aux
                }
                if (opPro.equals("x")) {
                    numPro = numPro * aux
                }
                if (opPro.equals("/")) {
                    numPro = numPro / aux
                }

                display.text = Integer.toBinaryString(numPro)
            }


            if (hexadecimal) {

                var aux = display.text.toString().toLong(radix = 16).toInt()
                if (opPro.equals("+")) {
                    numPro = numPro + aux
                }
                if (opPro.equals("-")) {
                    numPro = numPro - aux
                }
                if (opPro.equals("x")) {
                    numPro = numPro * aux
                }
                if (opPro.equals("/")) {
                    numPro = numPro / aux
                }

                display.text = numPro.toString(16).toUpperCase()
            }




            if (decimal) {

                var aux = Integer.parseInt(display.text.toString())
                if (opPro.equals("+")) {
                    numPro = numPro + aux
                }
                if (opPro.equals("-")) {
                    numPro = numPro - aux
                }
                if (opPro.equals("x")) {
                    numPro = numPro * aux
                }
                if (opPro.equals("/")) {
                    numPro = numPro / aux
                }
                display.text = numPro.toString()
            }
        }
    }

    fun convertBinaryToDecimal(num: Long): Int {
        var num = num
        var decimalNumber = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt()
            ++i
        }
        return decimalNumber
    }



    fun deshabilitarBotones(){

        buttonMas.isEnabled = false
        buttonMenos.isEnabled = false
        buttonMult.isEnabled = false
        buttonDiv.isEnabled = false


    }
    fun habilitarBotones(){

        buttonMas.isEnabled = true
        buttonMenos.isEnabled = true
        buttonMult.isEnabled = true
        buttonDiv.isEnabled = true
        buttonIgual.isEnabled= true

    }

    fun pulsa(v: View){
        var numeroPulsado = findViewById<Button>(v.id)
        display.text = display.text.toString() + numeroPulsado.text.toString()
        if ((rt) == Surface.ROTATION_0){
            habilitarBotones()
        }
        if ((rt) == Surface.ROTATION_90){

            habilatarOperadoresPro();

        }

    }
    fun delete(v: View){
        display.text = ""
        if ((rt) == Surface.ROTATION_0){
            op = ""
            num1 = 0
            buttonIgual.isEnabled= false
            deshabilitarBotones()
        }
        if ((rt) == Surface.ROTATION_90){
            opPro = ""
            numPro = 0

        }

    }
    fun suma(v: View){
        if (op.equals("")) {
            num1 = Integer.parseInt(display.text.toString())
        }else{
            if (op.equals("+")){
                num1 = num1 + Integer.parseInt(display.text.toString())
            }
            if (op.equals("-")){
                num1 = num1 - Integer.parseInt(display.text.toString())
            }
            if (op.equals("x")){
                num1 = num1 * Integer.parseInt(display.text.toString())
            }
            if (op.equals("/")){
                num1 = num1 / Integer.parseInt(display.text.toString())
            }
        }
        op = "+"
        display.text = ""
        deshabilitarBotones()
    }
    fun resta(v: View){
        if (op.equals("")) {
            num1 = Integer.parseInt(display.text.toString())
        }else{
            if (op.equals("+")){
                num1 = num1 + Integer.parseInt(display.text.toString())
            }
            if (op.equals("-")){
                num1 = num1 - Integer.parseInt(display.text.toString())
            }
            if (op.equals("x")){
                num1 = num1 * Integer.parseInt(display.text.toString())
            }
            if (op.equals("/")){
                num1 = num1 / Integer.parseInt(display.text.toString())
            }
        }
        op = "-"
        display.text = ""
        deshabilitarBotones()
    }
    fun multiplica(v: View){
        if (op.equals("")) {
            num1 = Integer.parseInt(display.text.toString())
        }else{
            if (op.equals("+")){
                num1 = num1 + Integer.parseInt(display.text.toString())
            }
            if (op.equals("-")){
                num1 = num1 - Integer.parseInt(display.text.toString())
            }
            if (op.equals("x")){
                num1 = num1 * Integer.parseInt(display.text.toString())
            }
            if (op.equals("/")){
                num1 = num1 / Integer.parseInt(display.text.toString())
            }
        }
        op = "x"
        display.text = ""
        deshabilitarBotones()
    }
    fun divide(v: View){
        if (op.equals("")) {
            num1 = Integer.parseInt(display.text.toString())
        }else{
            if (op.equals("+")){
                num1 = num1 + Integer.parseInt(display.text.toString())
            }
            if (op.equals("-")){
                num1 = num1 - Integer.parseInt(display.text.toString())
            }
            if (op.equals("x")){
                num1 = num1 * Integer.parseInt(display.text.toString())
            }
            if (op.equals("/")){
                num1 = num1 / Integer.parseInt(display.text.toString())
            }
        }
        op = "/"
        display.text = ""
        deshabilitarBotones()

    }

    fun igual(v: View){
        if (!display.text.equals("")) {
            if (num1 != 0) {
                var sol: Int = 0
                if (op == "+") {
                    sol = num1 + Integer.parseInt(display.text.toString())
                }
                if (op == "-") {
                    sol = num1 - Integer.parseInt(display.text.toString())
                }
                if (op == "x") {
                    sol = num1 * Integer.parseInt(display.text.toString())
                }
                if (op == "/") {
                    sol = num1 / Integer.parseInt(display.text.toString())
                }

                display.text = sol.toString()
                op = ""
                num1 = 0
                habilitarBotones()
            }
        }
    }



}